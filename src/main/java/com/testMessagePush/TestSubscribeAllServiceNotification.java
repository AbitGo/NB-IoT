package com.testMessagePush;

import org.apache.http.HttpResponse;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//测试订阅所有服务通知
public class TestSubscribeAllServiceNotification {

    public static void main(String args[]) throws Exception {

    	// start server to receive message
    	SimpleHttpServer.startServer(8133);

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
	    
        // Authentication.get token
		String accessToken = login(httpsUtil);
		
		//Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID; 
        String urlSubscribe = Constant.SUBSCRIBE_SERVICE_NOTIFYCATION; 

        String callbackurl = NotifyType.TEST_CALLBACK_BASE_URL; 
        
        /*
         * na to subscribe notification from the IoT platform
         * notifyTypes: 
         * bindDevice/serviceInfoChanged/deviceInfoChanged/deviceDataChanged/deviceAdded/deviceDeleted
         * messageConfirm/commandRsp/deviceEvent/appDeleted/ruleEvent/deviceDatasChanged
         */
        List<String> serviceNotifyTypes = NotifyType.getServiceNotifyTypes();
        for (String serviceNotifyType : serviceNotifyTypes) {
            
            Map<String, Object> paramServiceSubscribe = new HashMap<>();
            paramServiceSubscribe.put("notifyType", serviceNotifyType);
            paramServiceSubscribe.put("callbackUrl", callbackurl);
            
            String jsonRequest = JsonUtil.jsonObj2Sting(paramServiceSubscribe);
            
            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, appId);
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            
            HttpResponse httpResponse = httpsUtil.doPostJson(urlSubscribe, header, jsonRequest);
            
            String bodySubscribe = httpsUtil.getHttpResponseBody(httpResponse);
            
            System.out.println("SubscribeServiceNotification, notifyType:" + serviceNotifyType + ", callbackurl:" + callbackurl +", response content:");
            System.out.print(httpResponse.getStatusLine());
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
        System.out.print(responseLogin.getStatusLine());
        System.out.println(responseLogin.getContent());
        System.out.println();

        Map<String, String> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
        return data.get("accessToken");
    }

}