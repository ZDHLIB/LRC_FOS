package net.lrc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
 
public class Pager 
{
	private int currentPage;
	private int totalPage;
	private int totalRecord;
	private int pageSize;
	@SuppressWarnings("unchecked")
	private ArrayList keys;
	@SuppressWarnings("unchecked")
	private ArrayList values;
	@SuppressWarnings("unused")
	private String previousLink;
	@SuppressWarnings("unchecked")
	
	public Pager(int pageSize,String queryString) 
	{
		keys=new ArrayList();
		values=new ArrayList();
		setQueryString(queryString);
		setPageSize(pageSize);
	}
	
	public String getForScriptLink() 
	{
		if(keys.contains("toPage"))
		{
			removeKey("toPage");
		}
		
		String tmp=getQueryString();
		
		if(tmp.length()==0)
		{
			return("?");
		}
		else
		{
			return("?"+tmp+"&");
		}
	}
	
	public int getCurrentPage() 
	{
		return(currentPage);
	}

	public void setCurrentPage(String toPage) 
	{
		int tmpage=1;
		
		try 
		{
			tmpage=Integer.parseInt(toPage);
		} 
		catch(NumberFormatException e) 
		{
			tmpage=1;
		}
		if(tmpage<1)
		{
			tmpage=1;
		}
		else if(tmpage>getTotalPage())
		{
			tmpage=getTotalPage();
		}
		
		currentPage=tmpage;
	}

	public String getFirstLink() 
	{
		return(getQueryStr(1));
	}
	
	public int getHasNext()
	{
		int i=1;
		if(getCurrentPage()>=getTotalPage())
		{
			i=0;
		}
		return(i);
	}
	
	public String getLastLink() 
	{
		return(getQueryStr(getTotalPage()));
	}

	public String getNextLink() 
	{
		return(getQueryStr(currentPage+1));
	}

	public int getPageSize() 
	{
		return(pageSize);
	}

	public void setPageSize(int pageSize) 
	{
		this.pageSize=pageSize;
	}

	public String getPreviousLink() 
	{
		return(getQueryStr(currentPage-1));
	}

	public void setPreviousLink(String previousLink) 
	{
		this.previousLink=previousLink;
	}

	public String getQueryString() 
	{
		StringBuffer sb=new StringBuffer();
		
		//key1=value1&key2=value2&key3=value3...
		for(int i=0;i<keys.size();i++) 
		{
			String key=(String)keys.get(i);
			String value=(String)values.get(i);
			sb.append("&");
			sb.append(key);
			sb.append("=");
			sb.append(value);
		}
		return(sb.delete(0,1).toString());
	}

	//key1=value1&key2=value2&key3=value3&key4=value4...
	@SuppressWarnings("unchecked")
	public void setQueryString(String queryString) 
	{
		if(queryString!=null) 
		{
			String s[]=queryString.split("&");
			
			for(int i=0;i<s.length;i++) 
			{
				String s1[]=s[i].split("=");
				
				if(s1.length==2) 
				{
					keys.add(s1[0]);
					values.add(s1[1]);
				}
				else 
				{
					keys.add(s1[0]);
					values.add("");
				}
			}
		}
	}
	
	public int getTotalPage() 
	{
		return(totalPage);
	}

	public void setTotalPage() 
	{
		if(totalRecord%pageSize==0)
		{
			totalPage=totalRecord/pageSize;
		}
		else
		{
			totalPage=(totalRecord/pageSize)+1;
		}
	}

	public int getTotalRecord() 
	{
		return(totalRecord);
	}

	public void setTotalRecord(int totalRecord) 
	{
		this.totalRecord=totalRecord;
		setTotalPage();
	}

	public String[] getQueryParameterValues(String key) 
	{
		return(getQueryParameterValues(key,"UTF-8"));
	}

	@SuppressWarnings("unchecked")
	public String[] getQueryParameterValues(String key,String decode) 
	{
		ArrayList ret=new ArrayList();
		
		for(int i=0;i<keys.size();i++)
		{
			if(((String)keys.get(i)).equals(key))
			{
				try
				{
					ret.add(URLDecoder.decode((String)values.get(i),decode));
				} 
				catch(UnsupportedEncodingException e) 
				{
					ret.add((String)values.get(i));
				}
			}
		}

		if(ret.size()==0)
		{
			return(null);
		}
		
		String strArr[]=new String[ret.size()];
		
		for(int i=0;i<ret.size();i++)
		{
			strArr[i]=(String)ret.get(i);
		}

		return(strArr);
	}

	public String getQueryParameter(String key) 
	{
		return(getQueryParameter(key,"UTF-8"));
	}

	public String getQueryParameter(String key,String decode) 
	{
		String value="";
		
		if(key!="toPage") 
		{
			try 
			{
				value=URLDecoder.decode(getValue(key),decode);
			} 
			catch(UnsupportedEncodingException e)
			{
				value=getValue(key);
			}
		} 
		else 
		{
			int tmpage=1;
			
			try 
			{
				value=getValue(key);
				tmpage=Integer.parseInt(value);
			} 
			catch(NumberFormatException e) 
			{
				tmpage=1;
			} 
			catch(NullPointerException e1)
			{
				tmpage=1;
			}
			
			if(tmpage<1)
			{
				tmpage=1;
			}
			else if(tmpage>getTotalPage())
			{
				tmpage=getTotalPage();
			}
			
			value=(new StringBuffer(String.valueOf(tmpage))).toString();
		}
		
		return(value);
	}

	@SuppressWarnings("unchecked")
	public void setQueryParameter(String key,String value) 
	{
		if(key.equals("toPage"))
		{
			removeKey(key);
		}
		
		keys.add(key);
		values.add(value);
	}

	public String getQueryStr(int toPage) 
	{
		setQueryParameter("toPage",(new StringBuffer(String.valueOf(toPage))).toString());
		return("?"+getQueryString());
	}

	private String getValue(String key) 
	{
		String ret="";
		
		for(int i=0;i<keys.size();i++) 
		{
			if(!((String)keys.get(i)).equals(key))
			{
				continue;
			}
			
			ret=(String)values.get(i);
			break;
		}

		return(ret);
	}

	private void removeKey(String key) 
	{
		for(int i=0;i<keys.size();i++) 
		{
			if(!((String)keys.get(i)).equals(key))
			{
				continue;
			}
			
			keys.remove(i);
			values.remove(i);
			break;
		}
	}

/*	public static void main(String args[]) 
	{
		String str="a=1&b=2&c=3&c=4&c=5";
		Pager page=new Pager(2,str);
		page.setTotalRecord(10);
		page.setCurrentPage("1");
		
		System.out.println(page.getFirstLink());
		System.out.println(page.getPreviousLink());
		System.out.println(page.getNextLink());
		System.out.println(page.getLastLink());
		
		String s=page.getQueryParameter("a");
		System.out.println("s:"+s);
	}*/
}
