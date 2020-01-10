package com.yukoon.market.controllers;

import com.yukoon.market.entities.Market;
import com.yukoon.market.entities.Tenant;
import com.yukoon.market.services.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MarketController {
    @Autowired
    private MarketService marketService;

    //获取Market对象
    @ModelAttribute
    public void getTenant(@RequestParam(value = "id",required = false)Integer id, Map<String,Object> map) {
        //若为修改
        if (id !=null) {
            Market market = marketService.findById(id);
            map.put("market",market);
        }
    }

    @GetMapping("/markets")
    public String allMarkets(Map<String,Object> map) {
        map.put("markets",marketService.findAllTenantByMarket());
        return "backend/dashboard.html";
    }

    @GetMapping("/market")
    public String toAdd() {
        return "backend/market_input.html";
    }

    @PostMapping("/market")
    public String add(Market market) {
        market = marketService.save(market);
        return "redirect:/markets";
    }

    @GetMapping("/market/{id}")
    public String toEdit(@PathVariable("id")Integer id,Map<String,Object> map) {
        map.put("market",marketService.findById(id));
        return "backend/market_input.html";
    }

    @PutMapping("/market")
    public String edit(Market market) {
        market = marketService.save(market);
        return "redirect:/markets";
    }
}
