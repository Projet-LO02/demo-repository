import java.util.*;

public class Secteur {

	private int id;
	private List<Hex> arrayHex; 
	private int nbHexEntré;
	private Hex hex;

	 public Secteur(int id) {
        this.id = id;
        this.arrayHex = new ArrayList<>();
    }
	
	//Guetteurs
	public int getId() {
		return id;
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

	public Hex getHex(){
		return this.hex;
	}

	//avec cette technique les hexs doivnt être entré dans l'ordre
	public void ajouterHex(int niveau) {
		Hex hex = new Hex(niveau, nbHexEntré);
		arrayHex.add(hex);
		nbHexEntré++;
	}
}
