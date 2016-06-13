package textBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.lrc.db.Mysql;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.*;
import org.apache.commons.lang.time.DateFormatUtils;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sun.org.apache.xerces.internal.util.URI;

import corpus.CorpusBean;

public class textBookServelet extends HttpServlet {

	private static final long serialVersionUID = -3096800116651263134L;

	private String fileSizeLimit;
	private File oldFile;
	private String[] allPath;
	private String saveDirectory;

	public void init(ServletConfig config) throws ServletException {
		this.fileSizeLimit = config.getInitParameter("fileSizeLimit");
	}

	public void destroy() {
		this.fileSizeLimit = null;
		super.destroy();
	}

	class MyFileRenamePolicy implements FileRenamePolicy {
		public File rename(File file) {
			oldFile = file;
			// String fileSaveName = StringUtils.join(new String[] {
			// java.util.UUID.randomUUID().toString(), ".",
			// FilenameUtils.getExtension(file.getName()) });
			File result = new File(file.getParentFile(), oldFile.getName());
			return result;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = (String) request.getParameter("method");
		String mytext = (String) request.getParameter("mytext");
		String finalAllPath = (String) request.getParameter("finalAllPath");
		System.out.println("method:" + method);
		System.out.println("mytext:" + mytext);
		System.out.println("finalAllPath:" + finalAllPath);
		if (method.equals("addTextBook")) {
			addTextBook(request, response);
		} else if (method.equals("addTextDetail")) {
			DealTextBookFunction dealTextBookFunction = new DealTextBookFunction();
			allPath = dealTextBookFunction.parseAllPath(finalAllPath, mytext);
			addTextDetail(request, response);
		} else if (method.equals("downloadDetail")) {
			downloadDetail(request, response);
		} else if (method.equals("viewDetatil")) {
			viewDetatil(request, response);
		} else if (method.equals("viewDetail2")) {
			String username = (String) request.getParameter("username");
			viewDetatil2(request, response, username);
		} else if (method.equals("downloadDetail2")) {
			downloadDetail2(request, response);
		} else if (method.equals("downloadDetail3")) {
			downloadDetail3(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void addTextBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			System.out.println("come here:public void addTextBook");

			System.out.println("--- BEGIN DOPOST ---");
			HttpSession session = request.getSession();

			String uploadDir = "upload" + File.separatorChar + "textBook"
					+ File.separatorChar;

			String autoCreatedDateDirByParttern = "yyyy" + File.separatorChar
					+ "MM" + File.separatorChar + "dd" + File.separatorChar;
			String autoCreatedDateDir = DateFormatUtils.format(
					new java.util.Date(), autoCreatedDateDirByParttern);
			String ctxDir = session.getServletContext().getRealPath(
					String.valueOf(File.separatorChar));
			if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
				ctxDir = ctxDir + File.separatorChar;
			}
			File savePath = new File(ctxDir + uploadDir + autoCreatedDateDir);
			if (!savePath.exists()) {
				savePath.mkdirs();
			}
			String saveDirectory = ctxDir + uploadDir + autoCreatedDateDir;

			if (StringUtils.isBlank(this.fileSizeLimit.toString())) {
				this.fileSizeLimit = "80";// Ĭ��100M
			}
			int maxPostSize = Integer.parseInt(this.fileSizeLimit) * 1024 * 1024;
			String encoding = "UTF-8";
			FileRenamePolicy rename = new MyFileRenamePolicy();
			MultipartRequest multi = null;
			try {
				multi = new MultipartRequest(request, saveDirectory,
						maxPostSize, encoding, rename);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}

			// ���
			Enumeration files = multi.getFileNames();
			CorpusBean corpusbean = new CorpusBean();
			String fileSavePath = "";
			String lastFileName = "";
			while (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				File f = multi.getFile(name);
				if (f != null) {
					String fileName = multi.getFilesystemName(name);
					lastFileName = saveDirectory + "\\" + fileName;
					fileSavePath = uploadDir + autoCreatedDateDir + fileName;
					System.out.println("SimpleUploaderServlet");
					System.out.println("lastFileName:" + lastFileName);
					System.out.println("fileSavePath:" + fileSavePath);
					response.getWriter().print(fileSavePath);
				}

			}
			/**
			 * 在这里添加对该文件的操作
			 */
			corpusbean.uploadTextbook(lastFileName);
			System.out.println("--- END DOPOST ---");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author zhongdunhao
	 * @date 2013-7-12
	 * @explain upload detail of texts
	 * @param
	 */
	public void addTextDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			System.out.println("come here:public void addTextDetail");
			System.out.println("--- BEGIN DOPOST ---");

			HttpSession session = request.getSession();

			String uploadDir = "upload" + File.separatorChar + "textDetail"
					+ File.separatorChar;

			String ctxDir = session.getServletContext().getRealPath(
					String.valueOf(File.separatorChar));
			if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
				ctxDir = ctxDir + File.separatorChar;
			}
			File savePath = new File(ctxDir + uploadDir);
			if (!savePath.exists()) {
				savePath.mkdirs();
			}
			String saveDirectory = ctxDir + uploadDir;

			if (StringUtils.isBlank(this.fileSizeLimit.toString())) {
				this.fileSizeLimit = "100";// Ĭ��100M
			}
			int maxPostSize = Integer.parseInt(this.fileSizeLimit) * 1024 * 1024;
			String encoding = "UTF-8";
			FileRenamePolicy rename = new MyFileRenamePolicy();
			MultipartRequest multi = null;

			try {
				multi = new MultipartRequest(request, saveDirectory,
						maxPostSize, encoding, rename);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}

			Enumeration files = multi.getFileNames();
			DealTextBookFunction dealTextBookFunction = new DealTextBookFunction();
			String fileSavePath = "";
			String lastFileName = "";
			String realFilePath = "";
			while (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				File f = multi.getFile(name);
				if (f != null) {
					String fileName = multi.getFilesystemName(name);

					// 在这里加入对文本名的比对函数，来确定该文本的路径
					realFilePath = dealTextBookFunction.preparePath(allPath,
							fileName);
					File savePath1 = new File(saveDirectory
							+ "\\"
							+ realFilePath.substring(0, realFilePath
									.lastIndexOf("\\")));
					if (!savePath1.exists()) {
						savePath1.mkdirs();
					}
					lastFileName = saveDirectory + realFilePath;
					fileSavePath = uploadDir + realFilePath;
					System.out.println("lastFileName:" + lastFileName);
					System.out.println("fileSavePath:" + fileSavePath);
					response.getWriter().print(fileSavePath);

					dealTextBookFunction.CopyFile(f.getAbsolutePath(),
							savePath1.getAbsolutePath(), fileName);
					f.delete();
					dealTextBookFunction.insertTextDetailtoSQL(savePath1
							.getAbsolutePath(), fileName);
					/**
					 * 在这里添加对该文件的操作
					 */

				}
			}
			System.out.println("--- END DOPOST ---");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void downloadDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String path = (String) request.getParameter("path");
		String folderPath = path.substring(0, path.lastIndexOf("\\"));
		String fileName = path.substring(path.lastIndexOf("\\") + 1, path
				.length());
		// String fileName2 = (String)request.getParameter("fileName");
		System.out.println(path);
		System.out.println(fileName);
		// System.out.println(fileName2);

		File file = new File(folderPath);
		File files[] = file.listFiles();
		for (int j = 0; j < files.length; j++) {
			if (new String(files[j].getName().getBytes(), "utf-8")
					.equals(fileName)) {
				path = files[j].getAbsolutePath();
				fileName = encodingFileName(files[j].getName());
				System.out.println("I got it");
				break;
			}
		}

		java.io.BufferedInputStream bufferedInputStream = null;
		java.io.BufferedOutputStream bufferedOutPutStream = null;
		try {

			bufferedInputStream = new java.io.BufferedInputStream(
					new java.io.FileInputStream(path));

			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ fileName);
			bufferedOutPutStream = new java.io.BufferedOutputStream(response
					.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;

			while (-1 != (bytesRead = bufferedInputStream.read(buff, 0,
					buff.length))) {
				bufferedOutPutStream.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedInputStream != null) {
				bufferedInputStream.close();
			}
			if (bufferedOutPutStream != null) {
				bufferedOutPutStream.close();
			}
		}
		return;
	}
	
