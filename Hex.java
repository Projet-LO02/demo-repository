package Cartes;
import java.util.Iterator;
import java.util.List;

public class Hex {
	private int niveau;
	List<Vaisseau> vaisseau;
	private boolean Occupe = false;
	private int hexId;
	private List<Hex> hexAutour;
	
	public Hex(int niveau, int hexId) {
		this.niveau = niveau;
		this.hexId = hexId;
	}
	
	//Guetteurs
	public int getNiveau() {
		return niveau;
	}
	
	public List<Vaisseau> getVaisseau() {
        return this.vaisseau;
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
	
	public void ajouterVaisseau(Vaisseau v){
		vaisseau.add(v);
		v.setHex(this);
	}

	public void ajouterFlotte(Flotte f){
		Iterator<Vaisseau> iterator = f.getVaisseau().iterator();
            while(iterator.hasNext() ) {
                Vaisseau v = iterator.next();
					ajouterVaisseau(v);
                }
	}

	public void enleverVaisseau(Vaisseau VaisseauSupprime) throws MauvaiseEntreeException{
		Iterator<Vaisseau> iterator = this.vaisseau.iterator();
		boolean vaisseeauTrouve = false;
		
		while(iterator.hasNext()) {
			Vaisseau v = iterator.next();
			if(v == VaisseauSupprime) {
				iterator.remove();
				vaisseeauTrouve = true;
				break;
			}
		}

		if (!vaisseeauTrouve)
			throw new MauvaiseEntreeException("Le vaisseau avec le numéro " + VaisseauSupprime + " n'existe pas.");
	}

	public void enleverVaisseauEtDesactiver(int nbAEnlever){
		for (int i=0;i<nbAEnlever;i++){
			vaisseau.get(0).desactiver();
			vaisseau.remove(0);
		}
	}

	public void enleverTousVaisseauEtDesaciver(){
		Iterator<Vaisseau> iterator = this.vaisseau.iterator();
		
		while(iterator.hasNext()) {
			iterator.next().desactiver();
			iterator.remove();
		}
	}

	public void enleverTousVaisseau(){
		Iterator<Vaisseau> iterator = this.vaisseau.iterator();
		
		while(iterator.hasNext()) {
			iterator.remove();
		}
	}

	public void ajouterHexAutour(Hex hex){
		hexAutour.add(hex);
	}
}
