<%@ page language="java" contentType="text/html;charset=utf-8" import="net.lrc.util.DownloadDetails"%>
<jsp:useBean id="theresource" class="net.lrc.javabean.ResourceBean"/>
<%
	if((session.getAttribute("user")==null) && (session.getAttribute("admin")==null)) 
	{
%>
<html>
	<link href="default1.css" rel="stylesheet" type="text/css"/>
	<center>
		<br>
		<br>
		<br>
		<br>
		对不起，您尚未登录，或您尚未注册，请在<a href="<%=request.getContextPath()%>/Tibet/index.jsp">首页</a>重新登录或注册；
		<br>
		<a href="#" onClick="javascript:window.close();">关闭窗口</a>
		<br>
		<br>
		<br>
		<br>
	</center>
</html>
<%
	} 
	else 
	{
%>
	<%!
		public String toUtf8String(String s) 
		{
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<s.length();i++) 
			{
				char c=s.charAt(i);
				if((c>=0) && (c<=255)) 
				{
					sb.append(c);
				} 
				else 
				{
					byte[] b;
					try 
					{
						b=Character.toString(c).getBytes("utf-8");
					} 
					catch(Exception ex) 
					{
						System.out.println(ex);
						b=new byte[0];
					}
					for(int j=0;j<b.length;j++) 
					{
						int k=b[j];
						if(k<0)
						{
							k+=256;
						}
						sb.append("%"+Integer.toHexString(k).toUpperCase());
					}
				}
			}
			return(sb.toString());
		}
	%>
	<%
		//通过按ID来下载文件
		int id=Integer.parseInt(request.getParameter("id"));
		DownloadDetails dd=theresource.getDownloadsDetails(id);
		String filename=dd.getName();//取得文件名
		String filepath=dd.getUrl();//取得文件路径
		filename=toUtf8String(filename);//转码
	
		java.io.BufferedInputStream bis=null;
		java.io.BufferedOutputStream bos=null;
	%>
	<%
		try 
		{
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition","attachment;filename="+filename);
			bis=new java.io.BufferedInputStream(new java.io.FileInputStream(config.getServletContext().getRealPath(filepath)));
			bos=new java.io.BufferedOutputStream(response.getOutputStream());
			byte[] buff=new byte[2048];
			int bytesRead;
			while((-1)!=(bytesRead=bis.read(buff,0,buff.length))) 
			{
				bos.write(buff,0,bytesRead);
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if(bis!=null)
			{
				bis.close();
			}
			if(bos!=null)
			{
				bos.close();
			}
		}
	%>
<%
	}
%>