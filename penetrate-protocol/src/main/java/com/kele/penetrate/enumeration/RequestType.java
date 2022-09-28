package com.kele.penetrate.enumeration;

import java.io.Serializable;

@SuppressWarnings("unused")
public enum RequestType implements Serializable {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    COPY("COPY"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    LINK("LINK"),
    UNLINK("UNLINK"),
    PURGE("PURGE"),
    LOCK("LOCK"),
    UNLOCK("UNLOCK"),
    PROPFIND("PROPFIND"),
    VIEW("VIEW"),
    TRACE("TRACE"),
    CONNECT("CONNECT");
    public final String code;

    RequestType(String code) {
        this.code = code;
    }

    public static RequestType getRequestTypeByCodeStr(String codeStr) {
        if (codeStr == null) {
            return null;
        }
        RequestType[] values = RequestType.values();
        for (RequestType type : values) {
            if (type.code.equals(codeStr)) {
                return type;
            }
        }
        return null;
    }
}
