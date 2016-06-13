package net.jtaq.servlets.form;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FunctionForm extends ActionForm 
{
	private static final long serialVersionUID = 3234936674667348118L;
	
	private String functionID;
	private String resume;
	private String operatorID;
	private String functionName;
	@SuppressWarnings("unchecked")
	private Collection functions;
	private Date updateTime;
	private Date createTime;

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
	{
		return null;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		this.functionID = null;
		this.functionName = null;
		this.operatorID = null;
		this.resume = null;
		this.functions = null;
	}

	public String getFunctionID() 
	{
		return functionID;
	}

	public void setFunctionID(String functionID) 
	{
		this.functionID = functionID;
	}

	public String getResume() 
	{
		return resume;
	}

	public void setResume(String resume) 
	{
		this.resume = resume;
	}

	public String getOperatorID() 
	{
		return operatorID;
	}

	public void setOperatorID(String operatorID) 
	{
		this.operatorID = operatorID;
	}

	public String getFunctionName() 
	{
		return functionName;
	}

	public void setFunctionName(String functionName) 
	{
		this.functionName = functionName;
	}
	@SuppressWarnings("unchecked")
	public Collection getFunctions() 
	{
		return functions;
	}

	@SuppressWarnings("unchecked")
	public void setFunctions(Collection functions) 
	{
		this.functions = functions;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}
	
	public Date getCreateTime() 
	{
		return createTime;
	}
	
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
}