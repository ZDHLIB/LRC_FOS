package corpus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uploadutilities.FileMover;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadFile;

public class UploadFiles2 extends HttpServlet {

	/**
	 * 
	 */
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final long serialVersionUID = 8056450736575689465L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		uploadCourpus(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

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
		String number = null;
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

			number = multipartFormDataRequest.getParameter("number");
			origin = multipartFormDataRequest.getParameter("origin");
			System.out.println("111111");
			System.out.println(origin + "111111");
			System.out.println(multipartFormDataRequest.getParameter("paper"));

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

		Integer num;
		for (num = 0; num < Integer.parseInt(number); num++) {
			String digit = String.valueOf(num + 1);
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
				UploadFile uploadFile = (UploadFile) hashTable.get(digit);// ��ȡ�ϴ��ĸ���
				String fileloute = multipartFormDataRequest.getParameter(digit);
				localFileName = fileloute.substring(
						fileloute.lastIndexOf("\\") + 1, fileloute.length());
				System.out.println(localFileName + "Is it true!");
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
				url = "corpusUpload" + "/"
						+ corpusBean.getCorpusCategory(type_id) + "/"
						+ localFileName;
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
					corpusStoreFileRealPath = fileDirectory.toURL().toString()
							+ "/" + localFileName;
					corpusStoreFileRealPath = corpusStoreFileRealPath
							.substring(
									corpusStoreFileRealPath.indexOf("/") + 1,
									corpusStoreFileRealPath.length());

					FileInputStream input = null;
					FileOutputStream output = null;
					input = new FileInputStream(fileloute);
					System.out.println(fileloute);
					output = new FileOutputStream(corpusStoreFileRealPath);
					System.out.println(corpusStoreFileRealPath);
					byte[] b = new byte[1024 * 20];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}

					output.flush();
					output.close();
					input.close();
					flag = corpusBean.addCorpus2(origin, date, localFileName,
							language, corpusStoreFileRealPath, lastModifyTime,
							size, type_id, tag, type, quality);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
