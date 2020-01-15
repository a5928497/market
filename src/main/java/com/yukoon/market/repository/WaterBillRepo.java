package com.yukoon.market.repository;

import com.yukoon.market.entities.WaterBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WaterBillRepo extends JpaRepository<WaterBill,Integer> {

    //查询某一水表下所有账单
    @Query("select w from WaterBill w where w.meter.id = :meterId")
    public List<WaterBill> findAllByWaterMeterId(@Param("meterId") Integer meterId);

    //查询某一水表下所有未付账单
    @Query("select w from WaterBill w where w.meter.id = :meterId and w.is_paid = 0")
    public List<WaterBill> findUnpaidByWaterMeterId(@Param("meterId") Integer meterId);

    //查询某一水表下所有已付账单
    @Query("select w from WaterBill w where w.meter.id = :meterId and w.is_paid = 1")
    public List<WaterBill> findpaidByWaterMeterId(@Param("meterId") Integer meterId);
}
