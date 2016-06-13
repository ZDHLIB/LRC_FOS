<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,corpus.CorpusCategory"%>
<%@ include file="/admin/commons/pages/include.jsp"%>
<jsp:useBean id="corpusBean" scope="page" class="corpus.CorpusBean"></jsp:useBean>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>语料类别列表</title>
	</head>
	<body>
		<table width="95%" border="0" cellspacing="1" cellpadding="3" align="center" class="tableBorder">
			<tr>
				<th height="24" colspan="8">
					<a href="<%=request.getContextPath()%>/admin/corpus/AddCategory.jsp"><b><font>添加类别</font></b>
				</th>
			</tr>
		</table>
		<table width="95%" border="0" cellspacing="1" cellpadding="3" align="center" class="tableBorder">
			<tr>
				<th height="24" colspan="3">
					<b>语料类别列表</b>
				</th>
			</tr>
			<tr>
				<td width="20%" height="30" class="forumrow">
					<center>
						<u>编号</u>
					</center>
				</td>
				<td width="20%" height="30" class="forumrow">
					<center>
						<u>类别</u>
					</center>
				</td>
				<td width="30%" class="forumrow">
					<center>
						<u>操作</u>
					</center>
				</td>
			</tr>
			<%
			    int n = 0;
				List<CorpusCategory> corpusCategoryList=corpusBean.getAllCategory();
				
				if(corpusCategoryList.size()>0)
				{
					for(int i=0;i<corpusCategoryList.size();i++) 
					{
						n++;
			%>
			<tr>
				<td width="20%" height="25" class="forumRow">
					<center><%=n%></center>
				</td>
				<td width="20%" height="25" class="forumRow">
					<center><%=corpusCategoryList.get(i).getType()%></center>
				</td>
				<td width="30%" class="forumRow">
					<center>
						<a href="corpusServlet?method=deleteCorpusCategory&type_id=<%=corpusCategoryList.get(i).getType_id()%>" onClick="if(confirm('是否删除此种语料资源类别？')){return(true);}else{return(false);}"><font color="#ff0000">删除</font></a>
					</center>
				</td>
			</tr>
			<%
					}
				}
			%>
		</table>
		<br>
	</body>
</html>