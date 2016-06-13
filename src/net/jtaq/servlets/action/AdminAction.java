package net.jtaq.servlets.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.jtaq.managers.AdminAdmin;
import net.jtaq.managers.RightsAdmin;
import net.jtaq.servlets.LoginAuthCodeServlet;
import net.jtaq.servlets.form.AdminForm;
import net.jtaq.utils.AdminDetails;
import net.jtaq.utils.FunctionDetails;
import net.jtaq.utils.OperationDetails;
import net.jtaq.utils.RoleDetails;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AdminAction extends DispatchAction {

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {

		AdminAdmin aa = new AdminAdmin();

		RightsAdmin ra = new RightsAdmin();
		AdminForm af = (AdminForm) form;
		String authcode = af.getAuthcode();
		System.out.print("authcode=====" + authcode);
		boolean is = validateAuthorcode(request, authcode);
		System.out.print("is=====" + is);
		if (!is) {
			String CONTENT_TYPE = "text/html; charset=utf-8";
			// 定义流输出的头规范
			response.setContentType(CONTENT_TYPE);
			response.getWriter().write(
					"<script>alert('验证码错误！');history.go(-1);</script>");
			return null;
		}
		request.getSession().removeAttribute("admin");
		AdminDetails ad = null;
		try {
			ad = aa.getAdminDetails(af.getAdminName(), af.getPassword());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Hashtable authList=new Hashtable ();
		// HashMap<String, String> authList=new HashMap<String, String>();
		ArrayList<Properties> authList = new ArrayList<Properties>();
		// java.util.Properties authList=new java.util.Properties()//
		// authList=new authList
		if (ad != null) {
			Collection roles = aa.getRolesOfAdmin(ad.getAdminID());
			int OperatorID = ad.getOperatorID();
			try {
				Iterator i = roles.iterator();
				while (i.hasNext()) {
					RoleDetails rd = (RoleDetails) i.next();
					Collection functionsofRole = ra.getFunctionsOfRole(rd
							.getRoleID());

					Iterator j = functionsofRole.iterator();
					while (j.hasNext()) {

						FunctionDetails fd = (FunctionDetails) j.next();
						Collection operationsOfFunction = ra.getOperationsOfFunction(fd.getFunctionID());
						Iterator k = operationsOfFunction.iterator();
						while (k.hasNext()) {
							OperationDetails od = (OperationDetails) k.next();
							Properties p = new Properties();
							p.put(fd.getResume(), od.getOperationName());
							authList.add(p);
							// authList.put(fd.getResume(),od.getOperationName());
						}
					}
				}

			} catch (Exception e) {
			}
			ad.setAuthList(authList);
			request.getSession().setAttribute("admin", ad);
			
			if( OperatorID == 11 ) {
				return mapping.findForward("admin_login_success");
			} else if( OperatorID == 1 ) {
				return mapping.findForward("admin_login_success1");
			} else if( OperatorID == 2 ) {
				return mapping.findForward("admin_login_success2");
			} else {
				return mapping.findForward("admin_login_success3");
			}
		}

		else {

			return mapping.findForward("admin_login_error");

		}
	}

	/**
	 * to add a administrator
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdminAdmin aa = new AdminAdmin();
		AdminForm af = (AdminForm) form;

		String adminName = (String) af.getAdminName();
		String password = (String) af.getPassword();
		int OperatorID = Integer.parseInt((String) af.getOperatorID());

		boolean isAdd = aa.addAdminInfo(adminName, password, OperatorID);

		if (isAdd) {
			return mapping.findForward("add_success");
		}

		return mapping.findForward("add_error");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdminAdmin aa = new AdminAdmin();
		AdminForm af = (AdminForm) form;

		int AdminID = Integer.parseInt((String) af.getAdminID());

		request.setAttribute("adminDetails", aa.getAdminDetails(AdminID));

		return mapping.findForward("admin_update");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdminAdmin aa = new AdminAdmin();
		AdminForm af = (AdminForm) form;
		
		String adminName = (String) af.getAdminName();
		String password = (String) af.getPassword();
		int OperatorID = Integer.parseInt((String) af.getOperatorID());
		int AdminID = Integer.parseInt((String) af.getAdminID());

		boolean isUpdate = aa.updateAdminInfo(AdminID, adminName, password,
				OperatorID);
		if (isUpdate) {

			return mapping.findForward("update_success");
		}

		else {
			return mapping.findForward("update_error");
		}

	}

	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdminAdmin aa = new AdminAdmin();
		AdminForm af = (AdminForm) form;

		int AdminID = Integer.parseInt((String) af.getAdminID());

		boolean isAdd = aa.deleteAdmin(AdminID);

		if (isAdd) {

			return mapping.findForward("remove_success");
		}
		
		return mapping.findForward("remove_error");
	}

	/**
	 * to list all the current administrators
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Collection admins;
		AdminAdmin aa = new AdminAdmin();
		AdminForm af = (AdminForm) form;

		try {
			admins = aa.getAdmins();
			af.setAdmins(admins);
		} catch (Exception e) {
			return mapping.findForward("list_success");
		}
		return mapping.findForward("list_success");
	}

	public ActionForward changePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AdminAdmin aa = new AdminAdmin();
		AdminForm af = (AdminForm) form;

		try {
			String password = af.getPassword();
			String newPassword = af.getNewPassword();
			AdminDetails ad = (AdminDetails) request.getSession().getAttribute(
					"admin");
			int AdminID = ad.getAdminID();
			boolean isChanged = aa.ChangeAdminPassword(AdminID, password,
					newPassword);
			if (isChanged)
				return mapping.findForward("change_success");
		} catch (Exception e) {
			return mapping.findForward("change_error");
		}
		return mapping.findForward("change_error");

	}

	// 验证校验码
	private boolean validateAuthorcode(HttpServletRequest request,
			String password2) {

		boolean isSuccess = false;
		HttpSession session = request.getSession(false);
		if (session != null
				&& session.getAttribute(LoginAuthCodeServlet.AUTHORCODE) != null) {

			String authorcode = (String) session
					.getAttribute(LoginAuthCodeServlet.AUTHORCODE);
			System.out.print("LoginAuthCodeServlet.AUTHORCODE====="
					+ authorcode);
			return authorcode.equals(password2);
		}

		return isSuccess;
	}
}