package com.NBFox.Controller;
import com.NBFox.Bean_Root.JsonRootBean;
import com.NBFox.Bean_out.BeanOfOutput;
import com.NBFox.Bean_out.BeanWaterData;
import com.NBFox.Service_floor.JSONBeanService;
import com.NBFox.WaterDataBean.Data;
import com.NBFox.WaterDataBean.JsonRootBean_water;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.commandDelivery.CreateDeviceCommand;
import com.utils.Constant;
import net.sf.json.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class Bean_out_Controller {
    @Autowired
    JSONBeanService jsonBeanService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/addData")
    public BeanOfOutput addData_Updata(String JSON_String)throws Exception
    {
        //String JSON_String = "{\"notifyType\":\"deviceDataChanged\",\"deviceId\":\"6fadac96-926e-40ee-9ba1-87054c6d8746\",\"gatewayId\":\"6fadac96-926e-40ee-9ba1-87054c6d8746\",\"requestId\":null,\"service\":{\"serviceId\":\"Updata\",\"serviceType\":\"Updata\",\"data\":{\"gprs\":\"1234512345\"},\"eventTime\":\"20190326T110900Z\"}}";
        BeanOfOutput beanOfOutput = new BeanOfOutput();
        JsonRootBean jsonRootBean = objectMapper.readValue(JSON_String,JsonRootBean.class);
        //System.out.println(jsonRootBean.getService().getEventTime());
        String ServiceId = jsonRootBean.getService().getServiceId();
        String ServiceType = jsonRootBean.getService().getServiceType();
        String Data = jsonRootBean.getService().getData().getGprs();
        String deviceId = jsonRootBean.getDeviceId();
        String code = jsonBeanService.deviceIdSearchCode(deviceId);;
        //只需要提取serviceID，serviceType，data,code
        beanOfOutput.setServiceId(ServiceId);
        beanOfOutput.setServiceType(ServiceType);
        beanOfOutput.setCode(code);
        System.out.println(Data);
        beanOfOutput.setData(Data);
        //updata_table
        if(ServiceId.equals("Updata"))
            jsonBeanService.addBeanOfOutput_Updata(beanOfOutput);

        else if(ServiceId.equals("GuangDian"))
            jsonBeanService.addBeanOfOutput_GuangDian(beanOfOutput);
        else
            jsonBeanService.addBeanOfOutput_StreetLight(beanOfOutput);
        return beanOfOutput;
    }
    @GetMapping("/addWaterData")
    public BeanWaterData addData_Water(String JSON_String)throws Exception
    {
        //String JSON_String= "{\"notifyType\":\"deviceDataChanged\",\"deviceId\":\"2f7f1236-8701-4074-8ee7-18a0e8e3ef5f\",\"gatewayId\":\"2f7f1236-8701-4074-8ee7-18a0e8e3ef5f\",\"requestId\":null,\"service\":{\"serviceId\":\"WaterData\",\"serviceType\":\"WaterData\",\"data\":{\"dianDao\":\"12.13\",\"rongJie\":\"22.23\",\"ph\":\"32.33\",\"temp\":\"42.43\"},\"eventTime\":\"20190404T080622Z\"}}";
        JsonRootBean_water jsonRootBean_water = objectMapper.readValue(JSON_String,JsonRootBean_water.class);

        //我们需要提取ServiceId，ServiceType，code，dianDao,rongJie,ph,temp
        String ServiceId = jsonRootBean_water.getService().getServiceId();
        String ServiceType = jsonRootBean_water.getService().getServiceType();
        String deviceId = jsonRootBean_water.getDeviceId();
        String code = jsonBeanService.deviceIdSearchCode(deviceId);;
        Data waterData = jsonRootBean_water.getService().getData();
        String dianDao = waterData.getDianDao();
        String rongJie = waterData.getRongJie();
        String ph = waterData.getPh();
        String temp = waterData.getTemp();

        //只需要提取ServiceId，ServiceType，code，dianDao,rongJie,ph,temp
        BeanWaterData beanWaterData = new BeanWaterData();
        beanWaterData.setServiceId(ServiceId);
        beanWaterData.setServiceType(ServiceType);
        beanWaterData.setCode(code);
        beanWaterData.setDianDao(dianDao);
        beanWaterData.setRongJie(rongJie);
        beanWaterData.setPh(ph);
        beanWaterData.setTemp(temp);
        jsonBeanService.addBeanOfOutput_WaterData(beanWaterData);
        return beanWaterData;
    }

    @RequestMapping(value = Constant.DEVICE_DATA_CHANGED_CALLBACK,method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<HttpStatus> recvDeviceDataChangedNotify(
            @RequestBody String deviceDataChanged_NotifyMessage
    ) throws Exception {
        System.out.println(deviceDataChanged_NotifyMessage);
        if(deviceDataChanged_NotifyMessage.contains("WaterData"))
            addData_Water(deviceDataChanged_NotifyMessage);
        else
            addData_Updata(deviceDataChanged_NotifyMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping("/FunctOfSendMessage")
    @ResponseBody
    public String funct1(String num,String Onoff) throws Exception {
        String s1 = num;
        String s2 = Onoff;
//        CreateDeviceCommand createDeviceCommand =new CreateDeviceCommand();
//        createDeviceCommand.cr(num,Onoff);
        return s1 + s2;
    }

    @GetMapping("/GetDataInFive/RanQi")
    public List<BeanOfOutput> GetDataInFive(String code,String type)
    {
        //String code = "jiangsuxinxi20181554253107361";
        List<BeanOfOutput> beanOfOutputs;
        if(type.equals("GuangDian"))
        {
            beanOfOutputs = jsonBeanService.getRanQiGuangDianDataInFive(code);
        }else if(type.equals("StreetLight")) {
            beanOfOutputs = jsonBeanService.getRanQiStreetLightDataInFive(code);
        }else{
            beanOfOutputs = jsonBeanService.getRanQiUpdataDataInFive(code);
        }
        return beanOfOutputs;
    }

    @GetMapping("/GetDataInFive/water")
    public List<BeanWaterData> GetWaterDataInFive(String code)
    {
        List<BeanWaterData> beanWaterData;
        beanWaterData = jsonBeanService.getWaterDataInFive(code);
        return beanWaterData;
    }

    @GetMapping("/GetDeviceCode")
    public String GetDeviceCode()
    {
        return jsonBeanService.deviceIdSearchCode("3ed1392d-a6ed-470b-897b-c4769f84f5be");
    }

}
