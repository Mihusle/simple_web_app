package validators.update;

import beans.User;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by MHSL on 10.04.2017.
 */

@FacesValidator("validators.update.OldPasswordValidator")
public class OldPasswordValidator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        User currentUser = (User) request.getSession().getAttribute("current_user");
        if (!currentUser.getPassword().equals(o.toString())) {
            FacesMessage message = new FacesMessage("Wrong old password");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
