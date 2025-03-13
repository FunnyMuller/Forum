package com.ricostone.communitymodule.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author RicoStone
 * @date 2023/11/28
 */
public class CommunityUtil {
    public static String DOMAIN = "http://localhost:8080";
    public static String CONTEXT_PATH = "/community";
    public static Random random = new Random();
    public static String getDomain(){
        return DOMAIN;
    }
    public static String getContextPath(){
        return CONTEXT_PATH;
    }

    public static String getJSONString(int code, String msg, Map<?,?> map){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if(map != null){
            for(Map.Entry<?,?> entry : map.entrySet()){
                json.put(entry.getKey().toString(), entry.getValue());
            }
        }
        return json.toJSONString();
    }
    public static String getJSONString(int code, String msg){
        return getJSONString(code, msg, null);
    }
    public static String getJSONString(int code){
        return getJSONString(code, null, null);
    }

    @org.jetbrains.annotations.NotNull
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static int getRandomInt(int min, int max){
        return random.nextInt(max)%(max-min+1) + min;
    }
}
