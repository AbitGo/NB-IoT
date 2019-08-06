package com.service.appAccessSecurity;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 *  Auth:
 *  
 *  This interface is called by an NA for access authentication when the NA accesses open APIs of the IoT platform for the first time. 
 */
public class Authentication {
    @SuppressWarnings("unchecked")
	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        String urlLogin = Constant.APP_AUTH;

        Map<String, String> param = new HashMap<>();
        param.put("appId", appId);
        param.put("secret", secret);

        StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, param);

        System.out.println("app auth success,return accessToken:");
        System.out.println(responseLogin.getStatusLine());
        System.out.println(responseLogin.getContent());
        System.out.println();

        //resolve the value of accessToken from responseLogin.
        Map<String, String> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
        String accessToken = data.get("accessToken");
        System.out.println("accessToken:" + accessToken);

    }
}
