/*
 * Copyright Notice:
 *      Copyright  1998-2008, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */

package com.utils;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
     
        //Setting the FAIL_ON_EMPTY_BEANS attribute. Do not throw an exception when a serialized object is empty.
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        
        //Setting the FAIL_ON_UNKNOWN_PROPERTIES attribute.
        //When the string JSON character contains attributes that do not exist in the Java object, ignore them.
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Convert Object to JsonString
     * 
     * @param jsonObj
     * @return
     */
    public static String jsonObj2Sting(Object jsonObj) {
        String jsonString = null;

        try {
            jsonString = objectMapper.writeValueAsString(jsonObj);
        } catch (IOException e) {
            System.out.printf("pasre json Object[{}] to string failed.",jsonString);
        }

        return jsonString;
    }

    /**
     * Convert JsonString to Simple Object
     * 
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T jsonString2SimpleObj(String jsonString, Class<T> cls) {
        T jsonObj = null;

        try {
            jsonObj = objectMapper.readValue(jsonString, cls);
        } catch (IOException e) {
        	System.out.printf("pasre json Object[{}] to string failed.",jsonString);
        }

        return jsonObj;
    }
   
    /**
     * Method that will convert object to the ObjectNode.
     *
     * @param value
     *            the source data; if null, will return null.
     * @return the ObjectNode data after converted.
     * @throws JsonException
     */
    public static <T> ObjectNode convertObject2ObjectNode(T object)
            throws Exception {
        if (null == object) {
            return null;
        }

        ObjectNode objectNode = null;

        if (object instanceof String) {
            objectNode = convertJsonStringToObject((String) object,
                    ObjectNode.class);
        } else {
            objectNode = convertValue(object, ObjectNode.class);
        }

        return objectNode;
    }
    
    /**
     * Method that will convert the json string to destination by the type(cls).
     * 
     * @param jsonString
     *            the source json string; if null, will return null.
     * @param cls
     *            the destination data type.
     * @return
     * @throws JsonException
     */
    public static <T> T convertJsonStringToObject(String jsonString,
            Class<T> cls) throws Exception {
        if (StringUtil.strIsNullOrEmpty(jsonString)) {
            return null;
        }

        try {
            T object = objectMapper.readValue(jsonString, cls);
            return object;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    /**
     * Method that will convert from given value into instance of given value
     * type.
     * 
     * @param fromValue
     * @param toValueType
     * @return
     * @throws JsonException
     */
    private static <T> T convertValue(Object fromValue, Class<T> toValueType)
            throws Exception {
        try {
            return objectMapper.convertValue(fromValue, toValueType);
        } catch (IllegalArgumentException e) {
            throw new Exception(e);
        }
    }
}
