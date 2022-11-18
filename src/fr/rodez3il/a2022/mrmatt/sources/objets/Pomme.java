package fr.rodez3il.a2022.mrmatt.sources.objets;

import fr.rodez3il.a2022.mrmatt.sources.Niveau;

public  class Pomme extends ObjetPlateau {

    public Pomme(){

    }
    @Override
    public boolean estMarchable(){
        return true ;
    }
    public  char afficher(){
        return '+';
    }
}