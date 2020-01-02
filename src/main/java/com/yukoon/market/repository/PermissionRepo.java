package com.yukoon.market.repository;

import com.yukoon.market.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepo extends JpaRepository<Permission,Integer> {

}
