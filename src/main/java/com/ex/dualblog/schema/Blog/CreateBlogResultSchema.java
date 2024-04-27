package com.ex.dualblog.schema.Blog;

public class CreateBlogResultSchema {
    public String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean ok;

    public boolean getOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public CreateBlogResultSchema(String msg, boolean ok) {
        this.msg = msg;
        this.ok = ok;
    }

    public CreateBlogResultSchema() {}

    
}
