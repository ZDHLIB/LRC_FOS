package textBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFileServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteFileServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = "";
		String filePath = request.getParameter("filePath");
		String prjPath = request.getSession().getServletContext().getRealPath("/");
		PrintWriter out = response.getWriter();
		//String fileName = new String(filePath.substring(filePath.lastIndexOf("\\"), filePath.length()).getBytes(), "utf-16");
		path = (prjPath+filePath).replace("\\", "\\\\");
		//File file = new File(URLDecoder.decode(path,"utf-8"));
		File file = new File(path);
	
		System.out.println("prjPath+filePath::"+path);
		//file.delete();
		
		if(file.exists()){
			file.delete();
			out.print("success");
		} else {
			System.out.println("false");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
