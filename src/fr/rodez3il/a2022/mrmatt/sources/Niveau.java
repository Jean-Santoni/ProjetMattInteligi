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

	
  // Autres attributs que vous jugerez nécessaires...
  
	/**
	 * Constructeur public : crée un niveau depuis un fichier.
	 * @param chemin .....
	 * @author .............
	 */
	public Niveau(String chemin) {
		this.plateau = new ObjetPlateau[0][0];
		this.joueurX=0;
		this.joueurY=0;
		this.pommesRestantes = 0;
		this.nombreDeplacements=0;
		chargerNiveau(chemin);
	}

	private void chargerNiveau(String chemin) {
		String fichier = Utils.lireFichier(chemin);
		String[] ligne = fichier.split("\n");
		int nbColonne = Integer.valueOf(ligne[0]);
		int nbLigne = Integer.valueOf(ligne[1]);
		this.plateau = new ObjetPlateau[nbColonne][nbLigne];
		int positionJoueurX = 0;
		int positionJoueurY = 0;

		for(int var1 = 2; var1 < ligne.length; ++var1) {
			for(int var2 = 0; var2 < ligne[var1].length(); ++var2) {
				char var3 = ligne[var1].charAt(var2);
				ObjetPlateau var11 = null;
				if ("*-+# H".indexOf(var3) >= 0) {
					var11 = ObjetPlateau.depuisCaractere(var3);
				}

				if (var3 == 'H') {
					this.joueurX = positionJoueurX;
					this.joueurY = positionJoueurY;
				}

				if (var3 == '+') {
					++this.pommesRestantes;
				}

				this.plateau[positionJoueurX++][positionJoueurY] = var11;
			}

			positionJoueurX = 0;
			++positionJoueurY;
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
		for(int var1 = 0; var1 < this.plateau[0].length; ++var1) {
			for(int var2 = 0; var2 < this.plateau.length; ++var2) {
				System.out.print(this.plateau[var2][var1].afficher());
			}

			System.out.println();
		}

		System.out.println("Pommes restantes : " + this.pommesRestantes);
		System.out.println("Déplacements : " + this.nombreDeplacements);
	}

  // TODO : patron visiteur du Rocher...
	public void etatSuivantVisiteur(Rocher r, int x, int y) {
		if(r.getEtat()== EtatRocher.FIXE){
			if(this.plateau[x][y+1].estVide()){
				r.setEtat(EtatRocher.CHUTE);
			}
		}
		if(r.getEtat()== EtatRocher.CHUTE){
			if(x==this.plateau.length){
				r.setEtat(EtatRocher.FIXE);
			}
			if (this.plateau[x][y+1].estGlissant()){}
			//if (this.plateau[x][y+1].){}
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
  
	public boolean enCours() {}

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
		if ((dx >=0 || dy>=0) && (PositionfX>=0 && PositionfY <=0)&& PositionfX < plateau.length && PositionfX< plateau[0].length) {
			if (this.plateau[PositionfX][PositionfY].estMarchable()) {
				result = false;
			} else {
				result = true;
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
	public boolean estIntermediaire() {}

  // Code pour empêcher la compilation

  //MANGEZ DES POMMES

}
