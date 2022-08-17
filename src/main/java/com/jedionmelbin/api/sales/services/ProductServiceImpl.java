package com.jedionmelbin.api.sales.services;


import com.jedionmelbin.api.sales.domain.Product;
import com.jedionmelbin.api.sales.dto.ProductDto;
import com.jedionmelbin.api.sales.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        try {
            List<Product> products = productRepository.findAll();
            return products.stream().map(a -> ProductDto.builder()
                    .id(a.getId())
                    .description(a.getDescription())
                    .capacity(a.getCapacity())
                    .packaging(a.getPackaging())
                    .createdBy(a.getCreatedBy())
                    .createdDate(a.getCreatedDate())
                    .lastModifiedBy(a.getLastModifiedBy())
                    .lastModifiedDate(a.getLastModifiedDate())
                    .build()).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error ", ex);
            throw ex;
        }
    }

    @Override
    public ProductDto getProductId(Long productId) {
        try {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                Product productResult = product.get();
                return ProductDto.builder()
                        .id(productResult.getId())
                        .description(productResult.getDescription())
                        .capacity(productResult.getCapacity())
                        .packaging(productResult.getPackaging())
                        .createdBy(productResult.getCreatedBy())
                        .createdDate(productResult.getCreatedDate())
                        .lastModifiedBy(productResult.getLastModifiedBy())
                        .lastModifiedDate(productResult.getLastModifiedDate())
                        .build();
            }
            return null;
        } catch (Exception ex) {
            log.error("Error", ex);
            throw ex;
        }
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        try {
            Product product = new Product();
            return getProductDto(productDto, product);
        } catch (Exception ex) {
            log.error("Error ", ex);
            throw ex;
        }
    }

    @Override
    public List<ProductDto> createProduct(List<ProductDto> productDto) {
        try {
            List<Product> products = productDto.stream().map(a -> Product.builder()
                    .description(a.getDescription())
                    .capacity(a.getCapacity())
                    .packaging(a.getPackaging())
                    .featureStatus(a.getFeatureStatus())
                    .createdBy(a.getCreatedBy())
                    .createdDate(a.getCreatedDate())
                    .lastModifiedBy(a.getLastModifiedBy())
                    .lastModifiedDate(a.getLastModifiedDate())
                    .build()).collect(Collectors.toList());
            List<Product> resultList =
                    productRepository.saveAll(products);
            return resultList.stream().map(a -> ProductDto.builder()
                    .id(a.getId())
                    .description(a.getDescription())
                    .capacity(a.getCapacity())
                    .packaging(a.getPackaging())
                    .createdBy(a.getCreatedBy())
                    .createdDate(a.getCreatedDate())
                    .lastModifiedBy(a.getLastModifiedBy())
                    .lastModifiedDate(a.getLastModifiedDate())
                    .build()).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error ", ex);
            throw ex;
        }
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        try {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                Product products = product.get();
                return getProductDto(productDto, products);
            }
            return null;
        } catch (Exception ex) {
            log.error("Error ", ex);
            throw ex;
        }
    }

    private ProductDto getProductDto(ProductDto productDto, Product products) {
        products.setDescription(productDto.getDescription());
        products.setCapacity(productDto.getCapacity());
        products.setPackaging(productDto.getPackaging());
        products.setFeatureStatus(productDto.getFeatureStatus());
        products.setCreatedBy(productDto.getCreatedBy());
        products.setCreatedDate(productDto.getCreatedDate());
        products.setLastModifiedBy(productDto.getLastModifiedBy());
        products.setLastModifiedDate(productDto.getLastModifiedDate());
        var productResult = productRepository.save(products);
        return ProductDto.builder()
                .id(productResult.getId())
                .description(productResult.getDescription())
                .capacity(productResult.getCapacity())
                .packaging(productResult.getPackaging())
                .createdBy(productResult.getCreatedBy())
                .featureStatus(productResult.getFeatureStatus())
                .lastModifiedDate(productResult.getLastModifiedDate())
                .build();
    }


    @Override
    public void removeProduct(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            product.ifPresent(productRepository::delete);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Error ", ex);
            throw ex;
        }
    }
}
