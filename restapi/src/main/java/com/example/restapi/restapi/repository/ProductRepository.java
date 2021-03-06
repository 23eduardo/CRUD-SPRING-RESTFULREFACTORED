package com.example.restapi.restapi.repository;

import com.example.restapi.restapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//this makes an actual repository this interface
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
