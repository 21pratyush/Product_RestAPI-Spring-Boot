package com.project.crud.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    private String Name;
    private String Description;
    private double Price;
}
