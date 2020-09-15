package com.company.controller;

import com.company.model.*;
import com.company.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	Mono<Product> postProducts(@Valid @RequestBody Product product){
		
		return productRepo.save(product);
	}
	
	@GetMapping("/products")
	Flux<Product> getProducts(){
		
		return productRepo.findAll();
	}

}
