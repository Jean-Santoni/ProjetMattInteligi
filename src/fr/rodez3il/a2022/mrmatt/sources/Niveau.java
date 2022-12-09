package fr.rodez3il.a2022.mrmatt.sources;

import fr.rodez3il.a2022.mrmatt.sources.objets.EtatRocher;
import fr.rodez3il.a2022.mrmatt.sources.objets.ObjetPlateau;
import fr.rodez3il.a2022.mrmatt.sources.objets.Rocher;
import fr.rodez3il.a2022.mrmatt.sources.objets.Vide;

public class Niveau {

	// Les objets sur le plateau du niveau
	private ObjetPlateau[][] plateau;
	private ObjetPlateau[][] plateauprecedent;
	// Position du joueur
	private int joueurX;
	private int joueurY;

	private int joueurXPrécédent;
	private int joueurYPrécédent;

	private int pommesRestantes;
	private int nombreDeplacements;
	private boolean enCours;
	private boolean gagner;
	private boolean perdue;
	private boolean estIntermediaire;


	// Autres attributs que vous jugerez nécessaires...

	/**
	 * Constructeur public : crée un niveau depuis un fichier.
	 * @param chemin chemin vers le niveau
	 * @author Jean Santoni
	 */
	public Niveau(String chemin) {

		this.pommesRestantes = 0;
		this.nombreDeplacements=0;
		chargerNiveau(chemin);
	}


	/**
	 * Permet de charger un niveau au début de la partie , configure le plateau de jeu
	 * @param chemin chemin vers le niveau
	 * @author Jean Santoni
	 */

	private void chargerNiveau(String chemin) {
		String fichier = Utils.lireFichier(chemin);
		String[] ligne = fichier.split("\n");
		int nbColonne = Integer.valueOf(ligne[0].trim());
		int nbLigne = Integer.valueOf(ligne[1].trim());
		this.plateau = new ObjetPlateau[nbColonne][nbLigne];
		this.enCours=true;

		for(int i = 0; i < nbColonne; ++i) {
			for(int j = 0; j < nbLigne; ++j) {
				char character = ligne[j+2].charAt(i);
				ObjetPlateau objetTampon = null;
				if ("*-+# H".indexOf(character) >= 0) {
					objetTampon = ObjetPlateau.depuisCaractere(character);
				}
				if (character == 'H') {
					this.joueurX = i;
					this.joueurY = j;
				}
				if (character == '+') {
					this.pommesRestantes++;
				}
				this.plateau[i][j] = objetTampon;
			}
		}
	}



	/**
	 * echange deux éléments du plateau
	 * @param sourceX coordonée présente en abscisse
	 * @param sourceY coordonée présente en ordonnée
	 * @param destinationX coordonée future en abscisse
	 * @param destinationY coordonée future en ordonnée
	 * @author Jean Santoni
	 */
	private void echanger(int sourceX, int sourceY, int destinationX, int destinationY) {

		ObjetPlateau plateauTampon = this.plateau[destinationX][destinationY];
		this.plateau[destinationX][destinationY] = this.plateau[sourceX][sourceY];
		this.plateau[sourceX][sourceY] = plateauTampon;

	}

	/**
	 * Produit une sortie du niveau sur la sortie standard.
	 * @author Jean Santoni
	 */
	public void afficher() {
		for(int x = 0; x < this.plateau[0].length; ++x) {
			for(int y = 0; y < this.plateau.length; ++y) {
				System.out.print(this.plateau[y][x].afficher());
			}

			System.out.println();
		}

		System.out.println("Pommes restantes : " + this.pommesRestantes);
		System.out.println("Déplacements : " + this.nombreDeplacements);
		System.out.println("Position joueur : " + this.joueurX+" "+this.joueurY);
	}

	/**
	 * calcul l'état suivant d'un rocher
	 * @param r rocher en cours de calcul
	 * @param x coordonée présente en ordonnée
	 * @param y coordonée présente en abscisse
	 * @author Jean Santoni
	 */
	public void etatSuivantVisiteur(Rocher r, int x, int y) {
		switch (r.getEtat()){
			case FIXE:
				if( y+1 <this.plateau[x].length && this.plateau[x][y+1].estVide()){
					r.setEtat(EtatRocher.CHUTE);
					this.estIntermediaire =true;
				}

				break;
			case CHUTE:

				if (x == this.joueurX && y + 1 == this.joueurY) {
					this.perdue = true;
					r.setEtat(EtatRocher.FIXE);
					this.enCours = false;
					this.estIntermediaire=false;
				}
				if(this.plateau[x][y+1].estVide()){
					if(x==this.plateau.length-1){
						r.setEtat(EtatRocher.FIXE);
						//this.estIntermediaire= false;
					}else {
						this.echanger(x,y,x,y+1);
					}
				}
				if (this.plateau[x][y+1].estGlissant()){
					if(this.plateau[x-1][y].estVide() && this.plateau[x-1][y+1].estVide()){
						this.echanger(x,y,x-1,y+1);
					}else if (x < this.plateau.length - 1 && this.plateau[x + 1][y].estVide() && this.plateau[x + 1][y + 1].estVide()) {
						this.echanger(x, y, x + 1, y + 1);
					} else {
						r.setEtat(EtatRocher.FIXE);
						//	this.estIntermediaire=false;
					}
				}else{
					r.setEtat(EtatRocher.FIXE);
					//this.estIntermediaire=false;
				}
		}
		if (r.getEtat()==EtatRocher.CHUTE){
			this.estIntermediaire = true;
		}
	}
	/**
	 * calcul l'état suivant d'une pomme
	 * @param x coordonée présente en ordonnée
	 * @param y coordonée présente en abscisse
	 * @author Jean Santoni
	 */
	public void etatSuivantVisiteurPomme( int x, int y) {
		this.pommesRestantes++;
	}

