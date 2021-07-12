package com.service.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Create a Device Command Cancel Task :
 * 
 * This interface is used to cancel all device commands under the specified device ID.
 */
public class CreateDeviceCmdCancelTask {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlCreateDeviceCmdCancelTask = Constant.CREATE_DEVICECMD_CANCEL_TASK;

        //please replace the deviceId, when you call this interface.
        String deviceId  = "de71ad4a-211e-4e53-b48c-80cb2d5c88c8";
        
        Map<String, Object> paraCreateDeviceCmdCancelTask = new HashMap<>();
        paraCreateDeviceCmdCancelTask.put("deviceId", deviceId);
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paraCreateDeviceCmdCancelTask);
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse bodyCreateDeviceCmdCancelTask = httpsUtil.doPostJsonGetStatusLine(urlCreateDeviceCmdCancelTask, header, jsonRequest);
        
        System.out.println("CreateDeviceCmdCancelTask, response content:");
        System.out.println(bodyCreateDeviceCmdCancelTask.getStatusLine());
        System.out.println(bodyCreateDeviceCmdCancelTask.getContent());
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
