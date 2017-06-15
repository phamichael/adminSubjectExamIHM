package modele.produit;

public class CD extends Produit {
    private String chanteur;

    CD(long id, String libelle, double prix,String chanteur) {
        super(id, libelle, prix);
        this.chanteur = chanteur;
    }

    public CD(String libelle, double prix,String chanteur) {
        this(idProduits++,libelle,prix,chanteur);
    }

    public String getChanteur() {
        return chanteur;
    }
}
