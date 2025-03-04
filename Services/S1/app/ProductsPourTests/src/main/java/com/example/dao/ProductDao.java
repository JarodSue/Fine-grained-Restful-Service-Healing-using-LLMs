package com.example.dao;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
	Product findById(int id);
        Product findByName(String name);
        boolean existsByName(String name);
}
