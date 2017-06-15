package com.rmt.repository;

import org.springframework.data.repository.CrudRepository;

import com.rmt.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}