package com.jedionmelbin.api.sales.services;

import com.jedionmelbin.api.sales.domain.Product;
import com.jedionmelbin.api.sales.domain.common.FeatureStatus;
import com.jedionmelbin.api.sales.dto.ProductDto;
import com.jedionmelbin.api.sales.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void getAllProduct() {
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(ProductDto.builder()
                .id(1L)
                .description("Kola")
                .featureStatus(FeatureStatus.NEVERA)
                .packaging("botella")
                .capacity(100D)
                .createdBy("jedionmelbin")
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .lastModifiedBy("jedionmelbin")
                .build());
        productDtos.add(ProductDto.builder()
                .id(2L)
                .description("Mayonesa")
                .featureStatus(FeatureStatus.NOT_NEVERA)
                .packaging("caja")
                .capacity(100D)
                .createdBy("jedionmelbin")
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .lastModifiedBy("jedionmelbin")
                .build());
        productDtos.add(ProductDto.builder()
                .id(3L)
                .description("Comino")
                .featureStatus(FeatureStatus.NOT_NEVERA)
                .packaging("caja")
                .capacity(1000D)
                .createdBy("jedionmelbin")
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .lastModifiedBy("jedionmelbin")
                .build());
        when(productService.getAllProduct()).thenReturn(productDtos);

        List<ProductDto> productResult = productService.getAllProduct();
        assertEquals(3, productResult.size());

    }

    @Test
    void getProductId() {
        when(productService.getProductId(anyLong())).thenReturn(ProductDto.builder()
                .id(3L)
                .description("Comino")
                .featureStatus(FeatureStatus.NOT_NEVERA)
                .packaging("caja")
                .capacity(1000D)
                .createdBy("jedionmelbin")
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .lastModifiedBy("jedionmelbin")
                .build());
        ProductDto productDto =
                productService.getProductId(anyLong());
        assertNotNull(productDto);
    }

    @Test
    void createProduct() {
        ProductDto productDto = ProductDto.builder()
                .description("Comino")
                .featureStatus(FeatureStatus.NOT_NEVERA)
                .packaging("caja")
                .capacity(1000D)
                .createdBy("jedionmelbin")
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .lastModifiedBy("jedionmelbin")
                .build();
        productService.createProduct(productDto);
        verify(productService, times(1)).createProduct(productDto);
    }

    @Test
    void createListProduct() {
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(ProductDto.builder()
                .description("Kola")
                .featureStatus(FeatureStatus.NEVERA)
                .packaging("botella")
                .capacity(100D)
                .createdBy("jedionmelbin")
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .lastModifiedBy("jedionmelbin")
                .build());
        productDtos.add(ProductDto.builder()
                .description("Mayonesa")
                .featureStatus(FeatureStatus.NOT_NEVERA)
                .packaging("caja")
                .capacity(100D)
                .createdBy("jedionmelbin")
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .lastModifiedBy("jedionmelbin")
                .build());
        productDtos.add(ProductDto.builder()
                .description("Comino")
                .featureStatus(FeatureStatus.NOT_NEVERA)
                .packaging("caja")
                .capacity(1000D)
                .createdBy("jedionmelbin")
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .lastModifiedBy("jedionmelbin")
                .build());
        productService.createProduct(productDtos);
        verify(productService, times(1)).createProduct(productDtos);
    }

    @Test
    void updateProduct() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(Product.builder()
                .id(3L)
                .description("Comino")
                .featureStatus(FeatureStatus.NOT_NEVERA)
                .packaging("caja")
                .capacity(1000D)
                .createdBy("jedionmelbin")
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .lastModifiedBy("jedionmelbin")
                .build()));

        ProductDto productDto = ProductDto.builder()
                .id(3L)
                .description("Comino")
                .featureStatus(FeatureStatus.NOT_NEVERA)
                .packaging("caja")
                .capacity(1000D)
                .createdBy("jedionmelbin")
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .lastModifiedBy("jedionmelbin")
                .build();
        ProductDto productDtoResult = productService.updateProduct(3l, productDto);
        assertNotNull(productDtoResult);
    }

    @Test
    void removeProduct() {
        when(productRepository.findById(any())).thenReturn(Optional.of(Product.builder()
                .id(3L)
                .description("Comino")
                .featureStatus(FeatureStatus.NOT_NEVERA)
                .packaging("caja")
                .capacity(1000D)
                .createdBy("jedionmelbin")
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .lastModifiedBy("jedionmelbin")
                .build()));

        productService.removeProduct(any());
    }
}