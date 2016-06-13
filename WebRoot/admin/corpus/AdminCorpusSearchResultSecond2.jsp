<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="corpus.CorpusBean"%>
<jsp:useBean id="corpusBean" scope="page" class="corpus.CorpusBean"></jsp:useBean>

<%

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
%>
<html>
	<head>
		<link href="corpusDownload.css" rel="stylesheet" type="text/css"/>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>语料资源列表</title>
	</head>
	<body>
		<div align="right">
			<a href="<%=request.getContextPath()%>/admin/corpus/AdminCorpusSearchResult2.jsp">返回</a>
		</div>
		<br>
		<table width="95%" border="0" cellspacing="1" cellpadding="3" align="center" class="tableBorder">
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
				<td width="10%" height="30" class="forumrow">
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
				<td width="10%" class="forumrow">
					<center>
						<u>操作</u>
					</center>
				</td>
			</tr>
			<%
				String idAll=(String)session.getAttribute("idAll");
				String title1=(String)session.getAttribute("title1");
				String title2=(String)session.getAttribute("title2");
				String number=(String)session.getAttribute("number");
				System.out.println("AAAAAAAAAA"+idAll+"&&&"+title1+"&&&"+title2+"&&&"+number);

				List<CorpusBean> corpusList=corpusBean.searchCorpus3(idAll,title1,title2,number);
				
				
				
				CorpusBean tempCorpusBean=null;
				
				int id=0;
				String idA[] =  new String[200000];
				for(int i=0; i<idA.length; i++) {
					idA[i] = new String();
				}
				
				int n = 0;
				
				
				if(corpusList!=null) 
				{
					Iterator i=corpusList.iterator();
					
					while(i.hasNext()) 
					{
						tempCorpusBean=(CorpusBean)i.next();
						id=tempCorpusBean.getType_id();
						
						idA[n] = String.valueOf(tempCorpusBean.getId());
						n++;
						
			%>
			<tr>
				<td width="10%" height="25" class="forumRow">
					<center><%=tempCorpusBean.getId()%></center>
				</td>
				<td width="10%" class="forumRow">
					<center><%=tempCorpusBean.getCorpusCategory(id) %></center>
				</td>
				<td width="10%" height="25" class="forumRow">
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
				<td width="10%" class="forumRow">
					<center>
						<a href="<%=request.getContextPath()%>/corpus/corpusServlet?method=searchResult&id=<%=tempCorpusBean.getId()%>" target="_black">查看</a> |
						<a href="<%=request.getContextPath()%>/corpus/corpusServlet?method=exportCorpus&id=<%=tempCorpusBean.getId()%>">下载</a>
					</center>
				</td>
			</tr>
			<%
					}
				}
				String idAll1 = new String();
				idAll1 = idA[0];
				for(int i=1;i<n;i++){
					idAll1 += "-"+idA[i];
				} 

				
			%>
		</table>
		<br>
		<center>
					<a href="<%=request.getContextPath()%>/corpus/corpusServlet?method=exportCorpusAll&idAll=<%=idAll1%>&number=<%=n%>">下载全部语料</a> 
					
		</center><br>
		<center>
			<%
				out.print("共"+n+"条");
			%>
		</center>
	</body>
</html>
