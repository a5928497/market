package com.yukoon.market.services;

import com.yukoon.market.entities.Tenant;
import com.yukoon.market.repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    @Autowired
    private TenantRepo tenantRepo;

    //查找市场下所有商家
    public List<Tenant> findAllByMarketId(Integer marketId){
        return tenantRepo.findAllByMarketId(marketId);
    }
}
