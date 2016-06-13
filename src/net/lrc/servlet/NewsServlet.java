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
import net.lrc.model.Content;
import net.lrc.model.Language;
import net.lrc.util.NewsDetails;
import net.lrc.model.*;

public class NewsServlet extends HttpServlet {

	private boolean debug = true;
    
	/**
	 * Constructor of the object.
	 */
	public NewsServlet() {
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
		} else if (method.equals("newslist")) {
			if (debug)
				System.out.println("NewsServlet: get newslist request:");
			Show(request, response);
		} else if (method.equals("new")) {
			SingleNew(request, response);
		} else if(method.equals("addnews")){
			AddNew(request,response);
		}else if(method.equals("deletenews")){
			DeleteNews(request,response);
		}else if(method.equals("updatenews")){
			UpdateNews(request,response);
		}		else if(method.equals("contentlist")){
			contentlist(request,response);
		}else if(method.equals("updateStatus")){
			updateStatus(request,response);
		}else if(method.equals("list")){
			list(request,response);
		}
	}

	private void contentlist(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		//要给出修改的新闻id号，然后就是新闻的各项其它信息
		 
		String part = request.getParameter("part").trim();
		
		ContentBean newsbean   = new ContentBean();
		List parts=newsbean.listbyPart(part);
		
		Content vo = null;
		StringBuffer yydmStr = new StringBuffer();
		yydmStr.append("<?xml version=\"1.0\"?>");
		yydmStr.append("<Doc>");
		for (int i = 0; i < parts.size(); i++) {
			vo = (Content) parts.get(i);
			yydmStr.append("<Fields><Field Name=\"DM\">" +vo.getId()
					+ "</Field><Field Name=\"MC\">" + vo.getTitle()
					+ "</Field></Fields>");
		}
		yydmStr.append("</Doc>");
		String CONTENT_TYPE = "text/xml; charset=utf-8";

		// 定义流输出的头规范
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println(yydmStr.toString());
		//System.out.print(yydmStr.toString());
		out.close();
		 
	}

	
	private void list(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		//要给出修改的新闻id号，然后就是新闻的各项其它信息
		 	 
		NewsBean newsbean   = new NewsBean();
		String offset = request.getParameter("offset");
		String filepath = request.getRequestURI();
		newsbean.setResult(offset, filepath);
		request.setAttribute("newsbean", newsbean);
		//返回列表页
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/admin/news/list.jsp").forward(request, response);
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/admin/Tibet/news/list.jsp").forward(request, response);
		}		
	}
	private void updateStatus(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		//要给出修改的新闻id号，然后就是新闻的各项其它信息
		int id = Integer.parseInt(request.getParameter("id"));
		String flag = request.getParameter("flag");
		 
		NewsBean newsbean   = new NewsBean();
		newsbean.updateStatus(id, flag);
		//返回列表页
		//request.getRequestDispatcher("/admin/news/list.jsp").forward(request, response);
		 String CONTENT_TYPE = "text/html; charset=utf-8";
			// 定义流输出的头规范
			response.setContentType(CONTENT_TYPE);
		 response.getWriter().write("<script>alert('更新成功！');</script>");
		list(request,response);
	}
	
	private void UpdateNews(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		//要给出修改的新闻id号，然后就是新闻的各项其它信息
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("news_title");
		String content = request.getParameter("content");
		net.jtaq.utils.AdminDetails admin=(net.jtaq.utils.AdminDetails)request.getSession().getAttribute("admin");
		int xgryid=admin.getAdminID();
		int effectivedays = Integer.valueOf((String)request.getParameter("effectivedays"));
		String type = request.getParameter("type");
		String kind = request.getParameter("kind");
	  NewsBean newsbean   = new NewsBean();
		newsbean.update( id, title, xgryid,  content,  effectivedays, type, kind);
		//返回列表页
		 String CONTENT_TYPE = "text/html; charset=utf-8";
			// 定义流输出的头规范
			response.setContentType(CONTENT_TYPE);
		 response.getWriter().write("<script>alert('更新成功！');</script>");
		list(request,response);
	}

	private void DeleteNews(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 String CONTENT_TYPE = "text/html; charset=utf-8";
		 response.setContentType(CONTENT_TYPE);
		int id = Integer.parseInt(request.getParameter("id"));
		NewsBean newsbean   = new NewsBean();
		boolean flag = newsbean.delete(id);
		if(flag){
			
			 response.getWriter().write("<script>alert('删除成功！');</script>");
			list(request,response);
		}else{
			response.getWriter().write("<script>alert('删除信息失败！');history.go(-1);</script>");
		}
	}

	private void AddNew(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		String title = request.getParameter("news_title");
		String content = request.getParameter("content");
	     
		String CONTENT_TYPE = "text/html; charset=utf-8";
		 response.setContentType(CONTENT_TYPE);
	     net.jtaq.utils.AdminDetails admin=(net.jtaq.utils.AdminDetails)request.getSession().getAttribute("admin");
			int xgryid=admin.getAdminID();
			 int lrryid=admin.getAdminID();			 
	     int effectivedays=Integer.parseInt(request.getParameter("effectivedays"));
	     String type=request.getParameter("type");
	    String kind=request.getParameter("kind");
	    String flag="0";    	
		NewsBean newsbean   = new NewsBean();
		newsbean.add(  title,  lrryid,  xgryid,  content,  effectivedays,  type,  kind,  flag);
		//返回列表页
		//request.getRequestDispatcher("/admin/news/list.jsp").forward(request, response);
		 response.getWriter().write("<script>alert('添加成功！');</script>");
		list(request,response);
	}

	private void SingleNew(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ʾ��������
		int id = Integer.parseInt(request.getParameter("id"));
		NewsBean newsbean   = new NewsBean();
		newsbean.updateCount(id);
		NewsDetails news = newsbean.show(id);
		request.setAttribute("news", news);
		String partid = request.getParameter("partid");
		String contentid = request.getParameter("contentid");
		request.setAttribute("partid", partid);
		request.setAttribute("contentid", contentid);
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/news/news.jsp").forward(request, response);
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/Tibet/news/news.jsp").forward(request, response);
		}		
	}

	private void Show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		 NewsBean newsbean = new NewsBean();
		 List<NewsDetails> list = newsbean.list();
		System.out
				.println("NewsServlet: now there is " + list.size() + " news");
		request.setAttribute("list", list);
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/news/index.jsp").forward(request,response);
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/Tibet/news/index.jsp").forward(request,response);
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
