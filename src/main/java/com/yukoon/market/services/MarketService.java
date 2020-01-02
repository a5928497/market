package com.yukoon.market.services;

import com.yukoon.market.entities.Market;
import com.yukoon.market.repository.MarketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {
    @Autowired
    private MarketRepo marketRepo;

    //查找所有市场
    public List<Market> findAll() {
        return marketRepo.findAll();
    }

    //添加&编辑市场
    public Market save(Market market) {
        return marketRepo.save(market);
    }

    //删除市场
    public void deleteById(Integer id) {
        marketRepo.delete(id);
    }
}
