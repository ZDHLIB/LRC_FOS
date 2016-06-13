package net.lrc.javabean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.lrc.db.Mysql;
import net.lrc.util.AbstractPage;
import net.lrc.util.NewsDetails;
import net.lrc.model.*;

public class NewsBean extends AbstractPage 
{
	public final static int NEWS_PAGE_SIZE = 20;
	private String newsTypeStr;
	private boolean debug = true;

	public void add(String title, int lrryid, int xgryid, String content, int effectivedays, String type, String kind, String flag) 
	{
		try 
		{
			Mysql mysql = new Mysql();
			String insertString = null;
			if(Language.language.equals("china")){
				insertString="insert into news(title,submittime,content,lrryID,xgryID,lastmodified,effectivedays,type,kind,flag,count) values('" + title + "',now(),'" + content + "'," + lrryid + "," + xgryid + ",now()," + effectivedays + ",'" + type + "','" + kind + "','" + flag + "',0)";
			}else if(Language.language.equals("tibet")){
				insertString="insert into tibetan_news(title,submittime,content,lrryID,xgryID,lastmodified,effectivedays,type,kind,flag,count) values('" + title + "',now(),'" + content + "'," + lrryid + "," + xgryid + ",now()," + effectivedays + ",'" + type + "','" + kind + "','" + flag + "',0)";
			}
			mysql.executeUpdate(insertString);
			mysql.close();
		} 
		catch(Exception e)
		{
			System.out.println("insett into newsinfo fail");
			e.printStackTrace();
		}
	}

	public void update(int id, String title, int xgryid, String content, int effectivedays, String type, String kind)
	{
		try 
		{
			Mysql mysql = new Mysql();
			String updateString = null;
			if(Language.language.equals("china")){
				updateString="update news set title='" + title + "',xgryID=" + xgryid + ",content='" + content + "',lastmodified=now(),effectivedays=" + effectivedays + ",type='" + type + "',kind='" + kind + "' where id=" + id;
			}else if(Language.language.equals("tibet")){
				updateString="update tibetan_news set title='" + title + "',xgryID=" + xgryid + ",content='" + content + "',lastmodified=now(),effectivedays=" + effectivedays + ",type='" + type + "',kind='" + kind + "' where id=" + id;
			}
			mysql.executeUpdate(updateString);
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("update newsinfo fail");
		}
	}

	public void updateStatus(int id, String flag) 
	{
		try 
		{
			Mysql mysql = new Mysql();
			String updateString = null;
			if(Language.language.equals("china")){
				updateString="update news set lastmodified=curdate(),flag='" + flag + "' where id=" + id;
			}else if(Language.language.equals("tibet")){
				updateString="update tibetan_news set lastmodified=curdate(),flag='" + flag + "' where id=" + id;
			}
			mysql.executeUpdate(updateString);
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("update newsinfo fail");
		}
	}

