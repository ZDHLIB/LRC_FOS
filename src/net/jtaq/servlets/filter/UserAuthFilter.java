package net.jtaq.servlets.filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jtaq.utils.AdminDetails;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
 

public class UserAuthFilter extends HttpServlet implements Filter {
     
	private FilterConfig filterConfig;
   
    public static ArrayList<String> ProtectedRes = new ArrayList<String>();
    
    public static String protectedurls=null;
   
    
    public void init(FilterConfig filterConfig) throws javax.servlet.
    ServletException {
    this.filterConfig=filterConfig;
     protectedurls=filterConfig.getInitParameter("protectedurls");
     
     try {
     	 	SAXBuilder builder = new SAXBuilder(); 
			String path =UserAuthFilter.class.getResource(protectedurls).getFile();
			Document doc = builder.build(path);
			Element root = doc.getRootElement();
			Element projectRoot = root.getChild("protectedurls");
			List props = projectRoot.getChildren("protectedurl");			 
			for(int i=0;i<props.size();i++){
				Element protectedurlElement = (Element) props.get(i);
				 		  String protectedurl=protectedurlElement.getText();
					   ProtectedRes.add(protectedurl);
			}
       } catch(Exception e) {
         e.printStackTrace();
     }
  }
	
	public void  doFilter(ServletRequest request ,ServletResponse response,FilterChain filterchain)
	throws java.io.IOException,javax.servlet.ServletException {
		
		
		
		try{
			
			 HttpServletRequest req = (HttpServletRequest)request;
	         HttpServletResponse resp = (HttpServletResponse)response;
	          
	        
	         
	           boolean isProtected = isProtectedUrl(req); 
	        if(isProtected)
	        {
	             
		            	 AdminDetails ad = (AdminDetails)req.getSession().getAttribute("admin");
		 	          
		            	 if(ad!=null)
		            	 {
		            		 
		            	 
						            	 if(checkSafe(req, ad)){
						 	                filterchain.doFilter(request, response);
						 	               
						 	                }
						 	           
						 	            else {//"http://localhost/WebRoot/admin/checkfail.jsp" 
						 	                resp.sendRedirect(req.getContextPath()+"/admin/checkfail.jsp");
						 	                }
		                } else
						                {
						            	
						                resp.sendRedirect(req.getContextPath()+"/admin/admin_login.jsp");
						               
						                }
	        }
	        else
	        {        
	        	filterchain.doFilter(request, response); 
	         
		        
	        }
	           
	        } catch(Exception e) {   e.printStackTrace(); }
	        
	        
	      // filterchain.doFilter(request, response);
           // return;
	        
	    }
       
	    private boolean isProtectedUrl(HttpServletRequest request) {
	       
	    	String url = request.getRequestURI().toString();
	       
	        if(url.endsWith(".do")){
	           
	        	url = url + "?" +  request.getQueryString();
	               
	            for(int i = 0; i < ProtectedRes.size(); i++)
	             {
	                String temp = (String)ProtectedRes.get(i);
	                if(url.indexOf(temp) > -1)
	                    return true;
	            }
	        }
	        return false;
	    }

	    private boolean checkSafe(HttpServletRequest request, AdminDetails ad) {
	        String url = request.getRequestURI();
	        String action =  request.getParameter("method");
	        try { 
	        
	        int index = url.lastIndexOf("/");
	        
	        if(index > -1){
	        	
	            url = url.substring(index + 1);
	        }
	        if(url.endsWith(".do")){
	            if(action == null) {
	                action ="list";
	              }
	           
	                
	               return ad.checkSafe(url, action);
	            
	        } 
	            
	        }catch(Exception es){  }   
            
	        return false; 
	    }
         

	    public void destroy() {
	    	filterConfig=null;
	    }

	   
	    public static void load(String cfg) {
	        
	    }

	   
	    
	   
	    

	}