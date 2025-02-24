package com.kaan.AsyncSpring.controller;


import com.kaan.AsyncSpring.dto.response.ProductResponse;
import com.kaan.AsyncSpring.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductResponse>> getAll(HttpServletResponse response) {
        long start = System.currentTimeMillis() ;
        ResponseEntity responseEntity =  ResponseEntity.ok(productService.getAll());
        response.addHeader("time",String.valueOf(System.currentTimeMillis() - start));
        return responseEntity ;
    }

    @GetMapping("/get-all-async")
    public ResponseEntity<List<ProductResponse>> getAllAsync(HttpServletResponse response) {
        long start = System.currentTimeMillis() ;
        List<ProductResponse> responses = new ArrayList<>();
        try (ExecutorService executor = Executors.new) {
            IntStream.range(1, 10000).forEach((i) -> {
                executor.submit(() -> {
                    responses.add(productService.getProductResponseById((long) i));
                });
            });
        }
        ResponseEntity responseEntity = ResponseEntity.ok(responses);
        response.addHeader("time",String.valueOf(System.currentTimeMillis() - start));
        return responseEntity;
    }


    @GetMapping("/add")
    public ResponseEntity<String> add() {
        productService.add();
        return ResponseEntity.ok("Success");
    }

}
