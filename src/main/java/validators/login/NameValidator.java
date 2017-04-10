package validators.login;

import beans.User;
import db.DBUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by MHSL on 14.03.2017.
 */
@FacesValidator("validators.login.NameValidator")
public class NameValidator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        User user = DBUtils.getUserByName(o.toString());
        if (user == null) {
            FacesMessage message = new FacesMessage("Wrong name");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else {
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            request.setAttribute("user_name", o);
        }
    }
}
