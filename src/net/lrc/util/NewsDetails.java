package net.lrc.util;
import java.util.*;

public class NewsDetails   implements Comparable {
	
	
	private int id;
	private String title;
	private Date submittime;
    private String content;
    private int lrryID;
    private int  xgryID;
    private Date lastmodified;
    private int effectivedays ;
    private String type;
    private String kind;
    private String flag;
    private int count;
    
    
    /**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the kind
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * @param kind the kind to set
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
    public  NewsDetails(){};
	public  NewsDetails(int id,String title,Date submittime,String content,  int lrryID,int xgryID,Date lastmodified,int effectivedays,String type,String kind,String flag,int count){
    	this.id=id;
    	this.title=title;
    	this.submittime=submittime;
    	this.content=content;
       	this.lrryID=lrryID;
       	this.xgryID=xgryID;
       	this.lastmodified=lastmodified;
       	this.effectivedays=effectivedays;
       	this.type=type;
       	this.kind=kind;
       	this.flag=flag;
       	this.count=count;
       	
    	
    }
    
    public int getId(){
    	
    	return this.id;
    	
    }
	public String getTitle(){
		return this.title;
		
	}
	 
	 
	public int getEffectiveDays(){
		
		
		
		return  this.effectivedays;
	}
 
	public String getContent(){
		return this.content;
	}
	
	public void  setId(int id){
		
		this.id=id;
	}
	public  void setTitle(String title){
		this.title=title;
	}
	 
 
	public void setContent(String content){
		this.content=content;
		
		
	}
	
	 
	public  void setEffectiveDays(int effectivedays){
		
		this.effectivedays=effectivedays;
		
	}
	
	public int compareTo(Object o){
		NewsDetails n=(NewsDetails)o;
		int lastCmp=lastmodified.compareTo(n.lastmodified);
		return(lastCmp);
	}

	/**
	 * @return the effectivedays
	 */
	public int getEffectivedays() {
		return effectivedays;
	}

	/**
	 * @param effectivedays the effectivedays to set
	 */
	public void setEffectivedays(int effectivedays) {
		this.effectivedays = effectivedays;
	}

	/**
	 * @return the lastmodified
	 */
	public Date getLastmodified() {
		return lastmodified;
	}

	/**
	 * @param lastmodified the lastmodified to set
	 */
	public void setLastmodified(Date lastmodified) {
		this.lastmodified = lastmodified;
	}

	 

	/**
	 * @return the submittime
	 */
	public Date getSubmittime() {
		return submittime;
	}

	/**
	 * @param submittime the submittime to set
	 */
	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}

	/**
	 * @return the lrryID
	 */
	public int getLrryID() {
		return lrryID;
	}

	/**
	 * @param lrryID the lrryID to set
	 */
	public void setLrryID(int lrryID) {
		this.lrryID = lrryID;
	}

	/**
	 * @return the xgryID
	 */
	public int getXgryID() {
		return xgryID;
	}

	/**
	 * @param xgryID the xgryID to set
	 */
	public void setXgryID(int xgryID) {
		this.xgryID = xgryID;
	}

	 
	
	
}
