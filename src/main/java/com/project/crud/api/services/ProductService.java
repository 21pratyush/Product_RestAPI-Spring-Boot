package com.project.crud.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        ProductModel productModel = productRepository.getById(id);
        return modelMapper.map(productModel, ProductDto.class);
    }

    public ProductDto createNewProduct(ProductDto productDto) {
        ProductModel productModel = modelMapper.map(productDto, ProductModel.class);
        return modelMapper.map(productRepository.save(productModel), (ProductDto.class));

    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDto.class))
                .collect(Collectors.toList());
    }

    public boolean deleteProductById(Long id){

        boolean isPresent = productRepository.existsById(id);
        if(!isPresent) return false;
        productRepository.deleteById(id);
        return true;
    }
}
