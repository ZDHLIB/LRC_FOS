package net.jtaq.servlets.form;

import javax.servlet.http.*;
import org.apache.struts.action.*;

public class UserForm extends ActionForm {

	private static final long serialVersionUID = 5172118975313438237L;

	private String logname = "";
	private String password = "";

	public UserForm() {
		logname = null;
		password = null;
	}

	public String getLogname() {
		return this.logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		logname = null;
		password = null;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if ((this.getLogname() == null) || (this.getPassword().length() < 3)) {
			errors.add("loginerror", new ActionError("error.user.login"));
		}
		return errors;
	}

}
