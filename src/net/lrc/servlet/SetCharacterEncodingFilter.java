package net.lrc.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SetCharacterEncodingFilter implements Filter {
	
	protected String encoding = null;
	protected FilterConfig filterConfig = null;
	
	public void init(FilterConfig filterConfig) throws ServletException{
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	public void destroy() {
		// TODO Auto-generated method stub
		this.encoding = null;
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}
	
	protected String selectEncoding(ServletRequest request){
		return(this.encoding);
	}

}
