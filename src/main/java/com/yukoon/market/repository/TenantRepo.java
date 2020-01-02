package com.yukoon.market.repository;

import com.yukoon.market.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TenantRepo extends JpaRepository<Tenant,Integer> {

    @Query("select t from Tenant t where t.market.id = :marketId")
    public List<Tenant> findAllByMarketId(@Param("marketId") Integer marketId);
}
