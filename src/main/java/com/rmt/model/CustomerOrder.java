package com.rmt.model;

import java.sql.Time;
import java.util.Date;
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

//	    @JsonProperty("orderTotal")
	    private Double total;
	    
//	    @JsonProperty("orderDate")
	    private Date date;
	    
//	    @JsonProperty("orderTime")
	    private Time time;

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

		public Date getDate() {
			return date;
		}

		public void setDate(java.util.Date day) {
			this.date = day;
		}

		public Time getTime() {
			return time;
		}

		public void setTime(Time time) {
			this.time = time;
		}
}