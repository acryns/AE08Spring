package com.rmt.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
public class Product {
    public Product() {
    }
    
    public Product(String productName, String productDescription, Double productPrice, Category category) {
    	this.productName = productName;
    	this.productDescription = productDescription;
    	this.productPrice = productPrice;
    	this.category = category;
    }
    

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer productId;

	@JsonProperty("productName")
	private String productName;

	@JsonProperty("productPrice")
	private Double productPrice;

	@JsonProperty("productDesc")
	private String productDescription;

	@OneToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	private Category category;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Name: '" + this.productName + "', Desc: '" + this.productDescription + "', productPrice: '"
				+ this.productPrice + "'";
	}

}
