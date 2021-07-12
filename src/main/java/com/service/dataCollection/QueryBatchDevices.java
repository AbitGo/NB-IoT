package com.service.dataCollection;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Query Batch Devices:
 * This interface is used to query information about devices in batches.
 */
public class QueryBatchDevices {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryBatchDevices = Constant.QUERY_BATCH_DEVICES;

        //please replace the status (ONLINE|OFFLINE|ABNORMAL), when you call this interface.
        String status = "OFFLINE";
        Integer pageNo = 0;

        Map<String, String> paramQueryBatchDevices = new HashMap<>();
        paramQueryBatchDevices.put("status", status);
        paramQueryBatchDevices.put("pageNo", pageNo.toString());

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseQueryBatchDevices = httpsUtil.doGetWithParasGetStatusLine(urlQueryBatchDevices,
        		paramQueryBatchDevices, header);

        System.out.println("QueryBatchDevices, response content:");
        System.out.println(responseQueryBatchDevices.getStatusLine());
        System.out.println(responseQueryBatchDevices.getContent());
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
