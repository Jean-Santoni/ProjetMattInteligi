package fr.rodez3il.a2022.mrmatt.sources.objets;

import fr.rodez3il.a2022.mrmatt.sources.Niveau;

public abstract class ObjetPlateau {
	/**
	 * Fabrique des objets
	 * @param chr le symbole à produire
	 * @return la classe ObjetPlateau correspondante
	 */
	public static ObjetPlateau depuisCaractere(char chr) {
		ObjetPlateau nouveau = null;
		switch(chr) {
		case '-':
			nouveau = new Herbe();
			break;
		case '+':
			nouveau = new Pomme();
			break;
		case '*':
			nouveau = new Rocher();
			break;
		case ' ':
			nouveau = new Vide();
			break;
		case '#':
			nouveau = new Mur();
			break;
		case 'H':
			nouveau = new Joueur();
			break;
		}
		return nouveau;
	}
	/**
	 * Affiche l'objet.
	 * @author Jean Santoni
	 */
	public abstract char afficher();
	/**
	 * retourne si l'objet est vide
	 * @author Jean Santoni
	 */
	public boolean estVide(){
		return false ;
	}
	/**
	 * retourne si l'objet est marchable
	 * @author Jean Santoni
	 */
	public boolean estMarchable(){
		return false ;
	}
	/**
	 * retourne si l'objet est poussable .
	 * @author Jean Santoni
	 */
	public boolean estPoussable(){
		return false ;
	}
	/**
	 * retourne si l'objet est glissant .
	 * @author Jean Santoni
	 */
	public boolean estGlissant(){
		return false ;
	}
	/**
	 * calcul l'état suivant de l'objet.
	 * @author Jean Santoni
	 */
	public void visiterPlateauCalculEtatSuivant(Niveau niveau, int x, int y){

	}
	/// Autres fonctions à réaliser ici...
}
