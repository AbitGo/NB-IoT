package com.service.subscribtionManagement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * SubscribeServiceNotification :
 * This interface is used to subscribe service data of IoT platform. 
 */
//订阅服务通知
public class SubscribeServiceNotification {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlSubscribeServiceNotification = Constant.SUBSCRIBE_SERVICE_NOTIFYCATION;

        /*
         * please replace the notifyType, when you call this interface.
         * service Notify Type:
         * deviceAdded|     bindDevice|     deviceInfoChanged|
         * deviceDataChanged|   deviceDatasChanged|     deviceDeleted|
         * messageConfirm|      commandRsp|     deviceEvent|
         * serviceInfoChanged|  ruleEvent|      deviceModelAdded|
         * deviceModelDeleted|      deviceDesiredPropertiesModifyStatusChanged
         */
        //该地方请注意,因为需要定义多处地方，我使用的就是很简单map方法储存

        Map<String,String> notifyTypeAndcallbackurlMap = new HashMap<>();
        notifyTypeAndcallbackurlMap.put("serviceInfoChanged",Constant.SERVICE_INFO_CHANGED_CALLBACK_URL);
        notifyTypeAndcallbackurlMap.put("deviceInfoChanged",Constant.DEVICE_INFO_CHANGED_CALLBACK_URL);
        notifyTypeAndcallbackurlMap.put("deviceDataChanged",Constant.DEVICE_DATA_CHANGED_CALLBACK_URL);
        notifyTypeAndcallbackurlMap.put("deviceAdded",Constant.DEVICE_ADDED_CALLBACK_URL);
        notifyTypeAndcallbackurlMap.put("deviceDeleted",Constant.DEVICE_DELETED_CALLBACK_URL);

        notifyTypeAndcallbackurlMap.put("messageConfirm",Constant.MESSAGE_CONFIRM_CALLBACK_URL);
        notifyTypeAndcallbackurlMap.put("commandRsp",Constant.COMMAND_RSP_CALLBACK_URL);
        notifyTypeAndcallbackurlMap.put("deviceEvent",Constant.DEVICE_EVENT_CALLBACK_URL);
        notifyTypeAndcallbackurlMap.put("ruleEvent",Constant.RULE_EVENT_CALLBACK_URL);
        notifyTypeAndcallbackurlMap.put("deviceDatasChanged",Constant.DEVICE_DATAS_CHANGED_CALLBACK_URL);

        Iterator<Map.Entry<String,String>> entryIterator = notifyTypeAndcallbackurlMap.entrySet().iterator();
        while(entryIterator.hasNext())
        {
            Map.Entry<String,String> entry = entryIterator.next();
            String notifyType = entry.getKey();
            String callbackurl = entry.getValue();

            Map<String, Object> paramSubscribe = new HashMap<>();
            paramSubscribe.put("notifyType", notifyType);
            paramSubscribe.put("callbackUrl", callbackurl);
            paramSubscribe.put("appId", appId);

            String jsonRequest = JsonUtil.jsonObj2Sting(paramSubscribe);

            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, appId);
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

            HttpResponse httpResponse = httpsUtil.doPostJson(urlSubscribeServiceNotification, header, jsonRequest);

            String bodySubscribe = httpsUtil.getHttpResponseBody(httpResponse);

            System.out.println("SubscribeServiceNotification: " + notifyType + ", response content:");
            System.out.println(httpResponse.getStatusLine());
            System.out.println(bodySubscribe);
            System.out.println();
        }
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
