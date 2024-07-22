package com.project.crud.api.dto;

import lombok.Data;

@Data
public class ProductDto{

    private Long Id;
    private String Name;
    private String Description;
    private Double Price;
 
}