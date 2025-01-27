package com.example.retornosAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name can't be empty!")
    @Size(min = 3, message = "Minimum allowed of 3 characters!")
    @Size(max = 100, message = "Maximum allowed of 100 characters!")
    private String name;

    @NotNull(message = "Price can't be empty!")
    @DecimalMin(value = "1.0", message = "Price minimum is 1,00!")
    private Double price;

    @NotNull(message = "Description can't be empty!")
    @Size(min = 1, message = "Minimum character is one!")
    @Size(max = 500, message = "Maximum allowed of 500 characters!")
    private String description;

    @NotNull(message = "In Stock Quantity can't be empty!")
    @Min(value = 0, message = "Can't use negative numbers!")
    private int inStockQuantity;

    //Garantir que o valor enviado no JSON seja mapeado para o enum
    @NotNull(message = "Category can't be empty!")
    @Enumerated(EnumType.STRING)
    private Category category;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, Double price, String description, int inStockQuantity, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.inStockQuantity = inStockQuantity;
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInStockQuantity() {
        return inStockQuantity;
    }

    public void setInStockQuantity(int inStockQuantity) {
        this.inStockQuantity = inStockQuantity;
    }



}