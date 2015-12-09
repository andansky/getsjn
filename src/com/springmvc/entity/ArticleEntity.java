package com.springmvc.entity;

/**
 * Created by Administrator on 2015/11/20 0020.
 */
public class ArticleEntity {
    private String id;
    private String title;
    private String content;
    private String type;
    private String rel_chan;
    private String is_hot;
    private String is_bot;
    private String create_date;
    private String update_date;
    private String is_collect;
    private String autor;

    public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRel_chan() {
        return rel_chan;
    }

    public void setRel_chan(String rel_chan) {
        this.rel_chan = rel_chan;
    }

    public String getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(String is_hot) {
        this.is_hot = is_hot;
    }

    public String getIs_bot() {
        return is_bot;
    }

    public void setIs_bot(String is_bot) {
        this.is_bot = is_bot;
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

    public String getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(String is_collect) {
        this.is_collect = is_collect;
    }

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }

    private String is_read;
}
