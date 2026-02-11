# E-commerce Product API

A RESTful API built with Spring Boot for managing e-commerce products. This API provides comprehensive product management capabilities including CRUD operations, search, filtering, and inventory management.

## Technologies Used

- Java 17
- Spring Boot 4.0.2
- Maven
- Spring Web MVC

## Features

- Complete CRUD operations for products
- Search products by keyword
- Filter products by category, brand, and price range
- Pagination support
- Stock management
- In-stock product filtering

## How to Run the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Steps to Run

1. **Clone the repository**
   ```bash
   cd c:\springboot-practicals\question4-ecommerce-product-api
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the JAR file:
   ```bash
   java -jar target/question4-ecommerce-product-api-0.0.1-SNAPSHOT.jar
   ```

4. **Access the API**
   - Base URL: `http://localhost:8080/api/products`
   - The application runs on port 8080 by default

## API Endpoints

### 1. Get All Products
**Endpoint:** `GET /api/products`

**Description:** Retrieves all products with optional pagination support.

**Query Parameters:**
- `page` (optional): Page number (starts from 0)
- `limit` (optional): Number of items per page

**Sample Request:**
```http
GET http://localhost:8080/api/products
```

**Sample Request with Pagination:**
```http
GET http://localhost:8080/api/products?page=0&limit=5
```

**Sample Response:**
```json
[
  {
    "productId": 1,
    "name": "Samsung Galaxy S24",
    "description": "Latest Samsung flagship phone",
    "price": 999.99,
    "category": "Electronics",
    "stockQuantity": 15,
    "brand": "Samsung"
  },
  {
    "productId": 2,
    "name": "iPhone 15 Pro",
    "description": "Apple's latest iPhone",
    "price": 1199.99,
    "category": "Electronics",
    "stockQuantity": 10,
    "brand": "Apple"
  }
]
```

**Screenshot:**

![Get All Products](screenshotq4/get%20all%20products.png)

---

### 2. Get Product by ID
**Endpoint:** `GET /api/products/{productId}`

**Description:** Retrieves a specific product by its ID.

**Path Parameters:**
- `productId`: The unique identifier of the product

**Sample Request:**
```http
GET http://localhost:8080/api/products/1
```

**Sample Response:**
```json
{
  "productId": 1,
  "name": "Samsung Galaxy S24",
  "description": "Latest Samsung flagship phone",
  "price": 999.99,
  "category": "Electronics",
  "stockQuantity": 15,
  "brand": "Samsung"
}
```

**Error Response (404 Not Found):**
```json
null
```

**Screenshots:**

![Get Product by ID](screenshotq4/get%20product%20by%20id%20.png)

![Not Found Error](screenshotq4/test%20not%20found.png)

---

### 3. Get Products by Category
**Endpoint:** `GET /api/products/category/{category}`

**Description:** Retrieves all products belonging to a specific category.

**Path Parameters:**
- `category`: The category name (e.g., Electronics, Footwear, Clothing)

**Sample Request:**
```http
GET http://localhost:8080/api/products/category/Electronics
```

**Sample Response:**
```json
[
  {
    "productId": 1,
    "name": "Samsung Galaxy S24",
    "description": "Latest Samsung flagship phone",
    "price": 999.99,
    "category": "Electronics",
    "stockQuantity": 15,
    "brand": "Samsung"
  },
  {
    "productId": 2,
    "name": "iPhone 15 Pro",
    "description": "Apple's latest iPhone",
    "price": 1199.99,
    "category": "Electronics",
    "stockQuantity": 10,
    "brand": "Apple"
  }
]
```

**Screenshot:**

![Get Products by Category](screenshotq4/get%20all%20product%20by%20category.png)

---

### 4. Get Products by Brand
**Endpoint:** `GET /api/products/brand/{brand}`

**Description:** Retrieves all products from a specific brand.

**Path Parameters:**
- `brand`: The brand name (e.g., Apple, Samsung, Nike)

**Sample Request:**
```http
GET http://localhost:8080/api/products/brand/Apple
```

**Sample Response:**
```json
[
  {
    "productId": 2,
    "name": "iPhone 15 Pro",
    "description": "Apple's latest iPhone",
    "price": 1199.99,
    "category": "Electronics",
    "stockQuantity": 10,
    "brand": "Apple"
  },
  {
    "productId": 10,
    "name": "MacBook Air M2",
    "description": "Thin and light laptop",
    "price": 1299.99,
    "category": "Electronics",
    "stockQuantity": 7,
    "brand": "Apple"
  }
]
```

