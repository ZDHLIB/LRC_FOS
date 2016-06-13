package net.jtaq.servlets.action;

import java.util.Collection;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import  net.jtaq.managers.RightsAdmin;
import net.jtaq.servlets.form.FunctionForm;
import net.jtaq.servlets.form.RoleForm;
import net.jtaq.utils.RoleDetails;
public class RoleAction extends DispatchAction {
      
 
	
	public 	ActionForward  add(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		RightsAdmin ra=new RightsAdmin(); 
		RoleForm rf=(RoleForm)form;
		
		String  RoleName=(String)rf.getRoleName();
		String Resume=(String)rf.getResume();
		int OperatorID =Integer.parseInt((String)rf.getOperatorID());
		boolean isAdd=ra.addRole(RoleName, Resume, OperatorID);
		
		if(isAdd){
			//return mapping.findForward("add_success");
			return list(mapping,form,request,response);
		}
		 
		return  mapping.findForward("add_error");
	}
	
	public 	ActionForward  update(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		
		RightsAdmin ra=new RightsAdmin(); 
			RoleForm rf=(RoleForm)form;
		 
		int RoleID =Integer.parseInt((String)rf.getRoleID().trim());
		request.setAttribute("roleDetails",ra.getRoleDetails(RoleID) );
		//RoleDetails roleDetails=ra.getRoleDetails(RoleID);
		//rf.setRoleDetails(roleDetails);
		
		return mapping.findForward("role_update");
		
			
	}

public 	ActionForward  save(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
{
	RightsAdmin ra=new RightsAdmin(); 
	RoleForm rf=(RoleForm)form;
	
	String  RoleName=(String)rf.getRoleName();
	String Resume=(String)rf.getResume();
	int RoleID =Integer.parseInt((String)rf.getRoleID());
	int OperatorID =Integer.parseInt((String)rf.getOperatorID());
	boolean isUpdate=ra.updateRole(RoleID,RoleName, Resume, OperatorID);
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
		
	RoleForm rf=(RoleForm)form;
	int RoleID =Integer.parseInt((String)rf.getRoleID());
	
	  boolean isAdd=ra.deleteRole(RoleID);
		if(isAdd){
			return list(mapping,form,request,response);
			//return   mapping.findForward("remove_success");
		}
		return   mapping.findForward("remove_error");
	 
	
}

public 	ActionForward  list(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)throws Exception
{
	   Collection roles;
	   RightsAdmin ra=new RightsAdmin(); 	     	
	    RoleForm rf=(RoleForm)form;
	    
		 
	  try{	
		    roles=ra.getAllRoles();  
			rf.setRoles(roles);
		}catch(Exception e){
		return new ActionForward("list_error");
		}
		return  mapping.findForward("list_success");
	
}

}
