package com.springmvc.controller;

import com.springmvc.dao.ArticleDao;
import com.springmvc.entity.ArticleEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("share")
public class ShareController {
	ArticleDao articleDao=new ArticleDao();
	@RequestMapping(value = "share/{id}",method = RequestMethod.GET)
	public String printWelcome(ModelMap model,@PathVariable("id")String id) throws UnsupportedEncodingException {
		ArticleEntity articleEntity=new ArticleEntity();
		articleEntity=articleDao.getArticle(id);
		model.addAttribute("article",articleEntity);
		return "share/share";
	}
}