	/**
	 * calcul l'état suivant du niveau et verification du nombre de pomme pour gagner la partie
	 * @author Jean Santoni
	 */
	public void etatSuivant() {
		this.pommesRestantes=0;
		this.estIntermediaire=false;
		for (int x = plateau.length - 1; x >= 0; x--) {
			for (int y = plateau[x].length - 1; y >= 0; y--) {
				plateau[x][y].visiterPlateauCalculEtatSuivant(this, x, y);
			}
		}
		if (this.pommesRestantes==0){
			this.enCours=false;
			this.estIntermediaire=false;
			this.gagner=true;
		}

	}
	// TODO



	// Illustrez les Javadocs manquantes lorsque vous coderez ces méthodes !
	/**
	 * retourne si la partie est en cours
	 * @author Jean Santoni
	 */
	public boolean enCours() {
		return this.enCours;
	}
	/**
	 * en fonction de la commande renseignée fais déplacer le joueur dans la direction voulu ou annule le déplacement précédent ou quitte le jeu
	 * @param c commande renseignée par le joueur

	 * @author Jean Santoni
	 */
	// Joue la commande C passée en paramètres
	public boolean jouer(Commande c) {
		switch (c) {
			case HAUT:

				deplacer(0, -1);
				break;
			case GAUCHE:
				deplacer(-1, 0);
				break;
			case BAS:
				deplacer(0, 1);
				break;
			case DROITE:
				deplacer(1, 0);
				break;
			case ANNULER:
			/*	this.plateau = plateauprecedent;
				this.joueurX = this.joueurXPrécédent;
				this.joueurY = this.joueurYPrécédent;*/
				break;
			case QUITTER:
				this.estIntermediaire=false;
				this.perdue=true;
				this.enCours = false;
				break;
			case ERREUR:
				break;
		}
		return true;
	}

	/**
	 * vérifie si le déplacement voulu est possible
	 * @param dx rocher en cours de calcul
	 * @param dy coordonée présente en ordonnée
	 * @author Jean Santoni
	 */
	private boolean deplacementPossible(int dx, int dy){
		boolean result;
		int PositionfX = this.joueurX+dx;
		int PositionfY = this.joueurY+dy;
		if ((dx !=0 || dy!=0) && (PositionfX>=0 && PositionfY >=0)&& PositionfX <= plateau.length-1 && PositionfY<= plateau[0].length-1) {
			if (this.plateau[PositionfX][PositionfY].estMarchable()) {
				result = true;
			} else {
				result = false;
			}
		}else{
			result = false;
		}
		return result ;
	}


	public void deplacer(int deltaX, int deltaY){
		if (this.deplacementPossible(deltaX,deltaY)) {
			this.plateauprecedent = this.plateau;
			this.joueurXPrécédent = this.joueurX;
			this.joueurYPrécédent = this.joueurY;
			this.echanger(this.joueurX, this.joueurY, this.joueurX+deltaX,  this.joueurY+deltaY);
			this.plateau[this.joueurX][this.joueurY]=new Vide();
			this.joueurX =  this.joueurX+deltaX;
			this.joueurY = this.joueurY+deltaY;
			this.nombreDeplacements++;
		}else{
			System.out.println("déplacement impossible");
		}
	}



	/**
	 * Affiche l'état final (gagné ou perdu) une fois le jeu terminé.
	 */
	public void afficherEtatFinal() {

		if (gagner){
			System.out.println("la partie est gagné :!!");
		}else if (perdue)
		{
			System.out.println("la partie est perdue :!!");
		}
	}

	/**
	 */
	public boolean estIntermediaire() {
		boolean result = false;
		if(this.enCours && this.estIntermediaire){
			result =  true;
		}

		return result;
	}

	// Code pour empêcher la compilation

	//MANGEZ DES POMMES

}