	public boolean delete(Integer id) 
	{
		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="delete from news where id =" + id;
			}else if(Language.language.equals("tibet")){
				sql="delete from tibetan_news where id =" + id;
			}
			mysql.executeUpdate(sql);
			return true;
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("delete from newsinfo fail");
			return false;
		}
	}

	public List<NewsDetails> list() 
	{
		List<NewsDetails> list = new ArrayList<NewsDetails>();
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select id,submittime,title,lrryID from news where type in ('02','01') order by submittime desc";
			}else if(Language.language.equals("tibet")){
				sql="select id,submittime,title,lrryID from tibetan_news where type in ('02','01') order by submittime desc";
			}
			ResultSet rs = db.executeQuery(sql);
			
			if(debug)
			{
				System.out.println("NewsBean: select " + rs);
			}
			
			while(rs.next()) 
			{
				NewsDetails news = new NewsDetails();
				news.setId(Integer.parseInt(rs.getString("id")));
				news.setSubmittime(rs.getDate("submittime"));
				news.setLrryID(rs.getInt("lrryID"));
				news.setTitle(rs.getString("title"));
				list.add(news);
			}
			
			db.close();
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return list;
	}

	public List<NewsDetails> listrecentlyNews(int count) 
	{
		List<NewsDetails> list = new ArrayList<NewsDetails>();
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select id,submittime,title,lrryID,kind,type from news where flag='1' and type in ('02','01') order by submittime desc";
			}else if(Language.language.equals("tibet")){
				sql="select id,submittime,title,lrryID,kind,type from tibetan_news where flag='1' and type in ('02','01') order by submittime desc";
			}
			ResultSet rs = db.executeQuery(sql);
			
			if(debug)
			{
				System.out.println("NewsBean: select " + rs);
			}
			
			if(count < 0)
			{
				count = 5;
			}
			
			if(count > 0) 
			{
				while(rs.next()) 
				{
					NewsDetails news = new NewsDetails();
					news.setId(Integer.parseInt(rs.getString("id")));
					news.setSubmittime(rs.getDate("submittime"));
					news.setLrryID(rs.getInt("lrryID"));
					news.setTitle(rs.getString("title"));
					news.setKind(rs.getString("kind"));
					news.setType(rs.getString("type"));
					list.add(news);
					count--;
				}
			}
			
			db.close();
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return list;
	}

	public List<NewsDetails> listByJGDM(int id, int count) 
	{
		List<NewsDetails> list = new ArrayList<NewsDetails>();
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select id,submittime,title,lrryID,kind,type from news n,admin a where n.type in ('2','1') and n.lrryID=a.Admin_ID and n.flag='1' and a.OperatorID=" + id + " order by submittime desc";
			}else if(Language.language.equals("tibet")){
				sql="select id,submittime,title,lrryID,kind,type from tibetan_news n,admin a where n.type in ('2','1') and n.lrryID=a.Admin_ID and n.flag='1' and a.OperatorID=" + id + " order by submittime desc";
			}
			ResultSet rs = db.executeQuery(sql);
			
			if(debug)
			{
				System.out.println("NewsBean: select " + rs);
			}
			
			if (count < 0)
			{
				count = 5;
			}
			
			if(count > 0) 
			{
				while(rs.next()) 
				{
					NewsDetails news = new NewsDetails();
					news.setId(Integer.parseInt(rs.getString("id")));
					news.setSubmittime(rs.getDate("submittime"));
					news.setLrryID(rs.getInt("lrryID"));
					news.setTitle(rs.getString("title"));
					news.setKind(rs.getString("kind"));
					news.setType(rs.getString("type"));
					list.add(news);
					count--;
				}
			}
			
			db.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return list;
	}

	public List<NewsDetails> listByKindandPart(String type, String kind, int count) 
	{
		List<NewsDetails> list = new ArrayList<NewsDetails>();
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select id,submittime,title,lrryID,kind,type from news where flag='1' and type='" + type + "' and kind='" + kind + "' order by submittime desc";
			}else if(Language.language.equals("tibet")){
				sql="select id,submittime,title,lrryID,kind,type from tibetan_news where flag='1' and type='" + type + "' and kind='" + kind + "' order by submittime desc";
			}
			ResultSet rs = db.executeQuery(sql);
			
			if(debug)
			{
				System.out.println("NewsBean: select " + rs);
			}
			
			if(count < 0)
			{
				count = 5;
			}

			while(rs.next()) 
			{
				if(count == 0)
				{
					break;
				}
				
				NewsDetails news = new NewsDetails();
				news.setId(Integer.parseInt(rs.getString("id")));
				news.setSubmittime(rs.getDate("submittime"));
				news.setLrryID(rs.getInt("lrryID"));
				news.setTitle(rs.getString("title"));
				news.setKind(rs.getString("kind"));
				news.setType(rs.getString("type"));
				list.add(news);
				count--;
			}

			db.close();
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return list;
	}

	public NewsDetails show(int id) 
	{
		NewsDetails news = null;
		
		try 
		{
			Mysql db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from news where id=" + id + " and flag!='2'";
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_news where id=" + id + " and flag!='2'";
			}
			ResultSet rs = db.executeQuery(sql);
			
			if(rs.next()) 
			{
				news = new NewsDetails();
				news.setId(Integer.parseInt(rs.getString("id")));
				news.setSubmittime(rs.getDate("submittime"));
				news.setLrryID(rs.getInt("lrryID"));
				news.setLrryID(rs.getInt("xgryID"));
				news.setCount(rs.getInt("count"));
				news.setContent(rs.getString("content"));
				news.setTitle(rs.getString("title"));
				news.setFlag(rs.getString("flag"));
				news.setKind(rs.getString("kind"));
				news.setType(rs.getString("type"));
				news.setCount(rs.getInt("count"));
				news.setLastmodified(rs.getDate("lastmodified"));
				news.setEffectivedays(rs.getInt("effectivedays"));
			}
			
			db.close();
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return news;
	}
	
	public void updateCount(int id) 
	{
		String sql = null;
		if(Language.language.equals("china")){
			sql="update news set count=count+1 where id=" + id;
		}else if(Language.language.equals("tibet")){
			sql="update tibetan_news set count=count+1 where id=" + id;
		}
		
		try
		{
			Mysql db = new Mysql();
			db.executeUpdate(sql);
			db.close();
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("update count fail;");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setResult(String offset, String filepath) 
	{
		this.setPagesize(NEWS_PAGE_SIZE);
		result = new ArrayList();
		String query = null;
		if(Language.language.equals("china")){
			query="select * FROM news where 1=1";
		}else if(Language.language.equals("tibet")){
			query="select * FROM tibetan_news where 1=1";
		}
		
		if(this.newsTypeStr != null) 
		{
			query += " and type in" + newsTypeStr;
		}

		query += " Order by submittime desc";

		try 
		{
			ResultSet rs = this.myQuery(query, offset, filepath);
			
			while(rs.next()) 
			{
				NewsDetails news = new NewsDetails();
				news.setId(Integer.parseInt(rs.getString("id")));
				news.setSubmittime(rs.getDate("submittime"));
				news.setLrryID(rs.getInt("lrryID"));
				news.setXgryID(rs.getInt("xgryID"));
				news.setCount(rs.getInt("count"));
				news.setContent(rs.getString("content"));
				news.setTitle(rs.getString("title"));
				news.setFlag(rs.getString("flag"));
				news.setKind(rs.getString("kind"));
				news.setType(rs.getString("type"));
				news.setCount(rs.getInt("count"));
				news.setLastmodified(rs.getDate("lastmodified"));
				news.setEffectivedays(rs.getInt("effectivedays"));
				result.add(news);
			}
			
			rs.close();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public String getNewsTypeStr() 
	{
		return newsTypeStr;
	}

	public void setNewsTypeStr(String newsTypeStr) 
	{
		this.newsTypeStr = newsTypeStr;
	}
}
