package net.lrc.javabean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import net.lrc.db.Mysql;
import net.lrc.model.ResourceKind;
import net.lrc.util.AbstractPage;
import net.lrc.util.DownloadDetails;
import net.lrc.model.*;

public class ResourceBean extends AbstractPage 
{
	public final static int MESSAGE_PAGE_SIZE = 20;
	
	public List<ResourceKind> getAllkind() 
	{
		List<ResourceKind> allkind = new ArrayList<ResourceKind>();
		ResultSet rs = null;

		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from resourcekind order by kind_id";
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_resourcekind order by kind_id";
			}
			rs = mysql.executeQuery(sql);

			while(rs.next()) 
			{
				ResourceKind temp = new ResourceKind();
				
				temp.setKind_id(rs.getString("kind_id"));
				temp.setKind_name(rs.getString("kind_name"));
				temp.setKind_info(rs.getString("kind_info"));
				
				allkind.add(temp);
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return allkind;
	}

	public ResourceKind getkind(int id) 
	{
		ResourceKind temp = null;
		ResultSet rs = null;

		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from resourcekind where kind_id=" + id;
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_resourcekind where kind_id=" + id;
			}
			rs = mysql.executeQuery(sql);

			while(rs.next()) 
			{
				temp = new ResourceKind();
				
				temp.setKind_id(rs.getString("kind_id"));
				temp.setKind_name(rs.getString("kind_name"));
				temp.setKind_info(rs.getString("kind_info"));
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		return (temp);
	}

