package com.springmvc.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apicloud.sdk.api.Resource;
import com.springmvc.data.Init;
import com.springmvc.entity.ArticleEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/22 0022.
 */
public class ArticleDao {

    private Resource resource;
    private String table_name="article";

    public ArticleDao(){
        resource=new Resource(Init.appId,Init.appKey,"");
    }

    public ArrayList<ArticleEntity> getArticleList(int index) throws UnsupportedEncodingException {

        ArrayList<ArticleEntity> list=new ArrayList<ArticleEntity>();
        JSONObject filterJSON=new JSONObject();
        filterJSON.put("limit", 10);
        filterJSON.put("skip", 10*index);
        filterJSON.put("order", "createdAt DESC");
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
            list.add(articleEntity);
        }
        return list;
    }

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

    public Boolean create(String title, String type, String rel_chan, String content, String img, String bot) throws UnsupportedEncodingException {

        JSONObject property=new JSONObject();
        if("txt".equals(type)||"img".equals(type)){
            property.put("imgs",img);
        }else {
            JSONObject json = resource.upload(img);
            if(json.getString("url")!=""){
                property.put("imgs",json.getString("url"));
            }
        }
//        property.put("title", URLEncoder.encode(title,"utf-8"));
        title = title.replaceAll("%", "%25");//转码%号
        property.put("title", title);
        property.put("rel_chan",rel_chan);
        property.put("type",type);
        content = content.replaceAll("%", "%25");//转码%号
        property.put("content",content);
//        property.put("content", URLEncoder.encode(content,"utf-8"));
        property.put("is_bot",bot);
        JSONObject jsonObject=resource.createObject(table_name, property);
        return true;
    }

    public Boolean delete(String id){
        JSONObject jsonObject=resource.deleteObject(table_name, id);
        return true;
    }

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
}
