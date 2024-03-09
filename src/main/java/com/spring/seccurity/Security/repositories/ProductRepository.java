package com.spring.seccurity.Security.repositories;

import com.spring.seccurity.Security.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
