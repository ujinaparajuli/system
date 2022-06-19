package com.order.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.system.entity.Menu;

@Repository
public interface MenuDao extends JpaRepository<Menu, Long>{
	List<Menu> findAll();
	List<Menu> findByCategory(String category);

}
