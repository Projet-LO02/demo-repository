public class Vaisseau {

	private Joueur proprietaire;
	private Hex position;
	private boolean etat;
	private int numero;
	
	public Vaisseau(Joueur proprietaire, Hex position, boolean etat, int numero) {
		this.proprietaire = proprietaire;
		this.position = position;
		this.etat = etat;
		this.numero = numero;
	}
	
	public Joueur getProprietaire() {
        return this.proprietaire;
    }
	
	public Hex getPosition() {
        return this.position;
    }

    public boolean isEtat() {
        return this.etat;
    }

    public int getNumero() {
        return this.numero;
    }
	
	public void modifierPositionVaisseau(Hex positionFinale) {
		position = positionFinale;
	}
	
	public void desactiver(){
		etat = false;
	}
	
	public void activer() {
		etat = true;
	}
	
}
