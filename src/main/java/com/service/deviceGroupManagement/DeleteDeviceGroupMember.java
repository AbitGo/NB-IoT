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
 * Delete Device Group Member :
 * 
 * This interface is used to delete devices from a specified device group on the IoT platform.
 */
public class DeleteDeviceGroupMember {

    public static void main(String[] args) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlDeleteDeviceGroupMember = Constant.DELETE_DEVICE_GROUP_MEMBER;
        String appId = Constant.APPID;

        //please replace the following parameter values, when you call this interface.
        String devGroupId = "e8b8edce-7e08-4131-b3e5-c78351e62dc2";
        List<String> deviceIds = new ArrayList<String>();
        deviceIds.add("3abc6184-9969-4bb6-95d9-f8646a3511ad");
        
        Map<String, Object> paramDeleteDeviceGroupMember = new HashMap<>();
        paramDeleteDeviceGroupMember.put("devGroupId", devGroupId);
        paramDeleteDeviceGroupMember.put("deviceIds", deviceIds);
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paramDeleteDeviceGroupMember);
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseDeleteDeviceGroupMember = httpsUtil.doPostJsonGetStatusLine(urlDeleteDeviceGroupMember, header, jsonRequest);

        System.out.println("DeleteDeviceGroupMember, response content:");
        System.out.println(responseDeleteDeviceGroupMember.getStatusLine());
        System.out.println(responseDeleteDeviceGroupMember.getContent());
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
