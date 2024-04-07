package com.ex.dualblog.schema;


public class LoginSchema {
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

