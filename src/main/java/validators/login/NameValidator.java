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
@FacesValidator("validators.login.NameValidator")
public class NameValidator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        List<String> names = new ArrayList<>();
        DBUtils.getAllUsers().forEach(user -> names.add(user.getName()));
        if (!names.contains(o.toString())) {
            FacesMessage message = new FacesMessage("Wrong name");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
