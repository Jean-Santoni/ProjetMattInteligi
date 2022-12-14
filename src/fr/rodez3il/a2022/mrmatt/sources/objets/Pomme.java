package fr.rodez3il.a2022.mrmatt.sources.objets;

import fr.rodez3il.a2022.mrmatt.sources.Niveau;

public  class Pomme extends ObjetPlateau {

    public Pomme(){

    }
    /**
     * retourne si l'objet est marchable
     * @author Jean Santoni
     */
    @Override
    public boolean estMarchable(){
        return true ;
    }
    /**
     * Affiche l'objet.
     * @author Jean Santoni
     */
    public  char afficher(){
        return '+';
    }
    @Override
    public void visiterPlateauCalculEtatSuivant(Niveau plateau, int x, int y) {
        plateau.etatSuivantVisiteurPomme(x, y);
    }
}