package com.ex.dualblog.schema;

import java.io.Serializable;

public class LoginSchema implements Serializable {
    public String token;

    public LoginSchema(String t){
        token = t;
    }
    public String getToken(){
        return token;
    }
    public void setToken(String newToken){
        token = newToken;
    }
}

