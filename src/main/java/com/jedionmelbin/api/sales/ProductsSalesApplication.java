package com.jedionmelbin.api.sales;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ProductsSalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsSalesApplication.class, args);
    }

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    @Bean
    ApplicationRunner runner(MeterRegistry mr){
        return  args -> this.scheduledExecutorService.scheduleWithFixedDelay(() -> {
            long sleep = (long) (Math.random() * 1000);
            mr.timer("transform-photo-task").record(Duration.ofMillis(sleep));
        },500,500, TimeUnit.MILLISECONDS);
    }
}
