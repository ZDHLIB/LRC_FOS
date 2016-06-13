package net.lrc.javabean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.lrc.db.Mysql;
import net.lrc.model.SitePart;
import net.lrc.model.*;

public class SitePartBean
{
	public void add(String title, String dm,String flag)
	{
		try
		{
			Mysql mysql = new Mysql();
			String insertString=null;
			if(Language.language.equals("china")){
				insertString="insert into sitecontentpart(dm,title,flag) values(" + dm + ",'" + title + "','" + flag + "')";
			}else if(Language.language.equals("tibet")){
				insertString="insert into tibetan_sitecontentpart(dm,title,flag) values(" + dm + ",'" + title + "','" + flag + "')";
			}
			mysql.executeUpdate(insertString);
			mysql.close();
		}
		catch(Exception e)
		{
			System.out.println("insett into content fail");
			e.printStackTrace();
		}
	}
	
	public void modify(int id,String title, String dm,String flag)
	{
		try
		{
			Mysql mysql = new Mysql();
			String insertString=null;
			if(Language.language.equals("china")){
				insertString="update sitecontentpart set dm=" + dm + ",title='" + title + "',flag='" + flag + "'  where id=" + id;
			}else if(Language.language.equals("tibet")){
				insertString="update tibetan_sitecontentpart set dm=" + dm + ",title='" + title + "',flag='" + flag + "'  where id=" + id;
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
			String insertString=null;
			if(Language.language.equals("china")){
				insertString="delete from sitecontentpart where id=" + id;
			}else if(Language.language.equals("tibet")){
				insertString="delete from tibetan_sitecontentpart where id=" + id;
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
	
	public List<SitePart> list()
	{
		List<SitePart> list = new ArrayList<SitePart>();
		Mysql db = null;
		
		try
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from sitecontentpart order by id;";
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_sitecontentpart order by id;";
			}
			
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next())
			{
				SitePart part = new SitePart();
				
				part.setFlag(rs.getString("flag"));
				part.setId(rs.getInt("id"));				 
				part.setTitle(rs.getString("title"));
				part.setDm(rs.getString("dm"));				 
				list.add(part);
			}
			
			db.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return list;
	}
	
	public SitePart show(int id)
	{
		SitePart part = new SitePart();
		Mysql db = null;
		
		try
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from sitecontentpart where id=" + id;
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_sitecontentpart where id=" + id;
			}
			
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next())
			{				
				part.setFlag(rs.getString("flag"));
				part.setId(rs.getInt("id"));				 
				part.setTitle(rs.getString("title"));
				part.setDm(rs.getString("dm"));	
			}
			
			db.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return part;
	}
}
