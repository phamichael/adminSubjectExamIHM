package facade;

import facade.erreurs.CoupleUtilisateurMDPInconnuException;
import modele.personnes.Personne;

public interface ConnexionService {

    Personne connexion(String pseudo, String mdp) throws CoupleUtilisateurMDPInconnuException;

    public void deconnexion(long idUtilisateur);

    boolean estUnUtilisateurConnu(String pseudo);

    boolean estUnAdmin(long idUtilisateur);

    boolean estUnVendeur(long idUtilisateur);

    boolean estResponsableDesStocks(long idUtilisateur);

}
