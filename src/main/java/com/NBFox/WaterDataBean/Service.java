package com.NBFox.WaterDataBean;

/**
 * Auto-generated: 2019-03-26 19:19:44
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Service {

    private String serviceId;
    private String serviceType;
    private Data data;
    private String eventTime;
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public String getServiceType() {
        return serviceType;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
    public String getEventTime() {
        return eventTime;
    }

}
