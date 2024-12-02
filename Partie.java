import java.util.*;

public class Partie {

	List<Joueur> joueur;
	List<PaquetDeCartes> ordreCarteSecteur;
	private int tour;
	private int nombreDeJoueursReels;
	private PaquetDeCartes cartesCommandes;
	private boolean finJeu = false;
	
	public Partie(PaquetDeCartes ordreCarteSecteur,int tour, int nombreDeJoueursReels, PaquetDeCartes cartesCommandes) {
		this.tour = tour;
		this.nombreDeJoueursReels = nombreDeJoueursReels;
		this.cartesCommandes = cartesCommandes;
	}

	public void enregistrer() {
    }

    public List<Joueur> getJoueurs() {
        return joueur;
    }

    public void miseEnPlace() 

		//Creation des secteurs
		Secteur secteur1 = new Secteur(1);
		Secteur secteur2 = new Secteur(2);
		Secteur secteur3 = new Secteur(3);
		Secteur secteur4 = new Secteur(4);
		Secteur secteur5 = new Secteur(5);
		Secteur secteur6 = new Secteur(6);
		Secteur secteur7 = new Secteur(7);
		Secteur secteur8 = new Secteur(8);
		Secteur triPrime = new Secteur(9);
		
		//Creation des hexagones
		
    }

    public void jouerTour() {
    }

    public void finirPartie() {
    }

	public void debuterPartie(){
		Scanner scanner = new Scanner(System.in);

		//creation des joueurs
		switch (nombreDeJoueursReels) {
			case 1:{
				JoueurVirtuel joueurVirtuel1 = new JoueurVirtuel("1");

				JoueurVirtuel joueurVirtuel2 = new JoueurVirtuel("2");

				System.out.println("Donner le nom du joueur 1");
				String nom = scanner.next();
				Joueur jouer1 = new Joueur(nom);
			}case 2:{
				JoueurVirtuel joueurVirtuel1 = new JoueurVirtuel("1");
				System.out.println("Donner le nom du joueur 1");
				String nom = scanner.next();
				Joueur jouer1 = new Joueur(nom);

				System.out.println("Donner le nom du joueur 2");
				String nom2 = scanner.next();
				Joueur jouer2 = new Joueur(nom);
			}case 3:{
				System.out.println("Donner le nom du joueur 1");
				String nom = scanner.next();
				Joueur jouer1 = new Joueur(nom);

				System.out.println("Donner le nom du joueur 1");
				String nom2 = scanner.next();
				Joueur jouer2 = new Joueur(nom);

				System.out.println("Donner le nom du joueur 1");
				String nom3 = scanner.next();
				Joueur jouer3 = new Joueur(nom);
			}
		}
		//Attribution aleatoir du jeton de depart

		//Mise en place aleatoir du plateau
		miseEnPlace();

		//Jouer les tours
		while(tour<=9 && !finJeu){
			jouerTour();
		}

		//Mettre fin au jeu
		finirPartie();
	}

}

