package net.jtaq.utils;

import java.util.Date;

@SuppressWarnings("unchecked")
public class FunctionDetails implements Comparable {

	private int Function_ID;
	private String Function_Name;
	private String Resume;
	private Date CreateTime;
	private Date UpdateTime;
	private int UserID;

	public FunctionDetails(int Function_ID, String Function_Name,
			String Resume, Date CreateTime, Date UpdateTime, int UserID) {
		this.Resume = Resume;
		this.Function_ID = Function_ID;
		this.Function_Name = Function_Name;
		this.CreateTime = CreateTime;
		this.UpdateTime = UpdateTime;
		this.UserID = UserID;
	}

	public void setFunctionID(int Function_ID) {
		this.Function_ID = Function_ID;
	}

	public void setFunctionName(String Function_Name) {
		this.Function_Name = Function_Name;
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

	public int getFunctionID() {
		return this.Function_ID;
	}

	public String getFunctionName() {
		return this.Function_Name;
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
		FunctionDetails n = (FunctionDetails) o;
		int lastCmp = Function_Name.compareTo(n.Function_Name);
		return (lastCmp);
	}
}
