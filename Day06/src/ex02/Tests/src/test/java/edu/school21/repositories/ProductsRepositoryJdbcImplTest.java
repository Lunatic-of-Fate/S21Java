package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsRepositoryJdbcImplTest {
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>();
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1, "Product 1", 10.99);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1, "cheeseUpdate", 299.99);

    private DataSource dataSource;
    private ProductsRepositoryJdbcImpl repository;

    @BeforeEach
    void init() {
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(1, "Product 1", 10.99));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(2, "Product 2", 15.49));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(3, "Product 3", 7.99));

        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setName("testdb")
                .addScript("schema.sql")
                .build();

        repository = new ProductsRepositoryJdbcImpl(dataSource);
        for (Product product : EXPECTED_FIND_ALL_PRODUCTS) {
            repository.save(product);
        }

    }

    @Test
    void testFindAll() {
        List<Product> products = repository.findAll();
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS.size(), products.size());
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, products);
    }

    @Test
    void testFindById() {
        Product product = repository.findById(1L).orElse(null);
        assertNotNull(product);
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT.getName(), product.getName());
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT.getPrice(), product.getPrice());
    }

    @Test
    void testUpdate() {
        repository.update(EXPECTED_UPDATED_PRODUCT);
        Product updatedProduct = repository.findById(1L).orElse(null);
        assertNotNull(updatedProduct);
        assertEquals(EXPECTED_UPDATED_PRODUCT.getName(), updatedProduct.getName());
        assertEquals(EXPECTED_UPDATED_PRODUCT.getPrice(), updatedProduct.getPrice());
    }

    @Test
    void testDelete() {
        repository.delete(1L);
        assertFalse(repository.findById(1L).isPresent());
    }

}
