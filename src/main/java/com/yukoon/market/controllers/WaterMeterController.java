package com.yukoon.market.controllers;

import com.yukoon.market.entities.WaterMeter;
import com.yukoon.market.services.*;
import com.yukoon.market.utils.DateFomatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class WaterMeterController {
    @Autowired
    private WaterMeterService waterMeterService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private WaterBillService waterBillService;

    private final static String H2_TEXT = "水表管理";
    private final static String ADD_BTN_TEXT = "添加水表";
    private final static String TOADD_URL = "/watermeter/";
    private final static String ADD_URL = "/watermeter";
    private final static String H5_TEXT = "水表列表";
    private final static String BILLS_URL = "/waterbills_m_unpaid/";
    private final static String RUN_URL = "/runwatermeter/";
    private final static String STOP_URL = "/stopwatermeter/";
    private final static String MODELATTR = "waterMeter";
    private final static String CHANGE_TENANT_URL = "/waterowner";
    private final static String DEGREE_UPLOAD_URL = "/uploaddegree_water";
    private final static String BACK_URL =  "/watermeters/";

    //获取WaterMeter对象
    @ModelAttribute
    public void getMeter(@RequestParam(value = "id",required = false)Integer id, Map<String,Object> map) {
        //若为修改
        if (id !=null) {
            WaterMeter waterMeter = waterMeterService.findById(id);
            map.put("waterMeter",waterMeter);
        }
    }

    //查询某一市场下所有水表
    @GetMapping("/watermeters/{marketId}")
    public String findAll(Map<String,Object>map,@PathVariable("marketId") Integer marketId) {
        map = metersElementPackage(map);
        map.put("meters",waterMeterService.findAllByMarketId(marketId));
        map.put("market",marketService.findById(marketId));
        map.put("tenants",tenantService.findAllByMarketId(marketId));
        return "backend/meters.html";
    }

    @GetMapping("/watermeter/{marketId}")
    public String toAdd(Map<String,Object>map,@PathVariable("marketId") Integer marketId) {
        map = inputElementPackage(map);
        map.put("market_id",marketId);
        return "backend/meter_input.html";
    }

    @PostMapping("/watermeter")
    public String add(WaterMeter waterMeter) {
        waterMeter.setUpdate_date(DateFomatter.getDate()).setStatus(1);
        waterMeter = waterMeterService.save(waterMeter);
        return "redirect:/watermeters/" + waterMeter.getMarket().getId();
    }

    //更改水表归属
    @ResponseBody
    @PutMapping("/waterowner")
    public WaterMeter changeOwner(WaterMeter waterMeter,Integer tenantId,float degree_now) {
        //检查抄表数是否与记录相同或新表
        if (degree_now == waterMeter.getDegree() || waterMeter.getDegree() == 0) {
            if(waterBillService.findUnpaidByMeterId(waterMeter.getId()).size() == 0) {
                waterMeter = waterMeterService.save(waterMeter.setTenant(tenantService.findById(tenantId)));
                waterMeter.setUpdate_date(DateFomatter.getDate()).setChange_status(1);
            }else {
                waterMeter.setUpdate_date(DateFomatter.getDate()).setChange_status(2);
            }
        }
        //检查表数是否小于已记录表数
        else if (degree_now < waterMeter.getDegree()){
            waterMeter.setChange_status(0);
        }
        //表数检查
        else{
            waterMeter.setUpdate_date(DateFomatter.getDate()).setChange_status(2);
            waterBillService.caculate(degree_now,waterMeter.getId());
        }
        //change_status=0表数异常 1成功更改客户 2生成新的欠款
        return waterMeter;
    }

    //停用水表
    @GetMapping("/stopwatermeter/{id}")
    public String stop(@PathVariable("id")Integer id) {
        WaterMeter waterMeter = waterMeterService.save(waterMeterService.findById(id).setStatus(0));
        return "redirect:/watermeters/" + waterMeter.getMarket().getId();
    }

    //启用水表
    @GetMapping("/runwatermeter/{id}")
    public String run(@PathVariable("id")Integer id) {
        WaterMeter waterMeter = waterMeterService.save(waterMeterService.findById(id).setStatus(1));
        return "redirect:/watermeters/" + waterMeter.getMarket().getId();
    }

    //抄表读数计价
    @ResponseBody
    @PutMapping("/uploaddegree_water")
    public WaterMeter uploadDegree(WaterMeter waterMeter,Float degree_now) {
        if (degree_now <= waterMeter.getDegree()) {
            waterMeter.setChange_status(0);
        }else {
            waterMeter.setUpdate_date(DateFomatter.getDate()).setChange_status(2);
            waterBillService.caculate(degree_now,waterMeter.getId());
        }
        //change_status=0表数异常 1成功更改客户 2生成新的欠款
        return waterMeter;
    }

    private Map<String,Object> metersElementPackage(Map<String,Object> map) {
        map.put("h2_text",H2_TEXT);map.put("toadd_url",TOADD_URL);map.put("h5_text",H5_TEXT);
        map.put("back_url",BACK_URL);map.put("run_url",RUN_URL);map.put("stop_url",STOP_URL);map.put("model_attr",MODELATTR);
        map.put("change_tenant_url",CHANGE_TENANT_URL);map.put("degree_upload_url",DEGREE_UPLOAD_URL);
        map.put("add_btn_text",ADD_BTN_TEXT);
        return map;
    }

    private Map<String,Object> inputElementPackage(Map<String,Object> map) {
        map.put("h5_text",H5_TEXT);map.put("add_url",ADD_URL);map.put("model_attr",MODELATTR);
        map.put("bills_url",BILLS_URL);
        return map;
    }

}
