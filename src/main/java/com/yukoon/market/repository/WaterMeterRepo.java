package com.yukoon.market.repository;

import com.yukoon.market.entities.EleMeter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterMeterRepo extends JpaRepository<EleMeter,Integer> {

}
