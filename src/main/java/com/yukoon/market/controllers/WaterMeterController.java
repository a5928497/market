package com.yukoon.market.controllers;

import com.yukoon.market.entities.WaterMeter;
import com.yukoon.market.services.EleMeterService;
import com.yukoon.market.services.MarketService;
import com.yukoon.market.services.TenantService;
import com.yukoon.market.services.WaterMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class WaterMeterController {
    @Autowired
    private WaterMeterService waterMeterService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private TenantService tenantService;

    private final static String H2_TEXT = "水表管理";
    private final static String ADD_URL = "/elemeter/";
    private final static String H5_TEXT = "电表列表";
    private final static String BILLS_URL = "/elebills_m_unpaid/";
    private final static String RUN_URL = "/runelemeter/";
    private final static String STOP_URL = "/stopelemeter/";
    private final static String MODELATTR = "eleMeter";
    private final static String CHANGE_TENANT_URL = "/eleowner";
    private final static String DEGREE_UPLOAD_URL = "/uploaddegree";

    //获取Market对象
    @ModelAttribute
    public void getMeter(@RequestParam(value = "id",required = false)Integer id, Map<String,Object> map) {
        //若为修改
        if (id !=null) {
            WaterMeter waterMeter = waterMeterService.findById(id);
            map.put("waterMeter",waterMeter);
        }
    }

    //查询某一市场下所有电表
    @GetMapping("/watermeters/{marketId}")
    public String findAll(Map<String,Object>map,@PathVariable("marketId") Integer marketId) {
        map.put("h2_text",H2_TEXT);map.put("add_url",ADD_URL);map.put("h5_text",H5_TEXT);
        map.put("bills_url",BILLS_URL);map.put("run_url",RUN_URL);map.put("stop_url",STOP_URL);map.put("model_attr",MODELATTR);
        map.put("change_tenant_url",CHANGE_TENANT_URL);map.put("degree_upload_url",DEGREE_UPLOAD_URL);
        map.put("meters",waterMeterService.findAllByMarketId(marketId));
        map.put("market",marketService.findById(marketId));
        map.put("tenants",tenantService.findAllByMarketId(marketId));
        return "backend/meters.html";
    }

    @GetMapping("/watermeter/{marketId}")
    public String toAdd(Map<String,Object>map,@PathVariable("marketId") Integer marketId) {
        map.put("market_id",marketId);
        return "backend/meter_input.html";
    }
}
