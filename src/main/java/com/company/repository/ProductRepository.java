package com.company.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.company.model.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}

