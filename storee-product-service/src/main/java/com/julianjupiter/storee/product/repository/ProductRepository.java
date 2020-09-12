package com.julianjupiter.storee.product.repository;

import com.julianjupiter.storee.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
