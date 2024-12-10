package Cartes;

import java.util.ArrayList;
import java.util.Iterator;

public class Contexte {
	/*Cette classe permet de stocker les donnees du joueur concerne necessaires pour selectionner la strategie. En fonction du contexte, 
	le joueur choisit la strategie*/
	private Strategie strategie;
	private Hex plusGrosHex;// c'est l'hex ennemi qui a le plus grand niveau
	ArrayList<Hex> HexsAvecAvantageNumerique;
	
	public void setStrategie(Strategie strategie) {
		this.strategie=strategie;
	}
	public void setHexsAvecAvantageNumerique(JoueurVirtuel joueur) {
		ArrayList<Hex> hexsAvecAvantage = new ArrayList<Hex>();
		ArrayList<Hex> hexsControles = joueur.getHexControle();
		Iterator<Hex> I1=hexsControles.iterator();
		while(I1.hasNext()) {
			Hex hexControle=I1.next();
			Iterator<Hex> I2 = hexControle.getHexAutour().iterator();
			
			while(I2.hasNext()) {
				Hex hexAutour = I2.next();
				if(hexControle.getVaisseau().isEmpty()!=true) {
					if(hexAutour.getVaisseau().isEmpty()!=true) {
						if(hexControle.getVaisseau().size()>hexAutour.getVaisseau().size()) {
							if(hexControle.getVaisseau().get(0).getProprietaire().equals(joueur)==false) {
							hexsAvecAvantage.add(hexAutour);
							}
						}
					}
				}
			}
			
		}
		this.HexsAvecAvantageNumerique=hexsAvecAvantage;
	}
	public ArrayList<Hex> getHexsAvecAvantageNumerique(){
		return this.HexsAvecAvantageNumerique;
	}
	public void setPlusGrosHex(JoueurVirtuel joueur) {
		ArrayList<Hex> hexsControles=joueur.getHexControle();
		Iterator<Hex> I1=hexsControles.iterator();
		while(I1.hasNext()) {
			Hex hexControle = I1.next();
			Iterator<Hex> I2 = hexControle.getHexAutour().iterator();
			
			Hex hexAutour = I2.next();
			Hex hexPlusGros = hexAutour;
			while(I2.hasNext()) {
				hexAutour = I2.next();
				if(hexAutour.getNiveau()>hexPlusGros.getNiveau()) {
					hexPlusGros=hexAutour;
				}
				
			}
			this.plusGrosHex=hexPlusGros;
			
		}
	
	}
	public Strategie getStrategie() {
		return this.strategie;
	}
	public void jouer(JoueurVirtuel joueur) {
		this.strategie.jouer(joueur);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
