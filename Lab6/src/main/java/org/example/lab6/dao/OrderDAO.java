package org.example.lab6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.lab6.entity.Order;

public interface OrderDAO extends JpaRepository<Order, String> {
}
