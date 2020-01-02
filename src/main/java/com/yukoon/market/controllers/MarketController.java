package com.yukoon.market.controllers;

import com.yukoon.market.services.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MarketController {
    @Autowired
    private MarketService marketService;

    @GetMapping("/markets")
    public String allMarkets(Map<String,Object> map) {
        map.put("markets",marketService.findAllTenantByMarket());
        return "backend/dashboard.html";
    }
}
