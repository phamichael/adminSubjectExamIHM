package modele.produit;

public abstract class Produit {
    private long id;
    private String libelle;
    private double prix;

    public static String[] TYPESPRODUITS = new String[]{"LIVRE","CD"};

    static long idProduits = 0;

    public Produit(long id, String libelle, double prix) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
