package com.service.deviceUpgrade;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Query Batch Packages :
 * This interface is used to query a list of uploaded version packages that meet a specified condition.
 */
public class QueryBatchPackages {

	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;
        String urlQueryBatchPackages = Constant.QUERY_BATCH_PACKAGES;
        
        //please replace the pageSize and fileType(firmwarePackage|softwarePackage), when you call this interface.
        Integer pageSize = 100;
        String fileType = "firmwarePackage";
        
        Map<String, String> paramQueryBatchPackages = new HashMap<>();
        paramQueryBatchPackages.put("pageSize", pageSize.toString());
        paramQueryBatchPackages.put("fileType", fileType);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseQueryBatchPackages = 
        		httpsUtil.doGetWithParasGetStatusLine(urlQueryBatchPackages, paramQueryBatchPackages, header);

        System.out.println("QueryBatchPackages, response content:");
        System.out.println(responseQueryBatchPackages.getStatusLine());
        System.out.println(responseQueryBatchPackages.getContent());
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
