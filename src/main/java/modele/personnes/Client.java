package modele.personnes;

import modele.produit.ProduitQuantite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client extends Personne{

    String prenom;
    String adresse;
    int codePostal;
    String ville;
    LocalDate naissance;
    private List<ProduitQuantite> achats;


    private static long ids = 1L;

    Client(long id, String nom, String prenom, String adresse, int codePostal, String ville, LocalDate naissance) {
        super(id,nom);
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.naissance = naissance;
        achats = new ArrayList<>();
    }

    public Client(String nom, String prenom, String adresse, int codePostal, String ville, LocalDate naissance) {
        this(ids++,nom,prenom,adresse,codePostal,ville,naissance);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    public List<ProduitQuantite> getAchats() {
        return achats;
    }

    public void addAchats(List<ProduitQuantite> achats) {
        this.achats.addAll(achats);
    }

    public void addAchats(ProduitQuantite unAchat) {
        achats.add(unAchat);
    }
}
