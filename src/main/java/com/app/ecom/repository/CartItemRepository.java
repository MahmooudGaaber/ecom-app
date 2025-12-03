package com.app.ecom.repository;

import com.app.ecom.entity.CartItem;
import com.app.ecom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem , Long> {

    @Query("SELECT c FROM cart c WHERE c.user.id = :userId AND c.product = :product")
    CartItem findByUserAndProduct(String userId, Product product);

}
