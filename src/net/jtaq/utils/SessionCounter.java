package net.jtaq.utils;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import net.jtaq.utils.Counter;
public class SessionCounter implements HttpSessionListener{
    
	public void sessionCreated(HttpSessionEvent e) {
		 
		try{
			 
			String count = (String)e.getSession().getServletContext().getAttribute("count");
			   int c=0;
			   if(count !=null){
			    c = Integer.valueOf(count).intValue();
			    System.out.println("旧Count:"+c);
			    e.getSession().getServletContext().setAttribute("count",String.valueOf((c+1)));
			   }else{
			    System.out.println("新Count");
			    e.getSession().getServletContext().setAttribute("count",String.valueOf((c+1)));
			   }
			   System.out.println("建立session时 Count:"+c);
				 
			   SAXBuilder builder = new SAXBuilder(); 
				String path =SessionCounter.class.getResource("/counter.xml").getFile();			
				Document doc = builder.build(path);			
				Element root = doc.getRootElement();
				Element counterfileRoot = root.getChild("counterfile");
				String counterfilepath=counterfileRoot.getTextTrim();			   
			   Counter counter = new Counter();
			   counter.setPath(counterfilepath);
				String g="";
				g = counter.ReadFile();
				e.getSession().getServletContext().setAttribute("totalcount", g);
				counter.WriteFile(g);
		  	}
		catch(Exception er)
			{
		er.printStackTrace();
			}
	}
         
	public void sessionDestroyed(HttpSessionEvent e) {
		try{
			 
			System.out.println("session失效时 ");
			   String count = (String)e.getSession().getServletContext().getAttribute("count");
			   int c=0 ;
			   if(count !=null){
			    c = Integer.valueOf(count).intValue();
			    if(c>=1){
			     System.out.println("sessionDestroyed:旧c-1:"+c);
			     e.getSession().getServletContext().setAttribute("count",String.valueOf((c-1)));
			    }
			   }else{
			    System.out.println("sessionDestroyed:旧新"+c);
			    e.getSession().getServletContext().setAttribute("count","1");
			   }
			   // session失效时

				 
	                
			 
		 	}
		catch(Exception er)
			{
			//e.getSession().setAttribute("count", "计数错误");
			er.printStackTrace();
			}
		
	}
	

}
