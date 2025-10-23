package org.example.lab6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.lab6.entity.OrderDetail;
public interface OrderDetailDAO extends JpaRepository<OrderDetail, String> {
}