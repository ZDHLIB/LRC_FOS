<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="net.lrc.db.Mysql" %>
<%@page import="java.sql.*" %>
<%@page import="com.mysql.jdbc.Driver" %>
<%@ page import="corpus.CorpusBean"%>
<jsp:useBean id="corpusBean" scope="page" class="corpus.CorpusBean"></jsp:useBean>

<%
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
%>
<html>
	<head>
		<link href="style02.css" rel="stylesheet" type="text/css"/>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>语料操作历史</title>
	</head>
	<body>
		<div align="right">
			<a href="../index.jsp">返回</a>
		</div>
		<br>
		<table width="95%" border="0" cellspacing="1" cellpadding="3" align="center" class="tableBorder">
			<tr>
				<th height="24" colspan="10">
					<b>语料操作历史</b>
				</th>
			</tr>
			<tr>
				<th height="24" colspan="10">
					<%String name = request.getParameter("username"); %>
					<a href="<%=request.getContextPath()%>/users/history.jsp?username=<%=name%>"><strong>语料查看历史记录</strong></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="<%=request.getContextPath()%>/users/historydown.jsp?username=<%=name%>"><strong>语料下载历史记录</strong></a>
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
						<u>标题</u>
					</center>
				</td>
				<td width="10%" class="forumrow">
					<center>
						<u>日&nbsp;期</u>
					</center>
				</td>
				<td width="10%" class="forumrow">
					<center>
						<u>操作</u>
					</center>
				</td>
			</tr>
			<%
					//int n = 1;
					//String username = request.getParameter("username");
					//String title = "null";
					//Mysql mysql = new Mysql();
					//String sql = "select viewID from user_view where user_name=" + username;
					//logger.info( sql );
					//int viewID = Integer.parseInt(sql);
					//CorpusBean corpusHistory = null;
					//title = corpusBean.viewHistory(username);
					//String sql1 = "select title from corpus_details where id=" + sql;
					//String sql2 = "select view_date from user_view where user_name=" + username;
					//Iterator xc=corpusList.iterator();
					//while (resultSet.next()) {
					//	corpusCategory = new CorpusCategory();
					//	corpusCategory.setType_id(resultSet.getInt("type_id"));
					//	corpusCategory.setType(resultSet.getString("type"));
					//	allCategory.add(corpusCategory);
					//}
					int n = 0;
					//String name = request.getParameter("username");
					String title = "null";
				    PreparedStatement pst = null;   //数据库操作
				    ResultSet rs = null;   //结果集处理		
				    ResultSet rs2 = null;		
					String driverName = "com.mysql.jdbc.Driver";
				    String userName = "root";
				    String passWord = "123456";
				    String dbName = "lrc_os";					
					String url = "jdbc:mysql://localhost/" + dbName +"?user=" + userName +"&password=" +passWord;
				    Class.forName(driverName).newInstance();
				    Connection conn = DriverManager.getConnection(url);
				    Statement stat = conn.createStatement();    
			
				 try{
				  String id = null;
				  String data = null;
				  String sql = "select * from user_view where user_name = '" + name + "'";
				  pst = conn.prepareStatement(sql);
				  rs = pst.executeQuery();

			    while(rs.next()){
			        id = rs.getString(3);
			        title = rs.getString(4);
			        //data = rs.getDate(4);
			       // String sql2 = "select * from corpus_details where id = "+id;
			       // pst = conn.prepareStatement(sql);
			       // rs2 = pst.executeQuery();
			       // title = rs2.getString(3);
			        n++;
			    // String sPWD = rs.getString(2);
			   
			%>
			   <tr>
				<td width="10%" height="25" class="forumRow">
					<center><%=n%></center>
				</td>
				<td width="10%" class="forumRow">
					<center><%=title%></center>
				</td>	
				<td width="10%" class="forumRow">
					<center><%=rs.getDate(5)%></center>
				</td>		
				<td width="10%" class="forumRow">
					<center><a href="<%=request.getContextPath()%>/textBookServelet?method=viewDetail2&id=<%=id%>&username=<%=name%>">查看</a></center>
				</td>			
			</tr>
			<%
			    }
			   pst.close();
			   rs.close(); 
			   conn.close();
			   }catch(Exception e){

			 }
			  
			%>
			  
			  </table>
		</table>
		<br>
		<br>

	</body>
</html>
