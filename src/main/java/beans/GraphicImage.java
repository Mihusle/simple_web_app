package beans;

import db.DBUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;
import java.io.Serializable;

/**
 * Created by MHSL on 03.04.2017.
 */

@RequestScoped
class GraphicImage implements Serializable {
    
    StreamedContent getImageFromDB(int id, int itemId) {
        if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            byte[] image = DBUtils.getImage(id, itemId);
            return new DefaultStreamedContent(new ByteArrayInputStream(image), "image/jpg");
        }
    }
}