	public boolean addResourceKind(String kindname, String kindinfo)
	{
		boolean flag = false;

		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="insert into resourcekind(kind_name, kind_info) values('" + kindname + "','" + kindinfo + "')";
			}else if(Language.language.equals("tibet")){
				sql="insert into tibetan_resourcekind(kind_name, kind_info) values('" + kindname + "','" + kindinfo + "')";
			}
			mysql.executeUpdate(sql);
			flag = true;
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		return (flag);
	}

	public boolean deleteResourceKind(int kindid) 
	{
		boolean flag = false;

		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="delete from resourcekind where kind_id=" + kindid;
			}else if(Language.language.equals("tibet")){
				sql="delete from tibetan_resourcekind where kind_id=" + kindid;
			}
			mysql.executeUpdate(sql);
			flag = true;
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		return (flag);
	}

	public boolean modifyResourceKind(int kindid, String kindname, String kindinfo) 
	{
		boolean flag = false;

		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="update resourcekind set kind_name='" + kindname + "',kind_info='" + kindinfo + "' where kind_id=" + kindid;
			}else if(Language.language.equals("tibet")){
				sql="update tibetan_resourcekind set kind_name='" + kindname + "',kind_info='" + kindinfo + "' where kind_id=" + kindid;
			}
			mysql.executeUpdate(sql);
			flag = true;
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		return (flag);
	}
	
	@SuppressWarnings("unchecked")
	public void setResult(String os, String filepath) 
	{
		setPagesize(MESSAGE_PAGE_SIZE);
		result = new ArrayList();
		String query = null;
		if(Language.language.equals("china")){
			query="select * FROM downloads order by lastmodified desc";
		}else if(Language.language.equals("tibet")){
			query="select * FROM tibetan_downloads order by lastmodified desc";
		}

		try 
		{
			ResultSet rs = myQuery(query, os, filepath);

			while(rs.next())
			{
				DownloadDetails dd = new DownloadDetails(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getString(14));
				result.add(dd);
			}

			rs.close();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public List<DownloadDetails> getRecentlyResource(String kindid, int count) 
	{
		List<DownloadDetails> result = new ArrayList<DownloadDetails>();
		ResultSet rs = null;

		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from downloads where flag='1'";
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_downloads where flag='1'";
			}

			if((null != kindid) && (!"".equalsIgnoreCase(kindid))) 
			{
				sql += " and part='" + kindid + "'";
			}

			sql += " order by lastmodified desc";
			rs = mysql.executeQuery(sql);

			while((rs.next()) && (count > 0))
			{
				DownloadDetails dd = new DownloadDetails(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getString(14));
				result.add(dd);
				count--;
			}

			mysql.close();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		return (result);
	}

	public int getNumberOfTotalDownloads() throws Exception {
		Mysql mysql = new Mysql();
		int count = 0;

		try {
			String selectStatement = null;
			if(Language.language.equals("china")){
				selectStatement="select count(*) count from downloads order by id desc";
			}else if(Language.language.equals("tibet")){
				selectStatement="select count(*) count from tibetan_downloads order by id desc";
			}
			
			ResultSet rs = mysql.executeQuery(selectStatement);

			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
		}

		return (count);
	}

	public DownloadDetails getDownloadsDetails(int id) throws Exception 
	{
		Mysql mysql = new Mysql();

		try 
		{
			String selectString = null;
			if(Language.language.equals("china")){
				selectString="select * from downloads where id=" + id;
			}else if(Language.language.equals("tibet")){
				selectString="select * from tibetan_downloads where id=" + id;
			}
			ResultSet rs = mysql.executeQuery(selectString);

			if(rs.next()) 
			{
				DownloadDetails dd = new DownloadDetails(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getString(14));
				return (dd);
			} 
			else 
			{
				return null;
			}
		} 
		finally 
		{
		}
	}

	public boolean addDownloads(String title, String name, String info, String url, String type, String part, String size, int lrryid, int xgryid) throws Exception 
	{
		ResultSet rs = null;

		try 
		{
			Mysql mysql = new Mysql();
			String checkString = null;
			if(Language.language.equals("china")){
				checkString="select * from downloads where name='" + name + "'";
			}else if(Language.language.equals("tibet")){
				checkString="select * from tibetan_downloads where name='" + name + "'";
			}
			rs = mysql.executeQuery(checkString);
			String insertString = null;
			if(Language.language.equals("china")){
				insertString="insert into downloads(name,title,info,date,lastmodified,url,type,part,size,count,lrryID,xgryID,flag) values('" + title + "','" + name + "','" + info + "',curdate(),curdate(),'" + url + "','" + type + "','" + part + "','" + size + "'," + 0 + "," + lrryid + "," + xgryid + ",'0')";
			}else if(Language.language.equals("tibet")){
				insertString="insert into tibetan_downloads(name,title,info,date,lastmodified,url,type,part,size,count,lrryID,xgryID,flag) values('" + title + "','" + name + "','" + info + "',curdate(),curdate(),'" + url + "','" + type + "','" + part + "','" + size + "'," + 0 + "," + lrryid + "," + xgryid + ",'0')";
			}

			if(rs.next())
			{
				return (false);
			}
			else 
			{
				mysql.executeUpdate(insertString);
			}

			return true;
		} 
		catch(Exception ex) 
		{
			throw ex;
		} 
		finally 
		{
		}
	}

	public boolean deleteDownloads(int id) throws Exception
	{
		try
		{
			Mysql mysql = new Mysql();
			String deleteString = null;
			if(Language.language.equals("china")){
				deleteString="delete from downloads where id=" + id;
			}else if(Language.language.equals("tibet")){
				deleteString="delete from tibetan_downloads where id=" + id;
			}
			mysql.executeUpdate(deleteString);
			return true;
		} 
		catch(Exception ex)
		{
			throw ex;
		}
	}

	public boolean updateDownloadsStatus(int id) throws Exception 
	{
		try 
		{
			Mysql mysql = new Mysql();
			String updateString = null;
			if(Language.language.equals("china")){
				updateString="update downloads set flag='1' where id=" + id;
			}else if(Language.language.equals("tibet")){
				updateString="update tibetan_downloads set flag='1' where id=" + id;
			}			
			mysql.executeUpdate(updateString);
			return true;
		} 
		catch(Exception ex) 
		{
			throw ex;
		} 
		finally 
		{
		}
	}
}
