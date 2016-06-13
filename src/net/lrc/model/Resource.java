package net.lrc.model;

import java.sql.ResultSet;
import net.lrc.db.Mysql;

public class Resource {
	private int resourceid;
	private int resourcekindid;
	private String resourceinfo;
	private String resourceurl;
	private String resourcetitle;
	private String resourceupadmin;
	private String resourceuptime;
	private String resourcemodifytime;
	private int resourcemodifyflag;
	private String resourcekindname;

	public String getResourcekindname() {
		return (resourcekindname);
	}

	public void setResourcekindname(String resourcekindname) {
		this.resourcekindname = resourcekindname;
	}

	public int getResourceid() {
		return (resourceid);
	}

	public void setResourceid(int resourceid) {
		this.resourceid = resourceid;
	}

	public String getResourceinfo() {
		return (resourceinfo);
	}

	public void setResourceinfo(String resourceinfo) {
		this.resourceinfo = resourceinfo;
	}

	public int getResourcekindid() {
		return (resourcekindid);
	}

	public void setResourcekindid(int resourcekindid) {
		this.resourcekindid = resourcekindid;
	}

	public String getResourcetitle() {
		return (resourcetitle);
	}

	public void setResourcetitle(String resourcetitle) {
		this.resourcetitle = resourcetitle;
	}

	public String getResourceurl() {
		return (resourceurl);
	}

	public void setResourceurl(String resourceurl) {
		this.resourceurl = resourceurl;
	}

	public int getResourcemodifyflag() {
		return (resourcemodifyflag);
	}

	public void setResourcemodifyflag(int resourcemodifyflag) {
		this.resourcemodifyflag = resourcemodifyflag;
	}

	public String getResourceupadmin() {
		return (resourceupadmin);
	}

	public void setResourceupadmin(String resourceupadmin) {
		this.resourceupadmin = resourceupadmin;
	}

	public String getResourceuptime() {
		return (resourceuptime);
	}

	public void setResourceuptime(String resourceuptime) {
		this.resourceuptime = resourceuptime;
	}

	public String getResourcemodifytime() {
		return (resourcemodifytime);
	}

	public void setResourcemodifytime(String resourcemodifytime) {
		this.resourcemodifytime = resourcemodifytime;
	}

	public Resource() {
		super();
	}

	public void setResource(String resourceid) {
		String sql = "select * from resourceinfo a,resourcekind b where a.resource_id="
				+ resourceid + " and a.resource_kind_id=b.kind_id";

		try {
			Mysql mysql = new Mysql();
			ResultSet rs = mysql.executeQuery(sql);

			if (rs.next()) {
				setResourceid(Integer.parseInt(rs.getString("resource_id")));
				setResourcemodifyflag(Integer.parseInt(rs
						.getString("resource_modify_flag")));
				setResourceinfo(rs.getString("resource_info"));
				setResourcekindname(rs.getString("kind_name"));
				setResourcemodifytime(rs.getString("resource_modify_time"));
				setResourcemodifyflag(Integer.parseInt(rs
						.getString("resource_modify_flag")));
				setResourcetitle(rs.getString("resource_title"));
				setResourceupadmin(rs.getString("resource_up_admin"));
				setResourceuptime(rs.getString("resource_up_time"));
				setResourceurl(rs.getString("resource_url"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
