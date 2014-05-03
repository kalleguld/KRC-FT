/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validation;

import java.util.ResourceBundle;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import service.ApplicationBean;

/**
 *
 * @author kalleguld
 */
@FacesValidator("validation.RepeatPasswordValidator")
@ApplicationScoped
@Named
public class RepeatPasswordValidator implements Validator {

	@Inject
	private ApplicationBean app;
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		
		String pass1 = ((HtmlInputSecret)component.findComponent("pass1")).getValue().toString();
		String pass2 = value.toString();
		
		if (!pass1.equals(pass2)) {
			ResourceBundle msgs = ResourceBundle.getBundle("properties.msgs", 
					context.getViewRoot().getLocale());
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					msgs.getString("opretLogin_error_passwordsDontMatchHeader"),
					msgs.getString("opretLogin_error_passwordsDontMatch"));
			throw new ValidatorException(message);
		}
	}
	
}
