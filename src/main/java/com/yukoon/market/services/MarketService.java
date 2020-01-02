package com.yukoon.market.services;

import com.yukoon.market.entities.Market;
import com.yukoon.market.entities.Tenant;
import com.yukoon.market.repository.MarketRepo;
import com.yukoon.market.repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {
    @Autowired
    private MarketRepo marketRepo;
    @Autowired
    private TenantRepo tenantRepo;

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

    //查询指定id市场下所有商家数
    public List<Market> findAllTenantByMarket() {
        List<Market> markets = findAll();
        //查询每个市场下商家数
        for (Market market:markets) {
            market.setTenant_number(tenantRepo.findAllByMarketId(market.getId()).size());
        }
        return markets;
    }

}
