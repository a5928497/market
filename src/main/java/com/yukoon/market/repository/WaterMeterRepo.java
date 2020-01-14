package com.yukoon.market.repository;

import com.yukoon.market.entities.EleMeter;
import com.yukoon.market.entities.WaterMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WaterMeterRepo extends JpaRepository<WaterMeter, Integer> {

    @Query("select w from WaterMeter w where w.market.id = :marketId")
    public List<WaterMeter> findAllByMarketId(@Param("marketId") Integer marketId);
}
