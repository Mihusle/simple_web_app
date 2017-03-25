package validators.login;

import db.DBUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MHSL on 14.03.2017.
 */
@FacesValidator("validators.login.PasswordValidator")
public class PasswordValidator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        List<String> passwords = new ArrayList<>();
        DBUtils.getAllUsers().forEach(user -> passwords.add(user.getPassword()));
        if (!passwords.contains(o.toString())) {
            FacesMessage message = new FacesMessage("Wrong password");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
