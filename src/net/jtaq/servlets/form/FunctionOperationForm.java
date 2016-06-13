package net.jtaq.servlets.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FunctionOperationForm extends ActionForm {

	private static final long serialVersionUID = -3075731258816946403L;

	private String functionID;
	private String functionName;
	private String operationID;
	private String operationName;
	private String operatorID;
	private String ID;
	private boolean isAdd;
	private boolean isRemove;
	@SuppressWarnings("unchecked")
	private Collection operations;
	@SuppressWarnings("unchecked")
	private Collection operationsOfFunction;

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
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
	public Collection getOperations() {
		return this.operations;
	}

	@SuppressWarnings("unchecked")
	public void setOperations(Collection operations) {
		this.operations = operations;
	}

	@SuppressWarnings("unchecked")
	public Collection getOperationsOfFunction() {
		return this.operationsOfFunction;
	}

	@SuppressWarnings("unchecked")
	public void setOperationsOfFunction(Collection operationsOfFunction) {
		this.operationsOfFunction = operationsOfFunction;
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

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
}