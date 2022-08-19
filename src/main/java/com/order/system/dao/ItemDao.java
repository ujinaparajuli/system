package com.order.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.order.system.entity.Item;

@Repository
public interface ItemDao extends JpaRepository<Item, Long>{

	List<Item> findAll();
	
	List<Item> findByMenuId(Long menuId);
	 
//	@Query("SELECT i FROM item i WHERE i.title LIKE '%?1%'")
//	List<Item> searchByText(String text);
}