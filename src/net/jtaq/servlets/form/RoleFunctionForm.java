package net.jtaq.servlets.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RoleFunctionForm extends ActionForm {
	private static final long serialVersionUID = -2767981921910875485L;

	private String functionID;
	private String functionName;
	private String functionsOfRole;
	@SuppressWarnings("unchecked")
	private Collection functions;
	private Boolean isRemove;
	private Boolean isAdd;
	private String operatorID;
	private String roleID;
	private String roleName;

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	public String getFunctionID() {
		return functionID;
	}

	public void setFunctionID(String functionID) {
		this.functionID = functionID;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionsOfRole() {
		return functionsOfRole;
	}

	public void setFunctionsOfRole(String functionsOfRole) {
		this.functionsOfRole = functionsOfRole;
	}

	@SuppressWarnings("unchecked")
	public Collection getFunctions() {
		return functions;
	}

	@SuppressWarnings("unchecked")
	public void setFunctions(Collection functions) {
		this.functions = functions;
	}

	public Boolean getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(Boolean isRemove) {
		this.isRemove = isRemove;
	}

	public Boolean getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}

	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}