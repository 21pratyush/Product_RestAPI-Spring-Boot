package com.project.crud.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crud.api.models.ProductModel;

@Repository
public interface ProductRepository extends  JpaRepository<ProductModel, Long>{}
