package Cartes;

public class CarteSecteur extends Carte {
	private Secteur secteur;
	public CarteSecteur(Secteur secteur) {
		super(typeDeCarte.SECTEUR);
		this.secteur=secteur;
	}
	public String toString() {
		return "Type de carte : "+this.getType()+"\nSecteur :\n\t id du secteur : "+this.secteur.getId();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
