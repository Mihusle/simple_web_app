package controllers;

import beans.User;
import db.DBUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by MHSL on 14.03.2017.
 */
@ManagedBean
@RequestScoped
public class UserController implements Serializable {
    
    private User user;
    
    public UserController() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        user = (User) request.getSession().getAttribute("current_user");
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout";
    }
    
    public String getUserName() {
        return user.getName();
    }
    
    public void setUserName(String name) {
        user.setName(name);
        DBUtils.updateUserName(user.getId(), name);
    }
    
    public String getUserEmail() {
        return user.getEmail();
    }
    
    public void setUserEmail(String email) {
        user.setEmail(email);
        DBUtils.updateUserEmail(user.getId(), email);
    }
    
    public String getUserPassword() {
        return user.getPassword();
    }
    
    public void setUserPassword(String password) {
        user.setPassword(password);
        DBUtils.updateUserPassword(user.getId(), password);
    }
    
    
}
