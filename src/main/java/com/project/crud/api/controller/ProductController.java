package com.project.crud.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.crud.api.dto.ProductDto;
import com.project.crud.api.services.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @SuppressWarnings("deprecation")
    @GetMapping(path = "/{id}")
    public ProductDto getProductById(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        return productService.createNewProduct(productDto);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}