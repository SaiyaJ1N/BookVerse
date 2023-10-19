package com.onlinebookshop.repository.order;

import com.onlinebookshop.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUserId(Long id);

    @Query(value = "select * from order join user on order.user_id = user.id " +
            "where order_id = order_id and user_id = current_user_id", nativeQuery = true)
    Optional<Order> findOrderByUserIdAndOrderId(Long orderId,Long userId);
}
