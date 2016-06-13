<%@ page language="java" import="net.lrc.model.User" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="corpus.CorpusBean"%>
<%@ page import="textBook.Textbook"%>
<%@ include file="/admin/commons/pages/include.jsp"%>
<jsp:useBean id="corpusBean" scope="page" class="corpus.CorpusBean"></jsp:useBean>



<html>
	<head>
		<link href="corpusDownload.css" rel="stylesheet" type="text/css"/>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>教材资源列表</title>
		<script type="text/javascript">
			function allCheck(){
			    var obj=document.getElementsByTagName("input");
			    if(document.getElementById("alltag").checked==true){
			        for(var i=0;i<obj.length;i++){
			            obj[i].checked=true;
			        }
			    }else{
			        for(var i=0;i<obj.length;i++){
			            obj[i].checked=false;
			        }
			    }
			}
			function checkT_F(){
			    var obj=document.getElementsByTagName("input");
			    var j=0;
			    for(var i=0;i<obj.length;i++){
			        if(obj[i].id!='alltag'){    //如果是复选框
			            if(obj[i].checked==true){    //并且为选中
			                j++;
			            }
			        }
			    }
			    if(j==(obj.length-1)){    //如果复选框选中的数量等于（复选框总和减去全选这个选框的数量）
			        document.getElementById("alltag").checked=true; //全选被激活
			    }else{
			        document.getElementById("alltag").checked=false;    //取消全选
			    }
			}
		</script>
	</head>
	<body>
		<form action="<%=request.getContextPath()%>/corpus/corpusServlet?method=deleteTextbook" method="post" name="corpusUploadForm" id="corpusUploadForm" onSubmit="return(FormCheck())">
			<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tableBorder" onclick="javascript:checkT_F()">
				<tr>
					<th height="24" colspan="11">
						<b>教材信息列表</b>
					</th>
				</tr>
				<tr>
					<td width="5%" height="30" class="forumrow">
						<center>
							<u>编号</u>
						</center>
					</td>
					<td width="10%" height="30" class="forumrow">
						<center>
							<u>课本名称</u>
						</center>
					</td>
					<td width="10%" height="30" class="forumrow">
						<center>
							<u>出版社</u>
						</center>
					</td>
					<td width="10%" height="30" class="forumrow">
						<center>
							<u>出版时间</u>
						</center>
					</td>
					<td width="10%" height="30" class="forumrow">
						<center>
							<u>课程标准</u>
						</center>
					</td>
					<td width="10%" class="forumrow">
						<center>
							<u>课程性质</u>
						</center>
					</td>
					<td width="10%" class="forumrow">
						<center>
							<u>学习阶段</u>
						</center>
					</td>
					<td width="10%" class="forumrow">
						<center>
							<u>上传时间</u>
						</center>
					</td>
					<td width="10%" class="forumrow">
						<center>
							<u>册数</u>
						</center>
					</td>
					<td width="8%" class="forumrow">
						<center>
							<u>语言</u>
						</center>
					</td>
				</tr>
				<%
					int id=0, n = 0;
					String alltag=(String)session.getAttribute("alltag");
					//System.out.println("alltag is :"+alltag);
					String query = "";
					String offset = request.getParameter("offset");
					String filepath = request.getRequestURI();
					List<Textbook> alltextbook = new ArrayList<Textbook>();
					
					
					//String edition=(String)request.getParameter("edition");
					//String curriculumstandard=(String)request.getParameter("curriculumstandard");
					//String typeofcurriculum=(String)request.getParameter("typeofcurriculum");
					//String textclass=(String)request.getParameter("textclass");
					String publisher=(String)session.getAttribute("publisher");
					String period=(String)session.getAttribute("period");
					String volume=(String)session.getAttribute("volume");
					String language=(String)session.getAttribute("language");
					
					if( publisher.equals("-1")&&period.equals("-1")&&volume.equals("-1")&&language.equals("-1") ) {
						query = "select * FROM textbook order by id desc";
						corpusBean.setResultUploadTextbook(offset, filepath, query);
						alltextbook=corpusBean.getResult();
					} else {
						
						query = "select * FROM textbook where publisher='"+publisher+"' and period='"+period+"' and volume='"+volume+"' and languages='"+language+"' order by id";
						System.out.println("query: "+query);
						//String query_utf8 = new String(query.getBytes(),"UTF-8");
						corpusBean.setResultUploadTextbook(offset, filepath, query);
						alltextbook = corpusBean.getResult();
					}
					
					if(alltextbook!=null) 
					{
						//Iterator i=alltextbook.iterator();
						
						for(int i = 0; i < alltextbook.size(); i++) 
						{
							n++;
				%>
				<tr>
					<td width="5%" height="25" class="forumRow">
						<center><%=n%></center>
					</td>
					<td width="10%" class="forumRow">
						<center><%=alltextbook.get(i).getBookName()%></center>
					</td>
					<td width="10%" height="25" class="forumRow">
						<center><%=alltextbook.get(i).getPublisher()%></center>
					</td>
					<td width="10%" height="25" class="forumRow">
						<center><%=alltextbook.get(i).getPublishtime()%></center>
					</td>
					<td width="10%" height="25" class="forumRow">
						<center><%=alltextbook.get(i).getCurriculumstandard()%></center>
					</td>
					<td width="10%" class="forumRow">
						<center><%=alltextbook.get(i).getTypeofcurriculum()%></center>
					</td>
					<td width="10%" class="forumRow">
						<center><%=alltextbook.get(i).getTextclass()%></center>
					</td>
					<td width="10%" class="forumRow">
						<center><%=alltextbook.get(i).getPeriod()%></center>
					</td>
					<td width="10%" class="forumRow">
						<center><%=alltextbook.get(i).getVolume()%></center>
					</td>
					<td width="8%" class="forumRow">
						<center><%=alltextbook.get(i).getLanguage()%></center>
					</td>
				</tr>
				<%
						}
					}
				%>
			</table>
			<br/>
			<center>
				<input type="reset" name="cancel" id="cancel" value="取消" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		<input type="reset" name="button" id="button" value="检索教材" onclick="location.replace('searchTextbook.jsp')"></center>
			<br>
			<center>
				<%
					out.print(corpusBean.PageLegend());
				%>
			</center>
		</form>
	</body>
</html>
