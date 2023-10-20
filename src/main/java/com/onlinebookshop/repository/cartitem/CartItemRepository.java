package com.onlinebookshop.repository.cartitem;

import com.onlinebookshop.model.CartItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findCartItemByIdAndShoppingCartId(Long cartItemId, Long shoppingCartId);
}
