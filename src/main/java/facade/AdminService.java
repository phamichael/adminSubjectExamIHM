package facade;

import facade.erreurs.IndividuNonConnecteException;
import facade.erreurs.RoleDejaAttribueException;
import facade.erreurs.UtilisateurDejaExistantException;
import facade.securite.HasRole;
import modele.personnes.Personne;
import modele.personnes.Utilisateur;

import java.util.Collection;

public interface AdminService {


    @HasRole("ADMIN")
    Personne creerUtilisateur(long u, String nom, String mdp) throws IndividuNonConnecteException,UtilisateurDejaExistantException;
    @HasRole("ADMIN")
    public void associerRoleUtilisateur(long u, long utilisateurConcerne, String role) throws IndividuNonConnecteException, RoleDejaAttribueException;
    @HasRole("ADMIN")
    public void supprimerClient(long u, long utilisateurConcerne) throws IndividuNonConnecteException;

    @HasRole("ADMIN")
    public Collection<Utilisateur> getListeUtilisateur(long idDemandeur)throws IndividuNonConnecteException;

    @HasRole("ADMIN")
    Utilisateur getUserById(long identifiant, long identifiant1);

    @HasRole("ADMIN")
    void supprimerRoleUtilisateur(long identifiant, long identifiant1, String role);

    @HasRole("ADMIN")
    void changerMotDePasseUtilisateur(long identifiant, long identifiant1, String mdp);
}
