package com.yukoon.market.controllers;

import com.yukoon.market.services.EleBillService;
import com.yukoon.market.services.EleMeterService;
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
    @Autowired
    private EleMeterService eleMeterService;



    /*
    查询某一电表下所有电费账单
    query_status: 0全部 1未支付 2已支付
     */
    @GetMapping("/elebills_m_all/{meterId}")
    public String findAllByMeterId(@PathVariable("meterId")Integer meterId, Map<String,Object> map) {
        int query_status = 0;
        map.put("query_status",query_status);
        map.put("bills",eleBillService.findAllByMeterId(meterId));
        map.put("meter",eleMeterService.findById(meterId));
        return "backend/bills.html";
    }

    //查询某一电表下所有未付电费账单
    @GetMapping("/elebills_m_unpaid/{meterId}")
    public String findUnpaidByMeterId(@PathVariable("meterId")Integer meterId, Map<String,Object> map) {
        int query_status = 1;
        map.put("query_status",query_status);
        map.put("bills",eleBillService.findUnpaidByMeterId(meterId));
        map.put("meter",eleMeterService.findById(meterId));
        return "backend/bills.html";
    }

    //查询某一电表下所有已付电费账单
    @GetMapping("/elebills_m_paid/{meterId}")
    public String findPaidByMeterId(@PathVariable("meterId")Integer meterId, Map<String,Object> map) {
        int query_status = 2;
        map.put("query_status",query_status);
        map.put("bills",eleBillService.findpaidByMeterId(meterId));
        map.put("meter",eleMeterService.findById(meterId));
        return "backend/bills.html";
    }

    //查询某一账单详情
    @GetMapping("/elebill/{billId}")
    public String findDetailsByBillId(@PathVariable("billId")Integer billId,Map<String,Object> map ) {
        map.put("bill",eleBillService.findById(billId));
        return "backend/bill_details.html";
    }
}
