package com.service.deviceGroupManagement;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Query Specify Device Group Members :
 * This interface is used to query information about members in a specified device group.
 */
public class QuerySpecifyDeviceGroupMembers {

    public static void main(String[] args) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlQuerySpecifyDeviceGroupMembers = Constant.QUERY_DEVICE_GROUP_MEMBERS;
        String appId = Constant.APPID;
        
        //please replace the devGroupId, when you call this interface.
        String devGroupId = "e8b8edce-7e08-4131-b3e5-c78351e62dc2";
        
        Map<String, String> paramQuerySpecifyDeviceGroupMembers = new HashMap<>();
        paramQuerySpecifyDeviceGroupMembers.put("devGroupId", devGroupId);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseparamQuerySpecifyDeviceGroupMembers = httpsUtil.doGetWithParasGetStatusLine(urlQuerySpecifyDeviceGroupMembers, paramQuerySpecifyDeviceGroupMembers, header);

        System.out.println("QuerySpecifyDeviceGroupMembers, response content:");
        System.out.println(responseparamQuerySpecifyDeviceGroupMembers.getStatusLine());
        System.out.println(responseparamQuerySpecifyDeviceGroupMembers.getContent());
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
