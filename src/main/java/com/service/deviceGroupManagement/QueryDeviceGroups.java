package com.service.deviceGroupManagement;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Query Device Groups :
 * This interface is used to query information of all the created device groups on the IoT platform.
 */
public class QueryDeviceGroups {

    public static void main(String[] args) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlQueryDeviceGroups = Constant.QUERY_DEVICE_GROUPS;
        String appId = Constant.APPID;
        
        //please replace the pageSize, when you call this interface.
        Integer pageSize = 100;
        
        Map<String, String> paramQueryDeviceGroups = new HashMap<>();
        paramQueryDeviceGroups.put("pageSize", pageSize.toString());
        
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseQueryDeviceGroups = 
        		httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceGroups, paramQueryDeviceGroups, header);

        System.out.println("QueryDeviceGroups, response content:");
        System.out.println(responseQueryDeviceGroups.getStatusLine());
        System.out.println(responseQueryDeviceGroups.getContent());
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
