package com.springmvc.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apicloud.sdk.api.Resource;
import com.springmvc.data.Init;
import com.springmvc.entity.ArticleEntity;

/**
* 类名: ArticleDao </br>
* 包名： com.springmvc.dao
* 描述: 文章类   </br>
* 创建时间：  2015-12-15 </br>
* 发布版本：V1.0  </br>
 */
public class ArticleDao {

    private Resource resource;
    private String table_name="article";

    public ArticleDao(){
        resource=new Resource(Init.appId,Init.appKey,"");
    }

    /**
    * 方法名：getArticleList</br>
    * 详述：获取文章列表  </br>
    * 创建时间：2015-12-15  </br>
    * @param index
    * @return
    * @throws UnsupportedEncodingException 说明返回值含义
    * @throws 说明发生此异常的条件
     */
    public ArrayList<ArticleEntity> getArticleList(int index) throws UnsupportedEncodingException {
        ArrayList<ArticleEntity> list=new ArrayList<ArticleEntity>();
        JSONObject filterJSON=new JSONObject();
        filterJSON.put("limit", 10);//限制返回十条记录
        filterJSON.put("skip", 10*(index-1));//跳过多少条纪录，比如当前在第二页，跳过第一面的10*（2-1）条纪录。
        filterJSON.put("order", "createdAt DESC");//按时间降序排列
        System.out.println(filterJSON.toJSONString());
        JSONArray jsonArray=resource.doFilterSearch(table_name,filterJSON.toJSONString()).getJSONArray("data");
        for(int i=0;i<jsonArray.size();i++){
            ArticleEntity articleEntity=new ArticleEntity();
            JSONObject jsonObject=new JSONObject();
            jsonObject=jsonArray.getJSONObject(i);
            articleEntity.setId(jsonObject.getString("id"));
            articleEntity.setTitle(URLDecoder.decode(jsonObject.getString("title"), "utf-8"));
            articleEntity.setType(jsonObject.getString("type"));
            articleEntity.setContent(URLDecoder.decode(jsonObject.getString("content"), "utf-8"));
            articleEntity.setCreate_date(jsonObject.getString("createdAt"));
            articleEntity.setAutor(jsonObject.getString("autor"));
            list.add(articleEntity);
        }
        return list;
    }
    
    /**
    * 方法名：checkObjectExists</br>
    * 详述：判断文章是否存在  </br>
    * 创建时间：2015-12-15  </br>
    * @param title
    * @return 说明返回值含义
    * @throws 说明发生此异常的条件
     */
    public boolean checkObjectExists(String title){
    	JSONObject where=new JSONObject();
    	where.put("title", title);
    	JSONObject filterJSON=new JSONObject();
    	filterJSON.put("where", where);     
    	System.out.println(filterJSON.toString());
        JSONArray jsonArray=resource.doFilterSearch(table_name,filterJSON.toJSONString()).getJSONArray("data");
        System.out.println(jsonArray.toString());
        if(jsonArray.size()>0){
        	return true;
        }
		return false;
    }
    
    /**
    * 方法名：getArticle</br>
    * 详述：根据 文章ID 获取文章  </br>
    * 创建时间：2015-12-15  </br>
    * @param id
    * @return
    * @throws UnsupportedEncodingException 说明返回值含义
    * @throws 说明发生此异常的条件
     */
    public ArticleEntity getArticle(String id) throws UnsupportedEncodingException {
        ArticleEntity articleEntity=new ArticleEntity();
        JSONObject jsonObject = resource.getObject(table_name, id);
        articleEntity.setId(jsonObject.getString("id"));
        articleEntity.setTitle(URLDecoder.decode(jsonObject.getString("title"), "utf-8"));
        articleEntity.setType(jsonObject.getString("type"));
        articleEntity.setRel_chan(jsonObject.getString("rel_chan"));
        articleEntity.setIs_bot(jsonObject.getString("is_bot"));
        articleEntity.setContent(URLDecoder.decode(jsonObject.getString("content"), "utf-8"));
        articleEntity.setCreate_date(jsonObject.getString("createdAt"));
        return articleEntity;
    }

