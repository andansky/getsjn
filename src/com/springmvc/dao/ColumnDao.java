package com.springmvc.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apicloud.sdk.api.Resource;
import com.springmvc.data.Init;
import com.springmvc.entity.ColumnEntity;

/**
* 类名: ColumnDao </br>
* 包名： com.springmvc.dao
* 描述: 栏目类   </br>
* 创建时间：  2015-12-11 </br>
* 发布版本：V1.0  </br>
 */
public class ColumnDao {
	
    private Resource resource;
    private String table_name="channel";

    public ColumnDao(){
        resource=new Resource(Init.appId,Init.appKey,"");
    }

    public ArrayList<ColumnEntity> columnList() throws UnsupportedEncodingException {
        ArrayList<ColumnEntity> list=new ArrayList<ColumnEntity>();
        JSONArray jsonArray=resource.getObjects(table_name).getJSONArray("data");
        for(int i=0;i<jsonArray.size();i++){
            ColumnEntity columnEntity=new ColumnEntity();
            JSONObject jsonObject=new JSONObject();
            jsonObject=jsonArray.getJSONObject(i);
            columnEntity.setId(jsonObject.getString("id"));
            columnEntity.setName(URLDecoder.decode(jsonObject.getString("title"), "utf-8"));
            columnEntity.setImage(jsonObject.getString("imgs"));
            columnEntity.setCreate_date(jsonObject.getString("createdAt"));
            columnEntity.setUpdate_date(jsonObject.getString("updateAt"));
            list.add(columnEntity);
        }
        return list;
    }

    public boolean create(String name,String img) throws UnsupportedEncodingException {
        JSONObject property=new JSONObject();
        property.put("title", URLEncoder.encode(name,"utf-8"));
        property.put("img","");
        JSONObject jsonObject=resource.createObject(table_name, property);
        return true;
    }

    public boolean delete(String id){
        JSONObject jsonObject=resource.deleteObject(table_name, id);
        return true;
    }
}
