package com.project.crud.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.crud.api.configuration.ProductNotFoundException;
import com.project.crud.api.dto.ProductDto;
import com.project.crud.api.models.ProductModel;
import com.project.crud.api.repositories.ProductRepository;

@Service
public class ProductService {

    final ProductRepository productRepository;
    final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository,
            ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Deprecated
    public ProductDto getProductById(Long id) {
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return modelMapper.map(productModel, ProductDto.class);
    }

    public ProductDto createNewProduct(ProductDto productDto) {
        ProductModel productModel = modelMapper.map(productDto, ProductModel.class);
        return modelMapper.map(productRepository.save(productModel), (ProductDto.class));

    }

    public List<ProductDto> getAllProducts() {
        List<ProductModel> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found!");
        }

        return products.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " is not present!"));

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        ProductModel updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductDto.class);

    }

    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
        productRepository.deleteById(id);
        System.out.println("Success in deletion of product with ID: "+id);
    }
}
