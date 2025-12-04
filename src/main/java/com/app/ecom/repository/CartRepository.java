package com.app.ecom.repository;

import com.app.ecom.entity.Cart;
import com.app.ecom.entity.Product;
import com.app.ecom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserAndProduct(User user, Product product);

    Cart deleteByUserAndProduct(User user, Product product);
}
