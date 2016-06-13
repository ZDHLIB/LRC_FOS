package net.lrc.model;

import java.util.Date;

public class User {
	private String loginName;
	private Integer id;
	private String password;
	private String name;
	private String age;
	private String gender;
	private String address;
	private String email;
	private String zipcode;
	private String birthdate;
	private String experience;
	private String telephone;
	private String mobilephone;
	private String birthPlace;
	private String unit;
	private String race;
	private String department;
	private Integer loginCount;
	private Date lastLoginTime;
	private String lastLoginIp;
	private Integer departmentId;
	private String info;
	private String research;
	private String flag;
	private Integer view;
	private Integer viewed;
	private Integer down;
	private Integer downed;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getResearch() {
		return research;
	}

	public void setResearch(String research) {
		this.research = research;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	


	public User() {
	}

	public User(String loginName, Integer id, String password) {
		this.loginName = loginName;
		this.id = id;
		this.password = password;
	}

	public User(String loginName, String password, String name, String age,
			String gender, String address, String email, String zipcode,
			String birthdate, String experience, String telephoto,
			String mobilephoto, String birthPlace, String unit, String race,
			String department, Integer loginCount, Date lastLoginTime,
			String lastLoginIp, Integer departmentId, String info,
			String research, Integer view, Integer down, Integer viewed, Integer downed ) {
		this.loginName = loginName;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.zipcode = zipcode;
		this.birthdate = birthdate;
		this.experience = experience;
		this.telephone = telephoto;
		this.mobilephone = mobilephoto;
		this.birthPlace = birthPlace;
		this.unit = unit;
		this.race = race;
		this.department = department;
		this.loginCount = loginCount;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
		this.departmentId = departmentId;
		this.info = info;
		this.research = research;
		this.view = view;
		this.viewed = viewed;
		this.down = down;
		this.downed = downed;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getExperience() {
		return this.experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getTelephoto() {
		return this.telephone;
	}

	public void setTelephoto(String telephoto) {
		this.telephone = telephoto;
	}

	public String getMobilephoto() {
		return this.mobilephone;
	}

	public void setMobilephoto(String mobilephoto) {
		this.mobilephone = mobilephoto;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRace() {
		return this.race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	public Integer getView() {
		return view;
	}
	
	public void setView( Integer view ) {
		this.view = view;
	}
	
	public Integer getViewed() {
		return viewed;
	}
	
	public void setViewed( Integer viewed ) {
		this.viewed = viewed;
	}
	
	public Integer getDown() {
		return down;
	}
	
	public void setDown( Integer down ) {
		this.down = down;
	}
	
	public Integer getDowned() {
		return downed;
	}
	
	public void setDowned( Integer downed ) {
		this.downed = downed;
	}
	
}