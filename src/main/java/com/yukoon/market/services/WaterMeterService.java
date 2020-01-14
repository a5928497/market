package com.yukoon.market.services;

import com.yukoon.market.entities.WaterMeter;
import com.yukoon.market.repository.WaterMeterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterMeterService {
    @Autowired
    private WaterMeterRepo waterMeterRepo;

    //根据市场查找所有电表
    public List<WaterMeter> findAllByMarketId(Integer marketId) {
        return waterMeterRepo.findAllByMarketId(marketId);
    }

    public WaterMeter findById(Integer id) {
        return waterMeterRepo.findOne(id);
    }

    public WaterMeter save(WaterMeter eleMeter) {
        return waterMeterRepo.saveAndFlush(eleMeter);
    }
}
