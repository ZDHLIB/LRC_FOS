package net.jtaq.utils;

import java.util.*;

@SuppressWarnings("unchecked")
public class RoleDetails implements Comparable {
	private int Role_ID;
	private String Role_Name;
	private String Resume;
	private Date CreateTime;
	private Date UpdateTime;
	private int UserID;

	public RoleDetails(int Role_ID, String Role_Name, String Resume,
			Date CreateTime, Date UpdateTime, int UserID) {
		this.Resume = Resume;
		this.Role_ID = Role_ID;
		this.Role_Name = Role_Name;
		this.CreateTime = CreateTime;
		this.UpdateTime = UpdateTime;
		this.UserID = UserID;
	}

	public void setRoleID(int Role_ID) {
		this.Role_ID = Role_ID;
	}

	public void setRoleName(String Role_Name) {
		this.Role_Name = Role_Name;
	}

	public void setResume(String Resume) {
		this.Resume = Resume;
	}

	public void setCreateTime(Date CreateTime) {
		this.CreateTime = CreateTime;
	}

	public void setUpdateTime(Date UpdateTime) {
		this.UpdateTime = UpdateTime;
	}

	public void setUserID(int UserID) {
		this.UserID = UserID;
	}

	public int getRoleID() {
		return this.Role_ID;
	}

	public String getRoleName() {
		return this.Role_Name;
	}

	public String getResume() {
		return this.Resume;
	}

	public Date getCreateTime() {
		return this.CreateTime;
	}

	public Date getUpdateTime() {
		return this.UpdateTime;
	}

	public int getUserID() {
		return this.UserID;
	}

	public int compareTo(Object o) {
		RoleDetails n = (RoleDetails) o;
		int lastCmp = Role_Name.compareTo(n.Role_Name);
		return (lastCmp);
	}
}
