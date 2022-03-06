package com.example.demo.utis;

public class JWTUtils {
    public static final String SECRET = "mySecretKey123";
    public static final String AUTH_HEADER = "Authorization";
    public static final long EXPIRE_ACCESS_TOKEN = 60*60*1000;
    public static final String PREFIX = "Bearer ";

}
