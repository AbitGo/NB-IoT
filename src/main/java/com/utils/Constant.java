/**
 * File Name: com.huawei.utils.Constant.java
 *
 * Copyright Notice:
 *      Copyright  1998-2008, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package com.utils;

public class Constant {

    //please replace the IP and Port of the IoT platform environment address, when you use the demo.
    public static final String BASE_URL = "https://180.101.147.89:8743";

    //修改以下两个参数，由电信平台提供.
    public static final String APPID = "**********************";
    public static final String SECRET = "**********************";
    /*
     *IP and port of callback url.
     *请将这个url改成你的云服务器网址
     */
    public static final String CALLBACK_BASE_URL = "http://***.***.***.***:****";

    /*
     * complete callback url.
     * please replace uri, when you use the demo.
     */
    //这是测试url
    public static final String DEVICE_DATA_CHANGED_CALLBACK =  "/na/iocm/devNotify/v1.1.0/updateDeviceData";

    public static final String DEVICE_ADDED_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/addDevice";
    public static final String DEVICE_INFO_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/updateDeviceInfo";
    public static final String DEVICE_DATA_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/updateDeviceData";
    public static final String DEVICE_DELETED_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/deletedDevice";
    public static final String MESSAGE_CONFIRM_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/commandConfirmData";
    public static final String SERVICE_INFO_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/updateServiceInfo";
    public static final String COMMAND_RSP_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/commandRspData";
    public static final String DEVICE_EVENT_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/DeviceEvent";
    public static final String RULE_EVENT_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/RulEevent";
    public static final String DEVICE_DATAS_CHANGED_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/updateDeviceDatas";
    public static final String DEVICE_SHADOW_MODIFIED_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/modifyDeviceDesired";
    
    public static final String SW_UPGRADE_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/upgradeSW";
    public static final String FW_UPGRADE_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/upgradeFW";


    /*
     * Specifies the callback URL for the command execution result notification.
     * For details about the execution result notification definition.
     *
     * please replace uri, when you use the demo.
     */
    public static final String REPORT_CMD_EXEC_RESULT_CALLBACK_URL = CALLBACK_BASE_URL + "/na/iocm/devNotify/v1.1.0/reportCmdExecResult";


    //Paths of certificates.
    public static String SELFCERTPATH = "/src/resource/cert/outgoing.CertwithKey.pkcs12";
    public static String TRUSTCAPATH = "/src/resource/cert/ca.jks";

    //Password of certificates.
    public static String SELFCERTPWD = "IoM@1234";
    public static String TRUSTCAPWD = "Huawei@123";


    //*************************** The following constants do not need to be modified *********************************//

    /*
     * request header
     * 1. HEADER_APP_KEY
     * 2. HEADER_APP_AUTH
     */
    public static final String HEADER_APP_KEY = "app_key";
    public static final String HEADER_APP_AUTH = "Authorization";
    
    /*
     * Application Access Security:
     * 1. APP_AUTH
     * 2. REFRESH_TOKEN
     */
    public static final String APP_AUTH = BASE_URL + "/iocm/app/sec/v1.1.0/login";
    public static final String REFRESH_TOKEN = BASE_URL + "/iocm/app/sec/v1.1.0/refreshToken";
    

    /*
     * Device Management:
     * 1. REGISTER_DIRECT_CONNECTED_DEVICE
     * 2. MODIFY_DEVICE_INFO
     * 3. QUERY_DEVICE_ACTIVATION_STATUS
     * 4. DELETE_DIRECT_CONNECTED_DEVICE
     * 5. DISCOVER_INDIRECT_CONNECTED_DEVICE
     * 6. REMOVE_INDIRECT_CONNECTED_DEVICE
     * 7. MODIFY_DEVICE_SHADOW
     * 8. QUERY_DEVICE_SHADOW
     * 9. QUERY_DEVICE_REALTIME_LOCATION
     */
    public static final String REGISTER_DIRECT_CONNECTED_DEVICE = BASE_URL + "/iocm/app/reg/v1.1.0/deviceCredentials";
    public static final String MODIFY_DEVICE_INFO = BASE_URL + "/iocm/app/dm/v1.4.0/devices";
    public static final String QUERY_DEVICE_ACTIVATION_STATUS = BASE_URL + "/iocm/app/reg/v1.1.0/deviceCredentials";
    public static final String DELETE_DIRECT_CONNECTED_DEVICE = BASE_URL + "/iocm/app/dm/v1.4.0/devices";
    public static final String DISCOVER_INDIRECT_CONNECTED_DEVICE = BASE_URL + "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
    public static final String REMOVE_INDIRECT_CONNECTED_DEVICE = BASE_URL + "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
    public static final String MODIFY_DEVICE_SHADOW = BASE_URL + "/iocm/app/shadow/v1.5.0/devices";
    public static final String QUERY_DEVICE_SHADOW = BASE_URL + "/iocm/app/shadow/v1.5.0/devices";
    public static final String QUERY_DEVICE_REALTIME_LOCATION = BASE_URL + "/iocm/app/location/v1.1.0/queryDeviceRealtimeLocation";
    
    /*
     * Batch Task:
     * 1. CREATE_BATCH_TASK
     * 2. QUERY_SPECIFY_TASK
     * 3. QUERY_TASK_DETAILS
     */
    public static final String CREATE_BATCH_TASK = BASE_URL + "/iocm/app/batchtask/v1.1.0/tasks";
    public static final String QUERY_SPECIFY_BATCH_TASK = BASE_URL + "/iocm/app/batchtask/v1.1.0/tasks";
    public static final String QUERY_BATCH_TASK_DETAILS = BASE_URL + "/iocm/app/batchtask/v1.1.0/taskDetails";

    /*
     * Data Collection:
     * 1. QUERY_SPECIFY_DEVICE
     * 2. QUERY_DEVICES
     * 3. QUERY_DEVICE_HISTORY_DATA
     * 4. QUERY_DEVICE_CAPABILITIES
     */
    public static final String QUERY_SPECIFY_DEVICE = BASE_URL + "/iocm/app/dm/v1.4.0/devices";
    public static final String QUERY_BATCH_DEVICES = BASE_URL + "/iocm/app/dm/v1.4.0/devices";
    public static final String QUERY_DEVICE_HISTORY_DATA = BASE_URL + "/iocm/app/data/v1.2.0/deviceDataHistory";
    public static final String QUERY_DEVICE_CAPABILITIES = BASE_URL + "/iocm/app/data/v1.1.0/deviceCapabilities";
    public static final String SUBSCRIBE_NOTIFYCATION = BASE_URL + "/iocm/app/sub/v1.1.0/subscribe";
    /*
     * Subscription Management:
     * 1. SUBSCRIBE_SERVICE_NOTIFYCATION
     * 2. SUBSCRIBE_MANAGEMENT_NOTIFYCATION
     * 3. QUERY_SPECIFY_SUBSCRIPTION
     * 4. QUERY_BATCH_SUBSCRIPTIONS
     * 5. DELETE_SPECIFY_SUBSCRIPTION
     * 6. DELETE_BATCH_SUBSCRIPTIONS
     */
    public static final String SUBSCRIBE_SERVICE_NOTIFYCATION = BASE_URL + "/iocm/app/sub/v1.2.0/subscriptions";
    public static final String SUBSCRIBE_MANAGEMENT_NOTIFYCATION = BASE_URL + "/iodm/app/sub/v1.1.0/subscribe";
    public static final String QUERY_SPECIFY_SUBSCRIPTION = BASE_URL + "/iocm/app/sub/v1.2.0/subscriptions";
    public static final String QUERY_BATCH_SUBSCRIPTIONS = BASE_URL + "/iocm/app/sub/v1.2.0/subscriptions";
    public static final String DELETE_SPECIFY_SUBSCRIPTION = BASE_URL + "/iocm/app/sub/v1.2.0/subscriptions";
    public static final String DELETE_BATCH_SUBSCRIPTIONS = BASE_URL + "/iocm/app/sub/v1.2.0/subscriptions";
    
    /*
     * Command Delivery:
     * 1. CREATE_DEVICE_CMD
     * 2. QUERY_DEVICE_CMD
     * 3. MODIFY_DEVICE_CMD
     * 4. CREATE_DEVICECMD_CANCEL_TASK
     * 5. QUERY_DEVICECMD_CANCEL_TASK
     * 6. INVOKE_DEVICE_SERVICES
     */
    public static final String CREATE_DEVICE_CMD = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands";
    public static final String QUERY_DEVICE_CMD = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands";
    public static final String MODIFY_DEVICE_COMMAND = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands";
    public static final String CREATE_DEVICECMD_CANCEL_TASK = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
    public static final String QUERY_DEVICECMD_CANCEL_TASK = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
    public static final String INVOKE_DEVICE_SERVICES = BASE_URL + "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
    
    
    /*
     * Device Group Management:
     * 1. CREATE_DEVICE_GROUP
     * 2. MODIFY_DEVICE_GROUP
     * 3. DELETE_DEVICE_GROUP
     * 4. QUERY_SPECIFY_DEVICE_GROUP
     * 5. QUERY_DEVICE_GROUPS
     * 6. QUERY_DEVICE_GROUP_MEMBERS
     * 7. ADD_DEVICE_GROUP_MEMBER
     * 8. DELETE_DEVICE_GROUP_MEMBER
     */
    public static final String CREATE_DEVICE_GROUP = BASE_URL + "/iocm/app/devgroup/v1.3.0/devGroups";
    public static final String MODIFY_DEVICE_GROUP = BASE_URL + "/iocm/app/devgroup/v1.3.0/devGroups";
    public static final String DELETE_DEVICE_GROUP = BASE_URL + "/iocm/app/devgroup/v1.3.0/devGroups";
    public static final String QUERY_SPECIFY_DEVICE_GROUP = BASE_URL + "/iocm/app/devgroup/v1.3.0/devGroups";
    public static final String QUERY_DEVICE_GROUPS = BASE_URL + "/iocm/app/devgroup/v1.3.0/devGroups";
    public static final String QUERY_DEVICE_GROUP_MEMBERS = BASE_URL + "/iocm/app/dm/v1.2.0/devices/ids";
    public static final String ADD_DEVICE_GROUP_MEMBER = BASE_URL + "/iocm/app/dm/v1.1.0/devices/addDevGroupTagToDevices";
    public static final String DELETE_DEVICE_GROUP_MEMBER = BASE_URL + "/iocm/app/dm/v1.1.0/devices/deleteDevGroupTagFromDevices";
    
    
    /*
     * Device Upgrade:
     * 1. CREATE_SW_UPGRADE_TASK
     * 2. CREATE_FW_UPGRADE_TASK
     * 3. QUERY_SPECIFY_PACKAGE
     * 4. QUERY_BATCH_PACKAGES
     * 5. DELETE_SPECIFY_PACKAGE
     * 6. QUERY_SPECIFY_UPGRADE_TASK_RESULT
     * 7. QUERY_UPGRADE_TASKS
     */
    public static final String CREATE_SW_UPGRADE_TASK = BASE_URL + "/iodm/northbound/v1.5.0/operations/softwareUpgrade";
    public static final String CREATE_FW_UPGRADE_TASK = BASE_URL + "/iodm/northbound/v1.5.0/operations/firmwareUpgrade";
    public static final String QUERY_SPECIFY_PACKAGE = BASE_URL + "/iodm/northbound/v1.5.0/category";
    public static final String QUERY_BATCH_PACKAGES = BASE_URL + "/iodm/northbound/v1.5.0/category";
    public static final String DELETE_SPECIFY_PACKAGE = BASE_URL + "/iodm/northbound/v1.5.0/category";
    public static final String QUERY_SPECIFY_UPGRADE_TASK_RESULT = BASE_URL + "/iodm/northbound/v1.5.0/operations";
    public static final String QUERY_UPGRADE_TASKS = BASE_URL + "/iodm/northbound/v1.5.0/operations";

    
    /*
     * service Notify Type
     * serviceInfoChanged|deviceInfoChanged|LocationChanged|deviceDataChanged|deviceDatasChanged
     * deviceAdded|deviceDeleted|messageConfirm|commandRsp|deviceEvent|ruleEvent
     */
    public static final String DEVICE_ADDED = "deviceAdded";
    public static final String DEVICE_INFO_CHANGED = "deviceInfoChanged";
    public static final String DEVICE_DATA_CHANGED = "deviceDataChanged";
    public static final String DEVICE_DELETED = "deviceDeleted";
    public static final String MESSAGE_CONFIRM = "messageConfirm";
    public static final String SERVICE_INFO_CHANGED = "serviceInfoChanged";
    public static final String COMMAND_RSP = "commandRsp";
    public static final String DEVICE_EVENT = "deviceEvent";
    public static final String RULE_EVENT = "ruleEvent";
    public static final String DEVICE_DATAS_CHANGED = "deviceDatasChanged";
    public static final String DEVICE_SHADOW_MODIFIED = "deviceDesiredPropertiesModifyStatusChanged";
    
    /*
     * management Notify Type
     * swUpgradeStateChangeNotify|swUpgradeResultNotify|fwUpgradeStateChangeNotify|fwUpgradeResultNotify
     */
    public static final String SW_UPGRADE_STATE_CHANGED = "swUpgradeStateChangeNotify";
    public static final String SW_UPGRADE_RESULT = "swUpgradeResultNotify";
    public static final String FW_UPGRADE_STATE_CHANGED = "fwUpgradeStateChangeNotify";
    public static final String FW_UPGRADE_RESULT = "fwUpgradeResultNotify";

}
