package com.kaan.AsyncSpring.service;


import com.kaan.AsyncSpring.dto.response.ProductResponse;
import com.kaan.AsyncSpring.model.Product;
import com.kaan.AsyncSpring.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductService {

    private ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<ProductResponse> getAll() {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            Product product = productRepo.findById((long) i).get();
            productResponses.add(convert(product));
        }
        return productResponses;
    }


    public ProductResponse getProductResponseById(Long id) {
        return convert(productRepo.findById(id).get());
    }

    private ProductResponse convert(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }

    private List<ProductResponse> convert(List<Product> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(new ProductResponse(product.getId(), product.getName(), product.getPrice()));
        }
        return productResponses;
    }

    public void add() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            Product product = new Product();
            product.setName("a");
            product.setPrice(random.nextFloat(1000, 100000));
            productRepo.save(product);
        }
    }
}
