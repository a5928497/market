package com.yukoon.market.services;

import com.yukoon.market.entities.EleBill;
import com.yukoon.market.entities.EleMeter;
import com.yukoon.market.repository.EleBillRepo;
import com.yukoon.market.repository.EleMeterRepo;
import com.yukoon.market.utils.DateFomatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleBillService {
    @Autowired
    private EleMeterRepo eleMeterRepo;
    @Autowired
    private EleBillRepo eleBillRepo;

    //生成电费账单
    public EleBill caculate(Float degree_now,Integer meter_id){
        EleMeter eleMeter = eleMeterRepo.findOne(meter_id);
        float degree_old = eleMeter.getDegree();
        float ele_price = 0;
        float price = 0;
        EleBill eleBill = new EleBill();
        //如有归属，取其电价
        if (null != eleMeter.getTenant()) {
            eleBill.setTenant(eleMeter.getTenant());
            ele_price = eleMeter.getTenant().getEle_price();
            price = ele_price * (degree_now - degree_old);
        }
        eleMeter.setDegree(degree_now);
        eleBill.setStart_date(eleMeter.getUpdate_date()).setEnd_date(DateFomatter.getDate())
                .setStart_degree(degree_old).setEnd_degree(degree_now)
                .setPrice(price).setIs_paid(0)
                .setMeter(eleMeter);
        eleBill = eleBillRepo.saveAndFlush(eleBill);
        return eleBill;
    }

    //查询某一电表下所有账单
    public List<EleBill> findAllByMeterId(Integer meterId) {
        return eleBillRepo.findAllByEleMeterId(meterId);
    }

    //查询某一电表下所有未付账单
    public List<EleBill> findUnpaidByMeterId(Integer meterId) {
        return eleBillRepo.findUnpaidByEleMeterId(meterId);
    }

    //查询某一电表下所有已付账单
    public List<EleBill> findpaidByMeterId(Integer meterId) {
        return eleBillRepo.findpaidByEleMeterId(meterId);
    }

    //根据ID查找单张账单
    public EleBill findById(Integer billId) {
        return eleBillRepo.findOne(billId);
    }
}
