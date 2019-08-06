package com.service.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import org.apache.http.HttpResponse;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Create Device Command :
 * 
 * This interface is used to send command to device.
 * If a device is not online,
 * the IoT platform buffers the command and delivers the message to the device after the device is online.
 * The NA can set the maximum buffering time.
 */


//该文件是创建设备下发命令
public class CreateDeviceCommand {

    public static void main(String[] args) throws Exception {

        String num="1";
        String Onoff= "1";
        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlCreateDeviceCommand = Constant.CREATE_DEVICE_CMD;
        String appId = Constant.APPID;
        String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;

        //由电信平台提供deviceID
        String deviceId = "********-****-****-****-********";
        //please replace the following parameter values as required, when you call this interface.
        Integer expireTime = 0;
        Integer maxRetransmit = 3;

        //please replace the following parameter values, when you call this interface.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        //The following parameter values of this demo are use the watermeter profile that already initialized to IoT platform.
        String serviceId_GuangDian = "GuangDian";
        String method_CLEAR_GETDATA = "CLEAR_GETDATA";
        String serviceId_streetLight = "streetLight";
        String method_CONTROL_STREETLIGHT = "CONTROL_STREETLIGHT";
        ObjectNode paras_1 = JsonUtil.convertObject2ObjectNode("{\"value\":\"1\"}");
        ObjectNode paras_0 = JsonUtil.convertObject2ObjectNode("{\"value\":\"0\"}");

        Map<String, Object> paramCommand = new HashMap<>();
        if(num.equals("0")){//01 guangdian
            paramCommand.put("serviceId", serviceId_GuangDian);
            paramCommand.put("method", method_CONTROL_STREETLIGHT);
        } else {
            paramCommand.put("serviceId", serviceId_streetLight);
            paramCommand.put("method", method_CLEAR_GETDATA);
        }
        if(Onoff.equals("1")) {
            paramCommand.put("paras", paras_1);
        }else {
            paramCommand.put("paras", paras_0);
        }
        
        Map<String, Object> paramCreateDeviceCommand = new HashMap<>();
        paramCreateDeviceCommand.put("deviceId", deviceId);
        paramCreateDeviceCommand.put("command", paramCommand);
        paramCreateDeviceCommand.put("callbackUrl", callbackUrl);
        paramCreateDeviceCommand.put("expireTime", expireTime);
        paramCreateDeviceCommand.put("maxRetransmit", maxRetransmit);
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paramCreateDeviceCommand);
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        HttpResponse responseCreateDeviceCommand = httpsUtil.doPostJson(urlCreateDeviceCommand, header, jsonRequest);

        String responseBody = httpsUtil.getHttpResponseBody(responseCreateDeviceCommand);

        System.out.println("CreateDeviceCommand, response content:");
        System.out.println(responseCreateDeviceCommand.getStatusLine());
        System.out.println(responseBody);
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
