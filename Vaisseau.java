public class Vaisseau {

	private Joueur proprietaire;
	private Hex hex = Hex.hex0Mort; //l'hex qui sert de position ne doit pas être modulé à partir du vaisseau mais de l'hex
	private boolean etat = false;
	private int numero;
	
	public Vaisseau(Joueur proprietaire, int numero) {
		this.proprietaire = proprietaire;
		proprietaire.ajouterVaisseauInactif(this);
		this.numero = numero;
	}
	
	public Joueur getProprietaire() {
        return this.proprietaire;
    }
	
	public Hex getHex() {
        return this.hex;
    }

    public boolean isEtat() {
        return this.etat;
    }

    public int getNumero() {
        return this.numero;
    }

	public void setHex(Hex hex){
		this.hex = hex;
	}
	
	public void modifierPositionVaisseau(Hex hexFinale) {
		this.hex = hexFinale;
		hexFinale.vaisseau.add(this);
	}
	
	public void desactiver(){
		this.hex = Hex.hex0Mort;
		etat = false;
		proprietaire.enleverVaisseauActif(this);
		proprietaire.ajouterVaisseauInactif(this);
	}
	
	public void activer() {
		etat = true;
		proprietaire.enleverVaisseauInactif(this);
		proprietaire.ajouterVaisseauActif(this);
	}
		
	public String toString() {
		String str = new String();
		if (hex != Hex.hex0Mort) {
			str = "\nVaisseau id=" + numero + ", proprietaire=" + proprietaire.getNom() + ", position=" + hex.getHexId() + ", etat=" + etat ;
		} else {
				str = "\nVaisseau id=" + numero + ", proprietaire=" + proprietaire.getNom() + ", position=null, etat=" + etat ;
		}
		return str;
	}
}
