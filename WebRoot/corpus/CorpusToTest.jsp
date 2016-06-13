<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="corpus.CorpusBean"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.FileReader"%>
<%@ page import="java.io.IOException"%>
<%@ page import="corpus.*" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="net.lrc.db.Mysql"%>
<jsp:useBean id="corpusBean" scope="page" class="corpus.CorpusBean"></jsp:useBean>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>语料检验</title>
	</head>
	<body>
			
			<%
			String tempId = request.getParameter("id");
			String tempusername = request.getParameter("username");
			String tempview = request.getParameter("view");
			String tempviewed = request.getParameter("viewed");
			int id = 0;
			int view = 0;
			int viewed = 0;
			int viewcount;
			int viewedcount;
			
			Mysql mysql = new Mysql();
			String sql = "select view, viewed  from userinfo where login_name='"
				+ tempusername + "'";
			ResultSet rs = mysql.executeQuery(sql);
			
			if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
				id = Integer.parseInt(tempId);		
			}		
			if( (!"".equalsIgnoreCase(tempId)) &&(null != tempview ) ) {
				view = Integer.parseInt(tempview);
				viewed = Integer.parseInt(tempviewed);
				viewcount = view - 1;
				viewedcount = viewed + 1;
				sql = "update userinfo set view=" + viewcount
					+ ",viewed='" + viewedcount
					+ "' where login_name='"+tempusername+"'";
				mysql.executeUpdate(sql);
			}
			CorpusBean cb = corpusBean.getCorpusBean(id);
			//mysql.executeUpdate(sql);
			System.out.println("dfsd"+sql);
			sql = "insert into user_view(user_name,viewid,view_name,view_date)"+ 
						  "values('"+ tempusername + "','"+ id +"','" + cb.getTitle() + "',curdate())";
			
			mysql.executeUpdate(sql);
			mysql.close();
			
			//Mysql mysql2 = new Mysql();
			//String sql2 = "insert into user_view(user_name,viewID,view_date)" + "values('"+ tempusername + "','"+ id +"','curdate()')";
			//mysql.executeUpdate(sql2);
			//mysql.close();
			
			%>
		<table width="95%" border="0" cellspacing="1" cellpadding="3"
			align="center" class="tableBorder">
			<tr>
				<th height="24" colspan="9">
					<b><%=cb.getTitle()%>></b>
				</th>
			</tr>
			
			<tr>
				<td>
				<%				
				if (cb.getUrl() == null) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(
						"<script>alert('链接的语料资源不能为空，请重试！');</script>");
					} 
				else {
				File file = new File(cb.getUrl());
				
				if(file.exists()&&file.isFile()){
				int index =cb.getUrl() .lastIndexOf(".");
				if(cb.getUrl().substring(index,cb.getUrl().length()) =="txt") {
					try{
						BufferedReader input = new BufferedReader(new FileReader(file));
						String text;
						while((text = input.readLine())!=null)
 							out.println(text+"<br>");
					}catch(IOException ioExcepion){
						System.err.println("File Error!");
					}
				} else {
					Runtime runtime = Runtime.getRuntime();   
				
					try{   
				        System.out.println(cb.getUrl());   
				        //打开文件   
				        runtime.exec("rundll32 url.dll FileProtocolHandler "+cb.getUrl());   
				    }catch(Exception ex){   
				        ex.printStackTrace();   
				    }   
				}
						
						
						/*else if(cb.getUrl().substring(index, cb.getUrl()
					.length()) =="doc")
					{
					try
					{
				
                Runtime.getRuntime().exec
("cmd /c start \"C:\\Program Files\\Microsoft Office\\OFFICE11\\WINWORD.EXEcb.getUrl()\"");
}catch(IOException ioExcepion){
						System.err.println("File Error!");}
					
					}
					else
					Runtime.getRuntime().exec("cmd /c start \"C:\\Program Files\\Microsoft Office\\OFFICE11\\WINWORD.EXEcb.getUrl()\"");*/
			
					}
				}
				%>
				</td>
			</tr>
			
			<tr>
				<td>
					
					<%="此语料当前类别:"%>
					<%=corpusBean.getCorpusCategory(cb.getType_id()) %>
				</td>
			</tr>

		</table>
	</body>
</html>