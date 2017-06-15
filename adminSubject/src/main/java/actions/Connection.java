package actions;

import com.opensymphony.xwork2.ActionSupport;
import facade.erreurs.CoupleUtilisateurMDPInconnuException;
import modele.personnes.Personne;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import facade.ConnexionService;
import facade.ServiceImpl;

import java.util.Map;

/**
 * Created by o2122061 on 15/06/17.
 */
public class Connection extends ActionSupport implements ApplicationAware, SessionAware
{
    private String login;

    private String password;

    private Map<String, Object> session;

    private ConnexionService service;

    public void setApplication(Map<String, Object> map)
    {
        service = (ConnexionService) map.get("service");
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

    public String login() throws Exception
    {
        boolean loginOk = service.estUnUtilisateurConnu(login);
        if(loginOk)
        {
            session.put("login", login);
            return SUCCESS;
        }
        else
            return ERROR;
    }

    public String password() throws Exception
    {
        try {
            Personne user = service.connexion((String) session.get("login"), password);
            Long id = user.getIdentifiant();
            session.put("id", id);
            session.put("isSeller", service.estUnVendeur(id));
            session.put("isAdmin", service.estUnAdmin(id));
            session.put("isStockManager", service.estResponsableDesStocks(id));
            session.put("password", password);
        } catch(CoupleUtilisateurMDPInconnuException e)
        {
            return ERROR;
        }
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

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
