package com.yukoon.market.services;

import com.yukoon.market.entities.EleBill;
import com.yukoon.market.repository.EleMeterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EleBillService {
    @Autowired
    private EleMeterRepo eleMeterRepo;

    //生成账单
    public EleBill caculate(Float degree_now,Integer meter_id){
        return null;
    }
}
