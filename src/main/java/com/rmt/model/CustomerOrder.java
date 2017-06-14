package com.rmt.model;

import java.sql.Timestamp;
import java.util.Set;

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
	    
	    private Timestamp currentTime;

	    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} , fetch = FetchType.LAZY)
	    private Set<Product> products;

		public Double getTotal() {
			return total;
		}

		public void setTotal(Double total) {
			this.total = total;
		}

		public Integer getOrderId() {
			return orderId;
		}

		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}

		public Set<Product> getProducts() {
			return products;
		}

		public void setProducts(Set<Product> products) {
			this.products = products;
		}

		public Timestamp getCurrentTime() {
			return currentTime;
		}

		public void setCurrentTime(Timestamp currentTime) {
			this.currentTime = currentTime;
		}

}