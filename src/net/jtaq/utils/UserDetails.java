package net.jtaq.utils;

import java.util.Date;

@SuppressWarnings("unchecked")
public class UserDetails implements Comparable {
	private int UserID = 0;
	private int Age = 0;
	private int Sex = 0;
	private int IsPublic = 0;
	private int state = 0;
	private int Telephone = 0;
	private int MobilePhone = 0;
	private int view = 0;
	private int down = 0;
	private String UserName = null;
	private String Password = null;
	private String RealName = null;
	private String HomePlace = null;
	private String Address = null;
	private String ZipCode = null;
	private String Email = null;
	private String Education = null;
	private String Nation = null;
	private String Company = null;
	private String Resume = null;
	private String Level = null;
	private String CreateIP = null;
	private String LastlyLoginIP = null;
	private Date Birthday = null;
	private Date CreateTime = null;
	private Date LastlyLoginTime = null;

	public UserDetails(int UserID, String UserName, String Password,
			String RealName, int Sex, int Age, String Nation, String HomePlace,
			Date Birthday, String Education, String Company, String Address,
			String ZipCode, String Email, int Telephone, int Mobilephone,
			String Resume, String Level, int IsPublic, Date CreateTime,
			String CreateIP, Date LastlyLoginTime, String LastlyLoginIP,
			int state, int view, int down ) {
		this.Address = Address;
		this.Age = Age;
		this.Birthday = Birthday;
		this.Company = Company;
		this.CreateIP = CreateIP;
		this.CreateTime = CreateTime;
		this.Education = Education;
		this.Email = Email;
		this.HomePlace = HomePlace;
		this.IsPublic = IsPublic;
		this.LastlyLoginIP = LastlyLoginIP;
		this.LastlyLoginTime = LastlyLoginTime;
		this.Level = Level;
		this.MobilePhone = Mobilephone;
		this.Nation = Nation;
		this.Password = Password;
		this.RealName = RealName;
		this.UserName = UserName;
		this.Sex = Sex;
		this.Resume = Resume;
		this.state = state;
		this.Email = Email;
		this.Telephone = Telephone;
		this.ZipCode = ZipCode;
		this.view = view;
		this.down = down;
	}

	public void setUserID(int UserID) {
		this.UserID = UserID;
	}
	
	public void setUserview(int view) {
		this.view = view;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	public void setRealName(String RealName) {
		this.RealName = RealName;
	}

	public void setAge(int Age) {
		this.Age = Age;
	}

	public void setSex(int Sex) {
		this.Sex = Sex;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setHomePlace(String HomePlace) {
		this.HomePlace = HomePlace;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public void setTelephone(int Telephone) {
		this.Telephone = Telephone;
	}

	public void setMobilephone(int Mobilephone) {
		this.MobilePhone = Mobilephone;
	}

	public void setZipCode(String ZipCode) {
		this.ZipCode = ZipCode;
	}

	public void setEducation(String Education) {
		this.Education = Education;
	}

	public void setNation(String Nation) {
		this.Nation = Nation;
	}

	public void setCreateIP(String CreateIP) {
		this.CreateIP = CreateIP;
	}

	public void setCompany(String Company) {
		this.Company = Company;
	}

	public void setLevel(String Level) {
		this.Level = Level;
	}

	public void setBirthday(Date Birthday) {
		this.Birthday = Birthday;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public void setCreateTime(Date CreateTime) {
		this.CreateTime = CreateTime;
	}

	public void setIsPublic(int IsPublic) {
		this.IsPublic = IsPublic;
	}

	public void setLastlyLoginTime(Date LastlyLoginTime) {
		this.LastlyLoginTime = LastlyLoginTime;
	}

	public void setResume(String Resume) {
		this.Resume = Resume;
	}

	public void setLastlyLoginIP(String LastlyLoginIP) {
		this.LastlyLoginIP = LastlyLoginIP;
	}

	public int getUserID() {
		return this.UserID;
	}
	
	public int getview() {
		return this.view;
	}

	public int getdown() {
		return this.down;
	}
	
	public String getUserName() {
		return this.UserName;
	}

	public String getPassword() {
		return this.Password;
	}

	public String getRealName() {
		return this.RealName;
	}

	public int getAge(int Age) {
		return this.Age;
	}

	public int getSex() {
		return this.Sex;
	}

	public int getState() {
		return this.state;
	}

	public String getHomePlace() {
		return this.HomePlace;
	}

	public String getEmail() {
		return this.Email;
	}

	public int getTelephone() {
		return this.Telephone;
	}

	public int getMobilephone() {
		return this.MobilePhone;
	}

	public String getZipCode() {
		return this.ZipCode;
	}

	public String getEducation() {
		return this.Education;
	}

	public String getNation() {
		return this.Nation;
	}

	public String getCreateIP() {
		return this.CreateIP;
	}

	public String getCompany() {
		return this.Company;
	}

	public String getLevel() {
		return this.Level;
	}

	public Date getBirthday() {
		return this.Birthday;
	}

	public String getAddress() {
		return this.Address;
	}

	public Date getCreateTime() {
		return this.CreateTime;
	}

	public int getIsPublic() {
		return this.IsPublic;
	}

	public Date getLastlyLoginTime() {
		return this.LastlyLoginTime;
	}

	public String getResume(String Resume) {
		return this.Resume;
	}

	public String getLastlyLoginIP(String LastlyLoginIP) {
		return this.LastlyLoginIP;
	}

	public int compareTo(Object o) {
		UserDetails n = (UserDetails) o;
		int lastCmp = UserName.compareTo(n.UserName);
		return (lastCmp);
	}
}
