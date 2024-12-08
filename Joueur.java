import java.util.*;

public class Joueur {
    List<Hex> hexControle;
    private PaquetDeCartes ordreDeCommande;
    List<Vaisseau> vaisseauActif;
    List<Vaisseau> vaisseauInactif;
    private boolean marqueurJoueurDebut = false;
    private int phaseDejeu = 0;
    private int score = 0;
    private String nom;
    private boolean joueurActif = true;
    private int nbVaisseauActif = 0;
    static Joueur proprietaireTriPrimeHex;
	List<Secteur> ordreSecteur;
    static HashMap<Joueur, Secteur> secteurChoisi;


    public Joueur(String nom, List<Secteur> ordreSecteur) {
        this.nom = nom;
        this.vaisseauActif = new ArrayList<>();
        this.vaisseauInactif = new ArrayList<>();
        this.ordreSecteur = ordreSecteur;
        secteurChoisi = new HashMap<>();
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

    private CarteCommande demanderCarteCommande(Scanner scanner){
        // Afficher les options disponibles
        System.out.println("1: EXPLORER");
        System.out.println("2: EXTERMINER");
        System.out.println("3: EXPAND");
        
        int choix = scanner.nextInt();
        switch(choix) {
            case 1:
            	CarteCommande commande = new CarteCommande(commandeCarte.EXPLORE, 1);
            	return commande;
            	
            case 2: 
                CarteCommande commande2 = new CarteCommande(commandeCarte.EXTERMINATE, 1);
                return commande2;
            case 3: 
                CarteCommande commande3 = new CarteCommande(commandeCarte.EXPAND, 1);
                return commande3;
            default:
                System.out.println("Choix invalide, veuillez réessayer");
                return demanderCarteCommande(scanner);
        }
    }

    public Flotte creerFlotte(Hex hexVise, Scanner scanner, Flotte flotte){

        //accéder aux hexs alentours (connaitres les hex autour de chaque hex)
        List<Hex> listeHexAutour = hexVise.getHexAutour();
        List<Integer> numVaisseauDispo = new ArrayList<>();

        //présenter les vaisseaux des hexs alentour
        for (Hex h : listeHexAutour) {
            if (hexControle.contains(h)) {
                System.out.println("Dans l'hex " + h.getHexId() + " il y a les vaisseaux suivants:");
                for (Vaisseau v : h.getVaisseau()) {
                    System.out.println("- Vaisseau n°" + v.getNumero());
                    // Stocker leur numéro dans la liste
                    numVaisseauDispo.add(v.getNumero());
                }
            }
        }

        //création d'une flotte
        

        while (true) {
            System.out.println("Quel vaisseau voulez-vous ajouter à la flotte ? (tapez son numero ou une lettre pour quitter)");
            
            // Vérification d'entrée
            if (!scanner.hasNextInt()) {
                scanner.next(); // Consomme l'entrée invalide
                break;
            }
        
            int vaisseauAjouter = scanner.nextInt();
        
            // Vérifier si le vaisseau est disponible
            if (!numVaisseauDispo.contains(vaisseauAjouter)) {
                System.out.println("Le vaisseau n'est pas dans la liste présentée.");
                continue;
            }
        
            // Vérifier si le vaisseau est déjà dans la flotte
            if (flotte.getVaisseau().stream().anyMatch(v -> v.getNumero() == vaisseauAjouter)) {
                System.out.println("Le vaisseau a déjà été ajouté.");
                continue;
            }
        
            // Ajouter le vaisseau à la flotte
            for (Vaisseau v : this.vaisseauActif) {
                if (v.getNumero() == vaisseauAjouter) {
                    flotte.ajouterVaisseau(v);
                    System.out.println("Le vaisseau a été ajouté à la flotte.");
                    break;
                }
            }
        }
        return flotte;
        
    }

    public void expand(int efficacite) {
        try (Scanner scanner = new Scanner(System.in)) {
            int i = 0;
    
            while (i < efficacite) {
                // Demander à l'utilisateur s'il souhaite arrêter
                System.out.println("Voulez-vous arrêter ? (true pour oui, false pour continuer)");
                if (scanner.hasNextBoolean() && scanner.nextBoolean()) {
                    break; // Sortie de la boucle si l'utilisateur veut arrêter
                }
    
                // Afficher les hex contrôlés
                for (int j = 0; j < hexControle.size(); j++) {
                    System.out.println(j + ": " + hexControle.get(j));
                }
    
                // Choisir un hex pour ajouter un vaisseau
                System.out.println("Choisissez sur quel hex ajouter un vaisseau (indice dans la liste ci-dessus) :");
                if (!scanner.hasNextInt()) {
                    System.out.println("Entrée invalide, veuillez saisir un entier.");
                    scanner.next(); // Consomme l'entrée invalide
                    continue;
                }
    
                int h = scanner.nextInt();
                if (h < 0 || h >= hexControle.size()) {
                    System.out.println("Indice hors limites. Veuillez réessayer.");
                    continue;
                }
    
                Hex hChoisi = hexControle.get(h);
    
                // Activer un vaisseau inactif et l'ajouter au hex choisi
                System.out.println("quel vaisseau voulez vous utiliser?");

                if (vaisseauInactif.isEmpty()) {
                    System.out.println("Aucun vaisseau inactif disponible pour l'ajouter.");
                    break;
                }
    
                Vaisseau v = vaisseauInactif.remove(0); // Retire le vaisseau de la liste des inactifs
                v.activer(); // Active le vaisseau
                hChoisi.ajouterVaisseau(v); // Ajoute le vaisseau au hex sélectionné
    
                System.out.println("Vaisseau n°"+ v.getNumero() +" ajouté à l'hex " + hChoisi.getHexId());
                i++; // Incrémentation de l'efficacité utilisée
            }
    
            if (i == efficacite) {
                System.out.println("Vous avez utilisé toute votre efficacité pour la commande Expand.");
            }
        }
    }    

    public void explorer(int efficacite) {
        try (Scanner scanner = new Scanner(System.in)) {
            int i = 0;
    
            while (i < efficacite) {
                // Demander à l'utilisateur s'il souhaite arrêter
                System.out.println("Voulez-vous arrêter ? (true pour oui, false pour continuer)");
                if (scanner.hasNextBoolean() && scanner.nextBoolean()) {
                    break;
                }
    
                // Choisir un hex à explorer
                System.out.println("Choisissez quel hex explorer (indice de la liste hexControle) :");
                if (!scanner.hasNextInt()) {
                    System.out.println("Entrée invalide, veuillez saisir un entier.");
                    scanner.next(); // Consomme l'entrée invalide
                    continue;
                }
    
                int h = scanner.nextInt();
                if (h < 0 || h >= hexControle.size()) {
                    System.out.println("Indice hors limites. Veuillez réessayer.");
                    continue;
                }
    
                Hex hChoisi = hexControle.get(h);
    
                // Créer une flotte pour explorer l'hex
                Flotte flotte = creerFlotte(hChoisi, scanner, new Flotte());
    
                // Ajouter la flotte si elle contient des vaisseaux
                if (flotte.getVaisseau() != null && !flotte.getVaisseau().isEmpty()) {
                    hChoisi.ajouterFlotte(flotte);
                    System.out.println("Exploration réussie. Flotte ajoutée à l'hex " + hChoisi.getHexId());
                    i++; // Incrémentation de l'efficacité utilisée
                } else {
                    System.out.println("L'hex choisi ne contient pas de vaisseau disponible pour l'exploration.");
                }
            }
        }
    }
    
    public void exterminer(Hex hexCible, int efficacite) {
        try (Scanner scanner = new Scanner(System.in)) {
            int i = 0;
    
            while (i < efficacite) {
                // Demander si l'utilisateur souhaite arrêter
                System.out.println("Voulez-vous arrêter ? (true pour oui, false pour continuer)");
                if (scanner.hasNextBoolean() && scanner.nextBoolean()) {
                    break;
                }
    
                // Choisir l'hex cible
                System.out.println("Choisissez quel hex combattre (ID de l'hex) :");
                if (!scanner.hasNextInt()) {
                    System.out.println("Entrée invalide, veuillez saisir un entier.");
                    scanner.next(); // Consomme l'entrée invalide
                    continue;
                }
    
                int h = scanner.nextInt();
    
                // Vérifier si l'hex cible appartient au joueur
                if (hexControle.stream().anyMatch(hex -> hex.getHexId() == h)) {
                    System.out.println("Merci de ne pas choisir un de vos hex.");
                    continue;
                }
    
                // Trouver l'hex cible dans les secteurs
                Hex hChoisi = ordreSecteur.stream()
                    .flatMap(secteur -> secteur.getArrayHex().stream())
                    .filter(hex -> hex.getHexId() == h)
                    .findFirst()
                    .orElse(null);
    
                if (hChoisi == null) {
                    System.out.println("L'hex choisi est introuvable.");
                    continue;
                }
    
                // Créer une flotte pour combattre
                Flotte flotte = creerFlotte(hChoisi, scanner, new Flotte());
    
                // Effectuer le combat
                if (flotte.getVaisseau() != null && !flotte.getVaisseau().isEmpty()) {
                    int nbVaisseauOpposant = hChoisi.getVaisseau().size();
                    int resultatCombat = flotte.getVaisseau().size() - nbVaisseauOpposant;
    
                    if (resultatCombat < 0) {
                        // Défaite totale
                        hChoisi.enleverVaisseauEtDesactiver(flotte.getVaisseau().size());
                        flotte.desactiverTout();
                    } else if (resultatCombat > 0) {
                        // Victoire
                        hChoisi.enleverTousVaisseauEtDesaciver();
                        flotte.deplacer(hChoisi);
                        hChoisi.enleverVaisseauEtDesactiver(nbVaisseauOpposant);
                    } else {
                        // Match nul
                        hChoisi.enleverTousVaisseauEtDesaciver();
                        flotte.desactiverTout();
                        ajouterHexControle(hChoisi);
                    }
    
                    flotte.getVaisseau().clear();
                    i++; // Incrémentation de l'efficacité utilisée
                } else {
                    System.out.println("L'hex choisi ne contient pas de vaisseaux à vous autour.");
                }
            }
        }
    }
    
    public void augmenterScore(int pointSupp) {
        score += pointSupp;
    }

    public void desactiverJoueur() {
        joueurActif = false;
    }

    public void calculerScore(Hex hexControles) {
        //choisir secteur
        //le choix du joueur ne prend pas en compte les secteurs deja choisis
        for (Secteur s : ordreSecteur) {
            if (s.getArrayHex().contains(hexControles)) {
                System.out.println(s);
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.err.println("quel secteur voulez vous choisir? (entrez son indice)");
        
        int choix;
        do {
            choix = scanner.nextInt();
            if (choix < 0 || choix >= ordreSecteur.size()) {
                System.out.println("Entrée invalide, veuillez saisir un entier.");
            }
        } while (choix < 0 || choix >= ordreSecteur.size());

        Secteur s = ordreSecteur.get(choix);
        int score = s.calculerScoreParJoueur(this);
        System.out.println("le score de ce secteur est de " + score);
        //regarder si le joueur a le triprime
        if (proprietaireTriPrimeHex == this) {
            score *= 2;
            System.out.println("le score du joueur est multiplié par 2 car il a le triprime");
        }
        augmenterScore(score);
        System.out.println("le score total du joueur est de " + score);

        
        scanner.close();
    }

    public void supprimerHexControle(Hex hex) throws MauvaiseEntreeException{
        if (!hexControle.remove(hex))
            throw new MauvaiseEntreeException("L'hex entre n'est pas controle par ce joueur");
    }

    public void  ajouterTriPrime() {
        proprietaireTriPrimeHex = this;
    }

    public void ajouterVaisseauInactif(Vaisseau v){
        vaisseauInactif.add(v);
    }
    
    public void enleverVaisseauInactif(Vaisseau v){
        vaisseauInactif.remove(v);
    }

    public void ajouterVaisseauActif(Vaisseau v){
        vaisseauActif.add(v);
    }

    public void enleverVaisseauActif(Vaisseau v){
        vaisseauActif.remove(v);
    }

    public void ajouterHexControle(Hex hex) {
        hexControle.add(hex);
    }

    public void enleverHexControle(Hex hex){
        hexControle.remove(hex);
    }

}
