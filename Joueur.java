import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Joueur {
    List<Hex> hexControle;
    private PaquetDeCartes ordreDeCommande;
    List<Vaisseau> vaisseauActif;
    List<Vaisseau> vaisseauInactif;
    private boolean marqueurJoueurDebut = false;
    private int phaseDejeu = 0;
    private int score = 0;
    private String nom;
    private Flotte flotte;
    private boolean joueurActif = true;
    private int nbVaisseauActif = 0;
    static Joueur proprietaireTriPrimeHex;

    public Joueur(String nom) {
        this.nom = nom;
        this.vaisseauActif = new ArrayList<>();
        this.vaisseauInactif = new ArrayList<>();
    }

    //Guetteurs
    public List<Vaisseau> getVaisseauActif() {
        return vaisseauActif;
    }

    public List<Vaisseau> getVaisseauInactif(){
        return vaisseauInactif;
    }

    public int getScore(){
        return this.score;
    }

    public String getNom() {
        return this.nom;
    }

    public boolean getMarqueurJoueurDebut() {
        return this.marqueurJoueurDebut;
    }
    
    public List<Hex> getHexControle() {
        return this.hexControle;
    }

    public PaquetDeCartes getOrdreDeCommande() {
        return this.ordreDeCommande;
    }

    public int getPhaseDejeu() {
        return this.phaseDejeu;
    }

    public Flotte getFlotte() {
        return this.flotte;
    }

    public boolean isJoueurActif() {
        return this.joueurActif;
    }

    public int getNbVaisseauActif() {
        return this.nbVaisseauActif;
    }

    public static Joueur getProprietaireTriPrimeHex() {
        return proprietaireTriPrimeHex;
    }

    public void choisirOrdreCommandes() {
        Scanner scanner = new Scanner(System.in);
        CarteCommande[] ordreDeCommande = new CarteCommande[3];
        
        System.out.println("Veuillez choisir la première carte de commande :");
        ordreDeCommande[0] = demanderCarteCommande(scanner);
        
        System.out.println("Veuillez choisir la deuxième carte de commande :");
        ordreDeCommande[1] = demanderCarteCommande(scanner);
        
        System.out.println("Veuillez choisir la troisième carte de commande :");
        ordreDeCommande[2] = demanderCarteCommande(scanner);
    }

    private CarteCommande demanderCarteCommande(Scanner scanner) {
        // Afficher les options disponibles
        System.out.println("1: EXPLORER");
        System.out.println("2: EXTERMINER");
        System.out.println("3: EXPAND");
        
        int choix = scanner.nextInt();
        switch(choix) {
            case 1: return CarteCommande.EXPLORER;
            case 2: return CarteCommande.EXTERMINER;
            case 3: return CarteCommande.EXPAND;
            default: 
                System.out.println("Choix invalide, veuillez réessayer");
                return demanderCarteCommande(scanner);
        }
    }
/* 
    public Hex choisirHex(Scanner scanner) {
        System.out.println("choisir sur quel hex ajouter un vaisseau");
        int choix = scanner.nextInt();

        if (choix >= 0 && choix < hexControle.size()) {
            return hexControle.get(choix);
        } else {
            System.out.println("Choix invalide, veuillez réessayer");
            return choisirHex(scanner);
        }
    }
*/  
    public void expand(int efficacite) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        boolean continuer = true;
        while (i < efficacite && continuer) {
            System.out.println("voulez-vous arreter?");
            boolean rep = scanner.nextBoolean();
            
            if (rep) {
                continuer = false;
                break;
            } else {
                i++;
            }

            for (int j = 0; j < hexControle.size(); j++){
                System.out.println(j+ ":"+ hexControle.get(j));
            }

            System.out.println("choisir sur quel hex ajouter un vaisseau");
            int h = scanner.nextInt();
            
            Hex hChoisi = hexControle.get(h);

            Vaisseau v = vaisseauInactif.get(0);

            v.activer();

            hChoisi.ajouterVaisseau(v);
        }

        if (i == efficacite) {
            System.out.println("Vous avez utilisé toute votre efficacité pour la commande Expand.");
        }
        scanner.close();
    }

    public void explorer(Flotte flotte,int efficacite) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        boolean continuer = true;
        while (i < efficacite && continuer) {
            System.out.println("voulez-vous arreter?");
            boolean rep = scanner.nextBoolean();
            
            if (rep) {
                continuer = false;
                break;
            } else {
                i++;
            }
        }

        

        System.out.println("Choisir quel hex explorer");
        int h = scanner.nextInt();

        Hex hChoisi = hexControle.get(h);


        

        scanner.close();
    }

    public void exterminer(Hex hexCible, Flotte flotte, int efficacite) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        boolean continuer = true;
        while (i < efficacite && continuer) {
            System.out.println("voulez-vous arreter?");
            boolean rep = scanner.nextBoolean();
            
            if (rep) {
                continuer = false;
                break;
            } else {
                i++;
            }
        }

        
        scanner.close();
    }

    public void augmenterScore(int pointSupp) {
        score += pointSupp;
    }

    public void desactiverJoueur() {
        joueurActif = false;
    }

    public void calculerScore(Hex hexControles) {
        
    }

    public void ajouterHexControle(Hex hex) {
        hexControle.add(hex);
    }

    public void supprimerHexControle(Hex hex) throws MauvaiseEntreeException{
        Iterator<Hex> iterator = this.hexControle.iterator();
		boolean hexTrouve = false;
		
		while(iterator.hasNext()) {
			Hex h = iterator.next();
			if(h == hex) {
				iterator.remove();
				hexTrouve = true;
				break;
			}
		}

        if (!hexTrouve)
            throw new MauvaiseEntreeException("L'hex entre n'est pas controle par ce joueur");
    }


    public void  ajouterControleTrifPrimeHex() {
    }
    
}
