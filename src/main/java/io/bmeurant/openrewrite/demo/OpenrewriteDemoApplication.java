package io.bmeurant.openrewrite.demo;

import io.bmeurant.openrewrite.demo.model.Product;
import io.bmeurant.openrewrite.demo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@SpringBootApplication
public class OpenrewriteDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenrewriteDemoApplication.class, args);
    }

    // CommandLineRunner to populate some data and demonstrate refactoring targets
    @Bean
    CommandLineRunner runner(ProductRepository productRepository) {
        return args -> {
            System.out.println("--- Initializing Products ---");
            Stream.of("Laptop", "Mouse", "Keyboard", "Monitor", "Webcam")
                    .forEach(name -> productRepository.save(new Product(null, name, ThreadLocalRandom.current().nextDouble() * 100)));
            productRepository.findAll().forEach(System.out::println);
        };
    }
}
