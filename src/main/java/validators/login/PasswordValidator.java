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
@FacesValidator("validators.login.PasswordValidator")
public class PasswordValidator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String userName = request.getAttribute("user_name").toString();
        User user = DBUtils.getUserByName(userName);
        if (user == null || !user.getPassword().equals(o.toString())) {
            FacesMessage message = new FacesMessage("Wrong password");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
