package com.service.deviceManagement;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Modify Device Information :
 * This interface is used to set or modify basic information about devices.
 */
public class ModifyDeviceInfo {

	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;

        //please replace the deviceId, when you call this interface.
        String deviceId = "3abc6184-9969-4bb6-95d9-f8646a3511ad";
        String urlModifyDeviceInfo = Constant.MODIFY_DEVICE_INFO + "/" + deviceId;

        //please replace the following parameter values, when you call this interface.
        String name = "demo01";
        String region = "China";

        Map<String, Object> paramModifyDeviceInfo = new HashMap<>();
        paramModifyDeviceInfo.put("name", name);
        paramModifyDeviceInfo.put("region", region);
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paramModifyDeviceInfo);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseModifyDeviceInfo = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceInfo,
                header, jsonRequest);

        System.out.println("ModifyDeviceInfo, response content:");
        System.out.println(responseModifyDeviceInfo.getStatusLine());
        System.out.println(responseModifyDeviceInfo.getContent());
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