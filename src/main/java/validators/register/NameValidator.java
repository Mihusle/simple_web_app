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
@FacesValidator("validators.register.NameValidator")
public class NameValidator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String name = o.toString();
        checkNameSymbols(name);
        checkExistenceOfSuchName(name);
    }
    
    private void checkNameSymbols(String name) {
        Pattern pattern = Pattern.compile(".*//W+.*");
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) {
            FacesMessage message = new FacesMessage("Incorrect symbols in the name");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
    
    private void checkExistenceOfSuchName(String name) {
        List<String> names = new ArrayList<>();
        DBUtils.getAllUsers().forEach(user -> names.add(user.getName()));
        if (names.contains(name)) {
            FacesMessage message = new FacesMessage("Such name is taken already");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
