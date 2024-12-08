import java.util.*;

public class Secteur {

	private int id;
	private List<Hex> arrayHex; 
	private int nbHexEntre;
	private Hex hex;

	public Secteur(int id) {
        this.id = id;
        this.arrayHex = new ArrayList<>();
    }
	
	//Guetteurs
	public int getId() {
		return id;
	}

	public List<Hex> getArrayHex(){
		return arrayHex;
	}

	public int getNbHexEntre(){
		return nbHexEntre;
	}

	public Hex getHex(){
		return this.hex;
	}


	public void trouverHex(int hexId) throws MauvaiseEntreeException{
		Iterator<Hex> iterator = this.arrayHex.iterator();
		boolean hexTrouve = false;
		while(iterator.hasNext()) {
			Hex h = iterator.next();
			if(h.getHexId()==hexId){
				this.hex = h;
				hexTrouve = true;
				break;
			}
		}
		if (!hexTrouve)
			throw new MauvaiseEntreeException("L'hex avec le id " + hexId + " n'existe pas.");
	}

	
	//avec cette technique les hexs doivnt être entré dans l'ordre
	public void ajouterHex(int niveau) {
		Hex hex = new Hex(niveau, nbHexEntre);
		arrayHex.add(hex);
		nbHexEntre++;
	}

	public int calculerScoreParJoueur(Joueur joueur) {
		int score = 0;
		List<Vaisseau> vaisseauxActifs = joueur.getVaisseauActif();

		for (Hex h : arrayHex) {
			for (Vaisseau v : h.getVaisseau()) {
				if (vaisseauxActifs.contains(v)) {
					score += h.getVaisseau().size();
					break;
				}
			}
		}
		return score;
	}
}
