package net.jtaq.utils;

import java.util.Date;

public class Function_OperationDetails {
	private int Function_Oper_ID;
	private int Function_ID;
	private int Operation_ID;
	private Date CreateTime;
	private Date UpdateTime;
	private int UserID;
	
	public Function_OperationDetails(int Function_Oper_ID, int Function_ID,int Operation_ID,Date CreateTime,Date UpdateTime,int UserID)
	{
		this.Function_Oper_ID=Function_Oper_ID;
		this.Function_ID=Function_ID;
		this.Operation_ID=Operation_ID;
		this.CreateTime=CreateTime;
		this.UpdateTime=UpdateTime;
		this.UserID=UserID;
	}
	
	public void setFunctionID(int Function_ID){
		this.Function_ID=Function_ID;
	}
   
	public void setOperation_ID(int Operation_ID){
		this.Operation_ID=Operation_ID;
	}
	
	public  void setFunction_Oper_ID(int Function_Oper_ID){
		this.Function_Oper_ID=Function_Oper_ID;
	}
	
	public void setCreateTime(Date CreateTime){
		this.CreateTime=CreateTime;
	}
   
	public void setUpdateTime(Date UpdateTime){
		this.UpdateTime=UpdateTime;
	}
	
	public  void setUserID(int UserID){
		this.UserID=UserID;
	}
	
	public int getFunctionID( ){
    	  return this.Function_ID;
	}
	
	public int getOperation_ID( ){
		return this.Operation_ID;
	}
	
	public  int getFunction_Oper_ID( ){
		return this.Function_Oper_ID;
	}
	
	public Date getCreateTime(){
		return this.CreateTime;
	}
	   
	public Date getUpdateTime(){
		return this.UpdateTime;
	}
		
	public  int getUserID(){
		return this.UserID;
	}
}

 
