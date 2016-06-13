<%@ page language="java" import="net.lrc.model.User" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="corpus.CorpusBean"%>
<%@ page import="textBook.*"%>
<%@ include file="/admin/commons/pages/include.jsp"%>
<jsp:useBean id="corpusBean" scope="page" class="corpus.CorpusBean"></jsp:useBean>



<html>
	<head>
		<link href="corpusDownload.css" rel="stylesheet" type="text/css"/>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>课文资源列表</title>
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
		<form action="<%=request.getContextPath()%>/corpus/corpusServlet?method=deleteTextdetail" method="post" name="corpusUploadForm" id="corpusUploadForm" onSubmit="return(FormCheck())">
			<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tableBorder" onclick="javascript:checkT_F()">
				<tr>
					<th height="24" colspan="12">
						<b>教材信息列表</b>
					</th>
				</tr>
				<tr>
					<td width="5%" height="30" class="forumrow">
						<center>
							<u>编号</u>
						</center>
					</td>
					<td width="9%" height="30" class="forumrow">
						<center>
							<u>课文名称</u>
						</center>
					</td>
					<td width="9%" height="30" class="forumrow">
						<center>
							<u>作者</u>
						</center>
					</td>
					<td width="9%" height="30" class="forumrow">
						<center>
							<u>课文形式</u>
						</center>
					</td>
					<td width="9%" height="30" class="forumrow">
						<center>
							<u>类型</u>
						</center>
					</td>
					<td width="9%" class="forumrow">
						<center>
							<u>语言</u>
						</center>
					</td>
					<td width="9%" class="forumrow">
						<center>
							<u>课程标准</u>
						</center>
					</td>
					<td width="9%" class="forumrow">
						<center>
							<u>班级类型</u>
						</center>
					</td>
					<td width="9%" class="forumrow">
						<center>
							<u>学段</u>
						</center>
					</td>
					<td width="8%" class="forumrow">
						<center>
							<u>册数</u>
						</center>
					</td>
					
					<td width="100" class="forumrow">
						<center>
							<u>查看|下载</u>
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
					List<Textdetail> alltextdetail = new ArrayList<Textdetail>();
					
					
					//String edition=(String)request.getParameter("edition");
					//String curriculumstandard=(String)request.getParameter("curriculumstandard");
					//String typeofcurriculum=(String)request.getParameter("typeofcurriculum");
					//String textclass=(String)request.getParameter("textclass");
					String publisher=(String)session.getAttribute("publisher");
					String period=(String)session.getAttribute("period");
					String volume=(String)session.getAttribute("volume");
					String language=(String)session.getAttribute("language");
					
	
					query = "select textbook.*,texts.* FROM textbook,texts where textbook.publisher_j = texts.publisher and "+publisher+" and "+period+" and "+volume+" and "+language+"";
					System.out.println("query: "+query);
					//String query_utf8 = new String(query.getBytes(),"UTF-8");
					corpusBean.setResultUploadTextdetail(offset, filepath, query);
					alltextdetail = corpusBean.getResult();
					
					
					if(alltextdetail!=null) 
					{
						//Iterator i=alltextbook.iterator();
						
						for(int i = 0; i < alltextdetail.size(); i++) 
						{
							n++;
				%>
				<tr>
					<td width="5%" height="25" class="forumRow">
						<center><%=n%></center>
					</td>
					<td width="9%" class="forumRow">
						<center><%=alltextdetail.get(i).getTitle()%></center>
					</td>
					<td width="9%" height="25" class="forumRow">
						<center><%=alltextdetail.get(i).getAuthor()%></center>
					</td>
					<td width="9%" height="25" class="forumRow">
						<center><%=alltextdetail.get(i).getCharaters()%></center>
					</td>
					<td width="9%" height="25" class="forumRow">
						<center><%=alltextdetail.get(i).getDomain()%></center>
					</td>
					<td width="9%" class="forumRow">
						<center><%=alltextdetail.get(i).getLanguages()%></center>
					</td>
					<td width="9%" class="forumRow">
						<center><%=alltextdetail.get(i).getTextstandard()%></center>
					</td>
					<td width="9%" class="forumRow">
						<center><%=alltextdetail.get(i).getClasstype()%></center>
					</td>
					<td width="9%" class="forumRow">
						<center><%=alltextdetail.get(i).getPeriod()%></center>
					</td>
					<td width="8%" class="forumRow">
						<center><%=alltextdetail.get(i).getVolume()%></center>
					</td>
					
					<td width="100" class="forumRow">
						<center>
							<a href="<%=alltextdetail.get(i).getContent()%>" target="_black">查看&nbsp;|&nbsp;</a>
							<a href="<%=request.getContextPath()%>/textBookServelet?method=downloadDetail&path=<%=new String(alltextdetail.get(i).getContent().getBytes(),"utf-8")%>&fileName=<%=new String(alltextdetail.get(i).getTitle().getBytes(),"utf-8")%>">下载</a>
						</center>
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
		  		<input type="reset" name="button" id="button" value="检索课文" onclick="location.replace('searchTextDetail.jsp')"></center>
			<br>
			<center>
				<%
					out.print(corpusBean.PageLegend());
				%>
			</center>
		</form>
	</body>
</html>
