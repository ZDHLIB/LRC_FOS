<%@ page language="java" import="java.util.*,net.lrc.model.*" pageEncoding="utf-8" %>
<jsp:useBean id="thenews" scope="page" class="net.lrc.javabean.NewsBean"/>
<jsp:directive.page import="net.lrc.model.Department,net.lrc.model.Content"/>
<jsp:useBean id="departmentBean" class="net.lrc.javabean.DepartmentBean"/>
<jsp:useBean id="contentBean" class="net.lrc.javabean.ContentBean"/>
<jsp:useBean id="commonBean" class="net.lrc.javabean.CommonBean"/>

<%@ include file="header.jsp"%>
<%
	Language.language="tibet"; 
%>
<center>
	<body leftMargin="0" topMargin="0">
		<table cellSpacing="0" cellPadding="0" width="968" border="0">
			<tbody>
				<tr>
					<td vAlign="top" align="left" width="680">
						<table cellSpacing="0" cellPadding="0" width="600" border="0">
							<tr>
								<td class="index_table_2">
									<table>
										<tr>
											<td vAlign="bottom" align="right" width="20">
												<img height="20" src="../lingdot_files/index_triangle.jpg" width="20">
											</td>
											<td class="index_type_text_2" align="center" vAlign="middle" width="200" bgColor="#8ca2a5">
												友情链接
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr bgColor="#e6efe7">
								<td class="index_table_2">
									<table>
										<tr>
											<td class="index_cs_1">
												<a href="#"> <img alt="服务项目一览" src="../lingdot_files/index_icon_guide.gif" border="0">
													<font class="index_cs_1"
													style="padding-left: 2px; bottom: 3px; position: relative">གྲངས་ཉུང་མི་རིགས་ཀྱི་སྐད་ཡིག་ཞིབ་འཇུག་ལྟེ་བ</font>
												</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td class="index_table_1" vAlign="top" align="left">
						<table cellSpacing="0" cellPadding="0" width="356" border="0">
							<tbody>
								<tr>
									<td vAlign="top" colSpan="2">
										<object id="obj2" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" border="0" width="355" height="80">
											<param name="movie" value="../images/index2.swf">
											<param name="quality" value="high">
											<embed src="../images/index2.swf" quality="high" name="obj2" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash"></embed>
										</object>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</center>
<br>
<br>
<br>
<%@ include file="footer.jsp"%>
