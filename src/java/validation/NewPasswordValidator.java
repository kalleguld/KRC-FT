/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validation;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import model.User;
import service.ApplicationBean;
import service.SessionBean;

/**
 *
 * @author kalleguld
 */
@FacesValidator("validation.NewPasswordValidator")
@ApplicationScoped
@Named
public class NewPasswordValidator implements Validator {

	@Inject
	private ApplicationBean app;
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String proposedPassword = value.toString();
		
		
		if (proposedPassword.length() < 4) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Password is too weak",
					"Password is too weak. Please use at least 4 characters");
			throw new ValidatorException(message);
		}
	}
	
}
