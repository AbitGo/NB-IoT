package com.NBFox.Mapper;

import com.NBFox.Bean_out.BeanOfOutput;
import com.NBFox.Bean_out.BeanWaterData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BeanMapper {
    //公用api
    String deviceIdSearchCode(String deviceId);
    //燃气api
    int addBeanOfOutput_Updata(BeanOfOutput beanOfOutput);
    int addBeanOfOutput_StreetLight(BeanOfOutput beanOfOutput);
    int addBeanOfOutput_GuangDian(BeanOfOutput beanOfOutput);
    List<BeanOfOutput> getRanQiUpdataDataInFive(String code);
    List<BeanOfOutput> getRanQiGuangDianDataInFive(String code);
    List<BeanOfOutput> getRanQiStreetLightDataInFive(String code);

    //水质api
    List<BeanWaterData> getWaterDataInFive(String code);
    void addBeanOfOutput_WaterData(BeanWaterData beanWaterData);


}
