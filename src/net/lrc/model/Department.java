package net.lrc.model;

public class Department {
	private int departmentid;
	private String departmentinfo;
	private String departmenttask;
	private int principalid;
	private User principal;
	private String modifytime;
	private String research;
	private String studyexperience;
	private String photo;
	private String departmentname;

	public int getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartmentinfo() {
		return departmentinfo;
	}

	public void setDepartmentinfo(String departmentinfo) {
		this.departmentinfo = departmentinfo;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getDepartmenttask() {
		return departmenttask;
	}

	public void setDepartmenttask(String departmenttask) {
		this.departmenttask = departmenttask;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public User getPrincipal() {
		return principal;
	}

	public void setPrincipal(User principal) {
		this.principal = principal;
	}

	public int getPrincipalid() {
		return principalid;
	}

	public void setPrincipalid(int principalid) {
		this.principalid = principalid;
	}

	public String getResearch() {
		return research;
	}

	public void setResearch(String research) {
		this.research = research;
	}

	public String getStudyexperience() {
		return studyexperience;
	}

	public void setStudyexperience(String studyexperience) {
		this.studyexperience = studyexperience;
	}
}
