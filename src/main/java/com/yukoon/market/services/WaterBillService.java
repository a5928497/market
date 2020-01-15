package com.yukoon.market.services;

import com.yukoon.market.entities.EleBill;
import com.yukoon.market.entities.WaterBill;
import com.yukoon.market.entities.WaterMeter;
import com.yukoon.market.repository.EleBillRepo;
import com.yukoon.market.repository.WaterMeterRepo;
import com.yukoon.market.repository.WaterBillRepo;
import com.yukoon.market.repository.WaterMeterRepo;
import com.yukoon.market.utils.DateFomatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterBillService {
    @Autowired
    private WaterMeterRepo waterMeterRepo;
    @Autowired
    private WaterBillRepo waterBillRepo;

    //生成水费账单
    public WaterBill caculate(Float degree_now,Integer meter_id){
        WaterMeter waterMeter = waterMeterRepo.findOne(meter_id);
        float degree_old = waterMeter.getDegree();
        float water_price = 0;
        float price = 0;
        WaterBill waterBill = new WaterBill();
        //如有归属，取其水
        if (null != waterMeter.getTenant()) {
            waterBill.setTenant(waterMeter.getTenant());
            water_price = waterMeter.getTenant().getWater_price();
            price = water_price * (degree_now - degree_old);
        }
        waterMeter.setDegree(degree_now);
        waterBill.setStart_date(waterMeter.getUpdate_date()).setEnd_date(DateFomatter.getDate())
                .setStart_degree(degree_old).setEnd_degree(degree_now)
                .setPrice(price).setIs_paid(0)
                .setWaterMeter(waterMeter);
        waterBill = waterBillRepo.saveAndFlush(waterBill);
        return waterBill;
    }

    //查询某一水表下所有账单
    public List<WaterBill> findAllByMeterId(Integer meterId) {
        return waterBillRepo.findAllByWaterMeterId(meterId);
    }

    //查询某一水表下所有未付账单1
    public List<WaterBill> findUnpaidByMeterId(Integer meterId) {
        return waterBillRepo.findUnpaidByWaterMeterId(meterId);
    }

    //查询某一水表下所有已付账单
    public List<WaterBill> findpaidByMeterId(Integer meterId) {
        return waterBillRepo.findpaidByWaterMeterId(meterId);
    }

    //根据ID查找单张账单
    public WaterBill findById(Integer billId) {
        return waterBillRepo.findOne(billId);
    }
}
