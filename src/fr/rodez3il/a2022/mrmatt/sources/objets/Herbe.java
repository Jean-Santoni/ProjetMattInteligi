package fr.rodez3il.a2022.mrmatt.sources.objets;

import fr.rodez3il.a2022.mrmatt.sources.Niveau;

public  class Herbe extends ObjetPlateau {

    public Herbe(){
       /* super.vide= false;
        super.glissant= false;
        super.poussable= false;
        super.marchable= true;*/
    }

    @Override
    public boolean estMarchable(){
        return true ;
    }

    public char afficher(){
        return '-';
    }
}
