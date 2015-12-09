package com.springmvc.controller;

import com.springmvc.dao.ColumnDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("column")
public class ColumnController {
	ColumnDao columnDao=new ColumnDao();
	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model,HttpSession httpSession) throws UnsupportedEncodingException {
		model.addAttribute("list",columnDao.columnList());
		return "column/list";
	}

	@RequestMapping(value = "create",method = RequestMethod.POST)
	public String create(@RequestParam(value="name")String name) throws UnsupportedEncodingException {
		columnDao.create(name,"");
		return "redirect:/column/";
	}

	@RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable("id")String id){
		columnDao.delete(id);
		return "redirect:/column/";
	}

	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String add(){
		return "column/add";
	}
}