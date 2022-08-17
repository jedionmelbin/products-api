package com.jedionmelbin.api.sales;

import com.jedionmelbin.api.sales.domain.common.FeatureStatus;
import com.jedionmelbin.api.sales.dto.ProductDto;
import com.jedionmelbin.api.sales.services.ProductService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ProductsSalesApplication {

    private final ProductService productService;

    public ProductsSalesApplication(ProductService productService) {
        this.productService = productService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductsSalesApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
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
        };
    }

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    @Bean
    ApplicationRunner runner(MeterRegistry mr) {
        return args -> this.scheduledExecutorService.scheduleWithFixedDelay(() -> {
            long sleep = (long) (Math.random() * 1000);
            mr.timer("transform-photo-task").record(Duration.ofMillis(sleep));
        }, 500, 500, TimeUnit.MILLISECONDS);
    }
}
