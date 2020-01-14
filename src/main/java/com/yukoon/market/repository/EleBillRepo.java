package com.yukoon.market.repository;

import com.yukoon.market.entities.EleBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EleBillRepo extends JpaRepository<EleBill,Integer> {

    //查询某一电表下所有账单
    @Query("select e from EleBill e where e.eleMeter.id = :meterId")
    public List<EleBill> findAllByEleMeterId(@Param("meterId")Integer meterId);

    //查询某一电表下所有未付账单
    @Query("select e from EleBill e where e.eleMeter.id = :meterId and e.is_paid = 0")
    public List<EleBill> findUnpaidByEleMeterId(@Param("meterId")Integer meterId);

    //查询某一电表下所有已付账单
    @Query("select e from EleBill e where e.eleMeter.id = :meterId and e.is_paid = 1")
    public List<EleBill> findpaidByEleMeterId(@Param("meterId")Integer meterId);
}
