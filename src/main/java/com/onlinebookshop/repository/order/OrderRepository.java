package com.onlinebookshop.repository.order;

import com.onlinebookshop.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByUserId(Long id);
}
