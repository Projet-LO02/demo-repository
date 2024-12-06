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
    //private Flotte flotte;
    private boolean joueurActif = true;
    private int nbVaisseauActif = 0;
    static Joueur proprietaireTriPrimeHex;
	List<Secteur> ordreSecteur;


    public Joueur(String nom, List<Secteur> ordreSecteur) {
        this.nom = nom;
        this.vaisseauActif = new ArrayList<>();
        this.vaisseauInactif = new ArrayList<>();
        this.ordreSecteur = ordreSecteur;
        
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

    /*public Flotte getFlotte() {
        return this.flotte;
    }*/

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

    public Flotte creerFlotte(Hex hexVise, Scanner scanner, Flotte flotte){

        //accéder aux hexs alentours (connaitres les hex autour de chaque hex)
        List<Hex> listeHexAutour = hexVise.getHexAutour();
        List<Integer> numVaisseauDispo = new ArrayList<>();

        //présenter les vaisseaux des hexs alentour
        Iterator<Hex> iterator1 = listeHexAutour.iterator();
        while (iterator1.hasNext()) {
            Hex h = iterator1.next();
            List<Vaisseau> listeVaisseauParHex = h.getVaisseau();
            Iterator<Vaisseau> iterator2 = listeVaisseauParHex.iterator();
            if (hexControle.contains(h)){
                System.out.println("Dans l'hex "+ h.getHexId()+ " il y a les vaisseaux suivant:");
                while (iterator2.hasNext()){
                    Vaisseau v = iterator2.next();
                    System.out.println("- Vaisseau n°"+ v.getNumero());
                    //stocker leur nombre d'identité dans un tableau
                    numVaisseauDispo.add(v.getNumero());
                }
            }
        }

        //création d'une flotte
        

        while (true){
            
            System.out.println("quel vaisseau voulez vous ajouter un vaisseau à la flotte? (aucun tapez une lettre)");
            int vaisseauAjouter = scanner.nextInt();

                //parcourir le tableau pour verrifier que le vaisseau est bien dans ceux compris  

                    

                if (!numVaisseauDispo.contains(vaisseauAjouter) || flotte.getVaisseau().contains(numVaisseauDispo)){
                    System.out.println("le vaisseau n'est pas dans ceux présents dans la liste présentée");
                    break;
                }



                //ajouter vaisseau à la flotte
                Iterator<Vaisseau> iterator3 = this.vaisseauActif.iterator();
                while(iterator3.hasNext() ) {
                    Vaisseau v = iterator3.next();
                    if(v.getNumero() == vaisseauAjouter) {
                        flotte.ajouterVaisseau(v);
                        break;
                    }
                }
                System.out.println("le vaisseau a déjà été ajouté");
            
        }
        return flotte;
    }

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

    public void explorer(int efficacite) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        boolean continuer = true;
        while (i < efficacite && continuer) {
            System.out.println("voulez-vous arreter?");
            boolean rep = scanner.nextBoolean();
            
            if (rep) {
                continuer = false;
                break;
            }
            
            i++;

            System.out.println("Choisir quel hex explorer");
            int h = scanner.nextInt();

            Hex hChoisi = hexControle.get(h);

            Flotte flotte = new Flotte();
            flotte = creerFlotte(hChoisi, scanner, flotte);

            if (!(flotte.getVaisseau() == null)){
                hChoisi.ajouterFlotte(flotte);
            }else{
                System.out.println("l'hex choisi ne contient pas de vaisseau à vous autour"); 
                i--;
            }
        }

        scanner.close();
    }

    public void exterminer(Hex hexCible, int efficacite) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        boolean continuer = true;
        while (i < efficacite && continuer) {
            System.out.println("voulez-vous arreter?");
            boolean rep = scanner.nextBoolean();
            
            if (rep) {
                continuer = false;
                break;
            }
            
            i++;
            System.out.println("Choisir quel hex combatre");
            int h = scanner.nextInt();

            Iterator<Hex> iterator = this.hexControle.iterator();
            while(iterator.hasNext() ) {
                Hex hex = iterator.next();
                if(hex.getHexId() == h) {
                    System.out.println("merci de ne pas choisir un de vos hex");
                    break;
                }
            }

            Hex hChoisi = null; 

            Iterator<Secteur> iterator2 = this.ordreSecteur.iterator();
            boolean hexTrouve = false;
            while(iterator2.hasNext() && hexTrouve==false) {
                Secteur s = iterator2.next();
                Iterator<Hex> iterator3 = s.getArrayHex().iterator();
                while(iterator3.hasNext()){
                    Hex hex = iterator3.next();
                    if(hex.getHexId() == h) {
                        hChoisi = hex;
                        hexTrouve = true;
                        break;
                    }
                }
            }

            if (hChoisi==null)
                break;


            Flotte flotte = new Flotte();
            flotte = creerFlotte(hChoisi, scanner, flotte);

            if (!(flotte.getVaisseau() == null) && hChoisi!=null){
                int nbVaisseauOpposant = hChoisi.getVaisseau().size();
                int resultatCombat = flotte.getVaisseau().size()-nbVaisseauOpposant;

                int nbVaisseauEnlever ;
                if (resultatCombat<0){
                    nbVaisseauEnlever = flotte.getVaisseau().size();
                    hChoisi.enleverVaisseauEtDesactiver(nbVaisseauEnlever);
                    flotte.desactiverTout();
                }
                else if(resultatCombat>0){
                    nbVaisseauEnlever = nbVaisseauOpposant;
                    hChoisi.enleverTousVaisseauEtDesaciver();
                    flotte.deplacer(hChoisi);
                    hChoisi.enleverVaisseauEtDesactiver(nbVaisseauEnlever);
                }
                else{
                    hChoisi.enleverTousVaisseauEtDesaciver();
                    flotte.desactiverTout();
                }
                flotte.getVaisseau().clear();
            }else{
                System.out.println("l'hex choisi ne contient pas de vaisseau à vous autour"); 
                i--;}
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
        //choisir quel hex rapporte des points
        //regarder si le joueur a le triprime
        
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


    public void  ajouterTriPrime() {
        proprietaireTriPrimeHex = this;
    }
    
}
