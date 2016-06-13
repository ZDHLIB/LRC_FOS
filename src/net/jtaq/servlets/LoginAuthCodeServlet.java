package net.jtaq.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jtaq.utils.AuthCodeImage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginAuthCodeServlet extends HttpServlet {
	private static final long serialVersionUID = -1605802483324593490L;
	private static Log logger = LogFactory.getLog(LoginAuthCodeServlet.class);
	public static final String AUTHORCODE = "authorcode";

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("----------------->in LoginAuthCodeServlet:准备生成验证码！");
		AuthCodeImage image = new AuthCodeImage(AUTHORCODE);
		image.DrawImage(request, response);
	}
}
