package com.example.question4_ecommerce_product_api.controller;

import com.example.question4_ecommerce_product_api.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    // list to store products
    private List<Product> productList = new ArrayList<>();
    
    // constructor to add sample data
    public ProductController() {
        // adding 10 sample products with simple IDs (1, 2, 3...)
        productList.add(new Product(1L, "Samsung Galaxy S24", "Latest Samsung flagship phone", 999.99, "Electronics", 15, "Samsung"));
        productList.add(new Product(2L, "iPhone 15 Pro", "Apple's latest iPhone", 1199.99, "Electronics", 10, "Apple"));
        productList.add(new Product(3L, "Dell XPS 15", "High performance laptop", 1599.99, "Electronics", 5, "Dell"));
        productList.add(new Product(4L, "Nike Air Max", "Comfortable running shoes", 129.99, "Footwear", 25, "Nike"));
        productList.add(new Product(5L, "Adidas Ultraboost", "Premium running shoes", 179.99, "Footwear", 0, "Adidas"));
        productList.add(new Product(6L, "Sony WH-1000XM5", "Noise cancelling headphones", 399.99, "Electronics", 8, "Sony"));
        productList.add(new Product(7L, "Levi's 501 Jeans", "Classic fit jeans", 69.99, "Clothing", 30, "Levi's"));
        productList.add(new Product(8L, "HP Pavilion", "Budget friendly laptop", 699.99, "Electronics", 12, "HP"));
        productList.add(new Product(9L, "Puma Sneakers", "Casual sneakers", 89.99, "Footwear", 20, "Puma"));
        productList.add(new Product(10L, "MacBook Air M2", "Thin and light laptop", 1299.99, "Electronics", 7, "Apple"));
    }
    
    // GET /api/products - get all products with optional pagination
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit) {
        
        if (page != null && limit != null) {
            // simple pagination logic
            int startIndex = page * limit;
            int endIndex = startIndex + limit;
            
            if (startIndex >= productList.size()) {
                return ResponseEntity.ok(new ArrayList<>());
            }
            
            if (endIndex > productList.size()) {
                endIndex = productList.size();
            }
            
            List<Product> paginatedList = productList.subList(startIndex, endIndex);
            return ResponseEntity.ok(paginatedList);
        }
        
        return ResponseEntity.ok(productList);
    }
    
    // GET /api/products/{productId} - get product by id
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        for (Product p : productList) {
            if (p.getProductId().equals(productId)) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    // GET /api/products/category/{category} - get products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return ResponseEntity.ok(result);
    }
    
    // GET /api/products/brand/{brand} - get products by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getBrand().equalsIgnoreCase(brand)) {
                result.add(p);
            }
        }
        return ResponseEntity.ok(result);
    }
    
    // GET /api/products/search?keyword={keyword} - search products
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            // search in name or description
            if (p.getName().toLowerCase().contains(keyword.toLowerCase()) || 
                p.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        return ResponseEntity.ok(result);
    }
    
    // GET /api/products/price-range?min={min}&max={max}
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double min, 
            @RequestParam Double max) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return ResponseEntity.ok(result);
    }
    
    // GET /api/products/in-stock - get products in stock
    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getStockQuantity() > 0) {
                result.add(p);
            }
        }
        return ResponseEntity.ok(result);
    }
    
    // POST /api/products - add new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        // generate new ID by finding the maximum ID and adding 1
        Long newId = 1L;
        for (Product p : productList) {
            if (p.getProductId() >= newId) {
                newId = p.getProductId() + 1;
            }
        }
        product.setProductId(newId);
        productList.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    
    // PUT /api/products/{productId} - update product
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long productId, 
            @RequestBody Product updatedProduct) {
        
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId().equals(productId)) {
                updatedProduct.setProductId(productId);
                productList.set(i, updatedProduct);
                return ResponseEntity.ok(updatedProduct);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    // PATCH /api/products/{productId}/stock?quantity={quantity}
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(
            @PathVariable Long productId, 
            @RequestParam int quantity) {
        
        for (Product p : productList) {
            if (p.getProductId().equals(productId)) {
                p.setStockQuantity(quantity);
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    // DELETE /api/products/{productId} - delete product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId().equals(productId)) {
                productList.remove(i);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

