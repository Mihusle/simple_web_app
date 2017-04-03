package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by MHSL on 14.03.2017.
 */
@ManagedBean
@RequestScoped
public class LoginController implements Serializable {
    
    public LoginController() {
        
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout";
    }
}
