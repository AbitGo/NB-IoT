package com.service.deviceGroupManagement;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Delete Device Group :
 * This interface is used to delete a device group from the IoT platform based on the device group ID.
 */
public class DeleteDeviceGroup {

    public static void main(String[] args) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //please replace the devGroupId, when you call this interface.
        String devGroupId = "e8b8edce-7e08-4131-b3e5-c78351e62dc2";
        
        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlDeleteDeviceGroup = Constant.DELETE_DEVICE_GROUP + "/" + devGroupId;
        String appId = Constant.APPID;
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseDeleteDeviceGroup = httpsUtil.doDeleteWithParasGetStatusLine(urlDeleteDeviceGroup, null, header);

        System.out.println("DeleteDeviceGroup, response content:");
        System.out.println(responseDeleteDeviceGroup.getStatusLine());
        System.out.println(responseDeleteDeviceGroup.getContent());
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
