package com.service.batchTask;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Query Specify Batch Task :
 * 
 * This interface is used by NAs to query specify batch task.
 */
public class QuerySpecifyBatchTask {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;

        //please replace the taskId, when you call this interface.
        String taskId = "5c26d34878965909ca44c1f0";
        String urlQuerySpecifyBatchTask = Constant.QUERY_SPECIFY_BATCH_TASK + "/" + taskId;

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseQuerySpecifyBatchTask = httpsUtil.doGetWithParasGetStatusLine(urlQuerySpecifyBatchTask,
        		null, header);

        System.out.println("QuerySpecifyBatchTask, response content:");
        System.out.println(responseQuerySpecifyBatchTask.getStatusLine());
        System.out.println(responseQuerySpecifyBatchTask.getContent());
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
