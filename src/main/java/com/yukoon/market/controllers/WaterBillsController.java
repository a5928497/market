package com.yukoon.market.controllers;

import com.yukoon.market.services.EleBillService;
import com.yukoon.market.services.EleMeterService;
import com.yukoon.market.services.WaterBillService;
import com.yukoon.market.services.WaterMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class WaterBillsController {

    @Autowired
    private WaterBillService waterBillService;
    @Autowired
    private WaterMeterService waterMeterService;

    private final static String ALL_BILLS_URL = "/waterbills_m_all/";
    private final static String UNPAID_BILLS_URL = "/waterbills_m_unpaid/";
    private final static String PAID_BILLS_URL = "/waterbills_m_paid/";
    private final static String BACK_URL =  "/watermeters/";
    private final static String DETAILS_URL ="/waterbill/";

    private Map<String,Object> billsElementPackage(Map<String,Object> map) {
        map.put("all_bills_url",ALL_BILLS_URL);
        map.put("unpaid_bills_url",UNPAID_BILLS_URL);
        map.put("paid_bills_url",PAID_BILLS_URL);
        map.put("back_url",BACK_URL);
        map.put("details_url",DETAILS_URL);
        return map;
    }

    /*
    查询某一水表下所有水费账单
    query_status: 0全部 1未支付 2已支付
     */
    @GetMapping("/waterbills_m_all/{meterId}")
    public String findAllByMeterId(@PathVariable("meterId")Integer meterId, Map<String,Object> map) {
        int query_status = 0;
        map = billsElementPackage(map);
        map.put("query_status",query_status);
        map.put("bills",waterBillService.findAllByMeterId(meterId));
        map.put("meter",waterMeterService.findById(meterId));
        return "backend/bills.html";
    }

    //查询某一水表下所有未付水费账单
    @GetMapping("/waterbills_m_unpaid/{meterId}")
    public String findUnpaidByMeterId(@PathVariable("meterId")Integer meterId, Map<String,Object> map) {
        int query_status = 1;
        map = billsElementPackage(map);
        map.put("query_status",query_status);
        map.put("bills",waterBillService.findUnpaidByMeterId(meterId));
        map.put("meter",waterMeterService.findById(meterId));
        return "backend/bills.html";
    }

    //查询某一水表下所有已付水费账单
    @GetMapping("/waterbills_m_paid/{meterId}")
    public String findPaidByMeterId(@PathVariable("meterId")Integer meterId, Map<String,Object> map) {
        int query_status = 2;
        map = billsElementPackage(map);
        map.put("query_status",query_status);
        map.put("bills",waterBillService.findpaidByMeterId(meterId));
        map.put("meter",waterMeterService.findById(meterId));
        return "backend/bills.html";
    }

    //查询某一账单详情
    @GetMapping("/waterbill/{billId}")
    public String findDetailsByBillId(@PathVariable("billId")Integer billId,Map<String,Object> map ) {
        map.put("bill",waterBillService.findById(billId));
        return "backend/bill_details.html";
    }
}
