package net.lrc.javabean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.lrc.db.Mysql;
import net.lrc.model.Department;
import net.lrc.model.*;

public class DepartmentBean 
{
	public boolean adddepartmentinfo(String departmentname, String departmentinfo, String departmenttask) 
	{
		boolean flag = false;

		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="insert into departmentinfo(department_name,department_info,department_task,modify_time) values('" + departmentname + "','" + departmentinfo + "','" + departmenttask + "',curtime())";
			}else if(Language.language.equals("tibet")){
				sql="insert into tibetan_departmentinfo(department_name,department_info,department_task,modify_time) values('" + departmentname + "','" + departmentinfo + "','" + departmenttask + "',curtime())";
			}
			mysql.executeUpdate(sql);
			flag = true;
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		return flag;
	}

	public boolean adddepartmentprincipal(int departmentid, int principalid, String research, String studyexperience, String workexperience, String name) 
	{
		boolean flag = false;

		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="update departmentinfo set principal_id=" + principalid + ",research='" + research + "',study_experience='" + studyexperience + "',work_experience='" + workexperience + "',name='" + name + "'where department_id=" + departmentid;
			}else if(Language.language.equals("tibet")){
				sql="update tibetan_departmentinfo set principal_id=" + principalid + ",research='" + research + "',study_experience='" + studyexperience + "',work_experience='" + workexperience + "',name='" + name + "'where department_id=" + departmentid;
			}
			mysql.executeUpdate(sql);
			flag = true;
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		return flag;
	}

	public boolean deleteDepartment(int departmentid) 
	{
		boolean flag = false;

		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="delete from departmentinfo where department_id=" + departmentid;
			}else if(Language.language.equals("tibet")){
				sql="delete from tibetan_departmentinfo where department_id=" + departmentid;
			}
			mysql.executeUpdate(sql);
			flag = true;
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		return flag;
	}

	public boolean modifyDepartment(int departmentid, String departmentname, String departmentinfo, String departmenttask) 
	{
		boolean flag = false;
		
		try 
		{
			Mysql mysql = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="update departmentinfo set department_name='" + departmentname + "',department_info='" + departmentinfo + "',department_task='" + departmenttask + "',modify_time=now() where department_id=" + departmentid;
			}else if(Language.language.equals("tibet")){
				sql="update tibetan_departmentinfo set department_name='" + departmentname + "',department_info='" + departmentinfo + "',department_task='" + departmenttask + "',modify_time=now() where department_id=" + departmentid;
			}
			mysql.executeUpdate(sql);
			flag = true;
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * to get the current department list
	 * @return
	 */
	public List<Department> list() 
	{
		List<Department> list = new ArrayList<Department>();
		Mysql db = null;
		
		try 
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from departmentinfo;";
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_departmentinfo;";
			}
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next()) 
			{
				Department department = new Department();
				
				department.setDepartmentid(rs.getInt("department_id"));
				department.setDepartmentname(rs.getString("department_name"));
				department.setDepartmentinfo(rs.getString("department_info"));
				department.setDepartmenttask(rs.getString("department_task"));
				
				list.add(department);
			}
			
			db.close();
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return list;
	}

	public Department show(int id) 
	{
		Mysql db = null;
		Department department = new Department();
		
		try 
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from departmentinfo where department_id=" + id;
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_departmentinfo where department_id=" + id;
			}
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next()) 
			{
				department.setDepartmentid(rs.getInt("department_id"));
				department.setDepartmentname(rs.getString("department_name"));
				department.setDepartmentinfo(rs.getString("department_info"));
				department.setDepartmenttask(rs.getString("department_task"));
			}
			
			db.close();
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return department;
	}

	public Master showMaster(int id) 
	{
		Mysql db = null;
		Master master = new Master();
		
		try 
		{
			db = new Mysql();
			String sql = null;
			if(Language.language.equals("china")){
				sql="select * from departmentinfo where department_id=" + id;
			}else if(Language.language.equals("tibet")){
				sql="select * from tibetan_departmentinfo where department_id=" + id;
			}
			ResultSet rs = db.executeQuery(sql);
			
			while(rs.next()) 
			{
				master.setId(rs.getInt("principal_id"));
				master.setName(rs.getString("name"));
				master.setResearch(rs.getString("research"));
				master.setStudyexperience(rs.getString("study_experience"));
				master.setWorkexperience(rs.getString("work_experience"));
			}
			
			db.close();
		}catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return master;
	}
}
