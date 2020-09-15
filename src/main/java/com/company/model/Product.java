package com.company.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
public class Product {
	
	@Id
	private String id; 
	
	@NotBlank
	@Size(max = 140)
	private String productName;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Product(String id, @NotBlank @Size(max = 140) String productName) {
		super();
		this.id = id;
		this.productName = productName;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	

}
