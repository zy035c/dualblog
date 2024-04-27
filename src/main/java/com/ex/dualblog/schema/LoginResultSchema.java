package com.ex.dualblog.schema;

import java.io.Serializable;

public class LoginResultSchema implements Serializable {
    public String token;
    public String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LoginResultSchema(String t, String uuid) {
        token = t;
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String newToken) {
        token = newToken;
    }
}