**Screenshot:**

![Get Products by Brand](screenshotq4/get%20product%20by%20brand.png)

---

### 5. Search Products
**Endpoint:** `GET /api/products/search`

**Description:** Searches products by keyword in name or description.

**Query Parameters:**
- `keyword`: Search term to match against product name or description

**Sample Request:**
```http
GET http://localhost:8080/api/products/search?keyword=laptop
```

**Sample Response:**
```json
[
  {
    "productId": 3,
    "name": "Dell XPS 15",
    "description": "High performance laptop",
    "price": 1599.99,
    "category": "Electronics",
    "stockQuantity": 5,
    "brand": "Dell"
  },
  {
    "productId": 8,
    "name": "HP Pavilion",
    "description": "Budget friendly laptop",
    "price": 699.99,
    "category": "Electronics",
    "stockQuantity": 12,
    "brand": "HP"
  }
]
```

**Screenshot:**

![Search Products](screenshotq4/search%20by%20keyword.png)

---

### 6. Get Products by Price Range
**Endpoint:** `GET /api/products/price-range`

**Description:** Retrieves products within a specified price range.

**Query Parameters:**
- `min`: Minimum price
- `max`: Maximum price

**Sample Request:**
```http
GET http://localhost:8080/api/products/price-range?min=100&max=500
```

**Sample Response:**
```json
[
  {
    "productId": 4,
    "name": "Nike Air Max",
    "description": "Comfortable running shoes",
    "price": 129.99,
    "category": "Footwear",
    "stockQuantity": 25,
    "brand": "Nike"
  },
  {
    "productId": 5,
    "name": "Adidas Ultraboost",
    "description": "Premium running shoes",
    "price": 179.99,
    "category": "Footwear",
    "stockQuantity": 0,
    "brand": "Adidas"
  },
  {
    "productId": 6,
    "name": "Sony WH-1000XM5",
    "description": "Noise cancelling headphones",
    "price": 399.99,
    "category": "Electronics",
    "stockQuantity": 8,
    "brand": "Sony"
  }
]
```

**Screenshot:**

![Get Products by Price Range](screenshotq4/get%20product%20by%20price%20range.png)

---

### 7. Get In-Stock Products
**Endpoint:** `GET /api/products/in-stock`

**Description:** Retrieves all products that are currently in stock (quantity > 0).

**Sample Request:**
```http
GET http://localhost:8080/api/products/in-stock
```

**Sample Response:**
```json
[
  {
    "productId": 1,
    "name": "Samsung Galaxy S24",
    "description": "Latest Samsung flagship phone",
    "price": 999.99,
    "category": "Electronics",
    "stockQuantity": 15,
    "brand": "Samsung"
  },
  {
    "productId": 2,
    "name": "iPhone 15 Pro",
    "description": "Apple's latest iPhone",
    "price": 1199.99,
    "category": "Electronics",
    "stockQuantity": 10,
    "brand": "Apple"
  }
]
```

**Screenshot:**

![Get In-Stock Products](screenshotq4/get%20instock%20products.png)

---

### 8. Add New Product
**Endpoint:** `POST /api/products`

**Description:** Creates a new product. Product ID is auto-generated.

**Request Body:**
```json
{
  "name": "Samsung Galaxy Watch 6",
  "description": "Latest smartwatch from Samsung",
  "price": 349.99,
  "category": "Electronics",
  "stockQuantity": 20,
  "brand": "Samsung"
}
```

**Sample Response (201 Created):**
```json
{
  "productId": 11,
  "name": "Samsung Galaxy Watch 6",
  "description": "Latest smartwatch from Samsung",
  "price": 349.99,
  "category": "Electronics",
  "stockQuantity": 20,
  "brand": "Samsung"
}
```

**Screenshot:**

![Add New Product](screenshotq4/add%20new%20product.png)

---

### 9. Update Product
**Endpoint:** `PUT /api/products/{productId}`

**Description:** Updates an existing product completely.

**Path Parameters:**
- `productId`: The unique identifier of the product to update

**Request Body:**
```json
{
  "name": "Samsung Galaxy S24 Ultra",
  "description": "Updated flagship phone with enhanced features",
  "price": 1199.99,
  "category": "Electronics",
  "stockQuantity": 20,
  "brand": "Samsung"
}
```

