package com.ggx.dev.product.service;

import com.ggx.dev.product.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    public Optional<Product> getProductById(Long id){
        return productList.stream().filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    public boolean updateProduct(Long id, Product updatedProduct){
        Optional<Product> existingProduct = getProductById(id);
        if(existingProduct.isPresent()){
            existingProduct.get().setName(updatedProduct.getName());
            existingProduct.get().setPrice(updatedProduct.getPrice());
            return true;
        }
        return false;
    }

    public boolean deleteProduct(Long id){
        return productList.removeIf(p -> p.getId().equals(id));
    }
}
