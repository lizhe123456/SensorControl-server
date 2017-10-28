package com.zlcm.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JackJsonUtils {

    private static ObjectMapper mObjectMapper;

    /**
     * 解析json
     * @param content
     * @param valueType
     * @param <T>
     * @return
     */

    public static <T> T fromJson(String content, Class<T> valueType){
        if (mObjectMapper == null){
            mObjectMapper = new ObjectMapper();
        }
        try {
            return mObjectMapper.readValue(content,valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成json
     * @param object
     * @return
     */
    public static String toJson(Object object){
        if (mObjectMapper == null){
            mObjectMapper = new ObjectMapper();
        }
        try {
            return mObjectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
