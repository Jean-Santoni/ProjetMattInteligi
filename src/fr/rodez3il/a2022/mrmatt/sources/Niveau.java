package fr.rodez3il.a2022.mrmatt.sources;

import fr.rodez3il.a2022.mrmatt.sources.objets.EtatRocher;
import fr.rodez3il.a2022.mrmatt.sources.objets.ObjetPlateau;
import fr.rodez3il.a2022.mrmatt.sources.objets.Rocher;

public class Niveau {
	
	// Les objets sur le plateau du niveau
	private ObjetPlateau[][] plateau;
	// Position du joueur
	private int joueurX;
	private int joueurY;
	private int pommesRestantes;
	private int nombreDeplacements;
	private boolean enCours;

	
  // Autres attributs que vous jugerez nécessaires...
  
	/**
	 * Constructeur public : crée un niveau depuis un fichier.
	 * @param chemin .....
	 * @author .............
	 */
	public Niveau(String chemin) {
		this.joueurX=0;
		this.joueurY=0;
		this.pommesRestantes = 0;
		this.nombreDeplacements=0;
		chargerNiveau(chemin);
	}

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
	 * Javadoc à réaliser...
	 */
	private void echanger(int sourceX, int sourceY, int destinationX, int destinationY) {

		ObjetPlateau plateauTampon = this.plateau[destinationX][destinationY];
		this.plateau[destinationX][destinationY] = this.plateau[sourceX][sourceY];
		this.plateau[sourceX][sourceY] = plateauTampon;

	}

	/**
	 * Produit une sortie du niveau sur la sortie standard.
	 * ................
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
	}

  // TODO : patron visiteur du Rocher...
	public void etatSuivantVisiteur(Rocher r, int x, int y) {
		switch (r.getEtat()){
			case FIXE:
				if(this.plateau[x][y+1].estVide()){
					r.setEtat(EtatRocher.CHUTE);
				}
			break;
			case CHUTE:
				if(x==this.plateau.length-1){
					this.echanger(x,y,x,y+1);
				}
				if (x == this.joueurX && y + 1 == this.joueurY) {
					//this.perdu = true;
				}
				if(this.plateau[x][y+1].estVide()){
					this.echanger(x,y,x,y+1);
				}
				if (this.plateau[x][y+1].estGlissant()){
					if(this.plateau[x-1][y].estVide() && this.plateau[x-1][y+1].estVide()){
						this.echanger(x,y,x-1,y+1);
					}else if (x < this.plateau.length - 1 && this.plateau[x + 1][y].estVide() && this.plateau[x + 1][y + 1].estVide()) {
						this.echanger(x, y, x + 1, y + 1);
					} else {
						r.setEtat(EtatRocher.FIXE);
					}
				 }else{
					r.setEtat(EtatRocher.FIXE);
				}
		}
	}

	/**
	 * Calcule l'état suivant du niveau.
	 * ........
	 * @author 
	 */
	public void etatSuivant() {
		for (int x = plateau.length - 1; x >= 0; x--) {
			for (int y = plateau[x].length - 1; y >= 0; y--) {
				plateau[x][y].visiterPlateauCalculEtatSuivant(this, x, y);
			}
		}

	}
    // TODO



  // Illustrez les Javadocs manquantes lorsque vous coderez ces méthodes !
  
	public boolean enCours() {
		return this.enCours;
	}

  // Joue la commande C passée en paramètres
	public boolean jouer(Commande c) {
		switch (c) {
			case HAUT:

				deplacer(-1, 0);
				break;
			case GAUCHE:
				deplacer(0, -1);
				break;
			case BAS:
				deplacer(1, 0);
				break;
			case DROITE:
				deplacer(0, 1);
				break;
			case ANNULER:
				break;
			case QUITTER:
				break;
			case ERREUR:
				break;
		}
		return true;
	}


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
			this.echanger(this.joueurX, joueurY, deltaX, deltaY);
			this.joueurX = deltaX;
			this.joueurY = deltaY;
			this.nombreDeplacements++;
		}else{
			System.out.println("deplacment impossible");
		}
	}



	/**
	 * Affiche l'état final (gagné ou perdu) une fois le jeu terminé.
	 */
	public void afficherEtatFinal() {
	}

	/**
	 */
	//public boolean estIntermediaire() {}

  // Code pour empêcher la compilation

  //MANGEZ DES POMMES

}
