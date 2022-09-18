package com.order.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.system.entity.Cart;
import com.order.system.entity.Chef;
import com.order.system.entity.Item;
import com.order.system.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
