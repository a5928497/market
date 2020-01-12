package com.yukoon.market.services;

import com.yukoon.market.entities.EleBill;
import com.yukoon.market.entities.EleMeter;
import com.yukoon.market.repository.EleBillRepo;
import com.yukoon.market.repository.EleMeterRepo;
import com.yukoon.market.utils.DateFomatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        eleBill.setStart_date(eleMeter.getUpdate_date()).setEnd_date(DateFomatter.getDate())
                .setStart_degree(degree_old).setEnd_degree(degree_now)
                .setPrice(price).setIs_paid(0)
                .setEleMeter(eleMeter);
        eleBill = eleBillRepo.saveAndFlush(eleBill);
        return eleBill;
    }
}
