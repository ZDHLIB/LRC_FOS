package net.lrc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lrc.javabean.DepartmentBean;
import net.lrc.model.*;

public class DepartmentServlet extends HttpServlet {

	private boolean debug = true;
    
	/**
	 * Constructor of the object.
	 */
	public DepartmentServlet() {
		super();

	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); 	
		
		String language=null;
		language=(String) request.getParameter("language");
		if(language!=null){
			Language.language=language;
		}
		String method = (String) request.getParameter("method");
		if(debug) System.out.println("NewsServlet: get " + method + "request:");
		if (method == null) {
			 PrintWriter out = response.getWriter();
			 out.println("invalid request!");
			 
		} else if (method.equals("add")) {
			adddepartmentinfo(request, response);
		} else if(method.equals("addprincipal")){
			adddepartmentprincipal(request,response);
		}else if(method.equals("delete")){
			deleteDepartment(request,response);
		}else if(method.equals("modify")){
			modifyDepartment(request,response);
		}else if(method.equals("list")){
			listDepartment(request,response);
		}else if(method.equals("show")){
			showDepartment(request,response);
		}else if(method.equals("showMaster")){
			showMaster(request,response);
		}
		
	}

	 

	private void adddepartmentinfo(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		//要给出修改的新闻id号，然后就是新闻的各项其它信息
		String departmentname = request.getParameter("departmentname");
		String departmentinfo = request.getParameter("departmentinfo");
		String departmenttask = request.getParameter("departmenttask");
		DepartmentBean departmentBean = new DepartmentBean();
		departmentBean.adddepartmentinfo(departmentname, departmentinfo, departmenttask);
		//返回列表页
		listDepartment(request,response);
		 
		
	}

	private void adddepartmentprincipal(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int departmentid = Integer.parseInt(request.getParameter("departmentid"));
		 
		String research = request.getParameter("research");
		String studyexperience = request.getParameter("studyexperience");
		String workexperience = request.getParameter("workexperience");
		String name=request.getParameter("name");
		DepartmentBean departmentBean = new DepartmentBean();
		boolean flag = departmentBean.adddepartmentprincipal(departmentid, 0, research, studyexperience, workexperience, name);
		if(flag){
			listDepartment(request,response);
		}else{
			//成功,则返回列表页
			listDepartment(request,response);
		}
	}

	private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		String departmentid = request.getParameter("departmentid");
		int  departmentidInt=0;
	     if(!"".equals(departmentid)&&null!=departmentid)
	    	    departmentidInt=Integer.parseInt(departmentid);
	    DepartmentBean departmentBean = new DepartmentBean();
	    departmentBean.deleteDepartment(departmentidInt);
	    listDepartment(request,response);
	}

	private void modifyDepartment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int departmentid = Integer.parseInt(request.getParameter("departmentid"));
		String departmentname = request.getParameter("departmentname");
		String departmentinfo = request.getParameter("departmentinfo");
		String departmenttask = request.getParameter("departmenttask");		 
		 DepartmentBean departmentBean = new DepartmentBean();
		boolean news = departmentBean.modifyDepartment(departmentid, departmentname, departmentinfo, departmenttask);
		request.setAttribute("news", news);
		listDepartment(request,response);
	}

	private void listDepartment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 		 
		 DepartmentBean departmentBean = new DepartmentBean();
		 List<Department> list=departmentBean.list();
		 request.setAttribute("departments", list);
		 if(Language.language.equals("china")){
			 request.getRequestDispatcher("/admin/department/list.jsp").forward(request, response);
		 }else if(Language.language.equals("tibet")){
			 request.getRequestDispatcher("/admin/Tibet/department/list.jsp").forward(request, response);
		 }
	}
	private void showDepartment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int departmentid = Integer.parseInt(request.getParameter("departmentid"));
		 	 
		 DepartmentBean departmentBean = new DepartmentBean();
		 Department department = departmentBean.show(departmentid);
		request.setAttribute("department", department);
		if(Language.language.equals("china")){
			 request.getRequestDispatcher("/admin/department/department.jsp").forward(request, response);
		}else if(Language.language.equals("tibet")){
			 request.getRequestDispatcher("/admin/Tibet/department/department.jsp").forward(request, response);
		}
	}
	  
	private void showMaster(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String departmentid =  request.getParameter("departmentid");
		 	 
		 DepartmentBean departmentBean = new DepartmentBean();
		 Master master = departmentBean.showMaster(Integer.parseInt(departmentid));
		request.setAttribute("departmentid", departmentid);
		request.setAttribute("master", master);
		if(Language.language.equals("china")){
			 request.getRequestDispatcher("/admin/department/master.jsp").forward(request, response);
		}else if(Language.language.equals("tibet")){
			 request.getRequestDispatcher("/admin/Tibet/department/master.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
