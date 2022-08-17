package com.jedionmelbin.api.sales.services;


import com.jedionmelbin.api.sales.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProduct();
    ProductDto getProductId(Long productId);
    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> createProduct(List<ProductDto> productDto);
    ProductDto updateProduct(Long productId, ProductDto product);
    void removeProduct(Long id);
}
