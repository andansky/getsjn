package com.springmvc.entity;

/**
* 类名: ColumnEntity </br>
* 包名： com.springmvc.entity
* 描述: 栏目类 </br>
* 创建时间：  2015-12-11 </br>
* 发布版本：V1.0  </br>
 */
public class ColumnEntity {
    private String id;
    private String name;
    private String image;
    private String create_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    private String update_date;

}
