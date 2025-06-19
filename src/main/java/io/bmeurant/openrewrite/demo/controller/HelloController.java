package io.bmeurant.openrewrite.demo.controller;

import io.bmeurant.openrewrite.demo.model.Product;
import io.bmeurant.openrewrite.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        // This string concatenation could be a target for Text Blocks in Java 17+
        return "Hello from Spring Boot 2.7 (Java 11)!\n" +
                "This application demonstrates OpenRewrite capabilities, including:\n" +
                "- Spring Boot 2.x to 3.x migration\n" +
                "- Java 11 to 21 modernization\n" +
                "Let's see the magic happen!";
    }
}
