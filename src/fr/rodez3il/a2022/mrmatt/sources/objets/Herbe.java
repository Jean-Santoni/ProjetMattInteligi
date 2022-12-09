package fr.rodez3il.a2022.mrmatt.sources.objets;

import fr.rodez3il.a2022.mrmatt.sources.Niveau;

public  class Herbe extends ObjetPlateau {

    public Herbe(){

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
    public char afficher(){
        return '-';
    }
}
