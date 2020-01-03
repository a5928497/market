package com.yukoon.market.services;

import com.yukoon.market.entities.EleMeter;
import com.yukoon.market.repository.EleMeterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleMeterService {
    @Autowired
    private EleMeterRepo eleMeterRepo;

    //根据市场查找所有电表
    public List<EleMeter> findAllByMarketId(Integer marketId) {
        return eleMeterRepo.findAllByMarketId(marketId);
    }

    public EleMeter findById(Integer id) {
        return eleMeterRepo.findOne(id);
    }

    public EleMeter save(EleMeter eleMeter) {
        return eleMeterRepo.saveAndFlush(eleMeter);
    }
}
