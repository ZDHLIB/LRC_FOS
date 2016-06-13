package net.lrc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.lrc.model.*;

import net.lrc.javabean.CommonBean;

public class CommonServlet extends HttpServlet 
{
	private static final long serialVersionUID = -3354673810592837832L;
	private boolean debug = true;

	public CommonServlet() 
	{
		super();
	}
	
	public void destroy() 
	{
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String method = (String)request.getParameter("method");
		
		if(debug)
		{
			System.out.println("ContentServlet: get " + method + "request:");
		}
		if(method == null) 
		{
			PrintWriter out = response.getWriter();
			out.println("invalid request!");
		} 
		else if(method.equals("getAdminNamebyID")) 
		{
			if(debug)
			{
				System.out.println("ContentServlet: get contentslist request:");
			}
			
			getAdminNamebyID(request, response);
		} 
		else if(method.equals("getComtentByCoutentAndPart")) 
		{
			if(debug)
			{
				System.out.println("ContentServlet: get contentslist request:");
			}
			
			getComtentByCoutentAndPart(request, response);
		}
		else if(method.equals("getComtentListByCoutentAndPart")) 
		{
			if(debug)
			{
				System.out.println("ContentServlet: get contentslist request:");
			}
			
			getComtentListByCoutentAndPart(request, response);
		}
	}

	private void getComtentByCoutentAndPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String partid = request.getParameter("partid");
		String contentid = request.getParameter("contentid");
		String CONTENT_TYPE = "text/xml; charset=utf-8";
		CommonBean bean = new CommonBean();
		String yydmStr = bean.getComtentByCoutentAndPart(partid, contentid);
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = null;
		
		try 
		{
			out = response.getWriter();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}

		out.println(yydmStr);
		out.close();
	}

	@SuppressWarnings("unchecked")
	private void getComtentListByCoutentAndPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String partid = request.getParameter("partid");
		String contentid = request.getParameter("contentid");
		CommonBean bean = new CommonBean();
		List newsList = bean.getComtentListByCoutentAndPart(partid, contentid);
		request.setAttribute("partid", partid);
		request.setAttribute("contentid", contentid);
		request.setAttribute("newsList", newsList);
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/research/index.jsp").forward(request, response);
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/Tibet/research/index.jsp").forward(request, response);
		}
		
	}

	private void getAdminNamebyID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
