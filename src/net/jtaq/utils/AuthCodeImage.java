package net.jtaq.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthCodeImage {
	private static Log logger = LogFactory.getLog(AuthCodeImage.class);
	private String authorcode;

	public String getAuthorcode() {
		return authorcode;
	}

	public void setAuthorcode(String authorcode) {
		this.authorcode = authorcode;
	}

	/**
	 * 
	 * @param fc FrontColor
	 * @param bc BackgroundColor
	 * @return Color
	 */
	@SuppressWarnings("unused")
	private Color getRandColor(int fc, int bc) {
		//
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);//red
		int g = fc + random.nextInt(bc - fc);//green
		int b = fc + random.nextInt(bc - fc);//blue
		return new Color(r, g, b);
	}

	/**
	 * 绘制认证码
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws IOException
	 */
	public void DrawImage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.debug("----------------->in AuthCodeImage:准备生成验证码！");
		// 在内存中创建图象
		int width = 70;
		int height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		// 设定背景色
		g.setColor(new Color(241, 245, 255));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("DialogInput", Font.BOLD, 20));
		// 画边框
		g.drawRect(0, 0, width - 1, height - 1);
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 14 * i + 6,16);
		}
		// 将认证码存入SESSION
		HttpSession session = request.getSession();
		session.setAttribute(authorcode, sRand);
		// 图象生效
		g.dispose();
		// 输出图象到页面
		ImageIO.setUseCache(false);// 一个解决方案
		// 请除掉原来默认的text/html
		response.reset();
		// 重设为image/jpeg
		response.setContentType("image/jpeg");
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ImageIO.write(image, "jpeg", response.getOutputStream());
		logger.debug("draw image successful!");
	}

	/**
	 * 构造函数
	 * 
	 * @param authorcode 认证码使用的作为记录的key值存储在session里
	 */
	public AuthCodeImage(String authorcode) {
		this.authorcode = authorcode;
	}
}
