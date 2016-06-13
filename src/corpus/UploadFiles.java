package corpus;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import antlr.collections.List;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadFile;
import uploadutilities.FileMover;

public class UploadFiles extends HttpServlet {

	/**
	 * 
	 */
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final long serialVersionUID = 8056450736575689465L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

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
		String corpusStoreFileRealPath = null;
		String quality = null;
		int type_id = 0;
		String[] paths;

		request.setCharacterEncoding("UTF-8");
		path = request.getParameter("path");
		origin = request.getParameter("origin");
		language = request.getParameter("language");
		tag = request.getParameter("tag");
		year = request.getParameter("year");
		month = request.getParameter("month");
		day = request.getParameter("day");
		quality = request.getParameter("quality");
		corpusCategory = request.getParameter("category");

		System.out.println(month);
		CorpusGetFile corpusGetFile = new CorpusGetFile();
		paths = corpusGetFile.getAllFileName_2(path); // 某文件夹下所有的文件名，包括子文件夹下的文件名。

		ArrayList<String> list = new ArrayList();
		if (paths != null) {
			for (int i = 0; i < paths.length; i++) {

				System.out.println(paths[i]);
				list.add(paths[i]);
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("paths", list);
		session.setAttribute("origin", origin);
		session.setAttribute("language", language);
		session.setAttribute("tag", tag);
		session.setAttribute("year", year);
		session.setAttribute("month", month);
		session.setAttribute("day", day);
		session.setAttribute("quality", quality);
		session.setAttribute("corpusCategory", corpusCategory);
		response.sendRedirect(request.getContextPath()
				+ "/admin/corpus/UploadFileDetail.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
