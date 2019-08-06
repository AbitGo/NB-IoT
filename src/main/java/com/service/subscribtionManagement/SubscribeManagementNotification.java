package com.service.subscribtionManagement;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Subscribe Management Notification :
 * This interface is used to subscribe to different types of device upgrade notifications on the IoT platform.
 */
//订阅管理通知
public class SubscribeManagementNotification {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlSubscribeManagementNotification = Constant.SUBSCRIBE_MANAGEMENT_NOTIFYCATION;
        

        /*
         * please replace the notifyType, when you call this interface.
         * subscribe swUpgradeStateChangeNotify notification
         * management Notify Type
         * swUpgradeStateChangeNotify|swUpgradeResultNotify|fwUpgradeStateChangeNotify|fwUpgradeResultNotify
         */
        String notifyType = "swUpgradeStateChangeNotify";
        
        //Please make sure that the value of callbackurl have been modified in the Constant file.
        //And choose the callbackurl according to the notifyType.
        String callbackurl = Constant.SW_UPGRADE_CALLBACK_URL;

        Map<String, Object> paramSubscribe = new HashMap<>();
        paramSubscribe.put("notifyType", notifyType);
        paramSubscribe.put("callbackurl", callbackurl);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramSubscribe);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse = httpsUtil.doPostJson(
        		urlSubscribeManagementNotification, header, jsonRequest);

        String bodySubscribe = httpsUtil.getHttpResponseBody(httpResponse);

        System.out.println("SubscribeManagementNotification: " + notifyType + ", response content:");
        System.out.println(httpResponse.getStatusLine());
        System.out.println(bodySubscribe);
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
