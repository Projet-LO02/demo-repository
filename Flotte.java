import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Flotte {

	private List<Vaisseau> vaisseau;
	
	public Flotte() {
		this.vaisseau = new ArrayList<>();
	}

	public List<Vaisseau> getVaisseau(){
		return vaisseau;
	}
	
	public void ajouterVaisseau(Vaisseau nouveauxVaisseau) {
		this.vaisseau.add(nouveauxVaisseau);
	}

	public void desactiverTout(){
		Iterator<Vaisseau> iterator = this.vaisseau.iterator();
		while(iterator.hasNext()) {
			iterator.next().desactiver();
		}
	}
	
	public void deplacer(Hex positionFinal) {
		for (Vaisseau v : vaisseau) {
			v.modifierPositionVaisseau(positionFinal);
		}
	}
	
}
