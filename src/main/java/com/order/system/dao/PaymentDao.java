package com.order.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.system.entity.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Long> {

}
