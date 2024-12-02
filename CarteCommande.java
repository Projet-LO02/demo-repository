package Cartes;

public class CarteCommande extends Carte{
	
	private commandeCarte commande;
	private int efficacite;
	public CarteCommande(commandeCarte commande,int efficacite) {
		super(typeDeCarte.COMMANDE);
		
		this.commande=commande;
		this.efficacite=efficacite;
		
	}
	public void setEfficacite(int nouvelleEfficacite) {
		this.efficacite=nouvelleEfficacite;
	}
	public String toString() {
		return "Type :"+this.getType()+"\nCommande : "+this.commande+"\nEfficacite : "+this.efficacite;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
