package com.company.controller;

import com.company.model.*;
import com.company.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import org.slf4j.Logger;

@RestController
public class ProductController {
	
	@Autowired
	ProductRepository productRepo; 
	
	Logger logger = LoggerFactory.getLogger(ProductController.class); 
	
	@PostMapping("/products")
	public Mono<Product> postProducts(@Valid @RequestBody Product product){
		
		return productRepo.save(product);
	}
	
	@GetMapping("/products")
	public Flux<Product> getProducts(){
		
		return productRepo.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Mono<ResponseEntity<Product>> getProductById(@PathVariable(value = "id") String productId){
		return productRepo.findById(productId).map(savedProduct -> ResponseEntity.ok(savedProduct))
				.defaultIfEmpty(ResponseEntity.notFound().build());	
	}

	@PutMapping("/products/{id}")
	public Mono<ResponseEntity<Product>> updateProduct(@PathVariable (value = "id") String productId, @Valid @RequestBody Product product){
		return productRepo.findById(productId).flatMap(existingProduct-> {
			existingProduct.setProductName(product.getProductName());
			return productRepo.save(existingProduct);
		}).log().map(updateProduct -> new ResponseEntity<>(updateProduct, HttpStatus.OK)).log()
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