	public void downloadDetail2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = (String) request.getParameter("path");
		String folderPath = path.substring(0, path.lastIndexOf("\\"));
		String fileName = path.substring(path.lastIndexOf("\\") + 1, path
				.length());
		String down = (String) request.getParameter("down");
		String downed = (String) request.getParameter("downed");
		String userID = (String) request.getParameter("userID");
		String userName = (String) request.getParameter("username");
		String fileID = (String) request.getParameter("fileID");
		
		down = String.valueOf(Integer.valueOf(down) - 1);
		downed = String.valueOf(Integer.valueOf(downed) + 1);

		File file = new File(folderPath);
		File files[] = file.listFiles();
		for (int j = 0; j < files.length; j++) {
			if (new String(files[j].getName().getBytes(), "utf-8")
					.equals(fileName)) {
				path = files[j].getAbsolutePath();
				updateDown(userID, down, downed, files[j].getName(), userName, fileID);
				fileName = encodingFileName(files[j].getName());
				System.out.println("I got "+fileName);
				break;
			}
		}

		java.io.BufferedInputStream bufferedInputStream = null;
		java.io.BufferedOutputStream bufferedOutPutStream = null;
		try {

			bufferedInputStream = new java.io.BufferedInputStream(
					new java.io.FileInputStream(path));

			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ fileName);
			bufferedOutPutStream = new java.io.BufferedOutputStream(response
					.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;

			while (-1 != (bytesRead = bufferedInputStream.read(buff, 0,
					buff.length))) {
				bufferedOutPutStream.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedInputStream != null) {
				bufferedInputStream.close();
			}
			if (bufferedOutPutStream != null) {
				bufferedOutPutStream.close();
			}
		}
		return;
	}
	
	
	public void downloadDetail3(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Integer fileID = Integer.valueOf(request.getParameter("id"));
		String folderPath = "";
		String fileName = "";
		String path = "";

		
		ResultSet resultSet = null;
		try{
			Mysql mysql = new Mysql();
			String sql = "select content from texts where id="+fileID;
			resultSet = mysql.executeQuery(sql);
			if( resultSet.next() ){
				path = resultSet.getString("content");
				folderPath = path.substring(0, path.lastIndexOf("\\"));
				fileName = path.substring(path.lastIndexOf("\\") + 1, path
						.length());
			}
		} catch (Exception ioExcepion) {
			ioExcepion.printStackTrace();
		}
		
		File file = new File(folderPath);
		File files[] = file.listFiles();
		for (int j = 0; j < files.length; j++) {
			if (files[j].getName().equals(fileName)) {
				path = files[j].getAbsolutePath();
				fileName = encodingFileName(files[j].getName());
				System.out.println("I got it");
				break;
			}
		}

		java.io.BufferedInputStream bufferedInputStream = null;
		java.io.BufferedOutputStream bufferedOutPutStream = null;
		try {

			bufferedInputStream = new java.io.BufferedInputStream(
					new java.io.FileInputStream(path));

			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ fileName);
			bufferedOutPutStream = new java.io.BufferedOutputStream(response
					.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;

			while (-1 != (bytesRead = bufferedInputStream.read(buff, 0,
					buff.length))) {
				bufferedOutPutStream.write(buff, 0, bytesRead);
			}
			
			//System.out.println("here");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedInputStream != null) {
				bufferedInputStream.close();
			}
			if (bufferedOutPutStream != null) {
				bufferedOutPutStream.close();
			}
		}
		return;
	}
	
	
	public void viewDetatil(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = (String) request.getParameter("path");
		String folderPath = path.substring(0, path.lastIndexOf("\\"));
		String fileName = path.substring(path.lastIndexOf("\\") + 1, path
				.length());
		String view = (String) request.getParameter("view");
		String viewed = (String) request.getParameter("viewed");
		String userID = (String) request.getParameter("userID");
		String userName = (String) request.getParameter("username");
		String fileID = (String) request.getParameter("fileID");
		
		view = String.valueOf(Integer.valueOf(view) - 1);
		viewed = String.valueOf(Integer.valueOf(viewed) + 1);

		File file = new File(folderPath);
		File files[] = file.listFiles();
		for (int j = 0; j < files.length; j++) {
			if (new String(files[j].getName().getBytes(), "utf-8")
					.equals(fileName)) {
				path = files[j].getAbsolutePath();
				fileName = files[j].getName();
				System.out.println("I got it");
				break;
			}
		}
		updateView(userID, view, viewed, fileName, userName, fileID);

		try {
			Runtime runtime = Runtime.getRuntime();
			PrintWriter out = response.getWriter();
			DealTextBookFunction dealTextBookFunction = new DealTextBookFunction();
			String text;

			dealTextBookFunction.CopyFile(path, folderPath, "111.xml");
			runtime.exec("rundll32 url.dll FileProtocolHandler " + folderPath
					+ "\\111.xml");

			response.setContentType("text/html");
			out
					.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.println("  <br>");
			out.println("  <br>");
			out.println("  <br>");
			out.println("  <br>");
			out.println("  <center>");
			out.println("<a href=" + request.getContextPath() + "/corpus/CorpusSearch.jsp>检索课文</a>");
			out.println("  </center>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();

		} catch (Exception ioExcepion) {
			ioExcepion.printStackTrace();
		}
		return;
	}

	
	public void viewDetatil2(HttpServletRequest request,
			HttpServletResponse response, String username) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Integer fileID = Integer.valueOf(request.getParameter("id"));
		String folderPath = "";
		String fileName = "";
		String path = "";

		
		ResultSet resultSet = null;
		try{
			Mysql mysql = new Mysql();
			String sql = "select content from texts where id="+fileID;
			resultSet = mysql.executeQuery(sql);
			if( resultSet.next() ){
				path = resultSet.getString("content");
				folderPath = path.substring(0, path.lastIndexOf("\\"));
				fileName = path.substring(path.lastIndexOf("\\") + 1, path
						.length());
			}
		} catch (Exception ioExcepion) {
			ioExcepion.printStackTrace();
		}
		

		File file = new File(folderPath);
		File files[] = file.listFiles();
		for (int j = 0; j < files.length; j++) {
			if (files[j].getName().equals(fileName)) {
				path = files[j].getAbsolutePath();
				System.out.println("I got it");
				break;
			}
		}

		try {
			Runtime runtime = Runtime.getRuntime();
			DealTextBookFunction dealTextBookFunction = new DealTextBookFunction();
			dealTextBookFunction.CopyFile(path, folderPath, "111.xml");
			runtime.exec("rundll32 url.dll FileProtocolHandler " + folderPath
					+ "\\111.xml");
			response.sendRedirect(request.getContextPath()
					+ "/users/history.jsp?username="+username);
			 

		} catch (Exception ioExcepion) {
			ioExcepion.printStackTrace();
		}
		return;
	}
	
	
	public void updateView(String userID, String view, String viewed, String fileName, String userName, String fileID) {
		try {
			Mysql mysql = new Mysql();

			String sql = "update userinfo set view=" + view + ",viewed='"
					+ viewed + "' where id='" + userID + "'";
			mysql.executeUpdate(sql);
			sql = "insert into user_view(user_name,viewid,view_name,view_date) values ( '"+userName+"','"+fileID+"','"+fileName+"',curdate())";
			System.out.println(sql);
			mysql.executeUpdate(sql);
			mysql.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updateDown(String userID, String down, String downed, String fileName, String userName, String fileID) {
		try {
			Mysql mysql = new Mysql();

			String sql = "update userinfo set down=" + down + ",downed='"
					+ downed + "' where id='" + userID + "'";
			mysql.executeUpdate(sql);
			sql = "insert into user_down(user_name,downid,down_name,downdate) values ( '"+userName+"','"+fileID+"','"+fileName+"',curdate())";
			System.out.println(sql);
			mysql.executeUpdate(sql);
			mysql.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c >= 0) && (c <= 255)) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0) {
						k += 256;
					}
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return (sb.toString());
	}

	public static String encodingFileName(String fileName) {
		String returnFileName = "";
		try {
			returnFileName = URLEncoder.encode(fileName, "UTF-8");
			returnFileName = StringUtils.replace(returnFileName, "+", "%20");
			// if (returnFileName.length() > 150) {
			// returnFileName = new String(fileName.getBytes("utf-8"),
			// "ISO8859-1");
			// returnFileName = StringUtils.replace(returnFileName, " ", "%20");
			// }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnFileName;
	}

	public String getFileSizeLimit() {
		return fileSizeLimit;
	}

	public void setFileSizeLimit(String fileSizeLimit) {
		this.fileSizeLimit = fileSizeLimit;
	}

}
