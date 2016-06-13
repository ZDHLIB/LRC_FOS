package corpus;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadFile;
import uploadutilities.FileMover;


public class UserUpload extends HttpServlet {
	private static final long serialVersionUID = 8028978133623287674L;
	ServletContext servletContext;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public UserUpload() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		servletContext = config.getServletContext();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = (String) request.getParameter("method");

		if (method == null) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(
					"<script>alert('访问方式错误，请检查访问路径后再重试！');</script>");
		} 
		else if (method.equals("uploadCourpus")) {
			uploadCourpus(request, response);
		}
	}
	public void searchCorpus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		String language = request.getParameter("language");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year2 = request.getParameter("year2");
		String month2 = request.getParameter("month2");
		String day2 = request.getParameter("day2");
		String tag = request.getParameter("tag");
		String date = null;
		String date2 = null;

		if (year.equals("----")) {
			date = "0000-00-00";
		} else if (month.equals("----")) {
			date = year;
		} else if (day.equals("----")) {
			date = year + "-" + month;
		} else {
			date = year + "-" + month + "-" + day;
		}

		if (year2.equals("----")) {
			date2 = "0000-00-00";
		} else if (month2.equals("----")) {
			date2 = year2;
		} else if (day2.equals("----")) {
			date2 = year2 + "-" + month2;
		} else {
			date2 = year2 + "-" + month2 + "-" + day2;
		}

		String origin = request.getParameter("origin");
		if (("网络媒体").equals(origin)) {
			origin = request.getParameter("net");
		} else if (("纸质媒体").equals(origin)) {
			origin = request.getParameter("paper");
		}
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String relationship = request.getParameter("relationship");

		HttpSession session = request.getSession();
		session.setAttribute("relationship", relationship);
		session.setAttribute("date2", date2);
		session.setAttribute("category", category);
		session.setAttribute("language", language);
		session.setAttribute("date", date);
		session.setAttribute("origin", origin);
		session.setAttribute("title", title);
		session.setAttribute("author", author);
		session.setAttribute("tag", tag);
		response.sendRedirect(request.getContextPath()
				+ "/corpus/CorpusSearchResult.jsp");
	}

	// 语料资源上传
	@SuppressWarnings( { "unchecked", "deprecation" })
	protected void uploadCourpus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		FileMover fileMover = new FileMover();
		UploadBean uploadBean = new UploadBean();
		MultipartFormDataRequest multipartFormDataRequest = null;
		Hashtable hashTable = null;// 用于存取上传的语料资源信息
		String localFileName = null;
		@SuppressWarnings("unused")
		String fileExtension = null;
		String corpusCategory = null;
		String origin = null;
		@SuppressWarnings("unused")
		String net = null;
		@SuppressWarnings("unused")
		String paper = null;
		String title = null;
		String author = null;
		String date = null;
		String year = null;
		String month = null;
		String day = null;
		String language = null;
		String lastModifyTime = null;
		String url = null;
		String urlBack = null;
		String size = null;
		String tag = null;
		int type_id = 0;

		if (MultipartFormDataRequest.isMultipartFormData(request)) {
			try {
				multipartFormDataRequest = new MultipartFormDataRequest(
						request, null, 100 * 1024 * 1024,
						MultipartFormDataRequest.COSPARSER, "utf-8");
			} catch (UploadException e) {
				e.printStackTrace();
			}
			origin = multipartFormDataRequest.getParameter("origin");
			System.out.println(multipartFormDataRequest.getParameter("net"));
			System.out.println(multipartFormDataRequest.getParameter("paper"));
			
			if (origin.equals("网络媒体")) {
				origin = multipartFormDataRequest.getParameter("net");
			} else if (origin.equals("纸质媒体")) {
				origin = multipartFormDataRequest.getParameter("paper");
			} else if ( origin.equals("人工语料")) {
				origin = "人工语料";
			}
			
			title = multipartFormDataRequest.getParameter("title");
			author = multipartFormDataRequest.getParameter("author");
			year = multipartFormDataRequest.getParameter("year");
			month = multipartFormDataRequest.getParameter("month");
			day = multipartFormDataRequest.getParameter("day");
			if (year.equals("----")) {
				date = "0000-00-00";
			} else if (month.equals("----")) {
				date = year;
			} else if (day.equals("----")) {
				date = year + "-" + month;
			} else {
				date = year + "-" + month + "-" + day;
			}
			
			tag = multipartFormDataRequest.getParameter("tag");
			if(tag.equals("1")) {
				tag = "1";
			}else {
				tag = "0";
			}
			
			language = multipartFormDataRequest.getParameter("language");
			corpusCategory = multipartFormDataRequest.getParameter("category");
			hashTable = multipartFormDataRequest.getFiles();
		}

		CorpusBean corpusBean = new CorpusBean();
		type_id = Integer.parseInt(corpusCategory);
		
		String corpusStoreFileRealPath ="corpusUpload" + "/" + corpusBean.getCorpusCategory(type_id);
		File sk = new File("uploadCorpus");
		if (!sk.exists())
			sk.mkdir();
		File directory = new File("corpusUpload");
		if (!directory.exists())
			directory.mkdir();
		if ((hashTable != null) || (!hashTable.isEmpty())) {
			UploadFile uploadFile = (UploadFile) hashTable.get("attachment");// 获取上传的附件
			localFileName = uploadFile.getFileName();

			if ((uploadFile.getFileSize()) >= (1024 * 1024))// 文件大于1M
			{
				double corpusSize = (double) ((uploadFile.getFileSize()) / (double) (1024 * 1024));
				if (corpusSize > 20) {
					response.setContentType("text/html;charset=UTF-8");
					response
							.getWriter()
							.write(
									"<script>alert('文件语料上传失败，单次上传的语料大小不能超过20M！');</script>");
				}

				NumberFormat numberFormat = NumberFormat.getNumberInstance();// 创建一个数据格式对象
				numberFormat.setMaximumFractionDigits(2);// 设置小数部分最多显示的数目为2
				String tempSize = numberFormat.format(corpusSize);// 调用numberFormat的format方法,格式化corpusSize,返回一个字符串对象
				size = tempSize + "M";
			} else// 文件小于1M
			{
				double corpusSize = (double) ((uploadFile.getFileSize()) / (double) (1024));
				NumberFormat numberFormat = NumberFormat.getNumberInstance();// 创建一个数据格式对象
				numberFormat.setMaximumFractionDigits(2);// 设置小数部分最多显示的数目为2
				String tempSize = numberFormat.format(corpusSize);// 调用numberFormat的format方法,格式化corpusSize,返回一个字符串对象
				size = tempSize + "K";
			}
			// 取文件名的后缀
			int index = localFileName.lastIndexOf(".");
			fileExtension = localFileName.substring(index, localFileName
					.length());
			File fileDirectory = new File(corpusStoreFileRealPath);

			if (!fileDirectory.exists()) {
				fileDirectory.mkdirs();
			}

			// 上传
			boolean flag = false;
			lastModifyTime = simpleDateFormat.format(new Date());
			url = "corpusUpload" + "/" + corpusBean.getCorpusCategory(type_id)
					+ "/" + localFileName;
			urlBack = "corpusBackup" + "/"
					+ corpusBean.getCorpusCategory(type_id) + "/"
					+ localFileName;
			
			try {
				if (!corpusBean.checkCorpus(localFileName)) {
					String message = "同名的语料资源已存在，请选择其他名称！";
					request.setAttribute("message", message);
					request.setAttribute("title", "");
					request.getRequestDispatcher(
							"/corpus/CorpusUploadFail.jsp").forward(
							request, response);
					return;
				}
				
				uploadBean.setFolderstore(corpusStoreFileRealPath);// 设置要上传的目录
				uploadBean.addUploadListener(fileMover);// 增加fileMover监听
				uploadBean.setFilesizelimit(1024 * 1024 * 20);
				uploadBean.store(multipartFormDataRequest, "attachment");
				
				
				
				corpusStoreFileRealPath = fileDirectory.toURL().toString() + "/"+localFileName;
				corpusStoreFileRealPath = corpusStoreFileRealPath.substring(corpusStoreFileRealPath.indexOf("/")+1, corpusStoreFileRealPath.length());
				flag = corpusBean.addTemps(origin, title, author, date,
						language, corpusStoreFileRealPath, lastModifyTime, size, type_id, tag);
			} catch (Exception e) {
				e.printStackTrace();
			}

		if (flag) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("<script alert('上传语料成功！');</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(	"<script>alert('语料上传失败，请重试！');</script>");		
			}
		}
	}

	
}
