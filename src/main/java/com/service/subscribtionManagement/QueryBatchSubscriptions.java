package com.service.subscribtionManagement;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Query Batch Subscriptions :
 * This interface is used to query subscription information in batch.
 */
//查找批量订阅
public class QueryBatchSubscriptions {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);
        
        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryBatchSubscriptions = Constant.QUERY_BATCH_SUBSCRIPTIONS;
        
        /*
         * please replace the subscriptionId, when you call this interface.
         * service Notify Type
         * deviceAdded|bindDevice|deviceInfoChanged|deviceDataChanged|deviceDatasChanged|
         * deviceDeleted|messageConfirm|commandRsp|deviceEvent|serviceInfoChanged|
         * ruleEvent|deviceModelAdded|deviceModelDeleted|
         * deviceDesiredPropertiesModifyStatusChanged
         * management Notify Type
         * swUpgradeStateChangeNotify|swUpgradeResultNotify|fwUpgradeStateChangeNotify|
         * fwUpgradeResultNotify
         */
        String notifyType = "deviceAdded";
        
        Map<String, String> paramQueryBatchSubscriptions = new HashMap<>();
        paramQueryBatchSubscriptions.put("notifyType", notifyType);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseQueryBatchSubscriptions = httpsUtil.doGetWithParasGetStatusLine(
        		urlQueryBatchSubscriptions, paramQueryBatchSubscriptions, header);

        System.out.println("QueryBatchSubscriptions, response content:");
        System.out.println(responseQueryBatchSubscriptions.getStatusLine());
        System.out.println(responseQueryBatchSubscriptions.getContent());
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
