package net.lrc.util;

import java.util.Date;

@SuppressWarnings("unchecked")
public class DownloadDetails implements Comparable
{
	private int id;
	private String name;
	private String title;
	private String info;
	private Date date;
	private Date lastmodified;
	private String url;
	private String type;
	private String part;
	private String size;
	private int count;
	private int lrryID;
	private int xgryID;
	private String flag;

	public String getFlag() 
	{
		return(flag);
	}
	
	public void setFlag(String flag)
	{
		this.flag=flag;
	}
	
	public Date getLastmodified() 
	{
		return(lastmodified);
	}

	public void setLastmodified(Date lastmodified) 
	{
		this.lastmodified=lastmodified;
	}

	public int getLrryID() 
	{
		return(lrryID);
	}
	
	public void setLrryID(int lrryID) 
	{
		this.lrryID=lrryID;
	}
	
	public int getXgryID() 
	{
		return(xgryID);
	}
	
	public void setXgryID(int xgryID) 
	{
		this.xgryID=xgryID;
	}
	
	public void setUrl(String url) 
	{
		this.url=url;
	}
	
	public DownloadDetails(int id,String title,String name,String info,Date date,Date lastmodified,String url,String type,String part,String size,int count,int lrryid,int xgryid,String flag)throws Exception
	{
		this.id=id;
		this.name=name;
		this.count=count;
		this.date=date;
		this.lastmodified=lastmodified;
		this.info=info;
		this.type=type;
		this.url=url;
		this.part=part;
		this.size=size;
		this.title=title;
		this.lrryID=lrryid;
		this.xgryID=xgryid;
		this.flag=flag;
	}
	
	public int getId()
	{
		return(id);
	}
	
	public String  getName()
	{
		return(name);
	}
	
	public String getTitle()
	{
		return(title); 
	}
	
	public Date getDate()
	{
		return(date);
	}
	
	public Date getLastModified()
	{
		return(lastmodified);
	}
	
	public String getInfo()
	{
		return(info);
	}
	
	public String getUrl()
	{
		return this.url;
	}
	
	public int getCount()
	{
		return(count);
	}
	
	public String getType()
	{
		return(type);
	}
	
	public String getPart()
	{
		return(part);
	}
	
	public String getSize()
	{
		return(size);
	}
	
	public void setId(int id)
	{
		this.id=id;   
	}
	
	public void setName(String name)
	{
		this.name=name;	   
	} 
	
	public void setTitle(String title)
	{
		this.title=title; 
	}
	
	public void setInfo(String info)
	{
		this.info=info;
	}
	
	public void setDate(Date date)
	{
		this.date=date;
	}
	
	public void setLastModified(Date lastmodified)
	{
		this.lastmodified=lastmodified;
	}
	
	public void setType(String type)
	{
		this.type=type;
	}
	
	public void setPart(String part)
	{
		this.part=part;
	}

	public void setSize(String size)
	{
		this.size=size;
	}
	
	public void setCount(int count )
	{
		this.count=count;
	}
 
	public int compareTo(Object o)
	{
		DownloadDetails n=(DownloadDetails)o;
		int lastCmp=date.compareTo(n.date);
		return(lastCmp);
	}
}