package net.jtaq.servlets.form;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UserRoleForm extends ActionForm {
	private static final long serialVersionUID = -4603829726658884650L;

	private String userName;
	private String userID;
	private String roleName;
	@SuppressWarnings("unchecked")
	private Collection rolesOfUser;
	private Boolean isRemove = false;
	private Boolean isAdd = false;
	private String operatorID;
	@SuppressWarnings("unchecked")
	private Collection roles;
	private String roleID;
	private Integer view;
	private Integer down;
	private boolean isupdate;

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.isAdd = false;
		this.isupdate = false;
		this.isRemove = false;
		this.operatorID = null;
		this.roleID = null;
		this.roleName = null;
		this.roles = null;
		this.rolesOfUser = null;
		this.userID = null;
		this.userName = null;
		this.view = null;
		this.down = null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public Integer getUserview() {
		return view;
	}

	public void setUserview(Integer view) {
		this.view = view;
	}
	
	public Integer getUserdown() {
		return down;
	}

	public void setUserdown( Integer down ) {
		this.down = down;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@SuppressWarnings("unchecked")
	public Collection getRolesOfUser() {
		return rolesOfUser;
	}

	@SuppressWarnings("unchecked")
	public void setRolesOfUser(Collection rolesOfUser) {
		this.rolesOfUser = rolesOfUser;
	}
	
	public Boolean getIsupdate() {
		return isupdate;
	}

	public void setIsupdate(Boolean isupdate) {
		this.isupdate = isupdate;
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
}