package fr.rodez3il.a2022.mrmatt.sources;

import fr.rodez3il.a2022.mrmatt.sources.objets.ObjetPlateau;
import fr.rodez3il.a2022.mrmatt.sources.objets.Rocher;

public class Niveau {
	
	// Les objets sur le plateau du niveau
	private ObjetPlateau[][] plateau;
	// Position du joueur
	private int joueurX;
	private int joueurY;
	
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
		chargerNiveau(chemin);
	}

	private void chargerNiveau(String chemin) {
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
    // TODO
	}

  // TODO : patron visiteur du Rocher...
	public void etatSuivantVisiteur(Rocher r, int x, int y) {
    
	}

	/**
	 * Calcule l'état suivant du niveau.
	 * ........
	 * @author 
	 */
	public void etatSuivant() {
    // TODO
	}


  // Illustrez les Javadocs manquantes lorsque vous coderez ces méthodes !
  
	public boolean enCours() {}

  // Joue la commande C passée en paramètres
	public boolean jouer(Commande c) {
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
