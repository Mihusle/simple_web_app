package beans;

import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MHSL on 02.04.2017.
 */

@ManagedBean
public class ImagesView {
    
    private List<StreamedContent> images;
    
    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int itemId = Integer.parseInt(request.getParameter("item_id"));
        for (int i = 1; i <= 2; i++) {
            images.add(new GraphicImage().getImageFromDB(i, itemId)); //It's so simple because I need it just for test and that's all
        }
    }
    
    public List<StreamedContent> getImages() {
        return images;
    }
}
