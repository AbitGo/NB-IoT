package com.NBFox.Bean_out;
import java.sql.Date;

public class BeanWaterData {
    private Integer id;
    private String serviceType;
    private String serviceId;
    private Date time;
    private String code;
    private String dianDao;
    private String rongJie;
    private String ph;
    private String temp;

    public String getCode() {
        return code;
    }

    public Date getTime() {
        return time;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Integer getId() {
        return id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getDianDao() {
        return dianDao;
    }

    public String getPh() {
        return ph;
    }

    public String getRongJie() {
        return rongJie;
    }

    public String getTemp() {
        return temp;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setDianDao(String dianDao) {
        this.dianDao = dianDao;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public void setRongJie(String rongJie) {
        this.rongJie = rongJie;
    }
}
