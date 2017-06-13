package com.rmt.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
	public class CustomerOrder {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer orderId;

	    private Double total;

	    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} , fetch = FetchType.LAZY)
	    private List<Product> products;

}