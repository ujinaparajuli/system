package com.order.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.system.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{	
	User findByEmail(String email);
}
