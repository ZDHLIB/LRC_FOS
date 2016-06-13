<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="corpus.CorpusBean"%>
<%@ include file="/admin/commons/pages/include.jsp"%>
<jsp:useBean id="corpusBean" scope="page" class="corpus.CorpusBean"></jsp:useBean>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>语料信息列表</title>
	</head>
	<body>
		<table width="95%" border="0" cellspacing="1" cellpadding="3"
			align="center" class="tableBorder">
			<tr>
				<th height="24" colspan="10">
					<a
						href="<%=request.getContextPath()%>/admin/corpus/UploadCorpus.jsp"><b>上传语料</font>
					</b>
				</th>
			</tr>
		</table>
		<table width="95%" border="0" cellspacing="1" cellpadding="3"
			align="center" class="tableBorder">
			<tr>
				<th height="24" colspan="10">
					<b>语料信息列表</b>
				</th>
			</tr>
			<tr>
				<td width="10%" height="30" class="forumrow">
					<center>
						<u>编号</u>
					</center>
				</td>
				<td width="10%" height="30" class="forumrow">
					<center>
						<u>类别</u>
					</center>
				</td>
				<td width = "10%" geight = "30" class = "forumrow">
					<center>
						<u>是否加工</u>
					</center>
				</td>
				<td width="10%" height="30" class="forumrow">
					<center>
						<u>语种</u>
					</center>
				</td>
				<td width="10%" height="30" class="forumrow">
					<center>
						<u>标题</u>
					</center>
				</td>
				<td width="10%" class="forumrow">
					<center>
						<u>来源</u>
					</center>
				</td>
				<td width="10%" class="forumrow">
					<center>
						<u>大小</u>
					</center>
				</td>
				<td width="10%" class="forumrow">
					<center>
						<u>发表日期</u>
					</center>
				</td>
				<td width="10%" class="forumrow">
					<center>
						<u>上传时间</u>
					</center>
				</td>
				<td width="20%" class="forumrow">
					<center>
						<u>操作</u>
					</center>
				</td>
			</tr>
			<%
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
						if( tempCorpusBean.getCorpustag2(tempCorpusBean.getId()).equals("1") ) {
							x = "是";
						} else {
							x = "否";
						}
			%>
			<tr>
				<td width="10%" height="25" class="forumRow">
					<center><%=n%></center>
				</td>
				<td width="10%" height = "25" class="forumRow">
					<center><%=tempCorpusBean.getCorpusCategory(id)%></center>
				</td>
				<td width="10%" class = "forumRow">
					<center><%=x%></center>
				</td>
				<td width="10%" class="forumRow">
					<center><%=tempCorpusBean.getLanguage()%></center>
				</td>
				<td width="10%" height="25" class="forumRow">
					<center><%=tempCorpusBean.getTitle()%></center>
				</td>
				<td width="10%" height="25" class="forumRow">
					<center><%=tempCorpusBean.getOrigin()%></center>
				</td>
				<td width="10%" class="forumRow">
					<center><%=tempCorpusBean.getSize()%></center>
				</td>
				<td width="10%" class="forumRow">
					<center><%=tempCorpusBean.getDate()%></center>
				</td>
				<td width="10%" class="forumRow">
					<center><%=tempCorpusBean.getLastModifyTime()%></center>
				</td>
				<td width="20%" class="forumRow">
					<center>
						<a
							href="corpusServlet?method=openCorpus&id=<%=tempCorpusBean.getId()%>"
							target="_black">查看&nbsp;&nbsp;</a>
						<a
							href="corpusServlet?method=deleteCorpus&id=<%=tempCorpusBean.getId()%>"
							onClick="if(confirm('是否删除此条语料资源？')){return(true);}else{return(false);}"><font
							color="#ff0000">删除</font>
						</a>
					</center>
				</td>
			</tr>
			<%
				}
				}
			%>
		</table>
		<br>
		<center>
			<%
				out.print(corpusBean.PageLegend());
			%>
		</center>
	</body>
</html>