package net.lrc.javabean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.jtaq.utils.AdminDetails;
import net.lrc.db.Mysql;
import net.lrc.util.NewsDetails;
import net.lrc.model.Language;

public class CommonBean 
{
	//get the administrator's name by the corresponding given ID
	public String getAdminNamebyID(int id) 
	{
		AdminDetails admin = new AdminDetails();
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql = "select Admin_Name from admin where Admin_ID=" + id;
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next()) 
			{
				admin.setAdminName(rs.getString("Admin_Name"));
			}
			
			db.close();
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return admin.getAdminName();
	}

	public String getDepNamebyAdminID(int id) 
	{
		String depname = "";
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select department_name from admin a,departmentinfo d where d.department_id=a.OperatorID and a.Admin_ID=" + id;
			}else if(Language.language.equals("tibet")){
				sql="select department_name from admin a,tibetan_departmentinfo d where d.department_id=a.OperatorID and a.Admin_ID=" + id;
			}
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next()) 
			{
				depname = rs.getString("department_name");
			}
			
			db.close();
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return depname;
	}

	/**
	 * get title from content by the corresponding ID
	 * @param id
	 * @return
	 */
	public String getTitlebyContentID(int id) 
	{
		String title = null;
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select title from content where id=" + id;
			}else if(Language.language.equals("tibet")){
				sql="select title from tibetan_content where id=" + id;
			}
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next()) 
			{
				title = rs.getString("title");
			}
			
			db.close();
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return title;
	}
	
	/**
	 * get the department name by ID
	 * @param id
	 * @return
	 */
	public String getDepNamebyID(int id) 
	{
		String name = null;
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql=null;
			if(Language.language.equals("china")){
				sql = "select department_name from departmentinfo where department_id="+ id;
			}else if(Language.language.equals("tibet")){
				sql = "select department_name from tibetan_departmentinfo where department_id="+ id;
			}
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next()) 
			{
				name = rs.getString("department_name");
			}
			
			db.close();
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return name;
	}

	public String getTitlebyPartID(String id) 
	{
		String title = null;
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			//sitecontentpart refers to the dynamic content parts in the home page
			String sql=null;
			if(Language.language.equals("china")){
				sql = "select * from sitecontentpart where dm=" + id;
			}else if(Language.language.equals("tibet")){
				sql = "select * from tibetan_sitecontentpart where dm=" + id;
			}
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next()) 
			{
				title = rs.getString("title");
			}
			
			db.close();
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}

		return title;
	}

	public String getComtentByCoutentAndPart(String partid, String contentid) 
	{
		String content = null;
		
		try 
		{
			Mysql db = null;
			db = new Mysql();

			String sql = null;
			if(Language.language.equals("china")){
				sql="select content from news where flag='1' and type='"+ partid + "' and kind='" + contentid + "'";
			}else if(Language.language.equals("tibet")){
				sql="select content from tibetan_news where flag='1' and type='"+ partid + "' and kind='" + contentid + "'";
			}
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next()) 
			{
				content = rs.getString("content");
			}
			
			db.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return content;
	}

	@SuppressWarnings("unchecked")
	public List getComtentListByCoutentAndPart(String partid, String contentid) 
	{
		List newsList = new ArrayList();
		
		try 
		{
			Mysql db = null;
			db = new Mysql();

			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from news where flag='1' and  type='" + partid + "' and kind='" + contentid + "'";
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_news where flag='1' and  type='" + partid + "' and kind='" + contentid + "'";
			}
			ResultSet rs = db.executeQuery(sql);
			
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
				newsList.add(news);
			}
			
			db.close();
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}

		return newsList;
	}

	//get the specified resource url
	public String getResourceurl(String id) 
	{
		String str = null;

		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select url from Downloads where id=" + id;
			}else if(Language.language.equals("tibet")){
				sql="select url from tibetan_Downloads where id=" + id;
			}
			ResultSet rs = mysql.executeQuery(sql);

			while(rs.next()) 
			{
				str = rs.getString("url");
			}
			
			mysql.close();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		if(null == str) 
		{
			str = "#";
		}

		return (str);
	}
	
	public String getResourceKinfNameByID(String id) 
	{
		String str = null;
		
		try 
		{
			Mysql mysql = new Mysql();
			String sql = "select kind_name from resourcekind where kind_id=" + id;
			ResultSet rs = mysql.executeQuery(sql);
			
			while(rs.next()) 
			{
				str = rs.getString("kind_name");
				mysql.close();
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		if(null == str)
		{
			str = "未知种类";
		}
		
		return str;
	}

	public String getMinXHByPartID(String id) 
	{
		int contentid = 0;
		
		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select id from content where xh in(select min(xh) minxh from content where part='" + id + "') and part='" + id + "'";
			}else if(Language.language.equals("tibet")){
				sql="select id from tibetan_content where xh in(select min(xh) minxh from tibetan_content where part='" + id + "') and part='" + id + "'";
			}
			ResultSet rs = mysql.executeQuery(sql);
			
			while(rs.next()) 
			{
				contentid = rs.getInt("id");
				mysql.close();
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		return String.valueOf(contentid);
	}
}
