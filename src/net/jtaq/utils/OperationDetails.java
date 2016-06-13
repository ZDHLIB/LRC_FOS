package net.jtaq.utils;

import java.util.Date;

@SuppressWarnings("unchecked")
public class OperationDetails implements Comparable {
	private int Operation_ID;
	private String Operation_Name;
	private String Resume;
	private int OperatorID;
	private Date CreateTime;
	private Date UpdateTime;

	public OperationDetails(int Operation_ID, String Operation_Name,
			String Resume, Date CreateTime, Date UpdateTime, int OperatorID) {
		this.Resume = Resume;
		this.Operation_ID = Operation_ID;
		this.Operation_Name = Operation_Name;
		this.CreateTime = CreateTime;
		this.UpdateTime = UpdateTime;
		this.OperatorID = OperatorID;
	}

	public void setOperationID(int Operation_ID) {
		this.Operation_ID = Operation_ID;
	}

	public void setOperationName(String Operation_Name) {
		this.Operation_Name = Operation_Name;
	}

	public void setResume(String Resume) {
		this.Resume = Resume;
	}

	public int getOperationID() {
		return this.Operation_ID;
	}

	public String getOperationName() {
		return this.Operation_Name;
	}

	public String getResume() {
		return this.Resume;
	}

	public void setCreateTime(Date CreateTime) {
		this.CreateTime = CreateTime;
	}

	public void setUpdateTime(Date UpdateTime) {
		this.UpdateTime = UpdateTime;
	}

	public void setUserID(int OperatorID) {
		this.OperatorID = OperatorID;
	}

	public Date getCreateTime() {
		return this.CreateTime;
	}

	public Date getUpdateTime() {
		return this.UpdateTime;
	}

	public int getOperatorID() {

		return this.OperatorID;
	}

	public int compareTo(Object o) {
		OperationDetails n = (OperationDetails) o;
		int lastCmp = Operation_Name.compareTo(n.Operation_Name);
		return (lastCmp);
	}
}
