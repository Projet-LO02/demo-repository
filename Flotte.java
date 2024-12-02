import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Flotte {

	private List<Vaisseau> vaisseau;
	
	public Flotte() {
		this.vaisseau = new ArrayList<>();
	}
	
	public void ajouterVaisseau(Vaisseau nouveauxVaisseau) {
		this.vaisseau.add(nouveauxVaisseau);
	}
	// pas sûr dutout  je pense que l'on doit l'enlever
	public void enleverVaisseau(int numeroVaisseauSupprime) throws MauvaiseEntreeException{
		Iterator<Vaisseau> iterator = this.vaisseau.iterator();
		boolean vaisseeauTrouve = false;
		
		while(iterator.hasNext()) {
			Vaisseau v = iterator.next();
			if(v.getNumero() == (numeroVaisseauSupprime)) {
				iterator.remove();
				vaisseeauTrouve = true;
				break;
			}
		}
		
		if (!vaisseeauTrouve)
			throw new MauvaiseEntreeException("Le vaisseau avec le numéro " + numeroVaisseauSupprime + " n'existe pas.");
	}
	
	public void deplacer(Hex positionFinal) {
		for (Vaisseau v : vaisseau) {
			v.modifierPositionVaisseau(positionFinal);
		}
	}
	
}
