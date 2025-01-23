package com.example.retornosAPI.controllers;

import com.example.retornosAPI.models.Product;
import com.example.retornosAPI.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    //Cria produto
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(service.createProduct(product));
    }

    //Busca individual por id ( /products/{id} )
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }
    
    //Busca individual por nome (/products/search?name=... )
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProductByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(service.getProductsByName(name));
    }


    
    //Listar tudo
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }
    
    //Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@Valid @PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}