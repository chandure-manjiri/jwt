package com.jwt.jwt.JwtConfig;

import org.springframework.stereotype.Component;

import java.io.Serializable;


public class JwtResonce implements Serializable{
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResonce(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken(){
        return this.jwttoken;
    }
}
