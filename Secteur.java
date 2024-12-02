import java.util.HashMap;
import java.util.Map;

public class Secteur {

	private int id;
	private Map<String, Hex> hex; 

	 public Secteur(int id) {
        this.id = id;
        this.hex = new HashMap<>();
    }
	
	//Guetteurs
	public int getId() {
		return id;
	}

	public Hex getHex(String hexId) {
        return hex.get(hexId);
    }

	public void ajouterHex(int niveau, int numeroSystem, int position) {
		Hex hex = new Hex(niveau, numeroSystem);
		this.hex.put(hex.getNumeroSystem(), hex);
	}
}
