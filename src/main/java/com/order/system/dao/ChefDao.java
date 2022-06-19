package com.order.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.system.entity.Chef;

@Repository
public interface ChefDao extends JpaRepository<Chef, Long> {
	

}
