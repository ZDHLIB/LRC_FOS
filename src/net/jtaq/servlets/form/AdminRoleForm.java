package net.jtaq.servlets.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AdminRoleForm extends ActionForm {

	private static final long serialVersionUID = 412350237556956062L;

	@SuppressWarnings("unchecked")
	private Collection rolesOfAdmin;
	private String operatorID;
	private String roleName;
	@SuppressWarnings("unchecked")
	private Collection roles;
	private String roleID;
	private String adminName;
	private String adminID;
	private boolean isAdd;
	private boolean isRemove;

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.adminID = null;
		this.adminName = null;
		this.isAdd = false;
		this.isRemove = false;
		this.operatorID = null;
		this.roleID = null;
		this.roleName = null;
		this.roles = null;
		this.rolesOfAdmin = null;
	}

	public boolean getIsAdd() {
		return this.isAdd;
	}

	public void setIsAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	public boolean getIsRemove() {
		return this.isRemove;
	}

	public void setIsRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}

	@SuppressWarnings("unchecked")
	public Collection getRolesOfAdmin() {
		return rolesOfAdmin;
	}

	@SuppressWarnings("unchecked")
	public void setRolesOfAdmin(Collection rolesOfAdmin) {
		this.rolesOfAdmin = rolesOfAdmin;
	}

	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@SuppressWarnings("unchecked")
	public Collection getRoles() {
		return roles;
	}

	@SuppressWarnings("unchecked")
	public void setRoles(Collection roles) {
		this.roles = roles;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
}