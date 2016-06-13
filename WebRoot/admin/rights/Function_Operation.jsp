<%@ page language="java" import="java.util.*,net.jtaq.utils.OperationDetails,net.jtaq.managers.RightsAdmin" pageEncoding="utf-8"%>
 <%@ include file="/admin/commons/pages/include.jsp" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
   <SCRIPT language="javascript">
     function add()
     {
        var operationID=getcheckedoperationIDIdStr();
        var functionID=document.Fun_Oper.functionID.value;
	  // alert("operationID="+operationID);
	  //alert("functionID="+functionID);
	    document.Fun_Oper.action="<%=request.getContextPath()%>/FunctionOperationAction.do?method=add&functionID="+functionID+"&operationID="+operationID;
       document.Fun_Oper.submit();
     }
      function getcheckedoperationIDIdStr() 
		  { 
		var strchoice=""; 
		for(var i=0;i<document.Fun_Oper.operationID.length;i++) 
		{ 
		if (document.Fun_Oper.operationID[i].checked) 
		{ 
		strchoice=strchoice+document.Fun_Oper.operationID[i].value+","; 
		} 
		} 
		if (!document.Fun_Oper.operationID.length) 
		{ 
		if (document.Fun_Oper.operationID.checked) 
		{ 
		strchoice=document.Fun_Oper.operationID[i].value;+"," 
		} 
		} 
		strchoice=strchoice.substring(0,strchoice.length-1); 
		//document.Fun_Oper.choiceid.value=strchoice; 
		 //alert(strchoice); 
		 return strchoice;
		} 
		
		 function selectoperationIDALl() 
		  { 
		var strchoice=""; 
		for(var i=0;i<document.Fun_Oper.operationID.length;i++) 
		{ 
		if (!document.Fun_Oper.operationID[i].checked) 
		{ 
		document.Fun_Oper.operationID[i].checked=true; 
		} 
		} 
		 
		} 
		 function deloperationIDALl() 
		  { 
		var strchoice=""; 
		for(var i=0;i<document.Fun_Oper.operationID.length;i++) 
		{ 
		if (document.Fun_Oper.operationID[i].checked) 
		{ 
		document.Fun_Oper.operationID[i].checked=false; 
		} 
		} 
		 
		} 
     function list() 
		  { 
		 window.location="<%=request.getContextPath()%>/FunctionAction.do?method=list";
		} 
  </SCRIPT>
  <title>Operation List</title>
  <body>
  功能操作列表    
   <html:errors/>
 
   <form    name="Fun_Oper"  method="post" > 
    <input  id="functionID" name="functionID"   type="hidden"   value="<bean:write name="FunctionOperationForm"  property="functionID"/>"  > 
    
  <logic:present name="FunctionOperationForm" property="functionID">
      
        <logic:present name="FunctionOperationForm" property="operations">
   <hr>
    <bean:size id="size" name="FunctionOperationForm" property="operations"/>
    <logic:equal name="size" value="0">
    <center>
    暂无记录！
    </center>
    </logic:equal>
   
   <logic:greaterThan name="size" value="0">
   
   <table border="2">
   <tr>
   <th>名称</th>
    <th>选择 </th>
    <th>名称</th>
    <th>选择 </th>
    <th>名称</th>
    <th>选择 </th>
    <th>名称</th>
    <th>选择 </th>
    <th>名称</th>
    <th>选择 </th>
    </tr>
    <tr>
    <% int index=5; %>
    <%
         RightsAdmin ra=new RightsAdmin(); 
       
        List  operations=(List)ra.getAllOperations();        
          for(int i=0;i<operations.size();i++){       
          OperationDetails tempoperation=(OperationDetails)operations.get(i);
           int tempid=tempoperation.getOperationID();  %>      
     <% index--; %>
     <td> <%=tempoperation.getOperationName() %>    </td>
     <td>
     <%
         int  count=1;   
          List operationsOfFunction=(List)request.getAttribute("operationsOfFunction");            
        if(operationsOfFunction.size()>0){
          for(int j=0;j<operationsOfFunction.size();j++){        
          OperationDetails operationOfFunction=(OperationDetails)operationsOfFunction.get(j);
           int id=operationOfFunction.getOperationID();   
              
         if(tempid==id){
     %>      
      
      <input  id="operationID" name="operationID"   type="checkbox"   value="<%=tempid%>" checked> 
       
        <% 
       }else if(tempid!=id){
       if(count==1){
        
       %>    <input id="operationID"  name="operationID"    type="checkbox"     value="<%=tempid%>" > 
       <% 
       --count;} } 
       %>
       <%}
       }else{
       %>  
       <input id="operationID"  name="operationID"    type="checkbox"     value="<%=tempid%>" > 
       <% 
        }
       %>
      
    </td>
    <% if(index==0){ %>
      <% out.print("</tr><tr>"); index=5;}   %>
     <%}%>  
      </tr>
       <tr><td  colspan="6"><input type="button" value="返回列表" onclick="list()"/><input type="button" value="全选" onclick="selectoperationIDALl()"/><input type="button" value="全不选" onclick="deloperationIDALl()"/> </td>  <td colspan="4"><html:submit value="确认保存" onclick="add()"></html:submit></td></tr>
  </table>
</logic:greaterThan>   

 </logic:present>
 
</logic:present>

  </form>
  </body>
  </html>
