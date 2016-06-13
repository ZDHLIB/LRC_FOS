<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="corpus.CorpusCategory,java.util.*" %>
<%@ include file="/admin/commons/pages/include.jsp" %>
<jsp:useBean id="corpusBean" class="corpus.CorpusBean"></jsp:useBean>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加语料类别</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/admin/corpus/js/AddCategoryCheck.js"></script>
</head>

<body>
<form action="corpusServlet?method=addCorpusCategory" method="post" name="addCategoryForm" id="addCategoryForm" onSubmit="return(check())">
  <table width="582" border="0">
    <tr>
      <td width="100">已有类别：</td>
      <td width="518">			
     <label>
        <select name="category">
      	<option selected="selected" value="----">----</option>
				<%        
       		 		List<CorpusCategory> corpusCategoryList=corpusBean.getAllCategory();
    				if(corpusCategoryList.size()>0)
    				{
    	 	 			for(int i=0;i<corpusCategoryList.size();i++)
    	 	 			{
           					out.print("<option value='"+corpusCategoryList.get(i).getType()+"'>"+corpusCategoryList.get(i).getType()+"</option>");
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
      <td width="100">新增类别：</td>
      <td><label>
        <input name="newCategory" type="text" id="newCategory" size="40" maxlength="40" />
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