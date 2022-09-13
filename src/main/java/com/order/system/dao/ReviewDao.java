package com.order.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.system.entity.Review;

@Repository
public interface ReviewDao extends JpaRepository<Review, Long> {
	
	List<Review> findByItemIdAndUserId(Long itemId, Long userId);

}
