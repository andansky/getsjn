package com.springmvc.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
* 类名: CaptchaImageCreateController </br>
* 包名： com.springmvc.controller
* 描述: 验证码生成类   </br>
* 开发人员： liuhf </br>
* 创建时间：  2015-12-15 </br>
* 发布版本：V1.0  </br>
 */
@Controller
public class CaptchaImageCreateController {

	private Producer captchaProducer = null;

	@Autowired
	public void setCaptchaProducer(Producer captchaProducer) {
		this.captchaProducer = captchaProducer;
	}

	/**
	* 方法名：handleRequest</br>
	* 详述：生成验证码方法  </br>
	* 开发人员：liuhf </br>
	* 创建时间：2015-12-15  </br>
	* @param request
	* @param response
	* @return
	* @throws Exception 说明返回值含义
	* @throws 说明发生此异常的条件
	 */
	@RequestMapping(value="/captcha-image",method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		String capText = captchaProducer.createText();
		// store the text in the session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,capText);
		// create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}

}
