import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Hex {
	private int niveau;
	List<Vaisseau> vaisseau;
	private boolean Occupe = false;
	private int hexId;
	List<Hex> hexAutour;
	static Hex hex0Mort = new Hex(0, 0);
	
	public Hex(int niveau, int hexId) {
		this.niveau = niveau;
		this.hexId = hexId;
		this.hexAutour = new ArrayList<>();
		this.vaisseau = new ArrayList<>();
	}
	
	//Guetteurs
	public int getNiveau() {
		return niveau;
	}
	
	public List<Vaisseau> getVaisseau() {
        return vaisseau;
    }

    public boolean isOccupe() {
        return this.Occupe;
    }

	public int getHexId(){
		return this.hexId;
	}

	public List<Hex> getHexAutour(){
		return hexAutour;
	}
	
	public void rendreOccupe() {
		this.Occupe = true;
	}
	
	public void rendreLibre() {
		this.Occupe = false;
	}

	public void etatAutoHex(){
		this.Occupe = !this.vaisseau.isEmpty();
	}
	
	public void ajouterVaisseau(Vaisseau v){
		if(!vaisseau.contains(v)){
			vaisseau.add(v);
			v.setHex(this);		
		}

		etatAutoHex();

	}

	public void ajouterFlotte(Flotte f){
		Iterator<Vaisseau> iterator = f.getVaisseau().iterator();
            while(iterator.hasNext() ) {
                Vaisseau v = iterator.next();
					ajouterVaisseau(v);
					v.setHex(this);
            }
		etatAutoHex();
	}

	public void enleverVaisseau(Vaisseau VaisseauSupprime) throws MauvaiseEntreeException{
		Iterator<Vaisseau> iterator = this.vaisseau.iterator();
		boolean vaisseeauTrouve = false;
		
		while(iterator.hasNext()) {
			Vaisseau v = iterator.next();
			if(v == VaisseauSupprime) {
				v.setHex(hex0Mort);
				iterator.remove();
				vaisseeauTrouve = true;
				break;
			}
		}

		if (!vaisseeauTrouve)
			throw new MauvaiseEntreeException("Le vaisseau avec le numéro " + VaisseauSupprime + " n'existe pas.");
		
		etatAutoHex();
	}

	public void enleverVaisseauEtDesactiver(int nbAEnlever){
		for (int i=0;i<nbAEnlever;i++){
			vaisseau.get(0).desactiver();
			vaisseau.get(0).setHex(hex0Mort);
			vaisseau.remove(0);
		}
		etatAutoHex();
	}	

	public void enleverTousVaisseauEtDesaciver(){
		Iterator<Vaisseau> iterator = this.vaisseau.iterator();
		
		while(iterator.hasNext()) {
			Vaisseau v = iterator.next();
			v.desactiver();
			v.setHex(hex0Mort);
			iterator.remove();
		}
		etatAutoHex();
	}

	public void enleverTousVaisseau(){
		Iterator<Vaisseau> iterator = this.vaisseau.iterator();
		
		while(iterator.hasNext()) {
			iterator.remove();
		}
		etatAutoHex();
	}

	public void ajouterHexAutour(Hex hex){
		hexAutour.add(hex);
	}

	public String toString() {
		return "Hex id=" + hexId + ", niveau=" + niveau + (vaisseau.isEmpty() ? "" : ", vaisseau=" + vaisseau);
	}
}
