package net.jtaq.servlets.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.lrc.db.Mysql;

public class UserPower {
	
	public void updatenum ( HttpServletRequest request,HttpServletResponse response)throws Exception {
		String view = request.getParameter("view1");
		String down = request.getParameter("down1");
		String userID = request.getParameter("userID");
		Mysql mysql;
		try {
			mysql = new Mysql();
			String sql = "update userinfo set view='" + view + "',down='" + down
					+ "' where id=" + userID;
			mysql.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
