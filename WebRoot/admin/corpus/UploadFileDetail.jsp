<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="corpus.CorpusBean"%>
<%@ include file="/admin/commons/pages/include.jsp"%>
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

		<title>My JSP 'UploadFileDetail.jsp' starting page</title>

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
			/*
			"UploadFileDetail.jsp?paths=" + paths
						+ "&origin=" + origin + "&author=" + author + "&language="
						+ language + "&tag=" + tag + "&year=" + year + "&month="
						+ month + "&day=" + day + "&quality=" + quality
						+ "&corpusCategory=" + corpusCategory
			 */
			ArrayList<String> list = new ArrayList<String>();
			list = (ArrayList<String>) session.getAttribute("paths");
			String origin = (String) session.getAttribute("origin");
			String language = (String) session.getAttribute("language");
			String year = (String) session.getAttribute("year");
			String month = (String) session.getAttribute("month");
			String day = (String) session.getAttribute("day");
			String quality = (String) session.getAttribute("quality");
			String tag = (String) session.getAttribute("tag");
			String corpusCategory = (String) session
					.getAttribute("corpusCategory");
			String date = year + "-" + month + "-" + day;
			String filename = null;
		%>

		<form
			action="<%=request.getContextPath()%>/SecondUploadCorpusFloder2.do"
			method="post" enctype="multipart/form-data" name="corpusUploadForm"
			id="corpusUploadForm" onSubmit="return(FormCheck())">
			<table width="95%" border="0" cellspacing="1" cellpadding="3"
				align="center" class="tableBorder">
				<tr>
					<th height="24" colspan="8">
						<b>上传语料信息列表</b>
					</th>
				</tr>

				<tr>
					<td width="25" height="30" class="forumrow">
						<center>
							<u>编号</u>
						</center>
					</td>
					<td width="251" height="30" class="forumrow">
						<center>
							<u>语料路径</u>
						</center>
					</td>
					<td width="55" height="30" class="forumrow">
						<center>
							<u>类别</u>
						</center>
					</td>
					<td width="80" geight="30" class="forumrow">
						<center>
							<u>是否加工</u>
						</center>
					</td>
					<td width="55" height="30" class="forumrow">
						<center>
							<u>语种</u>
						</center>
					</td>
					<td width="55" class="forumrow">
						<center>
							<u>来源</u>
						</center>
					</td>
					<td width="150" class="forumrow">
						<center>
							<u>上传时间</u>
						</center>
					</td>
					<td width="55" class="forumrow">
						<center>
							<u>閾值</u>
						</center>
					</td>
				</tr>
				<%
					/*
						String offset = request.getParameter("offset");
						String filepath = request.getRequestURI();
						corpusBean.setResult(offset, filepath);
						CorpusBean tempCorpusBean = null;
						int id = 0, n = 0;
						String x = "否";
						List corpusList = corpusBean.getResult();

						if (corpusList != null) {
							Iterator i = corpusList.iterator();

							while (i.hasNext()) {
								tempCorpusBean = (CorpusBean) i.next();
								id = tempCorpusBean.getType_id();
								n++;
								if (tempCorpusBean.getCorpustag2(tempCorpusBean.getId())
										.equals("1")) {
									x = "是";
								} else {
									x = "否";
								}
					 */

					int n = 0;
					int i = 0;
					String name = null;

					String offset = request.getParameter("offset");
					String filepath = request.getRequestURI();
					corpusBean.setResult(offset, filepath);

					if (list != null) {

						Iterator<String> it = list.iterator();

						while (it.hasNext()) {
							name = it.next();
							n++;
							filename = String.valueOf(n);
				%>
				<tr>
					<td width="20" height="25" class="forumRow">
						<center><%=n%></center>
					</td>
					<td width="251" height="25" class="forumRow">
						<center>
							<input type="text" style="width: 251px" name=<%=filename%>
								value=<%=name%> readonly />
						</center>
					</td>
					<td width="55" class="forumRow">
						<center>
							<input type="text" style="width: 55px" name="category"
								value=<%=corpusCategory%> readonly />
						</center>
					</td>
					<td width="80" class="forumRow">
						<center>
							<input type="text" style="width: 80px" name="tag" value=<%=tag%>
								readonly />
						</center>
					</td>
					<td width="55" height="25" class="forumRow">
						<center>
							<input type="text" style="width: 55px" name="language"
								value=<%=language%> readonly />
						</center>
					</td>
					<td width="55" height="25" class="forumRow">
						<center>
							<input type="text" style="width: 55px" name="origin"
								value=<%=origin%> readonly />
						</center>
					</td>
					<td width="150" class="forumRow">

						<input type="text" style="width: 35px" name="year" value=<%=year%> readonly >-<input type="text" style="width: 25px" name="month" value=<%=month%> readonly >-<input type="text" style="width: 25px" name="day" value=<%=month%>
							readonly />

					</td>
					<td width="55" class="forumRow">
						<center>
							<input type="text" style="width: 55px" name="quality"
								value=<%=quality%> readonly />
						</center>
					</td>
				</tr>
				<%
					}
					}
				%>

				<tr>
					
					<center>
						<%
							out.print(corpusBean.PageLegend());
						%>
					</center>
					
				</tr>
				<tr>
					
					上传文件总数：<input type="text" name="number" value="<%=filename%>"/>个！
					
				</tr>
				<tr>
					<td>
						<label>
							<input type="submit" name="submit" id="submit" value="确认上传" />
						</label>
					</td>
					<td>
						<label>
							<input type="reset" name="cancel" id="cancel" value="取消" />
						</label>
					</td>
				</tr>
			</table>


		</form>
	</body>
</html>
