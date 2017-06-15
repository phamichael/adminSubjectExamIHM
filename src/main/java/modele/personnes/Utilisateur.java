package modele.personnes;

import facade.erreurs.RoleDejaAttribueException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Utilisateur extends Personne {

    String mdp;

    static long ids = 1L;

    Set<String> mesRoles;


    Utilisateur(long id, String nom, String mdp) {
        super(id,nom);
        this.mdp = mdp;
        mesRoles = new HashSet<>();

    }

    public Utilisateur(String nom, String mdp) {
        this(ids++,nom,mdp);
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void addRole(String role) throws RoleDejaAttribueException {
        if (this.hasRole(role)) {
            throw new RoleDejaAttribueException();
        }
        mesRoles.add(role);}
    public boolean hasRole(String role) {
        return mesRoles.contains(role);
    }

    public Collection<String> getRoles() {
        return mesRoles;
    }

    public void supprimerRole(String role) {
        mesRoles.remove(role);
    }
}
