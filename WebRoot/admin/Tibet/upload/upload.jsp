<%@ page contentType="text/html;charset=utf-8" language="java" import="java.util.*,net.lrc.model.*,net.lrc.model.ResourceKind" %>
<%@ include file="/admin/commons/pages/include.jsp" %>
<jsp:useBean id="resourceadmin" class="net.lrc.javabean.ResourceBean"></jsp:useBean>
<html>
<head>
	<title>增加下载</title>
</head>
<%Language.language="tibet"; %>
<body>
<form name="form1" method="post" action="resource.do?method=add&language=tibet" enctype="multipart/form-data">
	<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
		<tr>
			<td>文件名称：<input name="title" type="text" value="文件的描述名称"></td>
		</tr> 
		<tr>
			<td>文件类别：
				<select name="part">
				<%        
       		 		List<ResourceKind> temp=resourceadmin.getAllkind();
    				if(temp.size()>0)
    				{
    	 	 			for(int i=0;i<temp.size();i++)
    	 	 			{
           					out.print("<option value='"+temp.get(i).getKind_name()+"'>"+temp.get(i).getKind_name()+"</option>");
           				}
    				}
    				else
    				{
    	   				out.print("<option value='0'>暂无分类</option>");
    				}
    			%>
				</select>   
			</td>
		</tr>
		<tr>
			<td>附件：<input name="attach" type="file" id="attach" size="50">[<font color="red">上传的文件大小请不要超过20M!</font>]</td>
		</tr>
		<tr> 
			<td>描述：<br>
 				<textarea rows="5" cols="46" name="info"></textarea>
			</td> 
		</tr> 
 	</table>
	<input name="ok" type="submit" value="提交">
</form>
</body>
</html>