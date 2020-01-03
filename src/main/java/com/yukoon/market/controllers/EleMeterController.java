package com.yukoon.market.controllers;

import com.yukoon.market.entities.EleMeter;
import com.yukoon.market.services.EleMeterService;
import com.yukoon.market.services.MarketService;
import com.yukoon.market.utils.DateFomatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class EleMeterController {
    @Autowired
    private EleMeterService eleMeterService;
    @Autowired
    private MarketService marketService;

    //查询某一市场下所有电表
    @GetMapping("/elemeters/{marketId}")
    public String findAll(Map<String,Object>map,@PathVariable("marketId") Integer marketId) {
        map.put("meters",eleMeterService.findAllByMarketId(marketId));
        map.put("market",marketService.findById(marketId));
        return "backend/eleMeters.html";
    }

    @GetMapping("/elemeter/{marketId}")
    public String toAdd(Map<String,Object>map,@PathVariable("marketId") Integer marketId) {
        map.put("market_id",marketId);
        return "backend/eleMeter_input.html";
    }

    @PostMapping("/elemeter")
    public String add(EleMeter eleMeter) {
        eleMeter.setUpdate_date(DateFomatter.getDate());
        eleMeter = eleMeterService.save(eleMeter);
        return "redirect:/elemeters/" + eleMeter.getMarket().getId();
    }
}
