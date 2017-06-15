package actions;

import com.opensymphony.xwork2.ActionSupport;
import facade.erreurs.IndividuNonConnecteException;
import modele.personnes.Utilisateur;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import facade.ServiceImpl;

import java.util.Collection;
import java.util.Map;

/**
 * Created by o2122061 on 15/06/17.
 */
public class Menu extends ActionSupport implements ApplicationAware, SessionAware
{
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

    public String seller() throws Exception
    {
        return SUCCESS;
    }

    public String admin() throws Exception
    {
        Long id = (Long) session.get("id");
        try {
            session.put("users", service.getListeUtilisateur(id));
        } catch(IndividuNonConnecteException e)
        {
        }
        return SUCCESS;
    }

    public String stockManager() throws Exception
    {
        return SUCCESS;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
