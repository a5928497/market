package com.yukoon.market.controllers;

import com.yukoon.market.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @GetMapping("/tenants/{marketId}")
    public String tenants(@PathVariable("marketId") Integer marketId, Map<String,Object> map) {
        map.put("tenants",tenantService.findAllByMarketId(marketId));
        map.put("market_id",marketId);
        return "backend/tenants.html";
    }

    @GetMapping("/tenant/{marketId}")
    public String toAdd(Map<String,Object> map,@PathVariable("marketId") Integer marketId) {
        map.put("market_id",marketId);
        return "backend/tenant_input.html";
    }
}
