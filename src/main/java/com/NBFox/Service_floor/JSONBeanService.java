package com.NBFox.Service_floor;

import com.NBFox.Bean_out.BeanOfOutput;
import com.NBFox.Bean_out.BeanWaterData;
import com.NBFox.Mapper.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JSONBeanService {
    @Autowired
    BeanMapper beanMapper;
    public int addBeanOfOutput_Updata(BeanOfOutput beanOfOutput)
    {
        return beanMapper.addBeanOfOutput_Updata(beanOfOutput);
    }

    public int addBeanOfOutput_StreetLight(BeanOfOutput beanOfOutput)
    {
        return beanMapper.addBeanOfOutput_StreetLight(beanOfOutput);
    }

    public int addBeanOfOutput_GuangDian(BeanOfOutput beanOfOutput)
    {
        return beanMapper.addBeanOfOutput_GuangDian(beanOfOutput);
    }

    public List<BeanOfOutput> getRanQiUpdataDataInFive(String code)
    {
        return beanMapper.getRanQiUpdataDataInFive(code);
    }
    public List<BeanOfOutput> getRanQiGuangDianDataInFive(String code)
    {
        return beanMapper.getRanQiGuangDianDataInFive(code);
    }
    public List<BeanOfOutput> getRanQiStreetLightDataInFive(String code)
    {
        return beanMapper.getRanQiStreetLightDataInFive(code);
    }

    public String deviceIdSearchCode(String deviceId)
    {
        return beanMapper.deviceIdSearchCode(deviceId);
    }

    public List<BeanWaterData> getWaterDataInFive(String code)
    {
        return beanMapper.getWaterDataInFive(code);
    }

    public void addBeanOfOutput_WaterData(BeanWaterData beanWaterData)
    {
        beanMapper.addBeanOfOutput_WaterData(beanWaterData);
    }

}
