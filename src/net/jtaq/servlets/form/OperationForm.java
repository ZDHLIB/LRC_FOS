package net.jtaq.servlets.form;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OperationForm extends ActionForm {
	private static final long serialVersionUID = -5115505864533266081L;

	private String operationID;
	private String operationName;
	private String operatorID;
	private String resume;
	@SuppressWarnings("unchecked")
	private Collection operations;
	private Date createTime;
	private Date updateTime;

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.operationID = null;
		this.operationName = null;
		this.operatorID = null;
		this.operations = null;
	}

	public String getOperationID() {
		return operationID;
	}

	public void setOperationID(String operationID) {
		this.operationID = operationID;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@SuppressWarnings("unchecked")
	public Collection getOperations() {
		return operations;
	}

	@SuppressWarnings("unchecked")
	public void setOperations(Collection operations) {
		this.operations = operations;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}