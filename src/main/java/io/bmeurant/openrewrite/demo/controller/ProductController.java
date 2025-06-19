package io.bmeurant.openrewrite.demo.controller;

import io.bmeurant.openrewrite.demo.model.Product;
import io.bmeurant.openrewrite.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its name.
     *
     * @param name The name of the product to retrieve, passed as a path variable.
     * @return A ResponseEntity containing the product if found, or a 404 Not Found status.
     */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productRepository.findByName(name);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
