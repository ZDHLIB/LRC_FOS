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
			<a href="<%=request.getContextPath()%>/admin/corpus/AdminCorpusSearch.jsp">返回</a>
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
				String offset=request.getParameter("offset");
				String filepath=request.getRequestURI();
				String relationship=(String)session.getAttribute("relationship");
				String date=(String)session.getAttribute("date");
				String origin=(String)session.getAttribute("origin");
				String language=(String)session.getAttribute("language");
				String title=(String)session.getAttribute("title");
				String author=(String)session.getAttribute("author");
				String date2=(String)session.getAttribute("date2");
				String category=(String)session.getAttribute("category");
				String factors =(String)session.getAttribute("factors");
				corpusBean.searchCorpus2(relationship,offset,filepath,category,language,date,date2,origin,title,author,factors);
				CorpusBean tempCorpusBean=null;
				
				int id=0;
				String idA[] =  new String[200000];
				for(int i=0; i<idA.length; i++) {
					idA[i] = new String();
				}
				
				int n = 0,x=0,y=0;
				List corpusList=corpusBean.getResult();
				
				
				if(corpusList!=null){
					Iterator xc=corpusList.iterator();
					while(xc.hasNext())
					{
						tempCorpusBean=(CorpusBean)xc.next();
						String temp = tempCorpusBean.getTitle().substring(tempCorpusBean.getTitle().indexOf("-")+1, tempCorpusBean.getTitle().length());
						if(temp.indexOf("-") == -1){					
							x++;
						}else{
							y++;
						}
					}
				
				}
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
					<center><%=n%></center>
				</td>
				<td width="10%" class="forumRow">
					<center><%=tempCorpusBean.getCorpusCategory(id) %></center>
				</td>
				<td width="10%" class="forumRow">
					<center><%=tempCorpusBean.getCorpustag2(tempCorpusBean.getId()) %></center>
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
				String idAll = new String();
				idAll = idA[0];
				for(int i=1;i<n;i++){
					idAll += "-"+idA[i];
				} 
				String nx = String.valueOf(n);
				
				
				
			%>
		</table>
		<br><center>
			<form id="corpusSearchForm" name="corpusSearchForm" method="post"
				action="<%=request.getContextPath()%>/corpus/corpusServlet?method=searchCorpus3&idAll=<%=idAll%>&number=<%=nx%>">
				<table>
					<tr>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<td>
									<div align="right">
										按百分比搜索：
									</div>
								</td>
								<td>
									<label>
										<input name="title1" type="text" id="title1" size="10"
											maxlength="80" />
									</label>%
								</td>
								<%="&nbsp;&nbsp;"%>
								<td>
									<div align="right">
										按确定数量搜索：
									</div>
								</td>
								
								<td>
									<label>
										<input name="title2" type="text" id="title2" size="10"
											maxlength="80" />
									</label>(请勿超出已搜索出的数量总和，且两者只选其一)
								</td>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<td>
									<div align="right">
										<label>
											<input type="submit" name="submit" id="submit" value="搜索" />
										</label>
									</div>
								</td>
								<td>
									<label>
										<input type="reset" name="cancel" id="cancel" value="取消" />
									</label>
								</td>
					</tr>
					
				</table>
			</form></center>
			
		<center>
					<a href="<%=request.getContextPath()%>/corpus/corpusServlet?method=exportCorpusAll&idAll=<%=idAll%>&number=<%=n%>">下载全部语料</a> 
					
		</center><br>
		<center>
			<%
				out.print(corpusBean.PageLegend2());
			%>
		</center>
	</body>
</html>
