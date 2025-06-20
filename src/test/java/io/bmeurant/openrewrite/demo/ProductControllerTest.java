package io.bmeurant.openrewrite.demo;

import io.bmeurant.openrewrite.demo.controller.ProductController;
import io.bmeurant.openrewrite.demo.model.Product;
import io.bmeurant.openrewrite.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductRepository productRepository;

    @Test
    public void testGetAllProducts_returnsJsonArray() throws Exception {
        final Product product1 = new Product(1L, "Laptop", 1200.00);
        final Product product2 = new Product(2L, "Mouse", 25.00);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/api/v1/products/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Laptop")))
                .andExpect(jsonPath("$[1].name", is("Mouse")));
    }

    @Test
    public void testGetProductByName_found() throws Exception {
        final Product product = new Product(1L, "Laptop", 1200.00);
        when(productRepository.findByName("Laptop")).thenReturn(product);

        mockMvc.perform(get("/api/v1/products/name/Laptop")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Laptop")));
    }

    @Test
    public void testGetProductByName_notFound() throws Exception {
        when(productRepository.findByName("Unknown")).thenReturn(null);

        mockMvc.perform(get("/api/v1/products/name/Unknown")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
