public class Map {

    private Secteur[] tousLesSecteurs;

    public Map(Secteur[] tousLesSecteurs){
        this.tousLesSecteurs = tousLesSecteurs;
    }

    public Secteur[] getTousLesSecteurs() {
        return tousLesSecteurs;
    }
}
