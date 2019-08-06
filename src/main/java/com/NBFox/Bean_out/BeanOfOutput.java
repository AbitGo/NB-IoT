package com.NBFox.Bean_out;


import java.sql.Date;

public class BeanOfOutput {
    private Integer id;
    private String serviceType;
    private String serviceId;
    private String data;
    private Date time;
    private String code;

    public String getCode() {
        return code;
    }

    public Date getTime() {
        return time;
    }

    public String getData() {
        return data;
    }


    public String getServiceId() {
        return serviceId;
    }

    public Integer getId() {
        return id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setData(String data) {
        this.data = data;
    }
}
