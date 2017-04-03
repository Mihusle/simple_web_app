package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by MHSL on 28.02.2017.
 *
 * This class represents comment on item page.
 */
@ManagedBean
@RequestScoped
public class Comment implements Serializable {
    
    private int id;
    private User user;
    private String text;
    private Date date;
    private int itemId;
    
    public Comment() {
        
    }
    
    
    public String getText() {
        return text;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public int getItemId() {
        return itemId;
    }
    
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
