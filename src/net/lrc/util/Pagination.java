package net.lrc.util;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

public class Pagination 
{
	private String strPage=null;
	private int curPages;
	private int m_rows;
	private int pages;
	
	public String strPage(HttpServletRequest request,String page)
	{
		try
		{
	       strPage=request.getParameter(page);
	      }
	      catch(Exception e){
	           System.out.println("delcolumn"+e.getMessage());    
	      }
	      return strPage;
	   }
	  
	  
	   public int curPages(String strPage){
	       try{
	           if(strPage == null){
	               curPages = 1;
	           }
	           else{
	               curPages = Integer.parseInt(strPage);
	               if(curPages < 1)
	            	   curPages = 1;
	           }
	       }
	       catch(Exception e){
	           System.out.print("curPages");
	       }
	       return curPages;
	   } 
	   
	   
	   public void setRows(int rows){
	       m_rows=rows;
	   }
	   
	  
	   public int getPages(int rowcounts){
	       int test;
	       test=rowcounts%m_rows;
	       if(test==0)
	           pages = rowcounts/m_rows;
	           else
	           pages=rowcounts/m_rows+1;
	       return pages;
	   }
	   
	  
	   public ResultSet getPageSet(ResultSet rs,int curPages){
	       if(curPages==1){
	           return rs;
	       }
	       else{
	           int i=1;
	           try{
	               while(rs.next()){
	                   i=i+1;
	                   if(i>((curPages-1)*m_rows))
	                       break;
	               }
	               return rs;
	           }
	           catch(Exception e){
	               System.out.print(e.getMessage());
	           }
	       }
	       return rs;
	   }

	   
}
