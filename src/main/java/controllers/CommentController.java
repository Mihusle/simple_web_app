package controllers;

import beans.Comment;
import beans.User;
import db.DBUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by MHSL on 18.03.2017.
 */
@ManagedBean
@SessionScoped
public class CommentController {
    
    private List<Comment> comments = new ArrayList<>();
    private boolean editMode = false;
    
    public CommentController() {
        
    }
    
    public String addComment(Comment comment) {
        java.util.Date date = Calendar.getInstance().getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int itemId = Integer.parseInt(request.getParameter("item_id"));
        comment.setUser((User)request.getSession().getAttribute("current_user"));
        comment.setDate(new Date(date.getYear(), date.getMonth(), date.getDay())); //bug
        comment.setItemId(itemId);
        DBUtils.addCommentForItem(comment);
        return "/pages/item.xhtml?item_id=" + itemId +"&faces-redirect=true";
    }
    
    public String changeComment(Comment comment) {
        DBUtils.changeCommentForItem(comment);
        return switchEditMode();
    }
    
    public String deleteComment(Comment comment) {
        DBUtils.deleteCommentForItem(comment);
        return "/pages/item.xhtml?item_id=" + comment.getItemId() +"&faces-redirect=true";
    }
    
    public boolean isCurrentUsersComment(Comment comment) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        User currentUser = (User) request.getSession().getAttribute("current_user");
        return currentUser.equals(comment.getUser());
    }
    
    public String switchEditMode() {
        editMode = !editMode;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int itemId = Integer.parseInt(request.getParameter("item_id"));
        return "/pages/item.xhtml?item_id=" + itemId +"&faces-redirect=true";
    }
    
    public List<Comment> getComments(int itemId) {
        comments = DBUtils.getCommentsForItem(itemId);
        return comments;
    }
    
    public boolean isEditMode() {
        return editMode;
    }
}