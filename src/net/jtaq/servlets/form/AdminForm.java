package net.jtaq.servlets.form;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AdminForm extends ActionForm 
{
	private static final long serialVersionUID = 8569476930630040714L;

	@SuppressWarnings("unchecked")
	private Collection admins;
	private Date updateTime;
	private String password;
	private String newPassword;
	private String authcode;
	private Date createTime;
	private String operatorID;
	private String adminName;
	private String adminID;

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
	{
		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
	}

	@SuppressWarnings("unchecked")
	public Collection getAdmins() 
	{
		return admins;
	}

	@SuppressWarnings("unchecked")
	public void setAdmins(Collection admins) 
	{
		this.admins = admins;
	}

	public Date getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getOperatorID() 
	{
		return operatorID;
	}

	public void setOperatorID(String operatorID) 
	{
		this.operatorID = operatorID;
	}

	public String getAdminName() 
	{
		return adminName;
	}

	public void setAdminName(String adminName) 
	{
		this.adminName = adminName;
	}

	public String getAdminID()
	{
		return adminID;
	}

	public void setAdminID(String adminID) 
	{
		this.adminID = adminID;
	}

	public String getNewPassword() 
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword) 
	{
		this.newPassword = newPassword;
	}

	public String getAuthcode() 
	{
		return authcode;
	}

	public void setAuthcode(String authcode) 
	{
		this.authcode = authcode;
	}
}