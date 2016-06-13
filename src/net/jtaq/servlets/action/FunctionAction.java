package net.jtaq.servlets.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jtaq.managers.RightsAdmin;
import net.jtaq.servlets.form.FunctionForm;
 

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
  
import org.apache.struts.actions.DispatchAction;

public class FunctionAction extends DispatchAction {
 
	
	public 	ActionForward  add(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		RightsAdmin ra=new RightsAdmin(); 
		FunctionForm ff=(FunctionForm)form;
		
		String  FunctionName=(String)ff.getFunctionName();
		String Resume=(String)ff.getResume();
		
		int OperatorID =Integer.parseInt((String)ff.getOperatorID());
		
		boolean isAdd=ra.addFunction(FunctionName, Resume, OperatorID);
		
		if(isAdd){
		//	return mapping.findForward("add_success");
			return list(mapping,form,request,response);
		}
		 
		return  mapping.findForward("add_error");
	}
	
	public 	ActionForward  update(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		
		RightsAdmin ra=new RightsAdmin(); 
			FunctionForm ff=(FunctionForm)form;
		 
		int FunctionID =Integer.parseInt((String)ff.getFunctionID());
		request.setAttribute("functionDetails",ra.getFunctionDetails(FunctionID) );
		 
		return mapping.findForward("function_update");
		
			
	}

public 	ActionForward  save(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
{
	RightsAdmin ra=new RightsAdmin(); 
	FunctionForm ff=(FunctionForm)form;
	
	String  FunctionName=(String)ff.getFunctionName();
	String Resume=(String)ff.getResume();
	int FunctionID =Integer.parseInt((String)ff.getFunctionID());
	
	int OperatorID =Integer.parseInt((String)ff.getOperatorID());
	
	boolean isUpdate=ra.updateFunction(FunctionID,FunctionName, Resume, OperatorID);
	if(isUpdate){
		return list(mapping,form,request,response);
	//return  mapping.findForward("update_success");
	}
	
	else {
		return  mapping.findForward("update_error");
	}
	
	
	 
	
}

public 	ActionForward  remove(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
{
	 
	RightsAdmin ra=new RightsAdmin(); 
	FunctionForm ff=(FunctionForm)form;
	int FunctionID =Integer.parseInt((String)ff.getFunctionID());
	
	  boolean isAdd=ra.deleteFunction(FunctionID);
		if(isAdd){
			return list(mapping,form,request,response);
			//return   mapping.findForward("remove_success");
		}
		return   mapping.findForward("remove_error");
	 
	
}

public 	ActionForward  list(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
{
	   Collection functions;
	   RightsAdmin ra=new RightsAdmin();     	
	    FunctionForm ff=(FunctionForm)form;
	    
		 
	  try{	
		    functions=ra.getAllFunctions();  
			ff.setFunctions(functions);
		}catch(Exception e)
		{
		return  mapping.findForward("list_error");
		}
		return  mapping.findForward("list_success");
	
}
}
