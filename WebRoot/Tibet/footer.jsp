<%@ page language="java" pageEncoding="utf-8"%>
<jsp:useBean id="mycount" scope="session" class="net.jtaq.utils.Counter"/>

	<center>
		<table cellSpacing="0" cellPadding="0" width="968" border="0">
			<tbody>
				<tr>
					<td class="footer_text" vAlign="middle" align="center" height="35">
						© 2008 Copyright 2008-2010@NMLR Research Centre |
						Visitors:<%=request.getSession().getServletContext().getAttribute("totalcount")%> |
						<%
							request.setCharacterEncoding("utf-8");
							response.setContentType("text/html;charset=utf-8");
						%>
						Online:<%=request.getSession().getServletContext().getAttribute("count")%> | 
						建议最佳屏幕分辨率1024*768 | 技术支持：<a href="mailto:zhoujian_1110@163.com">Sterna paradisaea Studio</a>
					</td>
				</tr>
			</tbody>
		</table>
	</center>
</html>
