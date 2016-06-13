package net.jtaq.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class AdminDetails 
{
	private String AdminName;
	private int AdminID;
	private String Password;
	private String deptName;
	private int OperatorID;
	private Date CreateTime;
	private Date UpdateTime;
	@SuppressWarnings("unchecked")
	private ArrayList authList = new ArrayList();

	public AdminDetails() 
	{
	}

	public AdminDetails(int adminID, String adminName, String password, Date createTime, Date updateTime, int operatorID) 
	{
		this.AdminID = adminID;
		this.AdminName = adminName;
		this.OperatorID = operatorID;
		this.Password = password;
		this.CreateTime = createTime;
		this.UpdateTime = updateTime;
	}

	public String getPassword() 
	{
		return Password;
	}

	public void setPassword(String password)
	{
		this.Password = password;
	}

	public int getOperatorID() 
	{
		return OperatorID;
	}

	public void setOperatorID(int operatorID) 
	{
		this.OperatorID = operatorID;
	}

	public String getAdminName() 
	{
		return AdminName;
	}

	public void setAdminName(String adminName) 
	{
		this.AdminName = adminName;
	}

	public int getAdminID() 
	{
		return AdminID;
	}

	public void setAdminID(int adminID) 
	{
		this.AdminID = adminID;
	}

	public Date getCreateTime() 
	{
		return this.CreateTime;
	}

	public void setCreateTime(Date createTime) 
	{
		this.CreateTime = createTime;
	}

	public Date getUpdateTime() 
	{
		return this.UpdateTime;
	}

	public void setUpdateTime(Date updateTime)
	{
		this.UpdateTime = updateTime;
	}

	@SuppressWarnings("unchecked")
	public ArrayList getAuthList() 
	{
		return authList;
	}

	@SuppressWarnings("unchecked")
	public void setAuthList(ArrayList authList) 
	{
		this.authList = authList;
	}
	
	public String getDeptName() 
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}
	
	public boolean checkSafe(String functionResume, String operationName) 
	{
		for(int i = 0; i <= authList.size(); i++) 
		{
			Properties auth = (Properties)authList.get(i);

			if(auth.containsKey(functionResume) && auth.containsValue(operationName)) 
			{
				return true;
			}
		}
		
		return false;
	}
}
