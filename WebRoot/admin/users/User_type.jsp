<%@ page language="java"  contentType="text/html; charset=utf-8" import="java.util.*,net.jtaq.utils.RoleDetails"  %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ include file="/admin/commons/pages/include.jsp" %>
 <%@page session="true" %>
  <html>
   
 
  <body>
   用户授权
   <table>
   	<tr>
   		<input type="radio" id="NO" name="NO" value="NO" onclick="location.replace('User_type.jsp')" checked/>否</input>
   		<input type="radio" id="YES" name="YES" value="YES" onclick="location.replace('User_Role.jsp')"/>是</input>
   	</tr>
   </table>
  </body>
  </html>