    /**
    * 方法名：create</br>
    * 详述：增加文章 </br>
    * 创建时间：2015-12-15  </br>
    * @param title
    * @param type
    * @param rel_chan
    * @param content
    * @param img
    * @param bot
    * @param autor
    * @return
    * @throws UnsupportedEncodingException 说明返回值含义
    * @throws 说明发生此异常的条件
     */
    public Boolean create(String title, String type, String rel_chan, String content, String img, String bot,String autor) throws UnsupportedEncodingException {
        JSONObject property=new JSONObject();
        if("txt".equals(type)||"img".equals(type)){
            property.put("imgs",img);
        }else {
            JSONObject json = resource.upload(img);
            if(json.getString("url")!=""){
                property.put("imgs",json.getString("url"));
            }
        }
        title = title.replaceAll("%", "%25");//转码%号
        property.put("title", title);
        property.put("rel_chan",rel_chan);
        property.put("type",type);
        content = content.replaceAll("%", "%25");//转码%号
        property.put("content",content);
        property.put("is_bot",bot);
        property.put("autor", autor);
        JSONObject jsonObject=resource.createObject(table_name, property);
        return true;
    }

    /**
    * 方法名：delete</br>
    * 详述：删除文章 </br>
    * 创建时间：2015-12-15  </br>
    * @param id
    * @return 说明返回值含义
    * @throws 说明发生此异常的条件
     */
    public Boolean delete(String id){
        JSONObject jsonObject=resource.deleteObject(table_name, id);
        return true;
    }

    /**
    * 方法名：update</br>
    * 详述：更新文章  </br>
    * 创建时间：2015-12-15  </br>
    * @param id
    * @param title
    * @param type
    * @param rel_chan
    * @param content
    * @param img
    * @param bot
    * @return
    * @throws UnsupportedEncodingException 说明返回值含义
    * @throws 说明发生此异常的条件
     */
    public Boolean update(String id,String title,String type,String rel_chan,String content,String img,String bot) throws UnsupportedEncodingException {
    	System.out.println(id);
    	JSONObject property=new JSONObject();
        if("txt".equals(type)||"img".equals(type)){
            property.put("imgs",img);
        }else {
            JSONObject json = resource.upload(img);
            if(json.getString("url")!=""){
                property.put("imgs",json.getString("url"));
            }
        }
        title = title.replaceAll("%", "%25");//转码%号
        property.put("title", title);
        property.put("rel_chan",rel_chan);
        property.put("type",type);
        content = content.replaceAll("%", "%25");//转码%号
        property.put("content",content);
        property.put("is_bot",bot);
        JSONObject jsonObject=resource.updateObject(table_name,id, property);
        return true;
    }
    
    /**
    * 方法名：getArticleForTitle</br>
    * 详述：通过文章标题搜索</br>
    * 创建时间：2015-12-17  </br>
    * @param title
    * @return
    * @throws UnsupportedEncodingException 说明返回值含义
    * @throws 说明发生此异常的条件
     */
    public ArrayList<ArticleEntity> getArticleForTitle(String title) throws UnsupportedEncodingException {//通过文章标题搜索
    	ArrayList<ArticleEntity> list=new ArrayList<ArticleEntity>();  
        JSONObject where=new JSONObject();
    	where.put("title", title);
    	JSONObject filterJSON=new JSONObject();
    	filterJSON.put("where", where); 
    	System.out.println(filterJSON.toJSONString());
    	JSONArray jsonArray=resource.doFilterSearch(table_name,filterJSON.toJSONString()).getJSONArray("data");
    	System.out.println(jsonArray.toJSONString());
        for(int i=0;i<jsonArray.size();i++){
            ArticleEntity articleEntity=new ArticleEntity();
            JSONObject jsonObject=new JSONObject();
            jsonObject=jsonArray.getJSONObject(i);
            articleEntity.setId(jsonObject.getString("id"));
            articleEntity.setTitle(URLDecoder.decode(jsonObject.getString("title"), "utf-8"));
            articleEntity.setType(jsonObject.getString("type"));
            articleEntity.setContent(URLDecoder.decode(jsonObject.getString("content"), "utf-8"));
            articleEntity.setCreate_date(jsonObject.getString("createdAt"));
            articleEntity.setAutor(jsonObject.getString("autor"));
            list.add(articleEntity);
        }
        System.out.println("list"+list.toString());
        return list;
    }

    
    
    public int getNumber(){
    	JSONObject json = resource.getObjectCount("article");
    	int number=json.getIntValue("article");
        return number;
    }
}
