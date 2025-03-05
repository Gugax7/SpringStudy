package com.ggx.dev.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggx.dev.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class RestPostController {

    ObjectMapper objectMapper = new ObjectMapper();
    private static final String FILE_PATH = "products.json";

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(
            @RequestBody Product product
    ){
        try {
            List<Product> products = readProductFromFile();
            products.add(product);
            writeProductsToFile(products);
        }catch (IOException io){
            System.err.println(io.getMessage());
        }
        return new ResponseEntity<>("Product %s created successfully!".formatted(product.getName()), HttpStatus.CREATED);

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) throws IOException {
        List<Product> products = readProductFromFile();
        for(Product product: products){
            if(product.getId().equals(id)){
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @RequestBody Product updatedProduct,
            @PathVariable("id") Long id
    ) throws IOException {
        List<Product> products = readProductFromFile();
        for(Product product : products){
            if(product.getId().equals(id)){
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                writeProductsToFile(products);
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        createProduct(updatedProduct);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable("id") Long id) throws IOException {
        List<Product> products = readProductFromFile();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
            if(product.getId().equals(id)){
                iterator.remove();
                writeProductsToFile(products);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private List<Product> readProductFromFile() throws IOException {
        File file = new File(FILE_PATH);
        if(!file.exists()){
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<>() {});
    }

    private void writeProductsToFile(List<Product> products) throws IOException {
        objectMapper.writeValue(new File(FILE_PATH), products);


    }
}
