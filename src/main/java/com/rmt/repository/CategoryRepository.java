package com.rmt.repository;

import org.springframework.data.repository.CrudRepository;

import com.rmt.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
