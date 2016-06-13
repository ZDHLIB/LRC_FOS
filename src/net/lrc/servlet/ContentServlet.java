package net.lrc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lrc.javabean.ContentBean;
import net.lrc.javabean.NewsBean;
import net.lrc.javabean.SitePartBean;
import net.lrc.model.Content;
import net.lrc.model.Language;
import net.lrc.util.NewsDetails;
import net.lrc.model.*;

public class ContentServlet extends HttpServlet {

	private boolean debug = true;
    
	/**
	 * Constructor of the object.
	 */
	public ContentServlet() {
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
		else if(method.equals("listbypart")){
			listbypart(request,response);
		}
		else if(method.equals("list")){
			list(request,response);
		}
	}

	 

	private void add(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		//要给出修改的新闻id号，然后就是新闻的各项其它信息
		String title = request.getParameter("title");
		String url = request.getParameter("url");		 
		int xh = Integer.valueOf((String)request.getParameter("xh"));
		String flag = request.getParameter("flag");
		String part = request.getParameter("part");
		ContentBean contentbean   = new ContentBean();
		contentbean.add(xh, title, url, part, flag);
		 
		list(request,response);
	}

	private void list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ContentBean contentbean   = new ContentBean();
		List  list=contentbean.list();
		request.setAttribute("contents", list);
		SitePartBean sitePartBean   = new SitePartBean();
		List  parts=sitePartBean.list();
		request.setAttribute("parts", parts);
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/admin/contents/list.jsp").forward(request, response);			
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/admin/Tibet/contents/list.jsp").forward(request, response);			
		}	 
	}
	private void listbypart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String part = request.getParameter("part");
		ContentBean contentbean   = new ContentBean();
		List  list=contentbean.listbyPart(part);
		request.setAttribute("contents", list);
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/admin/contents/list.jsp").forward(request, response);			
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/admin/Tibet/contents/list.jsp").forward(request, response);			
		} 
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String url = request.getParameter("url");		 
		int xh = Integer.valueOf((String)request.getParameter("xh"));
		String flag = request.getParameter("flag");
		String part = request.getParameter("part");
		ContentBean contentbean   = new ContentBean();
		contentbean.modify(id,xh, title, url, part, flag);
		list(request,response);
	}

	private void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ContentBean contentbean   = new ContentBean();
		contentbean.delete(id);
		list(request,response);
	}

	private void Show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		int id = Integer.parseInt(request.getParameter("id"));
		ContentBean contentbean   = new ContentBean();
		Content content=contentbean.show(id);		 
		request.setAttribute("content", content);
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/admin/contents/content.jsp").forward(request, response);			
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/admin/Tibet/contents/content.jsp").forward(request, response);			
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
