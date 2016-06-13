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
		<table width = "95%" border = "0"  cellspacing = "1"  cellpadding = "3"
		       align = "center"  class = "tableBorder" >
			<tr>
				<th height = "24"  closan="1">
					<b>检验语料条件</b>
				</th>
			</tr>
			<tr>
				<th height = "24"  closan="1">
					<input type="radio" name="quality" id="quality" value="0" onclick="location.replace('Corpus0Test.jsp')"checked>无</input> 
					<input type="radio" name="quality" id="quality" value="1" onclick="location.replace('Corpus1Test.jsp')">低</input>
					<input type="radio" name="quality" id="quality" value="2" onclick="location.replace('Corpus2Test.jsp')">中</input>
					<input type="radio" name="quality" id="quality" value="3" onclick="location.replace('Corpus3Test.jsp')">高</input>
				</th>
			</tr>
			<tr>
				<th height = "24"  closan="1">文件类型：
					<input type="radio" name="type" id="type" value="All" onclick="location.replace('Corpus0Test.jsp')" >All</input>
					<input type="radio" name="type" id="type" value=".doc" onclick="location.replace('Corpus0Text_doc.jsp')">.doc</input>
					<input type="radio" name="type" id="type" value=".txt" onclick="location.replace('Corpus0Test_txt.jsp')" checked> .txt</input>
					<input type="radio" name="type" id="type" value=".xml" onclick="location.replace('Corpus0Text_xml.jsp')"> .xml</input>
					<input type="radio" name="type" id="type" value=".pdf" onclick="location.replace('Corpus0Text_pdf.jsp')"> .pdf</input>
				</th>
			</tr>
		</table>
		<table width="95%" border="0" cellspacing="1" cellpadding="3"
			align="center" class="tableBorder" >
			<tr>
				<th height="24" colspan="12">
					<b>语料信息列表</b>
				</th>
			</tr>
			<tr>
				<td width="9%" class="forumrow">
					<center>
						<u>编号</u>
					</center>
				</td>
				<td width="9%" class="forumrow">
					<center>
						<u>类别</u>
					</center>
				</td>
				<td width="9%" class="forumrow">
					<center>
						<u>加工</u>
					</center>
				</td>
				<td width="9%"  class="forumrow">
					<center>
						<u>语种</u>
					</center>
				</td>
				<td width="9%" class="forumrow">
					<center>
						<u>标题</u>
					</center>
				</td>
				<td width="9%" class="forumrow">
					<center>
						<u>来源</u>
					</center>
				</td>
				<td width="9%" class="forumrow">
					<center>
						<u>大小</u>
					</center>
				</td>
				<td width="9%" class="forumrow">
					<center>
						<u>发表日期</u>
					</center>
				</td>
				<td width="9%" class="forumrow">
					<center>
						<u>上传时间</u>
					</center>
				</td>
				<td width="9%" class="forumrow">
					<center>
						<u>文件类型</u>
					</center>
				</td>
				<td width="10%" class="forumrow">
					<center>
						<u>是否被检验</u>
					</center>
				</td>
				<td width="9%" class="forumrow">
					<center>
						<u>操作</u>
					</center>
				</td>
			</tr>
			<%
				String offset = request.getParameter("offset");
				String filepath = request.getRequestURI();
				String type = request.getParameter("type");
				int id = 0, n = 0, p = 0;
				String x = "否";
				String tempType = ".txt";
				String Type = null;
				corpusBean.setResult(offset, filepath, "corpus_details", p);
				CorpusBean tempCorpusBean = null;	
				List corpusList = corpusBean.getResult();
				
				if (corpusList != null) {
					Iterator i = corpusList.iterator();

					while (i.hasNext()) {
						tempCorpusBean = (CorpusBean) i.next();
						id = tempCorpusBean.getType_id();
						Type = tempCorpusBean.getType();					
						n++;
						if( tempCorpusBean.getCorpustag2(tempCorpusBean.getId()).equals("1") ) {
							x = "是";
						} else {
							x = "否";
						}
						if( tempType.equals(Type) ) {
			%>
			<tr>
				<td width="9%" height="25" class="forumRow">
					<center><%=n%></center>
				</td>
				<td width="9%" class="forumRow">
					<center><%=tempCorpusBean.getCorpusCategory(id)%></center>
				</td>
				<td width="9%" class="forumRow">
					<center><%=x%></center>
				</td>
				<td width="9%" height="25" class="forumRow">
					<center><%=tempCorpusBean.getLanguage()%></center>
				</td>
				<td width="9%" height="25" class="forumRow">
					<center><%=tempCorpusBean.getTitle()%></center>
				</td>
				<td width="9%" height="25" class="forumRow">
					<center><%=tempCorpusBean.getOrigin()%></center>
				</td>
				<td width="9%" class="forumRow">
					<center><%=tempCorpusBean.getSize()%></center>
				</td>
				<td width="9%" class="forumRow">
					<center><%=tempCorpusBean.getDate()%></center>
				</td>
				<td width="9%" class="forumRow">
					<center><%=tempCorpusBean.getLastModifyTime()%></center>
				</td>
				<td width="9%" class="forumRow">
					<center><%=tempCorpusBean.getType()%></center>
				</td>
				<td width="10%" class="forumRow">
					<center><%=tempCorpusBean.getTest()%></center>
				</td>
				<td width="10%" class="forumRow">
					<center>
						<a
							href="corpusServlet?method=testCorpus&id=<%=tempCorpusBean.getId()%>"
							target="_black">检验<br> 
						</a>
					</center>
				</td>
			</tr>
			<%
						}
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