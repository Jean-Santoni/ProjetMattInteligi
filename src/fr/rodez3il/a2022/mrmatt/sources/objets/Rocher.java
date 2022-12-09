package fr.rodez3il.a2022.mrmatt.sources.objets;

import fr.rodez3il.a2022.mrmatt.sources.Niveau;

public  class Rocher extends ObjetPlateau {
    private EtatRocher etat;

    public Rocher(){
        this.etat= EtatRocher.FIXE;
    }

    public EtatRocher getEtat() {
        return etat;
    }

    public void setEtat(EtatRocher etat) {
        this.etat = etat;
    }

    /**
     * retourne si l'objet est poussable.
     * @author Jean Santoni
     */
    @Override
    public boolean estPoussable(){
        return true ;
    }
    /**
     * retourne si l'objet est glissant .
     * @author Jean Santoni
     */
    @Override
    public boolean estGlissant(){
        return true ;
    }
    /**
     * Affiche l'objet.
     * @author Jean Santoni
     */
    public  char afficher(){
        return '*';
    }
    @Override
    public void visiterPlateauCalculEtatSuivant(Niveau plateau, int x, int y) {
        plateau.etatSuivantVisiteur(this, x, y);
    }
}