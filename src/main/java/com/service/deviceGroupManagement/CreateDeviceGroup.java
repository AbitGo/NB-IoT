package com.service.deviceGroupManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Create Device Group :
 * This interface is used to create a device group for managing devices by group on the IoT platform.
 */
public class CreateDeviceGroup {

    public static void main(String[] args) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlCreateDeviceGroup = Constant.CREATE_DEVICE_GROUP;
        String appId = Constant.APPID;
        
        //please replace the following parameter values, when you call this interface.
        String name = "Group01";
        Integer maxDevNum = 100;
        List<String> deviceIds = new ArrayList<String>();
        deviceIds.add("de71ad4a-211e-4e53-b48c-80cb2d5c88c8");
      
        Map<String, Object> paramCreateDeviceGroup = new HashMap<>();
        paramCreateDeviceGroup.put("name", name);
        paramCreateDeviceGroup.put("maxDevNum", maxDevNum);
        paramCreateDeviceGroup.put("deviceIds", deviceIds);
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paramCreateDeviceGroup);
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseCreateDeviceGroup = httpsUtil.doPostJsonGetStatusLine(urlCreateDeviceGroup, header, jsonRequest);

        System.out.println("CreateDeviceGroup, response content:");
        System.out.println(responseCreateDeviceGroup.getStatusLine());
        System.out.println(responseCreateDeviceGroup.getContent());
        System.out.println();
    }

    /**
     * Authentication.get token
     */
    @SuppressWarnings("unchecked")
    public static String login(HttpsUtil httpsUtil) throws Exception {

        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        String urlLogin = Constant.APP_AUTH;

        Map<String, String> paramLogin = new HashMap<>();
        paramLogin.put("appId", appId);
        paramLogin.put("secret", secret);

        StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);

        System.out.println("app auth success,return accessToken:");
        System.out.println(responseLogin.getStatusLine());
        System.out.println(responseLogin.getContent());
        System.out.println();

        Map<String, String> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
        return data.get("accessToken");
    }

}
