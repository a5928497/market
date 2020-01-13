package com.yukoon.market.controllers;

import com.yukoon.market.entities.EleMeter;
import com.yukoon.market.entities.Market;
import com.yukoon.market.services.EleBillService;
import com.yukoon.market.services.EleMeterService;
import com.yukoon.market.services.MarketService;
import com.yukoon.market.services.TenantService;
import com.yukoon.market.utils.DateFomatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class EleMeterController {
    @Autowired
    private EleMeterService eleMeterService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private EleBillService eleBillService;

    //获取Market对象
    @ModelAttribute
    public void getMeter(@RequestParam(value = "id",required = false)Integer id, Map<String,Object> map) {
        //若为修改
        if (id !=null) {
            EleMeter eleMeter = eleMeterService.findById(id);
            map.put("eleMeter",eleMeter);
        }
    }

    //查询某一市场下所有电表
    @GetMapping("/elemeters/{marketId}")
    public String findAll(Map<String,Object>map,@PathVariable("marketId") Integer marketId) {
        map.put("meters",eleMeterService.findAllByMarketId(marketId));
        map.put("market",marketService.findById(marketId));
        map.put("tenants",tenantService.findAllByMarketId(marketId));
        return "backend/eleMeters.html";
    }

    @GetMapping("/elemeter/{marketId}")
    public String toAdd(Map<String,Object>map,@PathVariable("marketId") Integer marketId) {
        map.put("market_id",marketId);
        return "backend/eleMeter_input.html";
    }

    @PostMapping("/elemeter")
    public String add(EleMeter eleMeter) {
        eleMeter.setUpdate_date(DateFomatter.getDate()).setStatus(1);
        eleMeter = eleMeterService.save(eleMeter);
        return "redirect:/elemeters/" + eleMeter.getMarket().getId();
    }

    //更改电表归属
    @PutMapping("/eleowner")
    public String changeOwner(EleMeter eleMeter,Integer tenantId) {
        eleMeter = eleMeterService.save(eleMeter.setTenant(tenantService.findById(tenantId)));
        return "redirect:/elemeters/" + eleMeter.getMarket().getId();
    }

    //停用电表
    @GetMapping("/stopelemeter/{id}")
    public String stop(@PathVariable("id")Integer id) {
        EleMeter eleMeter = eleMeterService.save(eleMeterService.findById(id).setStatus(0));
        return "redirect:/elemeters/" + eleMeter.getMarket().getId();
    }

    //启用电表
    @GetMapping("/runelemeter/{id}")
    public String run(@PathVariable("id")Integer id) {
        EleMeter eleMeter = eleMeterService.save(eleMeterService.findById(id).setStatus(1));
        return "redirect:/elemeters/" + eleMeter.getMarket().getId();
    }

    //抄表读数计价
    @ResponseBody
    @PutMapping("/uploaddegree")
    public EleMeter uploadDegree(EleMeter eleMeter,Float degree_now) {
        eleBillService.caculate(degree_now,eleMeter.getId());
        return eleMeterService.findById(eleMeter.getId());
    }
}
