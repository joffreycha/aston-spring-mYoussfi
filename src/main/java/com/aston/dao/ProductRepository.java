package com.aston.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aston.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