**Sample Response (200 OK):**
```json
{
  "productId": 1,
  "name": "Samsung Galaxy S24 Ultra",
  "description": "Updated flagship phone with enhanced features",
  "price": 1199.99,
  "category": "Electronics",
  "stockQuantity": 20,
  "brand": "Samsung"
}
```

**Error Response (404 Not Found):**
```json
null
```

**Screenshot:**

![Update Product](screenshotq4/update%20product.png)

---

### 10. Update Product Stock
**Endpoint:** `PATCH /api/products/{productId}/stock`

**Description:** Updates only the stock quantity of a product.

**Path Parameters:**
- `productId`: The unique identifier of the product

**Query Parameters:**
- `quantity`: New stock quantity

**Sample Request:**
```http
PATCH http://localhost:8080/api/products/1/stock?quantity=50
```

**Sample Response (200 OK):**
```json
{
  "productId": 1,
  "name": "Samsung Galaxy S24",
  "description": "Latest Samsung flagship phone",
  "price": 999.99,
  "category": "Electronics",
  "stockQuantity": 50,
  "brand": "Samsung"
}
```

**Screenshot:**

![Update Stock](screenshotq4/patch%20method.png)

---

### 11. Delete Product
**Endpoint:** `DELETE /api/products/{productId}`

**Description:** Deletes a product by its ID.

**Path Parameters:**
- `productId`: The unique identifier of the product to delete

**Sample Request:**
```http
DELETE http://localhost:8080/api/products/1
```

**Sample Response (204 No Content):**
```
No content returned
```

**Error Response (404 Not Found):**
```
404 Not Found
```

**Screenshot:**

![Delete Product](screenshotq4/delete%20method.png)

---

## Product Model

```java
{
  "productId": Long,        // Auto-generated unique identifier
  "name": String,           // Product name
  "description": String,    // Product description
  "price": Double,          // Product price
  "category": String,       // Product category
  "stockQuantity": Integer, // Available stock quantity
  "brand": String          // Product brand
}
```

## Sample Data

The application comes pre-loaded with 10 sample products:

1. Samsung Galaxy S24 - Electronics
2. iPhone 15 Pro - Electronics
3. Dell XPS 15 - Electronics
4. Nike Air Max - Footwear
5. Adidas Ultraboost - Footwear
6. Sony WH-1000XM5 - Electronics
7. Levi's 501 Jeans - Clothing
8. HP Pavilion - Electronics
9. Puma Sneakers - Footwear
10. MacBook Air M2 - Electronics

## Testing

All API endpoints have been tested using Postman. Screenshots of successful API calls are available in the `screenshotq4/` directory:

- `get all products.png` - GET all products
- `get product by id .png` - GET product by ID
- `get all product by category.png` - GET products by category
- `get product by brand.png` - GET products by brand
- `search by keyword.png` - Search products
- `get product by price range.png` - GET products by price range
- `get instock products.png` - GET in-stock products
- `add new product.png` - POST new product
- `update product.png` - PUT update product
- `patch method.png` - PATCH update stock
- `delete method.png` - DELETE product
- `test not found.png` - Error handling (404)

## Code Quality

The project follows Java best practices:

- **Meaningful Variable Names**: All variables use descriptive names (e.g., `productList`, `stockQuantity`)
- **Java Naming Conventions**: 
  - Classes use PascalCase (e.g., `ProductController`, `Product`)
  - Methods use camelCase (e.g., `getAllProducts`, `getProductById`)
  - Constants would use UPPER_SNAKE_CASE
- **Comments**: Code includes comments explaining key functionality
- **Proper Indentation**: Consistent 4-space indentation throughout
- **RESTful Design**: Follows REST principles with appropriate HTTP methods and status codes
- **Clean Code**: Simple, readable implementation without unnecessary complexity

## Project Structure

```
question4-ecommerce-product-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/question4_ecommerce_product_api/
│   │   │       ├── controller/
│   │   │       │   └── ProductController.java
│   │   │       ├── model/
│   │   │       │   └── Product.java
│   │   │       └── Question4EcommerceProductApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── screenshotq4/          # API testing screenshots
├── pom.xml
└── README.md
```

## HTTP Status Codes

- `200 OK` - Successful GET, PUT, PATCH requests
- `201 Created` - Successful POST request
- `204 No Content` - Successful DELETE request
- `404 Not Found` - Resource not found

## Author

Spring Boot E-commerce Product API Project

## License

This project is created for educational purposes.
