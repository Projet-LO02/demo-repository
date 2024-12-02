package Cartes;
import java.util.ArrayList;
import java.util.Iterator;

public class PaquetDeCartes {
	/*Cette classe représente les paquets de carte*/
	/*Pour stocker les cartes, on va utiliser des listes*/
	private ArrayList<Carte> Cartes;
	public PaquetDeCartes() {
		this.Cartes=new ArrayList();
	}
	public void ajouterCarte(Carte carte) {
		this.Cartes.add(carte);
	}
	public ArrayList getPaquet() {
		return this.Cartes;
	}
	public void afficher() {
		Iterator<Carte> I= this.Cartes.iterator();
		while(I.hasNext()) {
			System.out.println(I.next());
		}
	}
	public void melanger() {
		Iterator<Carte> I=this.Cartes.iterator();
		int indiceActuel =0;
		while(I.hasNext()) {
			int indiceAleatoire = (int) Math.round((Math.random()*this.Cartes.size()-1));
			if(indiceAleatoire<0) {
				indiceAleatoire=0;
			}
			Carte carteADeplacer = this.Cartes.get(indiceAleatoire);
			this.Cartes.set(indiceAleatoire, I.next());
			this.Cartes.set(indiceActuel, carteADeplacer);
			indiceActuel+=1;
		}
	}
	public static void main(String[] args) {
		PaquetDeCartes P=new PaquetDeCartes();
		CarteCommande c1=new CarteCommande(commandeCarte.EXPAND, 1);
		CarteCommande c2=new CarteCommande(commandeCarte.EXTERMINATE, 2);
		CarteCommande c3=new CarteCommande(commandeCarte.EXPLORE, 3);
		P.ajouterCarte(c1);
		P.ajouterCarte(c2);
		P.ajouterCarte(c3);
		
		P.melanger();
		P.afficher();
		
		
		
	}
}
