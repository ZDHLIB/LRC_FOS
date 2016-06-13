package net.jtaq.servlets.action;


import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jtaq.managers.RightsAdmin;
import net.jtaq.servlets.form.OperationForm;
//import net.jtaq.utils.OperationDetails;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class OperationAction extends DispatchAction {
	 
	 
	
	public 	ActionForward  add(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
	   // DynaActionForm df=(DynaActionForm)form;
		//String  OperationName=(String)df.get("Name");
		//String Resume=(String)df.get("Resume");
		//int OperatorID =Integer.parseInt((String)df.get("OperatorID"));
		// OperatorID=101;
		RightsAdmin ra=new RightsAdmin(); 
		OperationForm of=(OperationForm)form;
		
		String  OperationName=(String)of.getOperationName();
		String Resume=(String)of.getResume();
		int OperatorID =Integer.parseInt((String)of.getOperatorID());
		boolean isAdd=ra.addOperation(OperationName, Resume, OperatorID);
		
		if(isAdd){
			return list(mapping,form,request,response);
		 
		}
	 
		return  mapping.findForward("add_error");
	}
	
	public 	ActionForward  update(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		
		//DynaActionForm df=new DynaActionForm();
		//String  OperationName=(String)df.get("Name");
		//String Resume=(String)df.get("Resume");
		//int OperatorID =Integer.parseInt((String)df.get("OperatorID"));
		//int OperationID =Integer.parseInt((String)df.get("ID"));
		//OperationDetails operationDetails;
		RightsAdmin ra=new RightsAdmin(); 
			OperationForm of=(OperationForm)form;
		 
		int OperationID =Integer.parseInt((String)of.getOperationID());
		request.setAttribute("operationDetails",ra.getOperationDetails(OperationID) );
		//operationDetails=ra.getOperationDetails(OperationID);
		//of.setOperationDetails(operationDetails);
		return mapping.findForward("operation_update");
		
			
	}

public 	ActionForward  save(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
{
	//DynaActionForm df=new DynaActionForm();
	//String  OperationName=(String)df.get("Name");
	//String Resume=(String)df.get("Resume");
	//int OperatorID =Integer.parseInt((String)df.get("OperatorID"));
	//int OperationID =Integer.parseInt((String)df.get("ID"));
	OperationForm of=(OperationForm)form;
	RightsAdmin ra=new RightsAdmin(); 
	String  OperationName=(String)of.getOperationName();
	String Resume=(String)of.getResume();
	int OperationID =Integer.parseInt((String)of.getOperationID());
	int OperatorID =Integer.parseInt((String)of.getOperatorID());
	boolean isUpdate=ra.updateOperation(OperationID,OperationName, Resume, OperatorID);
	if(isUpdate){
	
		  return list(mapping,form,request,response);
	}
	
	else {
		return  mapping.findForward("update_error");
	}
	
	
	 
	
}

public 	ActionForward  remove(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
{
	
	   //DynaActionForm df=new DynaActionForm();
		 
		 
		//int OperationID =Integer.parseInt((String)df.get("ID"));
	RightsAdmin ra=new RightsAdmin(); 
	OperationForm of=(OperationForm)form;
	int OperationID =Integer.parseInt((String)of.getOperationID());
	
	  boolean isAdd=ra.deleteOperation(OperationID);
		if(isAdd){
		
			return list(mapping,form,request,response);
		}
		return   mapping.findForward("remove_error");
	 
	  
	
}

public 	ActionForward  list(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
{
	   Collection operations;
	  	     	
	    OperationForm of=(OperationForm)form;
	    RightsAdmin ra=new RightsAdmin(); 
		 
	  try{	
		    operations=ra.getAllOperations();  
			of.setOperations(operations);
		}catch(Exception e){
		return new ActionForward("list_error");
		}
		return  mapping.findForward("list_success");
	
}

}
