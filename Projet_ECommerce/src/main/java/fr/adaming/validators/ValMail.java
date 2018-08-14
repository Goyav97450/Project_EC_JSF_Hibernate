package fr.adaming.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("valMail")
public class ValMail implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent composant, Object value) throws ValidatorException {
		
		String val = (String) value;
		
		if (!val.contains("@")) {
			throw new ValidatorException(new FacesMessage("Une adresse mail doit contenir un @"));
		}
		
	}

}
