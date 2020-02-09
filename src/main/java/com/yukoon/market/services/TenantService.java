package com.yukoon.market.services;

import com.yukoon.market.entities.Tenant;
import com.yukoon.market.entities.TenantTotalBill;
import com.yukoon.market.repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TenantService {
    @Autowired
    private TenantRepo tenantRepo;

    //查找市场下所有商家
    public List<Tenant> findAllByMarketId(Integer marketId){
        return tenantRepo.findAllByMarketId(marketId);
    }

    //保存商家
    public Tenant save(Tenant tenant) {
        if (tenant.getIs_pure_income() == 1) {
            tenant.setManagement_fees(0f).setOther_fees(0f)
                    .setWater_price(0f).setEle_price(0f);
        }
        return tenantRepo.saveAndFlush(tenant);
    }

    //通过id查找商家
    public Tenant findById(Integer id) {
        Tenant tenant = tenantRepo.findOne(id);
        tenant = decorate(tenant);
        return tenant;
    }

    //查询指定id市场下所有商家未缴账单
    public List<TenantTotalBill> findAllTenantTotalBills(Integer market_id) {
        List<TenantTotalBill> bills = new ArrayList<>();
        List<Tenant> tenants = findAllByMarketId(market_id);
        for (Tenant tenant:tenants) {
//            List<>
        }
        return bills;
    }

    //修饰租户描述文字
    private Tenant decorate(Tenant tenant) {
        //修饰缴款方式
        switch (tenant.getRent_style()) {
            case 0:
                tenant.setRent_style_string("日");
                break;
            case 1:
                tenant.setRent_style_string("月");
                break;
            case 2:
                tenant.setRent_style_string("年");
                break;
        }
        return tenant;
    }
}
