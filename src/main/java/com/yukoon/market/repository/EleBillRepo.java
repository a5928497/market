package com.yukoon.market.repository;

import com.yukoon.market.entities.EleBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EleBillRepo extends JpaRepository<EleBill,Integer> {

    @Query("select e from EleBill e where e.eleMeter.id = :meterId")
    public List<EleBill> findAllByEleMeterId(@Param("meterId")Integer meterId);
}
