package com.ggx.dev.product.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggx.dev.product.models.Product;
import com.ggx.dev.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CrudController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getProductList();
    }

    @PostMapping("/products")
    public ResponseEntity<String> postNewProduct(@RequestBody Product product){
        service.addProduct(product);
        return new ResponseEntity<>("Product %s added successfully!".formatted(product.getName()), HttpStatus.CREATED);
    }

    @PutMapping("/products")
    public ResponseEntity<String> changeOrAddProduct(
            @RequestParam(value = "id", defaultValue = "0") Long id,
            @RequestBody Product updatedProduct){

        if(service.updateProduct(id, updatedProduct)){
            return new ResponseEntity<>("Product %s updated successfully", HttpStatus.OK);
        }
        service.addProduct(updatedProduct);
        return new ResponseEntity<>("Product with id=%d not found, but now created as inserted product".formatted(id), HttpStatus.CREATED);

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("id") Long id
    ){
        if(service.deleteProduct(id)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
