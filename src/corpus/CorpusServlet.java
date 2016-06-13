package corpus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.lrc.db.Mysql;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadFile;
import uploadutilities.FileMover;

public class CorpusServlet extends HttpServlet {
	private static final long serialVersionUID = 8028978133623287674L;
	ServletContext servletContext;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/* 8888-88-88 */

	public ArrayList<String> list = new ArrayList<String>();

	public CorpusServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		servletContext = config.getServletContext();
	}

	public void updatenum(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Mysql mysql = new Mysql();
			String tempview = request.getParameter("view1");
			String tempdown = request.getParameter("down1");
			String tempuserID = request.getParameter("userID");
			int view = 0;
			int down = 0;
			int userID = 0;
			view = Integer.parseInt(tempview);
			down = Integer.parseInt(tempdown);
			userID = Integer.parseInt(tempuserID);
			String sql = "select view, down  from userinfo where id='" + userID
					+ "'";

			sql = "update userinfo set view=" + view + ",down='" + down
					+ "' where id='" + userID + "'";
			mysql.executeUpdate(sql);
			mysql.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		} else if (method.equals("addCorpusCategory")) {
			addCorpusCategory(request, response);
		} else if (method.equals("deleteCorpusCategory")) {
			deleteCorpusCategory(request, response);
		} else if (method.equals("ListAllCorpusCategory")) {
			ListAllCorpusCategory(request, response);
		} else if (method.equals("addCorpusLanguage")) {
			addCorpusLanguage(request, response);
		} else if (method.equals("deleteCorpusLanguage")) {
			deleteCorpusLanguage(request, response);
		} else if (method.equals("ListAllCorpusLanguage")) {
			ListAllCorpusLanguage(request, response);
		} else if (method.equals("addCorpusNetMedia")) {
			addCorpusNetMedia(request, response);
		} else if (method.equals("deleteCorpusNetMedia")) {
			deleteCorpusNetMedia(request, response);
		} else if (method.equals("ListAllCorpusNetMedia")) {
			ListAllCorpusNetMedia(request, response);
		} else if (method.equals("addCorpusPaperMedia")) {
			addCorpusPaperMedia(request, response);
		} else if (method.equals("deleteCorpusPaperMedia")) {
			deleteCorpusPaperMedia(request, response);
		} else if (method.equals("ListAllCorpusPaperMedia")) {
			ListAllCorpusPaperMedia(request, response);
		} else if (method.equals("uploadCourpus")) {
			uploadCourpus(request, response);
		} else if (method.equals("deleteCorpus")) {
			deleteCorpus(request, response);
		} else if (method.equals("getCorpus")) {
			getCorpus(request, response);
		} else if (method.equals("listCorpus")) {
			listCorpus(request, response);
		} else if (method.equals("exportCorpus")) {
			try {
				exportCorpus(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (method.equals("exportCorpusAll")) {
			exportCorpusAll(request, response);
		} else if (method.equals("exportCorpusAllhelp")) {
			exportCorpusAllhelp(request, response);
		} else if (method.equals("searchCorpus")) {
			searchCorpus(request, response);
		} else if (method.equals("searchCorpus2")) {
			searchCorpus2(request, response);
		} else if (method.equals("searchCorpus3")) {
			searchCorpus3(request, response);
		} else if (method.equals("searchCorpus6")) {
			searchCorpus6(request, response);
		} else if (method.equals("searchCorpus8")) {
			searchCorpus8(request, response);
		} else if (method.equals("checkCorpus")) {
			checkCorpus(request, response);
		} else if (method.equals("getCorpusTemps")) {
			getCorpusTemps(request, response);
		} else if (method.equals("deleteCorpusTemps")) {
			deleteCorpusTemps(request, response);
		} else if (method.equals("saveCorpus")) {
			saveCorpus(request, response);
		} else if (method.equals("exportCorpus3")) {
			exportCorpus3(request, response);
		} else if (method.equals("testCorpus")) {
			testCorpus(request, response);
		} else if (method.equals("list0ToTest")) {
			list0ToTest(request, response);
		} else if (method.equals("list1ToTest")) {
			list1ToTest(request, response);
		} else if (method.equals("changeCat")) {
			changeCat(request, response);
		} else if (method.equals("saveRemarks")) {
			saveRemarks(request, response);
		} else if (method.equals("searchResult")) {
			searchResult(request, response);
		} else if (method.equals("openCorpus")) {
			openCorpus(request, response);
		} else if (method.equals("deleteTextbook")) {
			deleteTextbook(request, response);
		} else if (method.equals("searchTextBook")) {
			searchTextBook(request, response);
		} else if (method.equals("searchTextDetail")) {
			searchTextDetail(request, response);
		} else if (method.equals("deleteTextdetail")) {
			deleteTextdetail(request, response);
		} else if (method.equals("openTextdetail")) {
			openTextdetail(request, response);
		} else if (method.equals("searchTextDetail2")) {
			searchTextDetail2(request, response);
		}
	}

	//added by zhongdunhao 2013-6-24
	public void searchTextBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CorpusBean corpusbean = new CorpusBean();		
		
		try{
			String type = "0";
			String alltag = "0";
			String chiefeditor = "";
			String publisher = "";
			String edition = "";
			String curriculumstandard = "";
			String typeofcurriculum = "";
			String textclass = "";
			String period = "";
			String volume = "";
			String language = "";
			String grade = "";
			
			/*
			 * 1、调用bean里面处理文件的函数；
			 * 2、向removeUploadtextbook.jsp传一参数‘all’，若为‘1’，则显示所有，若为‘0’，则为查找
			 */
			type = request.getParameter("type");
			publisher = request.getParameter("publisher");
			period = request.getParameter("period");
			volume = request.getParameter("volume");
			language = request.getParameter("language");
			
			
			HttpSession session = request.getSession();
			session.setAttribute("alltag", alltag);
			//session.setAttribute("chiefeditor", chiefeditor);
			session.setAttribute("publisher", publisher);
			//session.setAttribute("edition", edition);
			//session.setAttribute("curriculumstandard", curriculumstandard);
			//session.setAttribute("typeofcurriculum", typeofcurriculum);
			//session.setAttribute("textclass", textclass);
			session.setAttribute("period", period);
			session.setAttribute("volume", volume);
			session.setAttribute("language", language);
			if( type.equals("0")){
				response.sendRedirect(request.getContextPath()
					+ "/admin/textbook/removeUploadtextbook.jsp");
			}else{
				response.sendRedirect(request.getContextPath()
						+ "/admin/textbook/removeUploadtextbook2.jsp");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void searchTextDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CorpusBean corpusbean = new CorpusBean();		
		
		try{
			String type = "0";
			String alltag = "0";
			String publisher = "";
			String period = "";
			String periodtext = "";
			String volume = "";
			String language = "";
			
			//type=0/1,0表示是超级管理员或者语料管理员过来的，1表示语料下载员过来的。
			type = request.getParameter("type");
			publisher = request.getParameter("publisher");
			period = request.getParameter("period");
			volume = request.getParameter("volume");
			language = request.getParameter("language");
			
			Mysql mysql = new Mysql();
			ResultSet resultSet = null;
			String sql = "select publisher_j from textbook where publisher ='"+publisher+"'";// and period = '"+period+"' and volume = '"+volume+"' and language = '"+language+"'";
			resultSet = mysql.executeQuery(sql);
			if (resultSet.next()) {
				publisher = "texts.publisher='"+resultSet.getString("publisher_j")+"'";
			} else {
				publisher = "1=1";
			}
			
			sql = "select period_j from textbook where period ='"+period+"'";
			resultSet = mysql.executeQuery(sql);
			if (resultSet.next()) {
				period = "texts.period='"+resultSet.getString("period_j")+"'";
			} else {
				period = "1=1";
			}
			
			sql = "select volume_j from textbook where volume ='"+volume+"'";
			resultSet = mysql.executeQuery(sql);
			if (resultSet.next()) {
				volume = "texts.volume='"+resultSet.getString("volume_j")+"'";
			}else{
				volume = "1=1";
			}
			
			sql = "select languages_j from textbook where languages ='"+language+"'";
			resultSet = mysql.executeQuery(sql);
			if (resultSet.next()) {
				language = "texts.languages='"+resultSet.getString("languages_j")+"'";
			} else {
				language = "1=1";
			}
			resultSet.close();
			
			HttpSession session = request.getSession();
			session.setAttribute("alltag", alltag);
			session.setAttribute("publisher", publisher);
			session.setAttribute("period", period);
			session.setAttribute("volume", volume);
			session.setAttribute("language", language);
			if( type.equals("0") ){
				response.sendRedirect(request.getContextPath()
					+ "/admin/textbook/removeUploadtextdetail.jsp");
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/admin/textbook/removeUploadtextdetail2.jsp");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void searchTextDetail2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String alltag = "0";
			String publisher = "";
			String period = "";
			String periodtext = "";
			String volume = "";
			String language = "";
			
			/*
			 * 1、调用bean里面处理文件的函数；
			 * 2、向removeUploadtextbook.jsp传一参数‘all’，若为‘1’，则显示所有，若为‘0’，则为查找
			 */
			publisher = request.getParameter("publisher");
			period = request.getParameter("period");
			volume = request.getParameter("volume");
			language = request.getParameter("language");
			
			Mysql mysql = new Mysql();
			ResultSet resultSet = null;
			String sql = "select publisher_j from textbook where publisher ='"+publisher+"'";// and period = '"+period+"' and volume = '"+volume+"' and language = '"+language+"'";
			resultSet = mysql.executeQuery(sql);
			if (resultSet.next()) {
				publisher = "texts.publisher='"+resultSet.getString("publisher_j")+"'";
			} else {
				publisher = "1=1";
			}
			
			sql = "select period_j from textbook where period ='"+period+"'";
			resultSet = mysql.executeQuery(sql);
			if (resultSet.next()) {
				period = "texts.period='"+resultSet.getString("period_j")+"'";
			} else {
				period = "1=1";
			}
			
			sql = "select volume_j from textbook where volume ='"+volume+"'";
			resultSet = mysql.executeQuery(sql);
			if (resultSet.next()) {
				volume = "texts.volume='"+resultSet.getString("volume_j")+"'";
			}else{
				volume = "1=1";
			}
			
			sql = "select languages_j from textbook where languages ='"+language+"'";
			resultSet = mysql.executeQuery(sql);
			if (resultSet.next()) {
				language = "texts.languages='"+resultSet.getString("languages_j")+"'";
			} else {
				language = "1=1";
			}
			resultSet.close();
			
			HttpSession session = request.getSession();
			session.setAttribute("alltag", alltag);
			session.setAttribute("publisher", publisher);
			session.setAttribute("period", period);
			session.setAttribute("volume", volume);
			session.setAttribute("language", language);
			response.sendRedirect(request.getContextPath()
					+ "/corpus/CorpusSearchResult.jsp");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	//added by zhongdunhao 2013-6-24
	public void deleteTextbook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try{
			String alltag = "1";
			String [] value = request.getParameterValues("checkbox");
			CorpusBean corpusbean = new CorpusBean();	
			System.out.println("value are" + value);
			corpusbean.deleteUploadTextbook(value);
			
			HttpSession session = request.getSession();
			session.setAttribute("alltag", alltag);
			response.sendRedirect(request.getContextPath()
					+ "/admin/textbook/removeUploadtextbook.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteTextdetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try{
			String alltag = "1";
			String [] value = request.getParameterValues("checkbox");
			CorpusBean corpusbean = new CorpusBean();	
			System.out.println("value are" + value);
			corpusbean.deleteUploadTextdetail(value);
			
			HttpSession session = request.getSession();
			session.setAttribute("alltag", alltag);
			response.sendRedirect(request.getContextPath()
					+ "/admin/textbook/removeUploadtextdetail.jsp");
		}catch(Exception e){
			e.printStackTrace();
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
		} else if ((origin.equals("人工语料"))) {
			origin = "人工语料";
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
		response.sendRedirect(request.getContextPath()
				+ "/corpus/CorpusSearchResult.jsp");
	}

	public void searchCorpus2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String factor1 = request.getParameter("factor1");
		String factor2 = request.getParameter("factor2");
		String factor3 = request.getParameter("factor3");
		String category = request.getParameter("category");
		String language = request.getParameter("language");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year2 = request.getParameter("year2");
		String month2 = request.getParameter("month2");
		String day2 = request.getParameter("day2");
		String date = null;
		String date2 = null;
		String[] factor = { factor1, factor2, factor3 };
		String factors = "";

		if (factor != null) {
			for (int i = 0; i < factor.length; i++) {
				factors = factors.concat(factor[i]);
			}
		} else {
			factors = "8";
		}

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
		} else if (("人工语料").equals(origin)) {
			origin = "人工语料";
		}

		String title = request.getParameter("title");
		String author = request.getParameter("author");

		HttpSession session = request.getSession();
		session.setAttribute("relationship", "��");
		session.setAttribute("date2", date2);
		session.setAttribute("category", category);
		session.setAttribute("language", language);
		session.setAttribute("date", date);
		session.setAttribute("origin", origin);
		session.setAttribute("title", title);
		session.setAttribute("author", author);
		session.setAttribute("factors", factors);

		response.sendRedirect(request.getContextPath()
				+ "/admin/corpus/AdminCorpusSearchResult.jsp");
	}

	public void searchCorpus3(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idAll = request.getParameter("idAll");
		String title1 = request.getParameter("title1");
		String title2 = request.getParameter("title2");
		String number = request.getParameter("number");

		HttpSession session = request.getSession();

		session.setAttribute("idAll", idAll);
		session.setAttribute("title1", title1);
		session.setAttribute("title2", title2);
		session.setAttribute("number", number);

		response.sendRedirect(request.getContextPath()
				+ "/admin/corpus/AdminCorpusSearchResultSecond.jsp");
	}

	public void searchCorpus8(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idAll = request.getParameter("idAll");
		String idAllx = request.getParameter("idAllx");
		String idAlly = request.getParameter("idAlly");
		String title1 = request.getParameter("title1");
		String title2 = request.getParameter("title2");
		String number = request.getParameter("number");
		String numberx = request.getParameter("numberx");
		String numbery = request.getParameter("numbery");

		HttpSession session = request.getSession();

		session.setAttribute("idAll", idAll);
		session.setAttribute("idAllx", idAllx);
		session.setAttribute("idAlly", idAlly);
		session.setAttribute("title1", title1);
		session.setAttribute("title2", title2);
		session.setAttribute("number", number);
		session.setAttribute("numberx", numberx);
		session.setAttribute("numbery", numbery);

		response.sendRedirect(request.getContextPath()
				+ "/admin/corpus/AdminCorpusSearchResultSecond3.jsp");
	}

	public void searchCorpus6(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idAll = request.getParameter("idAll");
		String title1 = request.getParameter("title1");
		String title2 = request.getParameter("title2");
		String number = request.getParameter("number");

		HttpSession session = request.getSession();

		session.setAttribute("idAll", idAll);
		session.setAttribute("title1", title1);
		session.setAttribute("title2", title2);
		session.setAttribute("number", number);

		response.sendRedirect(request.getContextPath()
				+ "/admin/corpus/AdminCorpusSearchResultSecond2.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// ���������Դ����
	private void addCorpusCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String corpusCategory = request.getParameter("newCategory");
		CorpusBean corpusBean = new CorpusBean();

		if (corpusBean.addCorpusCategory(corpusCategory)) {
			ListAllCorpusCategory(request, response);
		}
	}

	// �����������
	private void addCorpusLanguage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String corpusLanguage = request.getParameter("newLanguage");
		CorpusBean corpusBean = new CorpusBean();

		if (corpusBean.addCorpusLanguage(corpusLanguage)) {
			ListAllCorpusLanguage(request, response);
		}
	}

	// ���������Դ��Դ�е�����ý������
	private void addCorpusNetMedia(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String corpusNetMedia = request.getParameter("newNetMedia");
		String NetAbbreviation = request.getParameter("NetAbbreviation");
		CorpusBean corpusBean = new CorpusBean();

		if (corpusBean.addCorpusOriginNetMedia(corpusNetMedia, NetAbbreviation)) {
			ListAllCorpusNetMedia(request, response);
		} else {
			ListAllCorpusPaperMedia(request, response);
		}
	}

	// ���������Դ����
	private void addCorpusPaperMedia(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String corpusPaperMedia = request.getParameter("newPaperMedia");
		String PaperAbbreviation = request.getParameter("PaperAbbreviation");
		CorpusBean corpusBean = new CorpusBean();

		if (corpusBean.addCorpusOriginPaperMedia(corpusPaperMedia,
				PaperAbbreviation)) {
			ListAllCorpusPaperMedia(request, response);
		} else {
			ListAllCorpusPaperMedia(request, response);
		}
	}

	// ɾ��ָ����������Դ����
	private void deleteCorpusCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		int type_id = Integer
				.parseInt((String) request.getParameter("type_id"));
		CorpusBean corpusBean = new CorpusBean();
		boolean flag = corpusBean.deleteCorpusCategory(type_id);

		if (flag) {
			ListAllCorpusCategory(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(
					"<script>alert('语料资源种类删除失败，请重试！');</script>");
		}
	}

	// 删除指定的语料资源种类
	private void deleteCorpusLanguage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		int type_id = Integer
				.parseInt((String) request.getParameter("type_id"));
		CorpusBean corpusBean = new CorpusBean();
		boolean flag = corpusBean.deleteCorpusLanguage(type_id);

		if (flag) {
			ListAllCorpusLanguage(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(
					"<script>alert('语料语种删除失败，请重试！');</script>");
		}
	}

	// ɾ��ָ��������ý������
	private void deleteCorpusNetMedia(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		int type_id = Integer
				.parseInt((String) request.getParameter("type_id"));
		CorpusBean corpusBean = new CorpusBean();
		boolean flag = corpusBean.deleteCorpusNetMedia(type_id);

		if (flag) {
			ListAllCorpusNetMedia(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(
					"<script>alert('网络媒体类别删除失败，请重试！');</script>");
		}
	}

	// ɾ��ָ����ֽ��ý������
	private void deleteCorpusPaperMedia(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		int type_id = Integer
				.parseInt((String) request.getParameter("type_id"));
		CorpusBean corpusBean = new CorpusBean();
		boolean flag = corpusBean.deleteCorpusPaperMedia(type_id);

		if (flag) {
			ListAllCorpusPaperMedia(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(
					"<script>alert('纸质媒体类别删除失败，请重试！');</script>");
		}
	}

	// ��ʾ���е�������Դ����
	private void ListAllCorpusCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()
				+ "/admin/corpus/ListAllCorpusCategory.jsp");
	}

	@SuppressWarnings("unused")
	private void ListAllusers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()
				+ "/admin/corpus/Userpower.jsp");
	}

	// ��ʾ���е���������
	private void ListAllCorpusLanguage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()
				+ "/admin/corpus/ListAllCorpusLanguage.jsp");
	}

	// ��ʾ����������Դ�е�����ý������
	private void ListAllCorpusNetMedia(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()
				+ "/admin/corpus/ListAllCorpusNetMedia.jsp");
	}

	// ��ʾ����������Դ�е�ֽ��ý������
	private void ListAllCorpusPaperMedia(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()
				+ "/admin/corpus/ListAllCorpusPaperMedia.jsp");
	}

	// ������Դ�ϴ�
	@SuppressWarnings( { "unchecked", "deprecation" })
	protected void uploadCourpus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("1111111111111111111111");
		FileMover fileMover = new FileMover();
		UploadBean uploadBean = new UploadBean();
		MultipartFormDataRequest multipartFormDataRequest = null;
		Hashtable hashTable = null;// ���ڴ�ȡ�ϴ���������Դ��Ϣ
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
		String type = null;
		String path = null;
		int quality = 0;
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
			} else if (origin.equals("人工语料")) {
				origin = "人工语料";
			} else
				origin = null;

			tag = multipartFormDataRequest.getParameter("tag");
			if (tag.equals("1")) {
				tag = "1";
			} else {
				tag = "0";
			}

			quality = Integer.parseInt(multipartFormDataRequest
					.getParameter("quality"));
			if (quality == 0) {
				quality = 0;
			} else if (quality == 1) {
				quality = 1;
			} else {
				quality = 2;
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
			language = multipartFormDataRequest.getParameter("language");
			corpusCategory = multipartFormDataRequest.getParameter("category");
			hashTable = multipartFormDataRequest.getFiles();
		}

		CorpusBean corpusBean = new CorpusBean();

		if (corpusCategory.equals("----")) {
			type_id = 0;
		} else {
			type_id = Integer.parseInt(corpusCategory);
		}

		String corpusStoreFileRealPath = "corpusUpload" + "/"
				+ corpusBean.getCorpusCategory(type_id);

		File directory = new File("corpusUpload");
		if (!directory.exists())
			directory.mkdir();
		if ((hashTable != null) || (!hashTable.isEmpty())) {
			UploadFile uploadFile = (UploadFile) hashTable.get("attachment");// ��ȡ�ϴ��ĸ���
			localFileName = uploadFile.getFileName();

			if ((uploadFile.getFileSize()) >= (1024 * 1024))// �ļ�����1M
			{
				double corpusSize = (double) ((uploadFile.getFileSize()) / (double) (1024 * 1024));

				if (corpusSize > 20) {
					response.setContentType("text/html;charset=UTF-8");
					response
							.getWriter()
							.write(
									"<script>alert('文件语料上传失败，单次上传的语料大小不能超过20M！');</script>");
				}

				NumberFormat numberFormat = NumberFormat.getNumberInstance();// ����һ����ݸ�ʽ����
				numberFormat.setMaximumFractionDigits(2);// ����С��������ʾ����ĿΪ2
				String tempSize = numberFormat.format(corpusSize);// ����numberFormat��format����,��ʽ��corpusSize,����һ���ַ����
				size = tempSize + "M";
			} else// �ļ�С��1M
			{
				double corpusSize = (double) ((uploadFile.getFileSize()) / (double) (1024));
				NumberFormat numberFormat = NumberFormat.getNumberInstance();// ����һ����ݸ�ʽ����
				numberFormat.setMaximumFractionDigits(2);// ����С��������ʾ����ĿΪ2
				String tempSize = numberFormat.format(corpusSize);// ����numberFormat��format����,��ʽ��corpusSize,����һ���ַ����
				size = tempSize + "K";
			}
			// ȡ�ļ���ĺ�׺
			int index = localFileName.lastIndexOf(".");
			fileExtension = localFileName.substring(index, localFileName
					.length());
			File fileDirectory = new File(corpusStoreFileRealPath);

			type = fileExtension;
			if (!fileDirectory.exists()) {
				fileDirectory.mkdirs();
			}

			// �ϴ�
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
							"/admin/corpus/UploadCorpusFail.jsp").forward(
							request, response);
					return;
				}

				uploadBean.setFolderstore(corpusStoreFileRealPath);// ����Ҫ�ϴ���Ŀ¼
				uploadBean.addUploadListener(fileMover);// ����fileMover����
				uploadBean.setFilesizelimit(1024 * 1024 * 20);
				uploadBean.store(multipartFormDataRequest, "attachment");

				corpusStoreFileRealPath = fileDirectory.toURL().toString()
						+ "/" + localFileName;
				corpusStoreFileRealPath = corpusStoreFileRealPath.substring(
						corpusStoreFileRealPath.indexOf("/") + 1,
						corpusStoreFileRealPath.length());
				flag = corpusBean.addCorpus(origin, title, author, date,
						language, corpusStoreFileRealPath, lastModifyTime,
						size, type_id, tag, type, quality);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (flag) {
				listCorpus(request, response);
				this.corpusBackup(type_id, localFileName,
						corpusStoreFileRealPath, urlBack);
			} else {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(
						"<script>alert('语料上传失败，请重试！');</script>");
			}
		}
	}

	// ������Դ����
	@SuppressWarnings( { "unchecked", "deprecation" })
	protected void corpusBackup(int id, String localFileName, String url,
			String urlBack) {
		CorpusBean corpusBean = new CorpusBean();

		String corpusStoreFileRealPathBack = "corpusBackup" + "/"
				+ corpusBean.getCorpusCategory(id);
		File fileDirectoryBack = new File(corpusStoreFileRealPathBack);

		if (!fileDirectoryBack.exists()) {
			fileDirectoryBack.mkdirs();
		}

		java.io.BufferedInputStream bufferedInputStream = null;
		java.io.BufferedOutputStream bufferedOutPutStream = null;

		try {
			bufferedInputStream = new java.io.BufferedInputStream(
					new java.io.FileInputStream(url));

			bufferedOutPutStream = new java.io.BufferedOutputStream(
					new java.io.FileOutputStream(urlBack));
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
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedOutPutStream != null) {
				try {
					bufferedOutPutStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ɾ��ָ����������Դ
	protected void deleteCorpus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String tempId = request.getParameter("id");
		int id = 0;
		if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
			id = Integer.parseInt(tempId);
		}
		CorpusBean corpusBean = new CorpusBean();
		String corpusURL = corpusBean.getCorpusURL(id);

		if (corpusURL == null) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(
					"<script>alert('链接的语料资源不能为空，请重试！');</script>");
		} else {
			String realPath = "E:/uploadCorpus/";
			java.io.File fileName = new java.io.File(corpusURL);

			if (fileName.exists()) {
				if (fileName.delete()) {
				} else {
					response.getWriter().write(
							"<script>alert('语料删除失败，请重试！');</script>");
				}
			} else {
				response.getWriter().write(
						"<script>alert('您要删除的语料资源不存在！');</script>");
			}
		}

		try {
			corpusBean.deleteCorpus(id);
			listCorpus(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ȡָ��������Դ����ϸ��Ϣ
	protected void getCorpus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CorpusBean corpusBean = new CorpusBean();
		int id = 0;
		String tempId = request.getParameter("id");

		if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
			id = Integer.parseInt(tempId);
		}

		try {
			corpusBean = corpusBean.getCorpusBean(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("corpusBean", corpusBean);
		request.getRequestDispatcher("/corpus/CorpusBeanDetails.jsp").forward(
				request, response);
	}

	// �������������Դ����Ϣ
	public void listCorpus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/corpus/ListCorpus.jsp").forward(
				request, response);
	}

	// ת��
	public String toUtf8String(String string) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if ((c >= 0) && (c <= 255)) {
				stringBuffer.append(c);
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
					stringBuffer.append("%"
							+ Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return (stringBuffer.toString());
	}

	// ����������Դ
	protected void exportCorpus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CorpusBean corpusBean = new CorpusBean();
		String tempId = request.getParameter("id");
		String tempusername = request.getParameter("username");
		String tempdown = request.getParameter("down");
		String tempdowned = request.getParameter("downed");
		int id = 0;
		int down = 0;
		int downed = 0;
		int downcount = 0;
		int downedcount = 0;

		if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
			id = Integer.parseInt(tempId);

		}

		if ((!"".equalsIgnoreCase(tempdown)) && (null != tempdown)) {
			down = Integer.parseInt(tempdown);
			downed = Integer.parseInt(tempdowned);
			downcount = down - 1;
			downedcount = downed + 1;
			Mysql mysql = new Mysql();

			String sql = "select down, downed  from userinfo where login_name='"
					+ tempusername + "'";

			sql = "update userinfo set down='" + downcount + "',downed='"
					+ downedcount + "' where login_name='" + tempusername + "'";
			mysql.executeUpdate(sql);
			mysql.close();
		}

		try {
			Mysql mysql = new Mysql();
			CorpusBean cb = corpusBean.getCorpusBean(id);

			String sql = "insert into user_down(user_name,downid,down_name,downdate)"
					+ "values('"
					+ tempusername
					+ "','"
					+ id
					+ "','"
					+ cb.getTitle() + "',curdate())";

			mysql.executeUpdate(sql);
			mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			corpusBean = corpusBean.getCorpusBean(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String url = corpusBean.getUrl();// ȡ���ļ���
		String[] array = url.split("/");
		String filepath = corpusBean.getUrl();// ȡ���ļ�·��
		String corpusName = this.toUtf8String(array[2]);

		String realurl = corpusBean.getUrl();
		java.io.BufferedInputStream bufferedInputStream = null;
		java.io.BufferedOutputStream bufferedOutPutStream = null;

		try {

			bufferedInputStream = new java.io.BufferedInputStream(
					new java.io.FileInputStream(realurl));

			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(corpusBean.getTitle().getBytes("gb2312"),
							"ISO8859-1"));
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
	}

	protected void exportCorpusAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String n = request.getParameter("number");
		int number = Integer.valueOf(n);

		String sk = request.getParameter("idAll");
		String idAll[] = new String[200000];
		for (int i = 0; i < idAll.length; i++) {
			idAll[i] = new String();
		}

		int begin = 0, j = 0;
		for (j = 0; j < number - 1; j++) {
			begin = sk.indexOf("-");
			idAll[j] = sk.substring(0, begin);
			sk = sk.substring(begin + 1, sk.length());
		}
		idAll[j] = sk;

		File srcfile[] = new File[number];
		File zipfile = new File("material.rar");

		for (int i = 0; i < number; i++) {
			CorpusBean temp = new CorpusBean();

			try {
				temp = temp.getCorpusBean(Integer.parseInt(idAll[i]));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String realurl1 = temp.getUrl();
			System.out.println(realurl1);

			srcfile[i] = new File(realurl1);
		}

		byte[] buf = new byte[1024];
		try {
			// Create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipfile));
			// Compress the files
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(new String(srcfile[i].getName()
						.getBytes("gb2312"), "ISO8859-1")));
				// Transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				// Complete the entry
				out.closeEntry();
				in.close();
			}
			// Complete the ZIP file
			out.close();
			System.out.println("压缩完成.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		exportCorpusAllhelp(request, response);

	}

	protected void exportCorpusAllhelp(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String realurl = "material.rar";
		java.io.BufferedInputStream bufferedInputStream = null;
		java.io.BufferedOutputStream bufferedOutPutStream = null;

		try {

			bufferedInputStream = new java.io.BufferedInputStream(
					new java.io.FileInputStream(realurl));

			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ "material.rar");
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
	}

	// �ϴ����ϴ��
	protected void saveCorpus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String tempId = request.getParameter("id");
		int id = 0;
		if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
			id = Integer.parseInt(tempId);
		}
		CorpusBean corpusBean = new CorpusBean();
		try {
			corpusBean = corpusBean.getCorpusBeanTemps(id);
			corpusBean.addCorpus(corpusBean.getOrigin(), corpusBean.getTitle(),
					corpusBean.getAuthor(), corpusBean.getDate(), corpusBean
							.getAuthor(), corpusBean.getUrl(), corpusBean
							.getLastModifyTime(), corpusBean.getSize(),
					corpusBean.getType_id(), corpusBean.getType_tag(),
					corpusBean.getType(), corpusBean.getQuality());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			corpusBean.deleteCorpusTemps(id);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("<script>alert('转存成功！');</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ɾ���ݴ��������Դ
	protected void deleteCorpusTemps(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String tempId = request.getParameter("id");
		int id = 0;
		if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
			id = Integer.parseInt(tempId);
		}
		CorpusBean corpusBean = new CorpusBean();
		String corpusURL = corpusBean.getCorpusURLTemps(id);

		if (corpusURL == null) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(
					"<script>alert('链接的语料资源不能为空，请重试！');</script>");
		} else {
			String realPath = "E:/uploadCorpus/";
			java.io.File fileName = new java.io.File(corpusURL);

			if (fileName.exists()) {
				if (fileName.delete()) {
				} else {
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write(
							"<script>alert('语料删除失败，请重试！');</script>");
				}
			} else {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(
						"<script>alert('您要删除的语料资源不存在！');</script>");
			}
		}

		try {
			corpusBean.deleteCorpusTemps(id);
			listCorpus(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ȡ�ݴ�������Դ����ϸ��Ϣ
	protected void getCorpusTemps(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CorpusBean corpusBean = new CorpusBean();
		int id = 0;
		String tempId = request.getParameter("id");

		if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
			id = Integer.parseInt(tempId);
		}

		try {
			corpusBean = corpusBean.getCorpusBeanTemps(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("corpusBean", corpusBean);
		request.getRequestDispatcher("/corpus/CorpusBeanDetailsTemps.jsp")
				.forward(request, response);
	}

	// �ϴ��������
	protected void checkCorpus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/corpus/CheckCorpus.jsp").forward(
				request, response);
	}

	//
	protected void exportCorpus3(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String tempId = request.getParameter("id");

		int id = 0;

		if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
			id = Integer.parseInt(tempId);
		}

		CorpusBean corpusBean = new CorpusBean();

		try {
			corpusBean = corpusBean.getCorpusBeanTemps(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String url = corpusBean.getUrl();// ȡ���ļ���

		int begin = 0;
		begin = url.lastIndexOf("/");

		url = url.substring(begin + 1, url.length());

		String realurl = corpusBean.getUrl();
		java.io.BufferedInputStream bufferedInputStream = null;
		java.io.BufferedOutputStream bufferedOutPutStream = null;

		try {

			bufferedInputStream = new java.io.BufferedInputStream(
					new java.io.FileInputStream(realurl));

			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(url.getBytes("gb2312"), "ISO8859-1"));
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
	}

	//
	protected void list0ToTest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/corpus/Corpus0Test.jsp").forward(
				request, response);
	}

	protected void list1ToTest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/corpus/Corpus1Test.jsp").forward(
				request, response);
	}

	protected void list2ToTest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/corpus/Corpus2Test.jsp").forward(
				request, response);
	}

	//
	protected void testCorpus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getRequestDispatcher("/admin/corpus/CorpusToTest.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查看语料功能
	protected void openCorpus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			request.getRequestDispatcher("/admin/corpus/OpenCorpus.jsp")
					.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void openTextdetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			Runtime runtime = Runtime.getRuntime();   
			
			try{   
				//CorpusBean cb2 = corpusBean.getCorpusBean(id);
				String path = request.getParameter("path");
		        System.out.println(path);   
		        //打开文件   
		        runtime.exec("rundll32 url.dll FileProtocolHandler "+path);   
		    }catch(Exception ex){   
		        ex.printStackTrace();   
		    }   

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void changeCat(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MultipartFormDataRequest multipartFormDataRequest = null;
		@SuppressWarnings("unused")
		String fileExtension = null;
		String corpusCategory = null;
		@SuppressWarnings("unused")
		String net = null;
		@SuppressWarnings("unused")
		String paper = null;
		int type_id = 0;

		String tempId = request.getParameter("id");
		String type = request.getParameter("type");

		int id = 0;
		if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
			id = Integer.parseInt(tempId);
		}

		try {
			CorpusBean corpusBean = new CorpusBean().getCorpusBean(id);
			if (MultipartFormDataRequest.isMultipartFormData(request)) {
				try {
					multipartFormDataRequest = new MultipartFormDataRequest(
							request, null, 100 * 1024 * 1024,
							MultipartFormDataRequest.COSPARSER, "utf-8");
				} catch (UploadException e) {
					e.printStackTrace();
				}
				corpusCategory = multipartFormDataRequest
						.getParameter("category");

			}
			type_id = Integer.parseInt(corpusCategory);
			corpusBean.changCat(id, type_id, type);
			corpusBean.changTest(id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.getRequestDispatcher("/admin/corpus/CorpusToTest.jsp").forward(
				request, response);
	}

	// ������Ϣ
	protected void saveRemarks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MultipartFormDataRequest multipartFormDataRequest = null;
		@SuppressWarnings("unused")
		String fileExtension = null;
		String corpusCategory = null;
		@SuppressWarnings("unused")
		String net = null;
		@SuppressWarnings("unused")
		String paper = null;
		String remarks = null;

		String tempId = request.getParameter("id");
		int id = 0;
		if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
			id = Integer.parseInt(tempId);
		}
		try {
			CorpusBean corpusBean = new CorpusBean().getCorpusBean(id);
			if (MultipartFormDataRequest.isMultipartFormData(request)) {
				try {
					multipartFormDataRequest = new MultipartFormDataRequest(
							request, null, 100 * 1024 * 1024,
							MultipartFormDataRequest.COSPARSER, "utf-8");
				} catch (UploadException e) {
					e.printStackTrace();
				}
				remarks = multipartFormDataRequest.getParameter("remarks");
			}

			corpusBean.saveRemarks(id, remarks);
			corpusBean.changTest(id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// /
	protected void searchResult(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			request.getRequestDispatcher("/admin/corpus/CorpusToTest.jsp")
					.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
