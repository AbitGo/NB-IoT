package com.testMessagePush;

import java.util.ArrayList;
import java.util.List;

public class NotifyType {

    //将你的电信平台url填写至此，并使用socket进行测试通讯
    public static final String TEST_CALLBACK_BASE_URL = "http://***.***.***.***:****";

    public static List<String> notifyTypes = new ArrayList<>();
    public static List<String> getServiceNotifyTypes () {
        //count==11
        notifyTypes.add(SERVICE_INFO_CHANGED);
        notifyTypes.add(DEVICE_INFO_CHANGED);
        notifyTypes.add(DEVICE_DATA_CHANGED);
        notifyTypes.add(DEVICE_ADDED);
        notifyTypes.add(DEVICE_DELETED);
        notifyTypes.add(MESSAGE_CONFIRM);
        notifyTypes.add(COMMAND_RSP);
        notifyTypes.add(DEVICE_EVENT);
        notifyTypes.add(RULE_EVENT);
        notifyTypes.add(DEVICE_DATAS_CHANGED);
        notifyTypes.add(DEVICE_DESIRED_MODIFY);
        return notifyTypes;
    }
    public static List<String> getManagementNotifyTypes () {
        notifyTypes.add(SW_UPGRADE_STATE_CHANGED);
        notifyTypes.add(SW_UPGRADE_RESULT);
        notifyTypes.add(FW_UPGRADE_STATE_CHANGED);
        notifyTypes.add(FW_UPGRADE_RESULT);
        return notifyTypes;
    }
    /*
     * service Notify Type
     * serviceInfoChanged|deviceInfoChanged|LocationChanged|deviceDataChanged|deviceDatasChanged
     * deviceAdded|deviceDeleted|messageConfirm|commandRsp|deviceEvent|ruleEvent|deviceModelAdded
     * deviceModelDeleted|deviceDesiredPropertiesModifyStatusChanged
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
    public static final String DEVICE_DESIRED_MODIFY = "deviceDesiredPropertiesModifyStatusChanged";
    
    /*
     * management Notify Type
     * swUpgradeStateChangeNotify|swUpgradeResultNotify|fwUpgradeStateChangeNotify|fwUpgradeResultNotify
     */
    public static final String SW_UPGRADE_STATE_CHANGED = "swUpgradeStateChangeNotify";
    public static final String SW_UPGRADE_RESULT = "swUpgradeResultNotify";
    public static final String FW_UPGRADE_STATE_CHANGED = "fwUpgradeStateChangeNotify";
    public static final String FW_UPGRADE_RESULT = "fwUpgradeResultNotify";
}
