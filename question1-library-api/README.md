# Library Management API

A RESTful API built with Spring Boot for managing a library's book collection. This API provides endpoints to perform CRUD operations on books.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [How to Run the Application](#how-to-run-the-application)
- [API Endpoints](#api-endpoints)
- [Sample Requests and Responses](#sample-requests-and-responses)
- [Testing](#testing)

## Technologies Used
- Java 17
- Spring Boot 4.0.2
- Maven
- Spring Web MVC

## Prerequisites
- Java Development Kit (JDK) 17 or higher
- Maven 3.6 or higher
- Postman (for API testing)

## How to Run the Application

### Option 1: Using Maven Wrapper (Recommended)
```bash
# Navigate to the project directory
cd c:\springboot-practicals\question1-library-api

# Run the application using Maven wrapper
mvnw.cmd spring-boot:run
```

### Option 2: Using Maven
```bash
# Navigate to the project directory
cd c:\springboot-practicals\question1-library-api

# Clean and build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Option 3: Using JAR file
```bash
# Build the JAR file
mvn clean package

# Run the JAR file
java -jar target/question1-library-api-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:8080**

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Retrieve all books |
| GET | `/api/books/{id}` | Retrieve a specific book by ID |
| GET | `/api/books/search?title={title}` | Search books by title |
| POST | `/api/books` | Add a new book |
| DELETE | `/api/books/{id}` | Delete a book by ID |

## Sample Requests and Responses

### 1. Get All Books
**Request:**
```http
GET http://localhost:8080/api/books
```

**Response:**
```json
[
    {
        "id": 1,
        "title": "billy journey",
        "author": "mucyo billy",
        "isbn": "978-0132350884",
        "publicationYear": 2008
    },
    {
        "id": 2,
        "title": "Effective Java",
        "author": "gashumba david",
        "isbn": "978-0134685991",
        "publicationYear": 2018
    },
    {
        "id": 3,
        "title": "life principles",
        "author": "murangwa tony",
        "isbn": "978-1617294945",
        "publicationYear": 2018
    }
]
```

**Status Code:** `200 OK`

---

### 2. Get Book by ID
**Request:**
```http
GET http://localhost:8080/api/books/1
```

**Response:**
```json
{
    "id": 1,
    "title": "billy journey",
    "author": "mucyo billy",
    "isbn": "978-0132350884",
    "publicationYear": 2008
}
```

**Status Code:** `200 OK`

**Error Response (Book Not Found):**
```
Status Code: 404 NOT FOUND
```

---

### 3. Search Books by Title
**Request:**
```http
GET http://localhost:8080/api/books/search?title=java
```

**Response:**
```json
[
    {
        "id": 2,
        "title": "Effective Java",
        "author": "gashumba david",
        "isbn": "978-0134685991",
        "publicationYear": 2018
    }
]
```

**Status Code:** `200 OK`

**Note:** Search is case-insensitive and matches partial titles.

---

### 4. Add a New Book
**Request:**
```http
POST http://localhost:8080/api/books
Content-Type: application/json

{
    "id": 4,
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
}
```

**Response:**
```json
{
    "id": 4,
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
}
```

**Status Code:** `201 CREATED`

---

### 5. Delete a Book
**Request:**
```http
DELETE http://localhost:8080/api/books/1
```

**Response:**
```
No content
```

**Status Code:** `204 NO CONTENT`

**Error Response (Book Not Found):**
```
Status Code: 404 NOT FOUND
```

---

## Testing

### Screenshots
API testing screenshots for all endpoints:


![All Endpoints](../screenshotq1/all%20end%20points%20shown.png)

**Delete a Book:**
![Delete Book](../screenshotq1/deleting%20a%20book%20.png)

**Get Book by ID:**
![Get Book By ID](../screenshotq1/get%20by%20id%201.png)
**Add New Book:**


![Add New Book](../screenshotq1/post%20adding%20new%20book.png)


**Get All Books:**
![Postman Collection Run](../screenshotq1/Screenshot%202026-02-08%20115654.png)

### Using Postman
1. Import the provided Postman collection (if available)
2. Ensure the application is running on `http://localhost:8080`
3. Execute each request to test the endpoints

### Manual Testing with cURL

**Get All Books:**
```bash
curl -X GET http://localhost:8080/api/books
```

**Get Book by ID:**
```bash
curl -X GET http://localhost:8080/api/books/1
```

**Search Books:**
```bash
curl -X GET "http://localhost:8080/api/books/search?title=java"
```

**Add New Book:**
```bash
curl -X POST http://localhost:8080/api/books ^
  -H "Content-Type: application/json" ^
  -d "{\"id\":4,\"title\":\"Clean Code\",\"author\":\"Robert C. Martin\",\"isbn\":\"978-0132350884\",\"publicationYear\":2008}"
```

**Delete a Book:**
```bash
curl -X DELETE http://localhost:8080/api/books/1
```

## Project Structure
```
question1-library-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/question1_library_api/
│   │   │       ├── controller/
│   │   │       │   └── BookController.java
│   │   │       ├── model/
│   │   │       │   └── Book.java
│   │   │       └── Question1LibraryApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Code Quality Standards
- Follows Java naming conventions (camelCase for variables/methods, PascalCase for classes)
- Meaningful variable and method names for better code readability
- Proper indentation and code formatting
- Comments added where necessary for clarity
- RESTful API design principles followed
- Appropriate HTTP status codes used for responses

## API Features
- ✅ CRUD operations for book management
- ✅ Search functionality with case-insensitive partial matching
- ✅ Proper HTTP status codes (200, 201, 204, 404)
- ✅ RESTful endpoint design
- ✅ JSON request/response format
- ✅ In-memory data storage with pre-populated sample data


## Author
26853-Mucyo Billy


