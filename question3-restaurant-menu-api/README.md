# Restaurant Menu API

A RESTful API built with Spring Boot for managing restaurant menu items. This API provides endpoints to perform CRUD operations, search, filter, and manage menu item availability.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [How to Run the Application](#how-to-run-the-application)
- [API Endpoints](#api-endpoints)
- [Sample Requests and Responses](#sample-requests-and-responses)
- [Testing Screenshots](#testing-screenshots)

## Features

- Get all menu items
- Get menu item by ID
- Filter menu items by category
- Filter menu items by availability
- Search menu items by name
- Add new menu items
- Toggle menu item availability
- Delete menu items

## Technologies Used

- Java 17
- Spring Boot 4.0.2
- Spring Web MVC
- Maven

## Prerequisites

Before running this application, ensure you have the following installed:
- Java Development Kit (JDK) 17 or higher
- Maven 3.6 or higher
- An IDE (IntelliJ IDEA, Eclipse, or VS Code)
- Postman or any REST client for testing (optional)

## How to Run the Application

### Option 1: Using Maven Command Line

1. **Clone or download the project**
   ```bash
   cd C:\springboot-practicals\question3-restaurant-menu-api
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

### Option 2: Using IDE

1. Open the project in your IDE
2. Locate the main application class (Question3RestaurantMenuApiApplication.java)
3. Right-click and select "Run"

### Option 3: Using JAR file

1. **Build the JAR file**
   ```bash
   mvn clean package
   ```

2. **Run the JAR file**
   ```bash
   java -jar target/question3-restaurant-menu-api-0.0.1-SNAPSHOT.jar
   ```

The application will start on **http://localhost:8080**

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/menu` | Get all menu items |
| GET | `/api/menu/{id}` | Get menu item by ID |
| GET | `/api/menu/category/{category}` | Get menu items by category |
| GET | `/api/menu/available?available=true` | Get available/unavailable items |
| GET | `/api/menu/search?name={name}` | Search menu items by name |
| POST | `/api/menu` | Add a new menu item |
| PUT | `/api/menu/{id}/availability` | Toggle menu item availability |
| DELETE | `/api/menu/{id}` | Delete a menu item |

## Sample Requests and Responses

### 1. Get All Menu Items

**Request:**
```http
GET http://localhost:8080/api/menu
```

**Response:** (200 OK)
```json
[
  {
    "id": 1,
    "name": "Spring Rolls",
    "description": "Crispy rolls",
    "price": 5.0,
    "category": "Appetizer",
    "available": true
  },
  {
    "id": 2,
    "name": "Chicken Wings",
    "description": "Spicy wings",
    "price": 7.5,
    "category": "Appetizer",
    "available": true
  },
  {
    "id": 3,
    "name": "Beef Burger",
    "description": "Grilled beef burger",
    "price": 10.0,
    "category": "Main Course",
    "available": true
  },
  {
    "id": 4,
    "name": "Pasta",
    "description": "Creamy pasta",
    "price": 9.0,
    "category": "Main Course",
    "available": false
  },
  {
    "id": 5,
    "name": "Ice Cream",
    "description": "Vanilla ice cream",
    "price": 4.0,
    "category": "Dessert",
    "available": true
  },
  {
    "id": 6,
    "name": "Cake",
    "description": "Chocolate cake",
    "price": 6.0,
    "category": "Dessert",
    "available": false
  },
  {
    "id": 7,
    "name": "Coffee",
    "description": "Hot coffee",
    "price": 3.0,
    "category": "Beverage",
    "available": true
  },
  {
    "id": 8,
    "name": "Juice",
    "description": "Fresh juice",
    "price": 3.5,
    "category": "Beverage",
    "available": true
  }
]
```

---

### 2. Get Menu Item by ID

**Request:**
```http
GET http://localhost:8080/api/menu/1
```

**Response:** (200 OK)
```json
{
  "id": 1,
  "name": "Spring Rolls",
  "description": "Crispy rolls",
  "price": 5.0,
  "category": "Appetizer",
  "available": true
}
```

**Response (Item Not Found):** (404 NOT FOUND)
```
(Empty response body)
```

---

### 3. Get Menu Items by Category

**Request:**
```http
GET http://localhost:8080/api/menu/category/Appetizer
```

**Response:** (200 OK)
```json
[
  {
    "id": 1,
    "name": "Spring Rolls",
    "description": "Crispy rolls",
    "price": 5.0,
    "category": "Appetizer",
    "available": true
  },
  {
    "id": 2,
    "name": "Chicken Wings",
    "description": "Spicy wings",
    "price": 7.5,
    "category": "Appetizer",
    "available": true
  }
]
```

**Available Categories:**
- Appetizer
- Main Course
- Dessert
- Beverage

---

### 4. Get Available Items

**Request (Get Available Items):**
```http
GET http://localhost:8080/api/menu/available?available=true
```

**Response:** (200 OK)
```json
[
  {
    "id": 1,
    "name": "Spring Rolls",
    "description": "Crispy rolls",
    "price": 5.0,
    "category": "Appetizer",
    "available": true
  },
  {
    "id": 2,
    "name": "Chicken Wings",
    "description": "Spicy wings",
    "price": 7.5,
    "category": "Appetizer",
    "available": true
  },
  {
    "id": 3,
    "name": "Beef Burger",
    "description": "Grilled beef burger",
    "price": 10.0,
    "category": "Main Course",
    "available": true
  },
  {
    "id": 5,
    "name": "Ice Cream",
    "description": "Vanilla ice cream",
    "price": 4.0,
    "category": "Dessert",
    "available": true
  },
  {
    "id": 7,
    "name": "Coffee",
    "description": "Hot coffee",
    "price": 3.0,
    "category": "Beverage",
    "available": true
  },
  {
    "id": 8,
    "name": "Juice",
    "description": "Fresh juice",
    "price": 3.5,
    "category": "Beverage",
    "available": true
  }
]
```

**Request (Get Unavailable Items):**
```http
GET http://localhost:8080/api/menu/available?available=false
```

**Response:** (200 OK)
```json
[
  {
    "id": 4,
    "name": "Pasta",
    "description": "Creamy pasta",
    "price": 9.0,
    "category": "Main Course",
    "available": false
  },
  {
    "id": 6,
    "name": "Cake",
    "description": "Chocolate cake",
    "price": 6.0,
    "category": "Dessert",
    "available": false
  }
]
```

---

### 5. Search Menu Items by Name

**Request:**
```http
GET http://localhost:8080/api/menu/search?name=chicken
```

**Response:** (200 OK)
```json
[
  {
    "id": 2,
    "name": "Chicken Wings",
    "description": "Spicy wings",
    "price": 7.5,
    "category": "Appetizer",
    "available": true
  }
]
```

**Note:** Search is case-insensitive and performs partial matching.

---

### 6. Add New Menu Item

**Request:**
```http
POST http://localhost:8080/api/menu
Content-Type: application/json

{
  "id": 9,
  "name": "Caesar Salad",
  "description": "Fresh salad with Caesar dressing",
  "price": 8.5,
  "category": "Appetizer",
  "available": true
}
```

**Response:** (201 CREATED)
```json
{
  "id": 9,
  "name": "Caesar Salad",
  "description": "Fresh salad with Caesar dressing",
  "price": 8.5,
  "category": "Appetizer",
  "available": true
}
```

---

### 7. Toggle Menu Item Availability

**Request:**
```http
PUT http://localhost:8080/api/menu/1/availability
```

**Response:** (200 OK)
```json
{
  "id": 1,
  "name": "Spring Rolls",
  "description": "Crispy rolls",
  "price": 5.0,
  "category": "Appetizer",
  "available": false
}
```

**Note:** This endpoint toggles the availability status. If the item was available (true), it becomes unavailable (false), and vice versa.

**Response (Item Not Found):** (404 NOT FOUND)
```
(Empty response body)
```

---

### 8. Delete Menu Item

**Request:**
```http
DELETE http://localhost:8080/api/menu/1
```

**Response:** (204 NO CONTENT)
```
(Empty response body)
```

**Response (Item Not Found):** (404 NOT FOUND)
```
(Empty response body)
```

---

## Testing Screenshots

Below are screenshots demonstrating successful API calls using Postman:

### 1. Get All Menu Items
![Get All Menu Items](screenshotq3/get%20all%20menu%20item.png)

### 2. Get Menu Item by ID
![Get Menu Item by ID](screenshotq3/get%20menu%20item%20by%20id.png)

### 3. Get Menu Items by Category
![Get by Category](screenshotq3/test%20by%20category.png)

### 4. Get Available Items
![Get Available Items](screenshotq3/get%20available%20items.png)

### 5. Search Menu Items by Name
![Search by Name](screenshotq3/search%20by%20name.png)

### 6. Add New Menu Item
![Add New Menu Item](screenshotq3/add%20new%20menu%20item.png)

### 7. Toggle Menu Item Availability
![Update Menu Item](screenshotq3/update%20menu%20item.png)

### 8. Delete Menu Item
![Delete Menu Item](screenshotq3/delete%20menu%20item.png)

---

## Project Structure

```
question3-restaurant-menu-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/question3_restaurant_menu_api/
│   │   │       ├── controller/
│   │   │       │   └── MenuController.java
│   │   │       ├── model/
│   │   │       │   └── MenuItem.java
│   │   │       └── Question3RestaurantMenuApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── screenshotq3/
├── pom.xml
└── README.md
```

## Code Quality Standards

This project follows Java best practices:
- **Meaningful Variable Names**: All variables and methods use descriptive names
- **Java Naming Conventions**: 
  - Classes use PascalCase (e.g., MenuController, MenuItem)
  - Methods and variables use camelCase (e.g., getAllMenuItems, menuItems)
  - Constants would use UPPER_SNAKE_CASE
- **Proper Indentation**: Consistent 4-space indentation throughout
- **Comments**: Added where necessary to explain business logic
- **RESTful Design**: Follows REST API conventions with appropriate HTTP methods and status codes

## HTTP Status Codes Used

- **200 OK**: Successful GET/PUT request
- **201 CREATED**: Successful POST request
- **204 NO CONTENT**: Successful DELETE request
- **404 NOT FOUND**: Resource not found

## Author

Spring Boot Practical Assignment - Question 3

## License

This project is created for educational purposes.
