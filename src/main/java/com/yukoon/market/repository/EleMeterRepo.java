package com.yukoon.market.repository;

import com.yukoon.market.entities.EleMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EleMeterRepo extends JpaRepository<EleMeter,Integer> {

    @Query("select e from EleMeter e where e.market.id = :marketId")
    public List<EleMeter> findAllByMarketId(@Param("marketId")Integer marketId);
}
