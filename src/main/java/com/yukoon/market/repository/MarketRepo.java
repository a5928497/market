package com.yukoon.market.repository;

import com.yukoon.market.entities.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarketRepo extends JpaRepository<Market,Integer> {

}
