package com.jedionmelbin.api.sales.repository;

import com.jedionmelbin.api.sales.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProductRepository extends JpaRepository<Product, Long> {
}
