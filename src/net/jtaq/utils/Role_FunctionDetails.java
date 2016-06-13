package net.jtaq.utils;

import java.util.Date;

public class Role_FunctionDetails {
	private int Role_Function_ID;
	private int Function_ID;
	private int Role_ID;
	private Date CreateTime;
	private Date UpdateTime;
	private int UserID;

	public Role_FunctionDetails(int Role_Function_ID, int Role_ID,
			int Function_ID, Date CreateTime, Date UpdateTime, int UserID) {
		this.Role_Function_ID = Role_Function_ID;
		this.Function_ID = Function_ID;
		this.Role_ID = Role_ID;
		this.CreateTime = CreateTime;
		this.UpdateTime = UpdateTime;
		this.UserID = UserID;
	}

	public void setFunctionID(int Function_ID) {
		this.Function_ID = Function_ID;
	}

	public void setRole_ID(int Role_ID) {
		this.Role_ID = Role_ID;
	}

	public void setRole_Function_ID(int Role_Function_ID) {
		this.Role_Function_ID = Role_Function_ID;
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

	public int getRole_ID() {
		return this.Role_ID;
	}

	public int getRole_Function_ID() {
		return this.Role_Function_ID;
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

}
