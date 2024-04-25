 Q Task -Create a spring boot application to do CRUD operations on Product Entity.


1)**/Define the Product Entity
Create a Product class representing your entity with fields like id, name, description, price, etc./**


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

}

2)**/Create a Repository Interface
Define a repository interface (ProductRepository) that extends JpaRepository or CrudRepository to handle CRUD operations on the Product entity./**


import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}


3)*/Implement Service Layer
Create a service class (ProductService) to encapsulate business logic for CRUD operations./*


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

4)*/Create RESTful Controller
Define a controller (ProductController) to handle HTTP requests for CRUD operations./*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id); // Ensure the product ID is set
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}


5)database configuration in application.properties or application.yml to specify the database connection details (e.g., H2 database for development).

6)Run the Spring Boot application and test the CRUD operations using tools like Postman or curl commands:

GET /api/products - Retrieve all products
GET /api/products/{id} - Retrieve a product by ID
POST /api/products - Add a new product (provide JSON payload)
PUT /api/products/{id} - Update an existing product (provide JSON payload)
DELETE /api/products/{id} - Delete a product by ID