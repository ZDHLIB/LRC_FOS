<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="corpus.*,java.util.*" %>
<%@ include file="/admin/commons/pages/include.jsp" %>
<jsp:useBean id="corpusBean" class="corpus.CorpusBean"></jsp:useBean>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加网络媒体类别</title>
<script type="text/JavaScript" src="<%=request.getContextPath()%>/admin/corpus/js/AddNetMediaCheck.js"></script>
</head>

<body>
<form action="corpusServlet?method=addCorpusNetMedia" method="post" name="addNetMediaForm" id="addNetMediaForm" onSubmit="return(check())">
  <table width="582" border="0">
    <tr>
      <td width="650">已有网络媒体类别：</td>
      <td width="518">			
     <label>
        <select name="netMedia">
      	<option selected="selected" value="----">----</option>
				<%        
       		 		List<CorpusNetMedia> corpusNetMediaList=corpusBean.getAllNetMedia();
    				if(corpusNetMediaList.size()>0)
    				{
    	 	 			for(int i=0;i<corpusNetMediaList.size();i++)
    	 	 			{
           					out.print("<option value='"+corpusNetMediaList.get(i).getType()+"'>"+corpusNetMediaList.get(i).getType()+"</option>");
           				}
    				}
    				else
    				{
    	   				out.print("<option>暂无分类</option>");
    				}
    			%>
				</select> 
      </label></td>
    </tr>
    <tr>
      <td width="650">新增网络媒体类别：</td>
      <td><label>
        <input name="newNetMedia" type="text" id="newNetMedia" size="40" maxlength="40" />
      </label></td>
    </tr>
    <tr>
      <td width="650">新增网络媒体类别汉文简写：</td>
      <td><label>
        <input name="NetAbbreviation" type="text" id="NetAbbreviation" size="40" maxlength="40" />
      </label></td>
    </tr>
    <tr>
      <td><label>
        <input type="submit" name="submit" id="submit" value="提交"/>
      </label></td>
      <td><label>
        <input type="reset" name="cancel" id="cancel" value="取消" />
      </label></td>
    </tr>
  </table>
</form>
</body>
</html>