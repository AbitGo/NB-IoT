package com.service.deviceManagement;

import java.util.HashMap;
import java.util.Map;

import com.utils.Constant;
import com.utils.HttpsUtil;
import com.utils.JsonUtil;
import com.utils.StreamClosedHttpResponse;

/**
 * Register Directly Connected Device :
 * This interface is used to register devices on the IoT platform.
 * After the registration is successful,
 * the IoT platform allocates a device ID for the device,which is used as the unique identifier of the device.
 * Unregistered devices are not allowed to access the IoT platform.
 */
public class RegisterDirectConnectedDevice {

	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;
		String urlRegisterDirectConnectedDevice = Constant.REGISTER_DIRECT_CONNECTED_DEVICE;

        //please replace the verifyCode and nodeId and timeout, when you call this interface.
        String verifyCode = "45678910";
		String nodeId = verifyCode;
        Integer timeout = 0;
        
        //please replace the following parameter values, when you call this interface.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        //The following parameter values of this demo are use the watermeter profile that already initialized to IoT platform.
        String manufacturerId= "Water";
        String manufacturerName = "Water";
        String deviceType = "WaterMeter";
        String model = "demo130";
        String protocolType = "CoAP";
        
        Map<String, Object> paramDeviceInfo = new HashMap<>();
        paramDeviceInfo.put("manufacturerId", manufacturerId);
        paramDeviceInfo.put("manufacturerName", manufacturerName);
        paramDeviceInfo.put("deviceType", deviceType);
        paramDeviceInfo.put("model", model);
        paramDeviceInfo.put("protocolType", protocolType);

        Map<String, Object> paramReg = new HashMap<>();
        paramReg.put("verifyCode", verifyCode.toUpperCase());
        paramReg.put("nodeId", nodeId.toUpperCase());
        paramReg.put("deviceInfo", paramDeviceInfo);
        paramReg.put("timeout", timeout);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseRegisterDirectConnectedDevice = httpsUtil.doPostJsonGetStatusLine(urlRegisterDirectConnectedDevice, header, jsonRequest);

        System.out.println("RegisterDirectConnectedDevice, response content:");
        System.out.println(responseRegisterDirectConnectedDevice.getStatusLine());
        System.out.println(responseRegisterDirectConnectedDevice.getContent());
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
