package com.rmt.repository;

import org.springframework.data.repository.CrudRepository;

import com.rmt.model.CustomerOrder;

public interface OrderRepository extends CrudRepository<CustomerOrder, Integer> {

}
