package modele.personnes;

public class Personne {
    String nom;
    long identifiant;

    public Personne(long id,String nom) {
        this.identifiant = id;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getIdentifiant() {
        return identifiant;
    }
}
