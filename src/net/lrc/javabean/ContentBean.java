package net.lrc.javabean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.lrc.db.Mysql;
import net.lrc.model.Content;
import net.lrc.model.*;

public class ContentBean 
{
	public void add(int xh, String title, String url, String part, String flag) 
	{
		try 
		{
			Mysql mysql = new Mysql();
			String insertString = null;
			if(Language.language.equals("china")){
				insertString="insert into content(xh,title,url,part,flag) values(" + xh + ",'" + title + "','" + url + "','" + part + "','" + flag + "')";
			}else if(Language.language.equals("tibet")){
				insertString="insert into tibetan_content(xh,title,url,part,flag) values(" + xh + ",'" + title + "','" + url + "','" + part + "','" + flag + "')";
			}
			mysql.executeUpdate(insertString);
			mysql.close();
		} 
		catch(Exception e) 
		{
			System.out.println("insert into content fail");
			e.printStackTrace();
		}
	}

	public void modify(int id, int xh, String title, String url, String part, String flag) 
	{
		try 
		{
			Mysql mysql = new Mysql();
			String insertString = null;
			if(Language.language.equals("china")){
				insertString="update content set xh=" + xh + ",title='" + title + "',part='" + part + "',url='" + url + "',flag='" + flag + "' where id=" + id;
			}else if(Language.language.equals("tibet")){
				insertString="update tibetan_content set xh=" + xh + ",title='" + title + "',part='" + part + "',url='" + url + "',flag='" + flag + "' where id=" + id;
			}
			mysql.executeUpdate(insertString);
			mysql.close();
		} 
		catch(Exception e) 
		{
			System.out.println("update content fail");
			e.printStackTrace();
		}
	}

	public void delete(int id) 
	{
		try 
		{
			Mysql mysql = new Mysql();
			String insertString = null;
			if(Language.language.equals("china")){
				insertString="delete from content where id=" + id;
			}else if(Language.language.equals("tibet")){
				insertString="delete from tibetan_content where id=" + id;
			}
			mysql.executeUpdate(insertString);
			mysql.close();
		} 
		catch(Exception e) 
		{
			System.out.println("delete content fail");
			e.printStackTrace();
		}
	}

	public List<Content> list()
	{
		List<Content> list = new ArrayList<Content>();
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from content order by part";
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_content order by part";
			}
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next()) 
			{
				Content content = new Content();
				content.setFlag(rs.getString("flag"));
				content.setId(rs.getInt("id"));
				content.setPart(rs.getString("part"));
				content.setTitle(rs.getString("title"));
				content.setUrl(rs.getString("url"));
				content.setXh(rs.getInt("xh"));
				list.add(content);
			}
			
			db.close();
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return list;
	}

	public List<Content> listbyPart(String part) 
	{
		List<Content> list = new ArrayList<Content>();
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql =null;
			if(Language.language.equals("china")){
				sql="select * from content where part='" + part + "' order by xh";
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_content where part='" + part + "' order by xh";
			}		
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next()) 
			{
				Content content = new Content();
				content.setFlag(rs.getString("flag"));
				content.setId(rs.getInt("id"));
				content.setPart(rs.getString("part"));
				content.setTitle(rs.getString("title"));
				content.setXh(rs.getInt("xh"));
				content.setUrl(rs.getString("url"));
				list.add(content);
			}
			
			db.close();
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return list;
	}

	public Content show(int id) 
	{
		Content content = new Content();
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from content where id=" + id;
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_content where id=" + id;
			}
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next())
			{
				content.setFlag(rs.getString("flag"));
				content.setId(rs.getInt("id"));
				content.setPart(rs.getString("part"));
				content.setTitle(rs.getString("title"));
				content.setUrl(rs.getString("url"));
				content.setXh(rs.getInt("xh"));
			}
			
			db.close();
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return content;
	}
}
