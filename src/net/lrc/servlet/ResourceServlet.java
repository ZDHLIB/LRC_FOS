package net.lrc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadFile;

import net.lrc.javabean.CommonBean;
import net.lrc.javabean.ResourceBean;
import net.lrc.model.Language;
import net.lrc.model.ResourceKind;
import net.lrc.util.DownloadDetails;
import net.lrc.model.*;

import uploadutilities.FileMover;

public class ResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 5129331056260291731L;
	ServletContext servletContext;

	public ResourceServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		servletContext = config.getServletContext();
	}

	public void destroy() {
		super.destroy();
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

		if (method == null) {
			PrintWriter out = response.getWriter();
			out.println("���ʷ�ʽ���ԣ�");
		} else if (method.equals("add")) {
			addResource(request, response);
		} else if (method.equals("delete")) {
			deleteResource(request, response);
		} else if (method.equals("export")) {
			export(request, response);
		} else if (method.equals("list")) {
			list(request, response);
		} else if (method.equals("getResource")) {
			getResource(request, response);
		} else if (method.equals("updateStatus")) {
			updateStatus(request, response);
		} else if (method.equals("addkind")) {
			addkind(request, response);
		} else if (method.equals("deletekind")) {
			deletekind(request, response);
		} else if (method.equals("updatekind")) {
			updatekind(request, response);
		} else if (method.equals("listkind")) {
			listkind(request, response);
		} else if (method.equals("showkind")) {
			showkind(request, response);
		}
	}

	// �����Դ����
	private void addkind(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String kindname = request.getParameter("kindname");
		String kindinfo = request.getParameter("kindinfo");
		ResourceBean bean = new ResourceBean();

		if (bean.addResourceKind(kindname, kindinfo)) {
			listkind(request, response);
		}
	}

	// �޸���Դ������Ϣ
	private void updatekind(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int kindid = Integer.parseInt((String) request.getParameter("id"));
		String kindname = request.getParameter("kindname");
		String kindinfo = request.getParameter("kindinfo");
		ResourceBean bean = new ResourceBean();

		if (bean.modifyResourceKind(kindid, kindname, kindinfo)) {
			listkind(request, response);
		}
	}

	// ɾ����Դ����
	private void deletekind(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int kindid = Integer.parseInt((String) request.getParameter("id"));
		ResourceBean bean = new ResourceBean();

		if (bean.deleteResourceKind(kindid)) {
			listkind(request, response);
		}
	}

	// ��ȡָ����Դ������Ϣ
	private void showkind(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int kindid = Integer.parseInt((String) request.getParameter("id"));
		ResourceBean bean = new ResourceBean();
		ResourceKind resourceKind = bean.getkind(kindid);
		request.setAttribute("resourceKind", resourceKind);
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/admin/upload/resourceKind.jsp").forward(
					request, response);
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/admin/Tibet/upload/resourceKind.jsp").forward(
					request, response);
		}
	}

	// ��ʾ������Դ����
	@SuppressWarnings("unchecked")
	private void listkind(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ResourceBean bean = new ResourceBean();
		List kindList = bean.getAllkind();
		request.setAttribute("kindList", kindList);
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/admin/upload/kindlist.jsp").forward(
					request, response);
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/admin/Tibet/upload/kindlist.jsp").forward(
					request, response);
		}
	}

	// �ϴ���Դ
	@SuppressWarnings("unchecked")
	protected void addResource(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		FileMover fileMover = new FileMover();
		UploadBean upBean = new UploadBean();
		MultipartFormDataRequest mrequest = null;
		Hashtable files = null;
		@SuppressWarnings("unused")
		int iFileCount = 0;
		String sLocalFileName = null;
		String sServerFileName = null;
		String info = null;
		String sExt = null;
		String part = null;
		String url = null;
		String size = null;

		if (MultipartFormDataRequest.isMultipartFormData(request)) {
			try {
				mrequest = new MultipartFormDataRequest(request, null,
						1024 * 1024 * 1024, MultipartFormDataRequest.COSPARSER,
						"utf-8");
			} catch (UploadException e) {
				e.printStackTrace();
			}

			sServerFileName = mrequest.getParameter("title");
			info = mrequest.getParameter("info");
			part = mrequest.getParameter("part");
			files = mrequest.getFiles();
		}

		String sWebRootPath = servletContext.getRealPath("/");
		String sPath = sWebRootPath + "uploadFiles";
		sPath = sPath + "\\part" + part;
		// �ļ���ȡ
		if ((files != null) || (!files.isEmpty())) {
			iFileCount = files.size();// �ϴ��ļ�����Ŀ
			UploadFile file = (UploadFile) files.get("attach");// ��ȡ�ϴ��ĸ���
			sLocalFileName = file.getFileName();

			if ((file.getFileSize()) >= (1024 * 1024))// �ļ�����1M
			{
				double ssize = (double) ((file.getFileSize()) / (double) (1024 * 1024));

				if (ssize > 1024) {
					response
							.getWriter()
							.write(
									"<script>alert('�����ϴ��ļ����ܳ���1G��');history.go(-1);</script>");
				}

				NumberFormat f = NumberFormat.getNumberInstance();// ����һ�����ݸ�ʽ����
				f.setMaximumFractionDigits(2);// ����С�����������ʾ����ĿΪ2
				String s = f.format(ssize);// ����f��format����,��ʽ��ssize,����һ���ַ�������
				size = s + "M";
			} else// �ļ�С��1M
			{
				double ssize = (double) ((file.getFileSize()) / (double) (1024));
				NumberFormat f = NumberFormat.getNumberInstance();// ����һ�����ݸ�ʽ����
				f.setMaximumFractionDigits(2);// ����С�����������ʾ����ĿΪ2
				String s = f.format(ssize);// ����f��format����,��ʽ��x,����һ���ַ�������
				size = s + "K";
			}
			// ȡ�ļ����ĺ�׺
			int ii = sLocalFileName.lastIndexOf(".");
			sExt = sLocalFileName.substring(ii, sLocalFileName.length());
			File dir = new File(sPath);

			if (!dir.exists()) {
				dir.mkdirs();
			}

			try {
				upBean.setFolderstore(sPath);// ����Ҫ�ϴ���Ŀ¼
				upBean.addUploadListener(fileMover);// ����fileMover����
				upBean.setFilesizelimit(1024 * 1024 * 1024);
				upBean.store(mrequest, "attach");
			} catch (UploadException e) {
				e.printStackTrace();
			}

			// �ϴ�
			ResourceBean bean = new ResourceBean();
			url = "uploadFiles/" + part + "/" + sLocalFileName;
			boolean isAdded = false;

			try {
				net.jtaq.utils.AdminDetails admin = (net.jtaq.utils.AdminDetails) request
						.getSession().getAttribute("admin");
				int xgryid = admin.getAdminID();
				int lrryid = admin.getAdminID();
				isAdded = bean.addDownloads(sServerFileName, sLocalFileName,
						info, url, sExt, part, size, lrryid, xgryid);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (isAdded) {
				list(request, response);
			} else {
			}
		}
	}

	// ɾ��ָ����Դ
	protected void deleteResource(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String resourceid = request.getParameter("id");
		String message = "";
		CommonBean combean = new CommonBean();
		String url = combean.getResourceurl(resourceid);

		if (url == null) {
			message = "�ļ������Ӳ���Ϊ��";
		} else {
			String path = servletContext.getRealPath("/");
			java.io.File fileName = new java.io.File(path, url);

			if (fileName.exists()) {
				if (fileName.delete()) {
					message = "ɾ���ɹ�";
				} else {
					message = "ɾ���ļ�����ʧ�ܣ�";
					System.out.println(message);
				}
			} else {
				System.out.println("��Ҫɾ�����ļ������ڣ�");
			}
		}

		int id = 0;

		if ((!"".equalsIgnoreCase(resourceid)) && (null != resourceid)) {
			id = Integer.parseInt(resourceid);
		}

		ResourceBean bean = new ResourceBean();

		try {
			bean.deleteDownloads(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		list(request, response);
	}

	// ���ͨ��ָ����Դ
	protected void updateStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String resourceid = request.getParameter("id");
		int id = 0;

		if ((!"".equalsIgnoreCase(resourceid)) && (null != resourceid)) {
			id = Integer.parseInt(resourceid);
		}

		ResourceBean bean = new ResourceBean();

		try {
			bean.updateDownloadsStatus(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		list(request, response);
	}

	// ��ȡָ����Դ����ϸ��Ϣ
	protected void getResource(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		DownloadDetails dd = null;
		String resourceid = request.getParameter("id");

		if ((!"".equalsIgnoreCase(resourceid)) && (null != resourceid)) {
			id = Integer.parseInt(resourceid);
		}

		ResourceBean bean = new ResourceBean();

		try {
			dd = bean.getDownloadsDetails(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("resource", dd);
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/download/DownloadDetails.jsp").forward(
					request, response);
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/Tibet/download/DownloadDetails.jsp").forward(
					request, response);
		}		
	}

	//��ʾ������Դ��Ϣ
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(Language.language.equals("china")){
			request.getRequestDispatcher("/admin/upload/list.jsp").forward(request,
					response);
		}else if(Language.language.equals("tibet")){
			request.getRequestDispatcher("/admin/Tibet/upload/list.jsp").forward(request,
					response);
		}
	}

	//����ָ����Դ
	protected void export(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String resourceid = request.getParameter("id");
		int id = 0;

		if ((!"".equalsIgnoreCase(resourceid)) && (null != resourceid)) {
			id = Integer.parseInt(resourceid);
		}

		ResourceBean bean = new ResourceBean();
		DownloadDetails dd = null;

		try {
			dd = bean.getDownloadsDetails(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String filename = dd.getName();// ȡ���ļ���
		String filepath = dd.getUrl();// ȡ���ļ�·��
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		try {
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment;filename="
					+ filename);
			bis = new java.io.BufferedInputStream(new java.io.FileInputStream(
					servletContext.getRealPath(filepath)));
			bos = new java.io.BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		doGet(request, response);
	}
}
