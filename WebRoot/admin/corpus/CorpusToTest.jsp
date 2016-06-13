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
<jsp:useBean id="corpusBean" scope="page" class="corpus.CorpusBean"></jsp:useBean>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>语料检验</title>
		<script type="text/javascript">
			function Reload() {
				window.opener.location.reload();
				window.close();
			}
		</script>
	</head>
	<body>
			<%
			String tempId = request.getParameter("id");
			int id = 0;
			if ((!"".equalsIgnoreCase(tempId)) && (null != tempId)) {
				id = Integer.parseInt(tempId);
			}		
			CorpusBean cb = corpusBean.getCorpusBean(id);
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
				String type = cb.getType();		
				if (cb.getUrl() == null) {
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write(
							"<script>alert('链接的语料资源不能为空，请重试！');</script>");
				} else {
					File file = new File(cb.getUrl());
					
					if(file.exists()&&file.isFile()) {
						int index =cb.getUrl() .lastIndexOf(".");
						if(cb.getUrl().substring(index,cb.getUrl().length()) =="txt"||cb.getUrl().substring(index,cb.getUrl().length()) =="xml") {
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
								//CorpusBean cb2 = corpusBean.getCorpusBean(id);
						        System.out.println(cb.getUrl());   
						        //打开文件   
						        runtime.exec("rundll32 url.dll FileProtocolHandler "+cb.getUrl());   
						    }catch(Exception ex){   
						        ex.printStackTrace();   
						    }   
						}
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

			<form action="<%=request.getContextPath()%>/corpus/corpusServlet?method=changeCat&id=<%=id%>&type=<%=type%>" method="post" enctype="multipart/form-data" name="changeCat" id="changeCat" onSubmit="return(FormCheck())">
			<tr>
      			<td>修改至类别：			
     			<label>
       			<select name="category">
      			<option selected="selected" value="----">----</option>
				<%        
       		 		List<CorpusCategory> corpusCategoryList=corpusBean.getAllCategory();
    				if(corpusCategoryList.size()>0)
    				{
    	 	 			for(int i=0;i<corpusCategoryList.size();i++)
    	 	 			{
           					out.print("<option value='"+corpusCategoryList.get(i).getType_id()+"'>"+corpusCategoryList.get(i).getType()+"</option>");
           				}
    				}
    				else
    				{
    	   				out.print("<option>暂无分类</option>");
    				}
    			%>
				</select> 
				<input type="submit" name="submit" id="submit" value="保存修改"/>
     			</label>   			
     			</td>
    		</tr>
    		</form>

    		<form action="<%=request.getContextPath()%>/corpus/corpusServlet?method=saveRemarks&id=<%=id%>" method="post" enctype="multipart/form-data" name="saveRemarks" id="saveRemarks" onSubmit="return(FormCheck())">
    		<tr>
    			<td>
    			修改人信息：<input type="text" name="remarks">  			
    			</td>
    		</tr>
    		<tr>
      		<td><label>
        	<input type="submit" name="submit" id="submit" value="确定" onclick="Reload();"/>
      		</label></td>
   			</tr>
   			</form>
		</table>
	</body>
</html>