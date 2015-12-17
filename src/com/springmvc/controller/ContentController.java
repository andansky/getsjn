package com.springmvc.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apicloud.sdk.api.Resource;
import com.springmvc.dao.ArticleDao;
import com.springmvc.dao.ColumnDao;
import com.springmvc.data.Init;
import com.springmvc.entity.ArticleEntity;

/**
* 类名: ContentController </br>
* 包名： com.springmvc.controller
* 描述: 文章类   </br>
* 创建时间：  2015-12-15 </br>
* 发布版本：V1.0  </br>
 */
@Controller
@RequestMapping("content")
public class ContentController {
	
	String autor;
	
	ArticleDao articleDao=new ArticleDao();
	ColumnDao columnDao=new ColumnDao();

	/**
	* 方法名：list</br>
	* 详述：文章默认列表 </br>
	* 创建时间：2015-12-15  </br>
	* @param model
	* @param httpSession
	* @return
	* @throws UnsupportedEncodingException 说明返回值含义
	* @throws 说明发生此异常的条件
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String  list(Model model,HttpSession httpSession,HttpServletRequest request) throws UnsupportedEncodingException {
		httpSession.getAttribute("admin");		
		if(httpSession.getAttribute("admin")==null){
			return "admin/login";
		}else{	
			JSONObject temp=JSON.parseObject(httpSession.getAttribute("admin").toString());
			autor=temp.getString("username");
			request.setAttribute("page", 1);
			model.addAttribute("list",articleDao.getArticleList(1));//getArticleList的参数为获取当前第几页的数据
			return "content/list";
		}	
	}
	
	/**
	* 方法名：list</br>
	* 详述：文章分页显示 </br>
	* 开发人员：liuhf </br>
	* 创建时间：2015-12-16  </br>
	* @param model
	* @param httpSession
	* @param page
	* @param request
	* @return
	* @throws UnsupportedEncodingException 说明返回值含义
	* @throws 说明发生此异常的条件
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
	public String  pageList(Model model,HttpSession httpSession,HttpServletRequest request) throws UnsupportedEncodingException {
		int page=Integer.parseInt(request.getParameter("page"));//获取页码
		String title=request.getParameter("searchinput");//获取查询条件
		//如果查询条件不为空的时候，就进行条件查询
		if(!"".equals(title)&&title!=null){
			title=URLDecoder.decode(title,"utf-8");
			request.setAttribute("page", -1);//设置点击上下页没有效果
			model.addAttribute("list",articleDao.getArticleForTitle(title));
		}else{//默认为直接查询
			JSONObject temp=JSON.parseObject(httpSession.getAttribute("admin").toString());
			autor=temp.getString("username");
			request.setAttribute("page", page);
			model.addAttribute("list",articleDao.getArticleList(page));//getArticleList的参数为获取当前第几页的数据
		}
		return "content/list";
		
	}

	/**
	* 方法名：add</br>
	* 详述：增加文章  </br>
	* 创建时间：2015-12-15  </br>
	* @param model
	* @return
	* @throws UnsupportedEncodingException 说明返回值含义
	* @throws 说明发生此异常的条件
	 */
	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String add(ModelMap model) throws UnsupportedEncodingException {
		model.addAttribute("list",columnDao.columnList());
		return "content/add";
	}

	@RequestMapping(value = "create",method = RequestMethod.POST)
	public String create(@RequestParam(value = "title")String title,@RequestParam(value = "type")String type,
						 @RequestParam(value = "column")String column,@RequestParam(value = "content")String content,
						 @RequestParam(value = "img")String img,@RequestParam(value = "bot")String bot) throws UnsupportedEncodingException {
		articleDao.create(title,type,column,content,img,bot,autor);
		return "redirect:/content/";
	}

	/**
	* 方法名：doUpdate</br>
	* 详述：更新文章 </br>
	* 创建时间：2015-12-15  </br>
	* @param id
	* @param title
	* @param type
	* @param column
	* @param content
	* @param img
	* @param bot
	* @return
	* @throws UnsupportedEncodingException 说明返回值含义
	* @throws 说明发生此异常的条件
	 */
	@RequestMapping(value = "do_update/{id}")
	public String doUpdate(@PathVariable(value = "id")String id,@RequestParam(value = "title")String title,
						   @RequestParam(value = "type")String type,@RequestParam(value = "column")String column,
						   @RequestParam(value = "content")String content,@RequestParam(value = "img")String img,
						   @RequestParam(value = "bot")String bot) throws UnsupportedEncodingException {
		articleDao.update(id,title,type,column,content,img,bot);
		return "redirect:/content/";
	}

	/**
	* 方法名：delete</br>
	* 详述：删除文章  </br>
	* 创建时间：2015-12-15  </br>
	* @param id
	* @return 说明返回值含义
	* @throws 说明发生此异常的条件
	 */
	@RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id")String id){
		articleDao.delete(id);
		return "redirect:/content/";
	}

	
	@RequestMapping(value = "getNumber",method = RequestMethod.GET)
	public int getNumber(){
		int number=articleDao.getNumber();
		return number;
	}
	
	
	@RequestMapping(value = "update/{id}",method = RequestMethod.GET)
	public String update(ModelMap modelMap,@PathVariable(value = "id")String id) throws UnsupportedEncodingException {

		modelMap.addAttribute("article",articleDao.getArticle(id));
		modelMap.addAttribute("list",columnDao.columnList());
		return "content/update";
	}

	@RequestMapping(value = "uploadfile",method = RequestMethod.GET)
	public String uploadfile(ModelMap modelMap) {
		
		Resource resource = new Resource(Init.appId, Init.appKey,"");
		JSONObject json = resource.upload("C:/Users/Administrator/Desktop/粉色水印.png");
		modelMap.addAttribute("test",json);
		return "test/test";
	}
	
	/**
	* 方法名：checkObjectExists</br>
	* 详述：根据文章的标题查询，是否重复 </br>
	* 创建时间：2015-12-17  </br>
	* @param response
	* @param title
	* @return
	* @throws IOException 说明返回值含义
	* @throws 说明发生此异常的条件
	 */
	@RequestMapping(value = "checkObjectExists/{title}",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkObjectExists(HttpServletResponse response,@PathVariable(value = "title")String title) throws IOException {
		Map<String, Object> map=new HashMap<String,Object>();
		ArticleDao articledao=new ArticleDao(); 
		title=URLDecoder.decode(title,"utf-8");
		boolean temp=articledao.checkObjectExists(title);
		System.out.println(temp);
		map.put("status", temp);
		return map;
	}
	
}