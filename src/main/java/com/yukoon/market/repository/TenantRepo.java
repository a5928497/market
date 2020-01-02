package com.yukoon.market.repository;

import com.yukoon.market.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepo extends JpaRepository<Tenant,Integer> {
}
