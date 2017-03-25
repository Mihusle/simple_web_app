package validators.register;

import db.DBUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MHSL on 14.03.2017.
 */
@FacesValidator("validators.register.EmailValidator")
public class EmailValidator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Pattern pattern = Pattern.compile(".+@.+\\.com|ru");
        Matcher matcher = pattern.matcher(o.toString());
        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("Wrong email");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        List<String> emails = new ArrayList<>();
        DBUtils.getAllUsers().forEach(user -> emails.add(user.getEmail()));
        if (emails.contains(o.toString())) {
            FacesMessage message = new FacesMessage("Such email is taken already");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
