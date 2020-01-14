package com.yukoon.market.controllers;

import com.yukoon.market.services.EleBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class EleBillsController {

    @Autowired
    private EleBillService eleBillService;

    //查询某一电表下所有电费账单
    @GetMapping("/elebills_m/{meterId}")
    public String findAllByMeterId(@PathVariable("meterId")Integer meterId, Map<String,Object> map) {
        map.put("bills",eleBillService.findAllByMeterId(meterId));
        return "backend/bills.html";
    }
}
