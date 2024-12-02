import java.util.Iterator;
import java.util.List;

public class Hex {
	private int niveau;
	List<Vaisseau> vaisseau;
	private int numeroSystem;
	private boolean Occupe = false;
	
	public Hex(int niveau, int numeroSystem) {
		this.niveau = niveau;
		this.numeroSystem = numeroSystem;
	}
	
	//Guetteurs
	public int getNiveau() {
		return niveau;
	}
	
	public List<Vaisseau> getVaisseau() {
        return this.vaisseau;
    }

    public int getNumeroSystem() {
        return this.numeroSystem;
    }

    public boolean isOccupe() {
        return this.Occupe;
    }
	
	public void rendreOccupe() {
		this.Occupe = true;
	}
	
	public void rendreLibre() {
		this.Occupe = false;
	}
	
	public void ajouterVaisseau(Vaisseau v){
		vaisseau.add(v);
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

	public void enleverTousVaisseau(){
		Iterator<Vaisseau> iterator = this.vaisseau.iterator();
		
		while(iterator.hasNext()) {
			iterator.remove();
		}
	}
}
