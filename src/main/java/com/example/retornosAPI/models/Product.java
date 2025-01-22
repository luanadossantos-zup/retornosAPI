package com.example.retornosAPI.models;

public record Product(Long id, String name, Double price, String description, int inStockQuantity, Category category) {
}