package com.service.batchTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Create Batch Task :
 * 
 * When an NA needs to perform an operation on a batch of devices, the NA can call this interface to create a batch task. 
 */
public class CreateBatchTask {

	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlCreateBatchTask = Constant.CREATE_BATCH_TASK;

        //please replace the following parameter values, when you call this interface.
        Integer timeout = 60;
        String taskName = "BatchTask";
        
        /*
         * Device Cmd Task
         */
        String taskType_DeviceCmd = "DeviceCmd";

        //please replace the following parameter values, when you call this interface.
        String serviceId = "WaterMeter";
        String method = "SET_TEMPERATURE_READ_PERIOD";
        ObjectNode paras = JsonUtil.convertObject2ObjectNode("{\"value\":\"12\"}");
        
        Map<String, Object> command = new HashMap<>();
        command.put("serviceId", serviceId);
        command.put("method", method);
        command.put("paras", paras);
        
        //DeviceList|DeviceType|DeviceArea|GroupList|Broadcast|GroupIdList
        String type = "DeviceList";
        //please replace the following parameter values, when you call this interface.
        List<String> deviceList = new ArrayList<String>();
        deviceList.add("a9c09ea9-a361-4a17-a381-a07dca4c8ebf");
        
        String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;
        Integer maxRetransmit = 3;
        
        Map<String, Object> paramBody_DeviceCmd = new HashMap<>();
        paramBody_DeviceCmd.put("type", type);
        paramBody_DeviceCmd.put("deviceList", deviceList);
        paramBody_DeviceCmd.put("command", command);
        paramBody_DeviceCmd.put("callbackUrl", callbackUrl);
        paramBody_DeviceCmd.put("maxRetransmit", maxRetransmit);
        
        ObjectNode param_DeviceCmd = JsonUtil.convertObject2ObjectNode(paramBody_DeviceCmd);
        
        Map<String, Object> paramDeviceCmdTask = new HashMap<>();
        paramDeviceCmdTask.put("appId", appId);
        paramDeviceCmdTask.put("timeout", timeout);
        paramDeviceCmdTask.put("taskName", taskName);
        paramDeviceCmdTask.put("taskType", taskType_DeviceCmd);
        paramDeviceCmdTask.put("param", param_DeviceCmd);
        
        String jsonRequestDeviceCmdTask = JsonUtil.jsonObj2Sting(paramDeviceCmdTask);

        Map<String, String> headerDeviceCmdTask = new HashMap<>();
        headerDeviceCmdTask.put(Constant.HEADER_APP_KEY, appId);
        headerDeviceCmdTask.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseDeviceCmdTask = httpsUtil.doPostJsonGetStatusLine(
        		urlCreateBatchTask, headerDeviceCmdTask, jsonRequestDeviceCmdTask);

        System.out.println("CreateBatchCmdTask, response content:");
        System.out.println(responseDeviceCmdTask.getStatusLine());
        System.out.println(responseDeviceCmdTask.getContent());
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
