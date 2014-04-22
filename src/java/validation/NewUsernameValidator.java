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
@FacesValidator("validation.NewUsernameValidator")
@ApplicationScoped
@Named
public class NewUsernameValidator implements Validator {

	@Inject
	private ApplicationBean app;
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String proposedUsername = value.toString();
		User otherUser = app.getUserByName(proposedUsername);
		
		if (otherUser != null) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Username already in use",
					"Username already in use. Please choose another username");
			throw new ValidatorException(message);
		}
	}
	
}
