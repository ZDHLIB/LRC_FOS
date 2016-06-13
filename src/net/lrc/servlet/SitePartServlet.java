package net.lrc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lrc.javabean.SitePartBean;
import net.lrc.model.SitePart;
import net.lrc.model.*;

public class SitePartServlet extends HttpServlet {

	private boolean debug = true;
    
	/**
	 * Constructor of the object.
	 */
	public SitePartServlet() {
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
		if(debug) System.out.println("ContentServlet: get " + method + "request:");
		if (method == null) {
			PrintWriter out = response.getWriter();
			out.println("invalid request!");
		} else if (method.equals("contentslist")) {
			if (debug)
				System.out.println("ContentServlet: get contentslist request:");
			Show(request, response);
		} else if (method.equals("add")) {
			add(request, response);
		} else if(method.equals("modify")){
			modify(request,response);
		}else if(method.equals("delete")){
			delete(request,response);
		}else if(method.equals("show")){
			Show(request,response);
		}		
		else if(method.equals("list")){
			list(request,response);
		}
	}

	 

	private void add(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		 
		String title = request.getParameter("title");
		String dm = request.getParameter("dm");		 
		 
		String flag = request.getParameter("flag");
		 
		SitePartBean sitePartBean   = new SitePartBean();
		sitePartBean.add(title, dm, flag);
		 
		list(request,response);
	}

	private void list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		SitePartBean sitePartBean   = new SitePartBean();
		List  list=sitePartBean.list();
		request.setAttribute("parts", list);
		 if(Language.language.equals("china")){
			 request.getRequestDispatcher("/admin/parts/list.jsp").forward(request, response);
		 }else if(Language.language.equals("tibet")){
			 request.getRequestDispatcher("/admin/Tibet/parts/list.jsp").forward(request, response);
		 }		 
	}
	private void modify(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String dm = request.getParameter("dm");			 
		String flag = request.getParameter("flag");
		SitePartBean sitePartBean   = new SitePartBean();
		sitePartBean.modify(id, title, dm, flag);
		list(request,response);
	}

	private void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		SitePartBean sitePartBean   = new SitePartBean();
		sitePartBean.delete(id);
		list(request,response);
	}

	private void Show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		int id = Integer.parseInt(request.getParameter("id"));
		SitePartBean sitePartBean   = new SitePartBean();
		SitePart part=sitePartBean.show(id);		 
		request.setAttribute("part", part);
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/admin/parts/part.jsp").forward(request, response);
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/admin/Tibet/parts/part.jsp").forward(request, response);
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
