package actions;

import com.opensymphony.xwork2.ActionSupport;
import facade.erreurs.RoleDejaAttribueException;
import facade.erreurs.UtilisateurDejaExistantException;
import modele.personnes.Utilisateur;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import facade.ServiceImpl;
import modele.personnes.Personne;
import facade.erreurs.IndividuNonConnecteException;

import java.util.Collection;
import java.util.Map;

/**
 * Created by o2122061 on 15/06/17.
 */
public class Admin extends ActionSupport implements ApplicationAware, SessionAware
{

    private String login;

    private String password;

    private String passwordConfirmation;

    private String userSelected;

    private Collection<String> roles;

    private String rolesToAdd;

    private boolean deleteAdminRole;

    private boolean deleteSellerRole;

    private boolean deleteStockManagerRole;

    private Map<String, Object> session;

    private ServiceImpl service;

    public void setApplication(Map<String, Object> map)
    {
        service = (ServiceImpl) map.get("service");
        if(service == null)
        {
            service = new ServiceImpl();
            map.put("service", service);
        }
    }

    @Override
    public String execute() throws Exception
    {
        return SUCCESS;
    }

    public String addUser() throws Exception
    {
        if (password.equals(passwordConfirmation))
        {
            try {
                Long id = (Long) session.get("id");
                Personne user = service.creerUtilisateur(id, login, password);
            } catch (IndividuNonConnecteException | UtilisateurDejaExistantException e) {
            }
        }
        return SUCCESS;
    }

    public String deleteUser() throws Exception
    {
        Long id = (Long) session.get("id");
        try {
            service.supprimerClient(id, Long.parseLong(userSelected));
            session.put("users", service.getListeUtilisateur(id));
        } catch(IndividuNonConnecteException e)
        {

        }
        return SUCCESS;
    }

    public String selectUser() throws Exception
    {
        session.put("userSelected", userSelected);
        Long id = (Long) session.get("id");
        Utilisateur user = service.getUserById(id, Long.parseLong(userSelected));
        roles = user.getRoles();
        return SUCCESS;
    }

    public String updateUser() throws Exception
    {
        Long id = (Long) session.get("id");
        Long userSelected = Long.parseLong((String) session.get("userSelected"));

        if(!password.equals(""))
            service.changerMotDePasseUtilisateur(id, userSelected, password);

        if (rolesToAdd != null)
        {
            String [] newRoles = rolesToAdd.split(",");
            for(String role: newRoles)
            {
                try {
                    service.associerRoleUtilisateur(id, userSelected, role);
                } catch (IndividuNonConnecteException | RoleDejaAttribueException e)
                {
                }
            }
        }

        if(deleteAdminRole)
            service.supprimerRoleUtilisateur(id, userSelected, "ADMIN");
        if(deleteSellerRole)
            service.supprimerRoleUtilisateur(id, userSelected, "VENDEUR");
        if(deleteStockManagerRole)
            service.supprimerRoleUtilisateur(id, userSelected, "RESPONSABLESTOCK");

        session.remove("userSelected");

        return SUCCESS;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(String userSelected) {
        this.userSelected = userSelected;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public String getRolesToAdd() {
        return rolesToAdd;
    }

    public void setRolesToAdd(String rolesToAdd) {
        this.rolesToAdd = rolesToAdd;
    }

    public boolean getDeleteAdminRole() {
        return deleteAdminRole;
    }

    public void setDeleteAdminRole(boolean deleteAdminRole) {
        this.deleteAdminRole = deleteAdminRole;
    }

    public boolean getDeleteSellerRole() {
        return deleteSellerRole;
    }

    public void setDeleteSellerRole(boolean deleteSellerRole) {
        this.deleteSellerRole = deleteSellerRole;
    }

    public boolean getDeleteStockManagerRole() {
        return deleteStockManagerRole;
    }

    public void setDeleteStockManagerRole(boolean deleteStockManagerRole) {
        this.deleteStockManagerRole = deleteStockManagerRole;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}


