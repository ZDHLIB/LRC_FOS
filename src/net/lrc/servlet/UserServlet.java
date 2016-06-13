package net.lrc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.jtaq.utils.MD5EncryptUtils;
import net.lrc.db.Mysql;
import net.lrc.javabean.UserBean;
import net.lrc.model.*;

public class UserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// System.out.print(request.getCharacterEncoding());
		// System.out.println("do this");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String method = (String) request.getParameter("method");
		if (method == null) {
			PrintWriter out = response.getWriter();
			out.println("invalid request!");
		} else if (method.equals("login")) {
			// System.out.println("ddd");
			Login(request, response);
		} else if (method.equals("logout")) {
			Logout(request, response);
		} else if (method.equals("register")) {
			Register(request, response);
		} else if (method.equals("modifypwd")) {
			Modifypwd(request, response);
		} else if (method.equals("update")) {
			update(request, response);
		} else if (method.equals("checkUsername")) {
			checkUsername(request, response);
		} else if (method.equals("getUser")) {
			getUser(request, response);
		} else if (method.equals("delete")) {
			delete(request, response);
		} else if (method.equals("updateStatus")) {
			updateStatus(request, response);
		} else if (method.equals("list")) {
			list(request, response);
		} else if (method.equals("edit")) {
			edit(request, response);
		} else if (method.equals("add")) {
			add(request, response);
		}

	}

	protected void Modifypwd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String CONTENT_TYPE = "text/html; charset=utf-8";
		response.setContentType(CONTENT_TYPE);
		int id = Integer.parseInt(request.getParameter("id"));
		String oldpass = request.getParameter("user_passold");
		String password = request.getParameter("user_passnew");

		// check null
		if (id == 0 || password == null || oldpass == null) {
			return;
		}

		try {
			Mysql mysql = new Mysql();
			oldpass = MD5EncryptUtils.MD5Encode(oldpass);
			String sql = "select login_name,password from userinfo where id="
					+ id + " and password='" + oldpass + "'";
			ResultSet rs;
			rs = mysql.executeQuery(sql);
			if (rs.next()) {
				password = MD5EncryptUtils.MD5Encode(password);
				String updateStr = "update userinfo set  password= '"
						+ password + "' where id=" + id;
				mysql.executeUpdate(updateStr);

				response.getWriter().write(
						"<script>alert('success!');window.close();</script>");
				rs.close();
			} else {

				response.getWriter().write(
						"<script>alert('failed!');history.go(-1);</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();

			response.getWriter().write(
					"<script>alert('failed!');window.close();</script>");
		}

		// request.getRequestDispatcher("/users/modify.jsp").forward(request,
		// response);
	}

	protected void Login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println(username + password);

		UserBean userBean = new UserBean();
		boolean isValid = userBean.valid(username, password);

		if (isValid) {
			userBean.updateLoginfo(username, request.getRemoteAddr().trim());
			HttpSession session = request.getSession();
			User user = userBean.getUserinfo(username);
			session.setAttribute("user", user);
			// request.getRequestDispatcher("/index.jsp").forward(request,
			// response);
			if (Language.language.equals("china")) {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} else if (Language.language.equals("tibet")) {
				response.sendRedirect(request.getContextPath()
						+ "/Tibet/index.jsp");
			}
			return;
		} else {
			String CONTENT_TYPE = "text/html; charset=utf-8";
			// �����������ͷ�淶
			response.setContentType(CONTENT_TYPE);
			response.getWriter().write(
					"<script>alert('failed!');history.go(-1);</script>");
			// response.sendRedirect("index.jsp");
			return;
		}
	}

	protected void Logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		// request.getRequestDispatcher("/index.jsp").forward(request,
		// response);
		if (Language.language.equals("china")) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else if (Language.language.equals("tibet")) {
			response
					.sendRedirect(request.getContextPath() + "/Tibet/index.jsp");
		}
	}

	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		UserBean userBean = new UserBean();
		userBean.delete(id);
		String CONTENT_TYPE = "text/html; charset=utf-8";
		// �����������ͷ�淶
		response.setContentType(CONTENT_TYPE);
		response.getWriter().write("<script>alert('success!');</script>");
		list(request, response);
		// request.getRequestDispatcher("list.jsp").forward(request, response);
	}

	protected void updateStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String flag = request.getParameter("flag");
		UserBean userBean = new UserBean();
		userBean.updateStatus(id, flag);
		String CONTENT_TYPE = "text/html; charset=utf-8";
		// �����������ͷ�淶
		response.setContentType(CONTENT_TYPE);
		response.getWriter().write("<script>alert('success!');</script>");
		list(request, response);
		// request.getRequestDispatcher("list.jsp").forward(request, response);
	}

	protected void Register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get parameters
		String loginName = request.getParameter("username");
		String password = request.getParameter("password");
		String age = (String) request.getParameter("age");
		String gender = (String) request.getParameter("gender");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String name = request.getParameter("xm");
		String zipcode = request.getParameter("zipcode");
		String birthdate = request.getParameter("birthdate");
		String experience = request.getParameter("experience");
		String birthPlace = request.getParameter("birthPlace");
		String unit = request.getParameter("unit");
		String race = request.getParameter("race");

		String department = request.getParameter("department");
		String telephotone = request.getParameter("telephotone");
		String mobilephotone = request.getParameter("mobilephotone");
		Integer loginCount = 0;
		Integer view = 10;
		Integer viewed = 0;
		Integer down = 5;
		Integer downed = 0;

		String lastLoginIp = request.getRemoteAddr();
		String depid = request.getParameter("departmentId");
		Integer departmentId = 0;
		if (!"".equalsIgnoreCase(depid) && null != depid) {
			departmentId = Integer.parseInt(depid);
		}
		String info = request.getParameter("info");
		String research = request.getParameter("research");
		UserBean userBean = new UserBean();
		boolean isAdd = userBean.save(loginName, password, name, age, gender,
				address, email, zipcode, birthdate, experience, telephotone,
				mobilephotone, birthPlace, unit, race, department, loginCount,
				lastLoginIp, departmentId, info, research, view, viewed, down,
				downed);

		if (isAdd) {
			User user = userBean.getUserinfo(loginName);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			String CONTENT_TYPE = "text/html; charset=utf-8";
			// �����������ͷ�淶
			response.setContentType(CONTENT_TYPE);
			response.getWriter().write("<script>alert('success!');</script>");
			if (Language.language.equals("china")) {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} else if (Language.language.equals("tibet")) {
				response.sendRedirect(request.getContextPath()
						+ "/Tibet/index.jsp");
			}

		} else {
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}

	}

	protected void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String CONTENT_TYPE = "text/html; charset=utf-8";
		// �����������ͷ�淶
		response.setContentType(CONTENT_TYPE);
		// get parameters
		String loginName = request.getParameter("username");
		String password = request.getParameter("password");
		String age = (String) request.getParameter("age");
		String gender = (String) request.getParameter("gender");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String name = request.getParameter("xm");
		String zipcode = request.getParameter("zipcode");
		String birthdate = request.getParameter("birthdate");
		String experience = request.getParameter("experience");
		String birthPlace = request.getParameter("birthPlace");
		String unit = request.getParameter("unit");
		String race = request.getParameter("race");

		String department = request.getParameter("department");
		String telephotone = request.getParameter("telephotone");
		String mobilephotone = request.getParameter("mobilephotone");
		Integer loginCount = 0;

		String lastLoginIp = request.getRemoteAddr();
		String depid = request.getParameter("departmentId");
		Integer departmentId = 0;
		Integer view = 10;
		Integer viewed = 0;
		Integer down = 5;
		Integer downed = 0;

		if (!"".equalsIgnoreCase(depid) && null != depid) {
			departmentId = Integer.parseInt(depid);
		}
		String info = request.getParameter("info");
		String research = request.getParameter("research");
		UserBean userBean = new UserBean();
		boolean isAdd = userBean.save(loginName, password, name, age, gender,
				address, email, zipcode, birthdate, experience, telephotone,
				mobilephotone, birthPlace, unit, race, department, loginCount,
				lastLoginIp, departmentId, info, research, view, viewed, down,
				downed);

		if (isAdd) {

			response.getWriter().write("<script>alert('success!');</script>");
			// response.sendRedirect(request.getContextPath()+"/admin/users/list.jsp");
			list(request, response);
		} else {
			response.getWriter().write(
					"<script>alert('failed');history.go(-1);</script>");
		}

	}

	protected void getUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// get parameters
		int id = Integer.parseInt(request.getParameter("id"));

		UserBean userBean = new UserBean();
		User user = userBean.getUser(id);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/users/modify.jsp").forward(request,
				response);
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get parameters
		String CONTENT_TYPE = "text/html; charset=utf-8";
		// �����������ͷ�淶
		response.setContentType(CONTENT_TYPE);
		int id = Integer.parseInt(request.getParameter("id"));

		UserBean userBean = new UserBean();
		User user = userBean.getUser(id);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/admin/users/modify.jsp").forward(
				request, response);
	}

	protected void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String age = (String) request.getParameter("age");
		String gender = (String) request.getParameter("gender");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String name = request.getParameter("xm");
		String zipcode = request.getParameter("zipcode");
		String birthdate = request.getParameter("birthdate");
		String experience = request.getParameter("experience");
		String birthPlace = request.getParameter("birthPlace");
		String race = request.getParameter("race");
		String department = request.getParameter("department");
		String telephotone = request.getParameter("telephotone");
		String mobilephotone = request.getParameter("mobilephotone");
		String depid = request.getParameter("departmentId");
		Integer departmentId = 0;
		if (!"".equalsIgnoreCase(depid) && null != depid) {
			departmentId = Integer.parseInt(depid);
		}
		String info = request.getParameter("info");
		String research = request.getParameter("research");
		Integer view = Integer.parseInt(request.getParameter("view"));
		Integer down = Integer.parseInt(request.getParameter("down"));
		UserBean userBean = new UserBean();
		boolean isok = userBean.update(id, name, age, gender, address, email,
				zipcode, birthdate, experience, telephotone, mobilephotone,
				birthPlace, race, department, departmentId, info, research,
				view, down);
		// request.getRequestDispatcher("/index.jsp").forward(request,
		// response);
		// response.sendRedirect(request.getContextPath()+"/index.jsp");
		if (isok) {

			response.getWriter().write(
					"<script>alert('修改成功！');history.go(-1);</script>");

			return;
		} else {

			response.getWriter().write(
					"<script>alert('修改失败，请重试！');history.go(-1);</script>");

			return;
		}
	}

	/*
	 * protected void update2(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { Integer
	 * id=Integer.parseInt(request.getParameter("id")); String age =
	 * (String)request.getParameter("age") ; String gender
	 * =(String)request.getParameter("gender") ; String email =
	 * request.getParameter("email"); String address =
	 * request.getParameter("address"); String name= request.getParameter("xm");
	 * String zipcode= request.getParameter("zipcode"); String
	 * birthdate=request.getParameter("birthdate"); String
	 * experience=request.getParameter("experience"); String
	 * birthPlace=request.getParameter("birthPlace"); String
	 * race=request.getParameter("race"); String
	 * department=request.getParameter("department"); String
	 * telephotone=request.getParameter("telephotone"); String
	 * mobilephotone=request.getParameter("mobilephotone"); String
	 * depid=request.getParameter("departmentId"); Integer departmentId=0;
	 * if(!"".equalsIgnoreCase(depid)&&null!=depid) {
	 * departmentId=Integer.parseInt(depid);} String
	 * info=request.getParameter("info"); String
	 * research=request.getParameter("research"); Integer view =
	 * Integer.parseInt( request.getParameter("view")) - 1; Integer viewed =
	 * Integer.parseInt( request.getParameter("viewed")) + 1; Integer down =
	 * Integer.parseInt( request.getParameter("down")); Integer downed =
	 * Integer.parseInt( request.getParameter("downed"));
	 * 
	 * UserBean userBean = new UserBean(); boolean isok=
	 * userBean.update2(loginName, password, name, age, gender, address, email,
	 * zipcode, birthdate, experience, telephotone, mobilephotone, birthPlace,
	 * unit, race, department, loginCount, lastLoginIp, departmentId, info,
	 * research,view,viewed,down,downed);
	 * //request.getRequestDispatcher("/index.jsp").forward(request, response);
	 * //response.sendRedirect(request.getContextPath()+"/index.jsp");
	 * 
	 * return; }
	 */

	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean userBean = new UserBean();
		String offset = request.getParameter("offset");
		String filepath = request.getRequestURI();
		userBean.setResult(offset, filepath);
		request.setAttribute("userBean", userBean);
		request.getRequestDispatcher("/admin/users/list.jsp").forward(request,
				response);
	}

	protected void checkUsername(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserBean userBean = new UserBean();
		boolean isExist = false;
		isExist = userBean.isExist(username);
		String CONTENT_TYPE = "text/xml; charset=utf-8";
		// �����������ͷ�淶
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.close();
		}
		if (!isExist) {
			out.println("11");

		} else {
			out.println("22");

		}
		out.close();
	}

}
