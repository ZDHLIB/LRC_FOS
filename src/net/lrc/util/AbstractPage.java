package net.lrc.util;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import corpus.CorpusBean;

import net.lrc.db.Mysql;
import net.lrc.model.*;

public abstract class AbstractPage implements Pageable {
	private int currentPage;
	private int pageSize;
	private int pages;
	protected int count;
	@SuppressWarnings("unchecked")
	protected List result = null;
	protected String PageQuery;
	protected String Query;
	protected String QueryPart;
	int Offset;
	int Total;
	protected String FilePath;

	public AbstractPage() {
		super();
	}

	public AbstractPage(int currentPage, int pageSize) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}

	protected void checkPage(int currentPage) throws PageException {
		if ((currentPage < 1) || (currentPage > this.getPages())) {
			throw new PageException("" + this.getPages() + "����ǰ"
					+ currentPage);
		}
	}

	abstract protected void setResult(String os, String filepath);

	public void setOffset(int Offset) {
		this.Offset = Offset;
	}

	public void setPagesize(int size) {
		pageSize = size;
	}

	public int getCount() {
		return count;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPages() {
		if (pages == 0) {
			this.pages = (count + pageSize - 1) / pageSize;
		}

		return pages;
	}

	@SuppressWarnings("unchecked")
	public List getResult() {
		return result;
	}

	public String PageLegend() {
		String str = "";
		int first, next, prev, last;

		first = 0;
		next = Offset + pageSize;
		prev = Offset - pageSize;
		last = (this.pages - 1) * pageSize;
		if(Language.language.equals("china")){
			if (Offset >= pageSize) {
				//at the second page currently
				str += "<a href=" + FilePath + "?offset=" + first + ">首页&nbsp</a>";
			} else {
				str += "首页&nbsp";
			}

			if (prev >= 0) {
				//have a previous page
				str += "<a href=" + FilePath + "?offset=" + prev + ">&nbsp前一页&nbsp</a>";
			} else {
				str += "&nbsp前一页&nbsp";
			}

			if (next < Total) {
				//have a next page
				str += "<a href=" + FilePath + "?offset=" + next + ">&nbsp下一页&nbsp</a>";
			} else {
				str += "&nbsp下一页&nbsp";
			}

			if ((pages != 0) && (currentPage < pages)) {
				//the last page
				str += "<a href=" + FilePath + "?offset=" + last + ">&nbsp尾页&nbsp</a>";
			} else {
				str += "&nbsp尾页&nbsp";
			}

			str += "&nbsp页次" + getCurrentPage() + "/" + getPages() + "页&nbsp";
			str += pageSize + " 条/页 共 " + Total + "条";
		}else if(Language.language.equals("tibet")){
			if (Offset >= pageSize) {
				//at the second page currently
				str += "<a href=" + FilePath + "?offset=" + first + ">&nbsp首页&nbsp </a>";
			} else {
				str += "&nbsp首页&nbsp";
			}

			if (prev >= 0) {
				//have a previous page
				str += "<a href=" + FilePath + "?offset=" + prev + ">&nbsp前一页&nbsp</a>";
			} else {
				str += "&nbsp前一页&nbsp";
			}

			if (next < Total) {
				//have a next page
				str += "<a href=" + FilePath + "?offset=" + next + ">&nbsp下一页&nbsp</a>";
			} else {
				str += "&nbsp下一页&nbsp";
			}

			if ((pages != 0) && (currentPage < pages)) {
				//the last page
				str += "<a href=" + FilePath + "?offset=" + last + ">&nbsp尾页&nbsp</a>";
			} else {
				str += "&nbsp尾页&nbsp";
			}

			str += "&nbsp页次 " + getCurrentPage() + "/" + getPages() + " 页  ";
			str += pageSize + " 条/页 共 " + Total + " 条 ";
		}
		return (str);
	}
	//"os" refers to offset
	public ResultSet myQuery1(String query, String os, String filepath)
			throws SQLException {
		String query_part;
		int begin;

		begin = query.indexOf(" FROM ");
		//get the query part from the query string from "FORM" to the end of the query string
		query_part = query.substring(begin, query.length()).trim();

		if (os == null) {
			Offset = 0;
		} else {
			Offset = Integer.parseInt(os);
		}

		FilePath = filepath;

		Query = query;
		QueryPart = query_part;

		try {
			Mysql mysql = new Mysql();

			String SQL = "SELECT Count(*) AS total " + this.QueryPart;
			ResultSet rs = mysql.executeQuery(SQL);

			if (rs.next()) {
				//Total refers to the total data items in the DB
				Total = rs.getInt(1);
			}
			
			/*增加开始——versoin2.0*/
			int m=Total;
			int i=0,j=0,x=0;
			if(m==1){
				i=1;
				x=0;
			}else{
				i=(int) (m*0.5);
				j=(int) (m*0.25);
				x = i-j;
			}
 
			Total =i;	
			x=x+Offset;
			/*增加结束——versoin2.0*/

			pages = (int) Math.ceil((double) this.Total / this.pageSize);
			currentPage = (int) Math.floor((double) Offset / this.pageSize + 1);
			
			/*增加开始——versoin2.0*/
			if(Total-Offset>pageSize){
				i=pageSize;
			}
			else{
				i=i%pageSize;
			}
			/*增加结束——versoin2.0*/
			
			
			if (Total > 0) {
				/*修改开始pageSize改为i，Offset改为x——versoin2.0*/
				SQL = Query + " LIMIT " + x + " , " + i;
				/*修改结束pageSize改为i，Offset改为x——versoin2.0*/
				rs = mysql.executeQuery(SQL);
			}

			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	//"os" refers to offset
	public ResultSet myQuery(String query, String os, String filepath)
			throws SQLException, UnsupportedEncodingException {
		String query_part;
		int begin;

		begin = query.indexOf(" FROM ");
		//get the query part from the query string from "FORM" to the end of the query string
		query_part = query.substring(begin, query.length());
		
		if (os == null) {
			Offset = 0;
		} else {
			Offset = Integer.parseInt(os);
		}

		FilePath = filepath;

		Query = query;
		QueryPart = query_part;
		//String newQueryPart = new String(QueryPart.getBytes(),"utf-8");
		try {
			Mysql mysql = new Mysql();

			String SQL = "SELECT Count(*) AS total " + QueryPart;
			System.out.println("SQL:"+SQL);
			ResultSet rs = mysql.executeQuery(SQL);

			if (rs.next()) {
				//Total refers to the total data items in the DB
				Total = rs.getInt(1);
			}

			pages = (int) Math.ceil((double) this.Total / this.pageSize);
			currentPage = (int) Math.floor((double) Offset / this.pageSize + 1);

			if (Total > 0) {
				SQL = Query + " LIMIT " + Offset + " , " + pageSize;
				rs = mysql.executeQuery(SQL);
			}

			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet myQuery2(String query, String os, String filepath)
			throws SQLException {
		String query_part;
		int begin;
		
		begin = query.indexOf(" FROM ");
		//get the query part from the query string from "FORM" to the end of the query string
		query_part = query.substring(begin, query.length()).trim();
		
		
		FilePath = filepath;
		
		Query = query;
		QueryPart = query_part;
		
		try {
			Mysql mysql = new Mysql();
		
			String SQL = "SELECT Count(*) AS total " + this.QueryPart;
			ResultSet rs = mysql.executeQuery(SQL);
		
			if (rs.next()) {
				//Total refers to the total data items in the DB
				Total = rs.getInt(1);
			}
		
		
			if (Total > 0) {
				SQL = Query;
				rs = mysql.executeQuery(Query);
			}
		
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet myQuery3(String query, String os, String filepath)
	throws SQLException {
		String query_part;
		int begin;
		
		begin = query.indexOf(" FROM ");
		//get the query part from the query string from "FORM" to the end of the query string
		query_part = query.substring(begin, query.length()).trim();
		
		
		FilePath = filepath;
		
		Query = query;
		QueryPart = query_part;
		
		try {
			Mysql mysql = new Mysql();
		
			String SQL = "SELECT Count(*) AS total " + this.QueryPart;
			ResultSet rs = mysql.executeQuery(SQL);
		
			
			SQL = Query;
			rs = mysql.executeQuery(Query);
			
			while (rs.next()) {
				String temp = rs.getString(3).substring(rs.getString(3).indexOf("-")+1, rs.getString(3).length());
				if(temp.indexOf("-") == -1){				
					
					Total++;
				}
			}
			rs.close();
			rs = mysql.executeQuery(Query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet myQuery4(String query, String os, String filepath)
	throws SQLException {
		String query_part;
		int begin;
		
		begin = query.indexOf(" FROM ");
		//get the query part from the query string from "FORM" to the end of the query string
		query_part = query.substring(begin, query.length()).trim();
		
		
		FilePath = filepath;
		
		Query = query;
		QueryPart = query_part;
		
		try {
			Mysql mysql = new Mysql();
		
			String SQL = "SELECT Count(*) AS total " + this.QueryPart;
			ResultSet rs = mysql.executeQuery(SQL);
		
			
			SQL = Query;
			rs = mysql.executeQuery(Query);
			
			while (rs.next()) {
				String temp = rs.getString(3).substring(rs.getString(3).indexOf("-")+1, rs.getString(3).length());
				if(temp.indexOf("-") != -1){				
					
					Total++;
				}
			}
			rs.close();
			rs = mysql.executeQuery(Query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String PageLegend2() {
		String str = "";
		if(Language.language.equals("china")){
			str += " 共 " + Total + " 条 ";
		}else if(Language.language.equals("tibet")){
			

			
			str +=  " 共 " + Total + " 条 ";
		}
		return (str);
	}
}
