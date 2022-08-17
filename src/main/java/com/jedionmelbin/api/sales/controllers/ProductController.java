package com.jedionmelbin.api.sales.controllers;

import com.jedionmelbin.api.sales.dto.ProductDto;
import com.jedionmelbin.api.sales.services.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@ApiOperation("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDto = productService.getAllProduct();
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping({"/{productId}"})
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getProductId(productId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto product) {
        ProductDto productDto = productService.createProduct(product);
        HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.add("products", "/api/v1/products/" + productDto.getId().toString());
        return new ResponseEntity<>(productDto, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{productId}"})
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto product) {
        productService.updateProduct(productId, product);
        return new ResponseEntity<>(productService.getProductId(productId), HttpStatus.OK);
    }

    @DeleteMapping({"/{productId}"})
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("productId") Long productId) {
        productService.removeProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
