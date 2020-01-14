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
        return "backend/meters.html";
    }

    @GetMapping("/elemeter/{marketId}")
    public String toAdd(Map<String,Object>map,@PathVariable("marketId") Integer marketId) {
        map.put("market_id",marketId);
        return "backend/meter_input.html";
    }

    @PostMapping("/elemeter")
    public String add(EleMeter eleMeter) {
        eleMeter.setUpdate_date(DateFomatter.getDate()).setStatus(1);
        eleMeter = eleMeterService.save(eleMeter);
        return "redirect:/elemeters/" + eleMeter.getMarket().getId();
    }

    //更改电表归属
    @ResponseBody
    @PutMapping("/eleowner")
    public EleMeter changeOwner(EleMeter eleMeter,Integer tenantId,float degree_now) {
        //检查抄表数是否与记录相同或新表
        if (degree_now == eleMeter.getDegree() || eleMeter.getDegree() == 0) {
            if(eleBillService.findUnpaidByMeterId(eleMeter.getId()).size() == 0) {
                eleMeter = eleMeterService.save(eleMeter.setTenant(tenantService.findById(tenantId)));
                eleMeter.setUpdate_date(DateFomatter.getDate()).setChange_status(1);
            }else {
                eleMeter.setUpdate_date(DateFomatter.getDate()).setChange_status(2);
            }
        }
        //检查表数是否小于已记录表数
        else if (degree_now < eleMeter.getDegree()){
            eleMeter.setChange_status(0);
        }
        //表数检查
        else{
            eleMeter.setUpdate_date(DateFomatter.getDate()).setChange_status(2);
            eleBillService.caculate(degree_now,eleMeter.getId());
        }
        //change_status=0表数异常 1成功更改客户 2生成新的欠款
        return eleMeter;
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
        if (degree_now <= eleMeter.getDegree()) {
            eleMeter.setChange_status(0);
        }else {
            eleMeter.setUpdate_date(DateFomatter.getDate()).setChange_status(2);
            eleBillService.caculate(degree_now,eleMeter.getId());
        }
        //change_status=0表数异常 1成功更改客户 2生成新的欠款
        return eleMeter;
    }
}
