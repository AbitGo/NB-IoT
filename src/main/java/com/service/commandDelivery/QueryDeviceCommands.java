package com.service.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Query Device Commands :
 * This interface is used to query delivered commands.
 */
public class QueryDeviceCommands {


	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;
        String urlQueryDeviceCMD = Constant.QUERY_DEVICE_CMD;
        
        //please replace the pageSize and startTime, when you call this interface.
        Integer pageSize = 100;
        String startTime = "20190101T121212Z";
        
        Map<String, String> paramQueryDeviceCommands = new HashMap<>();
        paramQueryDeviceCommands.put("pageSize", pageSize.toString());
        paramQueryDeviceCommands.put("startTime", startTime);
        
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseQueryDeviceCMD = httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceCMD, paramQueryDeviceCommands, header);
        
        System.out.println("QueryDeviceCommands, response content:");
		System.out.println(responseQueryDeviceCMD.getStatusLine());
		System.out.println(responseQueryDeviceCMD.getContent());
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