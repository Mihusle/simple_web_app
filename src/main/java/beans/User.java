package beans;

import db.DBUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by MHSL on 14.03.2017.
 */
@ManagedBean
@SessionScoped
public class User implements Serializable {
    
    private int id;
    private String name;
    private String email;
    private String password;
    
    public User() {
        
    }
    
    //I should carry over this method to a controller.
    public String login() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(name, password);
            initUser();
            request.getSession().setAttribute("current_user", this);
            return "/index?faces-redirect=true";
        } catch (ServletException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("Wrong login or password");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage("login", message);
        }
        return "/pages/login.xhtml";
    }
    
    //I should carry over this method to a controller.
    public String registration() {
        if (name != null) {
            int createdUsers = DBUtils.addUser(name, password, email);
            if (createdUsers > 0) {
                try {
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    request.login(name, password);
                    initUser();
                    request.getSession().setAttribute("current_user", this);
                } catch (ServletException e) {
                    FacesContext context = FacesContext.getCurrentInstance();
                    FacesMessage message = new FacesMessage("Wrong login or password");
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    context.addMessage("login", message);
                }
                return "/index?faces-redirect=true";
            }
        }
        return "/pages/register.xhtml?faces-redirect=true";
    }
    
    private void initUser() {
        List<User> users = DBUtils.getAllUsers().stream()
                .filter(user -> user.getName().equals(name))
                .collect(Collectors.toList());
        setId(users.get(0).getId());
        setEmail(users.get(0).getEmail());
    }
    
    public boolean isLoged() {
        return name != null;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
