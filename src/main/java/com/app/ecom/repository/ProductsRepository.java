package com.app.ecom.repository;

import com.app.ecom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository <Product,Long> {
    List<Product> findByActiveTrue();

    @Query("SELECT p from products p where p.active = true and p.stockQuantity > 0 and lower(p.name) like  lower(concat('%', :keyword , '%'))")
    List<Product> searchProduct(@Param("keyword") String keyword);
}
