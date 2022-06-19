package com.order.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.system.entity.Cart;
import com.order.system.entity.Chef;
import com.order.system.entity.Item;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {
 List<Cart> findByItemId(Long itemId);
 
 List<Cart> findBySessionId(String sessionId);
 
 List<Cart> findByOrderId(Long orderId);
}
