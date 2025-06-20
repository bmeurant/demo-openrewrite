package io.bmeurant.openrewrite.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @GetMapping("/")
    public String hello() {
        // This string concatenation could be a target for Text Blocks in Java 17+
        return """
                Hello from Spring Boot 2.7 (Java 11)!
                This application demonstrates OpenRewrite capabilities, including:
                - Spring Boot 2.x to 3.x migration
                - Java 11 to 21 modernization
                Let's see the magic happen!""";
    }
}
