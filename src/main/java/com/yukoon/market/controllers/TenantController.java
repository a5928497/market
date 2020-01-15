package com.yukoon.market.controllers;

import com.yukoon.market.entities.Market;
import com.yukoon.market.entities.Tenant;
import com.yukoon.market.services.MarketService;
import com.yukoon.market.services.TenantService;
import com.yukoon.market.utils.DateFomatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Controller
public class TenantController {
    private static final String ENTERTEXT = "进驻时间";
    private static final String EXITTEXT = "离场时间";
    @Autowired
    private TenantService tenantService;
    @Autowired
    private MarketService  marketService;

    //获取Tenant对象
    @ModelAttribute
    public void getTenant(@RequestParam(value = "id",required = false)Integer id, Map<String,Object> map) {
        //若为修改
        if (id !=null) {
            Tenant tenant = tenantService.findById(id);
            map.put("tenant",tenant);
        }
    }

    //查询指定市场下所有商户
    @GetMapping("/tenants/{marketId}")
    public String tenants(@PathVariable("marketId") Integer marketId, Map<String,Object> map) {
        map.put("tenants",tenantService.findAllByMarketId(marketId));
        map.put("market",marketService.findById(marketId));
        return "backend/tenants.html";
    }

    //前往添加商户
    @GetMapping("/tenant/{marketId}")
    public String toAdd(Map<String,Object> map,@PathVariable("marketId") Integer marketId) {
        map.put("market_id",marketId);
        map.put("markets",marketService.findAll());
        return "backend/tenant_input.html";
    }

    //添加商户
    @PostMapping("/tenant")
    public String add(Tenant tenant) {
        tenant.setStatus(1).setChange_date(ENTERTEXT + "1:" + DateFomatter.getDate());
        tenant = tenantService.save(tenant);
        return "redirect:/tenants/" + tenant.getMarket().getId();
    }

    //查看商户详情
    @GetMapping("/tenant_details/{tenantId}")
    public String details(Map<String,Object> map,@PathVariable("tenantId") Integer tenantId) {
        map.put("tenant",tenantService.findById(tenantId));
        return "backend/tenant_details.html";
    }

    //前往编辑商户
    @GetMapping("/tenantedit/{tenantId}")
    public String toEdit(Map<String,Object> map,@PathVariable("tenantId") Integer tenantId) {
        map.put("tenant",tenantService.findById(tenantId));
        map.put("markets",marketService.findAll());
        return "backend/tenant_input.html";
    }

    //编辑商户
    @PutMapping("/tenant")
    public String edit(Map<String,Object> map, Tenant tenant) {
        tenant = tenantService.save(tenant);
        return "redirect:/tenant_details/" + tenant.getId();
    }

    
}
