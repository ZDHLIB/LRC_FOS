/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package net.jtaq.servlets.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jtaq.managers.RightsAdmin;
import net.jtaq.servlets.form.RoleFunctionForm;
import net.jtaq.utils.AdminDetails;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/** 
 * MyEclipse Struts
 * Creation date: 06-08-2007
 * 
 * XDoclet definition:
 * @struts.action path="/roleFunction" name="roleFunctionForm" input="/form/roleFunction.jsp" scope="request" validate="true"
 */
public class RoleFunctionAction extends DispatchAction {
public 	ActionForward  add(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
	
	{
		RightsAdmin ra=new RightsAdmin(); 
		RoleFunctionForm rff=(RoleFunctionForm)form;
		
		int   RoleID=Integer.parseInt((String)rff.getRoleID());
		 
		//int  functionID=Integer.parseInt((String)rff.getFunctionID());
		
		//int OperatorID =Integer.parseInt((String)rff.getOperatorID());
		
		//boolean isAdd=ra.addFunctionOfRole(RoleID, functionID, OperatorID);
		
		AdminDetails admin=(AdminDetails)request.getSession().getAttribute("admin");
		int  OperationID=admin.getAdminID();
		
		int functionID =0;
		String functionIDStr=(String)rff.getFunctionID();
		boolean isAdd=false;
		isAdd=ra.deleteAllFunctionOfRole(RoleID);
		
		if(null!=functionIDStr&&!"".equalsIgnoreCase(functionIDStr)){
			String [] functionIDStrArray=functionIDStr.split(",");
			for(int i =0;i<functionIDStrArray.length;i++){
				String OperationIDString=functionIDStrArray[i];
				functionID=Integer.parseInt(OperationIDString);
				//ra.deleteFunctionOfRole(RoleID,functionID);
				 isAdd=ra.addFunctionOfRole(RoleID,functionID, OperationID);
				 if(!isAdd) break;
			}
			
		} 
			
		 
		if(isAdd){
			//return mapping.findForward("add_success");
			// fof.setIsAdd(true);
			  String CONTENT_TYPE = "text/html; charset=utf-8";
			  response.setContentType(CONTENT_TYPE);
			 response.getWriter().write("<script>alert('操作成功！');history.go(-1);</script>");
			return null;//list(mapping,form,request,response);
			//return null;
		}
		 
		return  mapping.findForward("add_error");
	}
	
	 
 
	
	 
 

public 	ActionForward  remove(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
{
	
		RightsAdmin ra=new RightsAdmin(); 
		RoleFunctionForm rff=(RoleFunctionForm)form;
		
		int   roleID=Integer.parseInt((String)rff.getRoleID());
		 
		int  functionID=Integer.parseInt((String)rff.getFunctionID());
		
		 
		
		boolean isdel=ra.deleteFunctionOfRole(roleID, functionID);
		
		if(isdel){
			rff.setIsRemove(true); 
			//return mapping.findForward("remove_success");
		    return list(mapping,form,request,response);
		}
		 
		return  mapping.findForward("remove_error");
	
}

public 	ActionForward  list(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
{
	   Collection functions;
	   Collection functionsOfRole;
	    RightsAdmin ra=new RightsAdmin(); 	     	
	  
	     RoleFunctionForm rff=(RoleFunctionForm)form;
	     int roleID=Integer.parseInt((String)rff.getRoleID());
	 
		 try{	
			 functions=ra.getAllFunctions();
		     rff.setFunctions(functions);
		    functionsOfRole=ra.getFunctionsOfRole(roleID);
		  //fof.setOperationsOfFunction(operationsOfFunction);
		 request.setAttribute("functionsOfRole", functionsOfRole);
		}catch(Exception e){
		return  mapping.findForward("list_error");
		}
		return  mapping.findForward("list_success");
	
}
}