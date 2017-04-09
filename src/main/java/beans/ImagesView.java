package beans;

import db.DBUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by MHSL on 02.04.2017.
 */

@ManagedBean
public class ImagesView {
    
    private List<String> images;
    
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int itemId = Integer.parseInt(request.getParameter("item_id"));
        images = DBUtils.getImageNames(itemId);
    }
    
    public List<String> getImages() {
        return images;
    }
}
