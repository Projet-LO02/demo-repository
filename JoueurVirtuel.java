package Cartes;
import java.util.List;

public class JoueurVirtuel  extends Joueur{
    private Contexte contexte;

    public JoueurVirtuel(String nom, List<Secteur> ordreSecteur){
        super(nom, ordreSecteur);
        this.contexte = new Contexte();
        
    }
    public Contexte getContexte() {
    	return this.contexte;
    }

}

/*
pour la fonction joueur
peut expand
a tjrs des vaisseaux en reserve
hex est vide */