package net.lrc.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.jtaq.utils.MD5EncryptUtils;
import net.lrc.db.Mysql;
import net.lrc.model.User;
import net.lrc.util.AbstractPage;

public class UserBean extends AbstractPage {
	public final static int USER_PAGE_SIZE = 20;

	public void updateLoginfo(String username, String last_login_ip) {
		try {
			Mysql mysql = new Mysql();
			String sql = "select email,name,login_count,last_login_ip,last_login_time  from userinfo where login_name='"
					+ username + "'";
			ResultSet rs = mysql.executeQuery(sql);

			int logincount;

			if (rs.next()) {
				logincount = rs.getInt("login_count") + 1;
				sql = "update userinfo set login_count=" + logincount
						+ ",last_login_ip='" + last_login_ip
						+ "',last_login_time=curdate() where login_name='"
						+ username + "'";
				mysql.executeUpdate(sql);
				rs.close();
			}

			mysql.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateStatus(int id, String flag) {
		try {
			Mysql mysql = new Mysql();
			String sql = "update userinfo set flag='" + flag + "' where id="
					+ id;
			mysql.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void delete(int id) {
		try {
			Mysql mysql = new Mysql();
			String sql = "delete from userinfo where id=" + id;
			mysql.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public User getUserinfo(String loginname) {
		User user = new User();

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from userinfo where login_name='"
					+ loginname + "'";
			ResultSet rs = mysql.executeQuery(sql);

			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setAge(rs.getString("age"));
				user.setLoginName(rs.getString("login_name"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setLoginCount(rs.getInt("login_count"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setBirthdate(rs.getString("birthdate"));
				user.setZipcode(rs.getString("zipcode"));
				user.setBirthPlace(rs.getString("birth_place"));
				user.setTelephone(rs.getString("telephone"));
				user.setMobilephone(rs.getString("mobilephone"));
				user.setDepartment(rs.getString("department"));
				user.setExperience(rs.getString("experience"));
				user.setInfo(rs.getString("info"));
				user.setRace(rs.getString("race"));
				user.setDepartmentId(rs.getInt("department_id"));
				user.setResearch(rs.getString("research"));
				user.setFlag(rs.getString("flag"));
				user.setView(rs.getInt("view"));
				user.setViewed(rs.getInt("viewed"));
				user.setDown(rs.getInt("down"));
				user.setDowned(rs.getInt("downed"));
			}

			mysql.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public User getUser(int id) {
		User user = new User();

		try {
			Mysql mysql = new Mysql();
			String sql = "select * from userinfo where id=" + id;
			ResultSet rs = mysql.executeQuery(sql);

			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setAge(rs.getString("age"));
				user.setLoginName(rs.getString("login_name"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setLoginCount(rs.getInt("login_count"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setBirthdate(rs.getString("birthdate"));
				user.setZipcode(rs.getString("zipcode"));
				user.setBirthPlace(rs.getString("birth_place"));
				user.setTelephone(rs.getString("telephone"));
				user.setMobilephone(rs.getString("mobilephone"));
				user.setDepartment(rs.getString("department"));
				user.setExperience(rs.getString("experience"));
				user.setInfo(rs.getString("info"));
				user.setRace(rs.getString("race"));
				user.setDepartmentId(rs.getInt("department_id"));
				user.setResearch(rs.getString("research"));
				user.setView(rs.getInt("view"));
				user.setViewed(rs.getInt("viewed"));
				user.setDown(rs.getInt("down"));
				user.setDowned(rs.getInt("downed"));
			}

			mysql.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public boolean save(String loginName, String password, String name,
			String age, String gender, String address, String email,
			String zipcode, String birthdate, String experience,
			String telephotone, String mobilephotone, String birthPlace,
			String unit, String race, String department, Integer loginCount,
			String lastLoginIp, Integer departmentId, String info,
			String research, Integer view, Integer viewed, Integer down, Integer downed ) {
		Mysql mysql;

		try {
			mysql = new Mysql();
			password = MD5EncryptUtils.MD5Encode(password);

			String sql = "insert into userinfo(login_name,password,name,age,gender,address,email,zipcode,birthdate,experience,telephone,mobilephone,birth_place,info,race,department,"
					+ "login_count,last_login_time,last_login_ip,department_id,research,flag,view,viewed,down,downed) values('"
					+ loginName
					+ "','"
					+ password
					+ "','"
					+ name
					+ "','"
					+ age
					+ "','"
					+ gender
					+ "','"
					+ address
					+ "','"
					+ email
					+ "','"
					+ zipcode
					+ "','"
					+ birthdate
					+ "','"
					+ experience
					+ "','"
					+ telephotone
					+ "','"
					+ mobilephotone
					+ "','"
					+ birthPlace
					+ "','"
					+ info
					+ "','"
					+ race
					+ "','"
					+ department
					+ "',"
					+ loginCount
					+ ",curdate(),'"
					+ lastLoginIp
					+ "',"
					+ departmentId + ",'" + research + "','0',"+ view +","+ viewed +","+ down + ","+ downed +")";
			try {
				mysql.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean update(int id, String name, String age, String gender,
			String address, String email, String zipcode, String birthdate,
			String experience, String telephotone, String mobilephotone,
			String birthPlace, String race, String department,
			Integer departmentId, String info, String research, Integer view, Integer down) {
		Mysql mysql;
		try {
			mysql = new Mysql();
			String sql = "update userinfo set name='" + name + "',age='" + age
					+ "',gender='" + gender + "',address='" + address
					+ "',email='" + email + "',zipcode='" + zipcode
					+ "',birthdate='" + birthdate + "',experience='"
					+ experience + "',telephone='" + telephotone
					+ "',mobilephone='" + mobilephotone + "',birth_place='"
					+ birthPlace + "',race='" + race + "',department='"
					+ department + "', department_id=" + departmentId
					+ ",info='" + info + "',research='" + research
					+ "',view='" + view
					+ "',down='" + down
					+ "' where id=" + id;
			try {
				mysql.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}

		return true;
	}
	/*
	public boolean update2(int id, String name, String age, String gender,
			String address, String email, String zipcode, String birthdate,
			String experience, String telephotone, String mobilephotone,
			String birthPlace, String race, String department,
			Integer departmentId, String info, String research,
			int view, int viewed, int down, int downed) {
		Mysql mysql;
		try {
			mysql = new Mysql();
			String sql = "update userinfo set name='" + name + "',age='" + age
					+ "',gender='" + gender + "',address='" + address
					+ "',email='" + email + "',zipcode='" + zipcode
					+ "',birthdate='" + birthdate + "',experience='"
					+ experience + "',telephone='" + telephotone
					+ "',mobilephone='" + mobilephotone + "',birth_place='"
					+ birthPlace + "',race='" + race + "',department='"
					+ department + "', department_id=" + departmentId
					+ ",info='" + info + "',research='" + research
					+ "', view=" + view + ", viewed=" + viewed + ", down=" + down + ", downed=" + downed + " where id=" + id;
			try {
				mysql.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}

		return true;
	}*/

	public boolean valid(String username, String password) {
		boolean isValid = false;
		try {
			Mysql db = new Mysql();
			password = MD5EncryptUtils.MD5Encode(password);
			String sql = "select * from userinfo where login_name='" + username
					+ "' and password='" + password + "'";
			ResultSet rs = db.executeQuery(sql);

			if (rs.next()) {
				isValid = true;
			}

			db.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isValid;
	}

	public boolean isExist(String username) {
		boolean isExist = false;
		try {
			Mysql db = new Mysql();
			String sql = "select * from userinfo where login_name='" + username
					+ "'";
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				isExist = true;
			}
			db.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isExist;
	}

	@SuppressWarnings("unchecked")
	public void setResult(String offset, String filepath) {
		this.setPagesize(UserBean.USER_PAGE_SIZE);
		result = new ArrayList();
		String query = "select * FROM userinfo Order by id desc";

		try {
			ResultSet rs = this.myQuery(query, offset, filepath);

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setAge(rs.getString("age"));
				user.setLoginName(rs.getString("login_name"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setLoginCount(rs.getInt("login_count"));
				user.setDepartment(rs.getString("department"));
				user.setFlag(rs.getString("flag"));

				result.add(user);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
