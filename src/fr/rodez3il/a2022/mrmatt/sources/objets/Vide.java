package fr.rodez3il.a2022.mrmatt.sources.objets;

import fr.rodez3il.a2022.mrmatt.sources.Niveau;

public  class Vide extends ObjetPlateau {

    public Vide(){
       /*super.vide= true;
        super.glissant= false;
        super.poussable= false;
        super.marchable= true;*/
    }
    @Override
    public boolean estVide(){
        return true ;
    }
    @Override
    public boolean estMarchable(){
        return true ;
    }
    public  char afficher(){
        return ' ';
    }
}