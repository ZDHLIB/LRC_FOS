<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="corpus.CorpusBean"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.FileReader"%>
<%@ page import="java.io.IOException"%>
<%@ page import="corpus.*"%>
<jsp:useBean id="corpusBean" scope="page" class="corpus.CorpusBean"></jsp:useBean>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<%
			String tempId = request.getParameter("id");
			int id = 0;
			if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
				id = Integer.parseInt(tempId);
			}
			CorpusBean cb = corpusBean.getCorpusBean(id);
		%>
		<%
			String type = cb.getType();
			if (cb.getUrl() == null) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(
						"<script>alert('链接的语料资源不能为空，请重试！');</script>");
			} else {
				File file = new File(cb.getUrl());

				if (file.exists() && file.isFile()) {
					int index = cb.getUrl().lastIndexOf(".");
					System.out.println("type*********"+cb.getUrl().substring(index+1, cb.getUrl().length()));
					if (cb.getUrl().substring(index+1, cb.getUrl().length()).equals("txt")							|| cb.getUrl().substring(index+1,
									cb.getUrl().length()).equals("xml")) {
						try {
							BufferedReader input = new BufferedReader(
									new FileReader(file));
							String text;
							while ((text = input.readLine()) != null)
								out.println(text + "<br>");
						} catch (IOException ioExcepion) {
							System.err.println("File Error!");
						}
					} else {
						Runtime runtime = Runtime.getRuntime();

						try {
							//CorpusBean cb2 = corpusBean.getCorpusBean(id);
							System.out.println(cb.getUrl());
							//打开文件   
							runtime.exec("rundll32 url.dll FileProtocolHandler "
											+ cb.getUrl());
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		%>
	</body>
</html>
