# Student API - Spring Boot REST Application

A RESTful API built with Spring Boot for managing student records. This application provides endpoints to create, read, update, and filter student information.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [How to Run the Application](#how-to-run-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)

## Technologies Used
- Java 17
- Spring Boot 4.0.2
- Spring Web MVC
- Maven

## Prerequisites
- Java Development Kit (JDK) 17 or higher
- Maven 3.6+ (or use the included Maven wrapper)
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## How to Run the Application

### Option 1: Using Maven Wrapper (Recommended)
```bash
# Navigate to project directory
cd c:\springboot-practicals\question2-student-api

# Run the application (Windows)
mvnw.cmd spring-boot:run

# Run the application (Linux/Mac)
./mvnw spring-boot:run
```

### Option 2: Using Maven
```bash
# Navigate to project directory
cd c:\springboot-practicals\question2-student-api

# Clean and build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Option 3: Using IDE
1. Import the project as a Maven project
2. Locate the main class: `Question2StudentApiApplication.java`
3. Right-click and select "Run"

The application will start on **http://localhost:8080**

## API Endpoints

### 1. Get All Students
**Endpoint:** `GET /api/students`

**Description:** Retrieves all students in the system.

**Request:**
```http
GET http://localhost:8080/api/students
```

**Response:**
```json
[
  {
    "studentId": 1,
    "firstName": "Alice",
    "lastName": "Johnson",
    "email": "alice@example.com",
    "major": "Computer Science",
    "gpa": 3.8
  },
  {
    "studentId": 2,
    "firstName": "Bob",
    "lastName": "Smith",
    "email": "bob@example.com",
    "major": "Mathematics",
    "gpa": 3.2
  }
]
```

**Status Code:** `200 OK`

**Screenshot:**

![Get All Students](screenshotq2/getting%20all%20students.png)

---

### 2. Get Student by ID
**Endpoint:** `GET /api/students/{studentId}`

**Description:** Retrieves a specific student by their ID.

**Request:**
```http
GET http://localhost:8080/api/students/1
```

**Response:**
```json
{
  "studentId": 1,
  "firstName": "Alice",
  "lastName": "Johnson",
  "email": "alice@example.com",
  "major": "Computer Science",
  "gpa": 3.8
}
```

**Status Code:** `200 OK` (if found) or `404 NOT FOUND` (if not found)

**Screenshot:**

![Get Student by ID](screenshotq2/getting%20student%20by%20id%20.png)

---

### 3. Get Students by Major
**Endpoint:** `GET /api/students/major/{major}`

**Description:** Retrieves all students enrolled in a specific major.

**Request:**
```http
GET http://localhost:8080/api/students/major/Computer Science
```

**Response:**
```json
[
  {
    "studentId": 1,
    "firstName": "Alice",
    "lastName": "Johnson",
    "email": "alice@example.com",
    "major": "Computer Science",
    "gpa": 3.8
  },
  {
    "studentId": 3,
    "firstName": "Charlie",
    "lastName": "Brown",
    "email": "charlie@example.com",
    "major": "Computer Science",
    "gpa": 3.5
  }
]
```

**Status Code:** `200 OK`

**Screenshot:**

![Get Students by Major](screenshotq2/getting%20student%20by%20major.png)

---

### 4. Filter Students by Minimum GPA
**Endpoint:** `GET /api/students/filter?gpa={minGpa}`

**Description:** Retrieves all students with a GPA greater than or equal to the specified value.

**Request:**
```http
GET http://localhost:8080/api/students/filter?gpa=3.5
```

**Response:**
```json
[
  {
    "studentId": 1,
    "firstName": "Alice",
    "lastName": "Johnson",
    "email": "alice@example.com",
    "major": "Computer Science",
    "gpa": 3.8
  },
  {
    "studentId": 3,
    "firstName": "Charlie",
    "lastName": "Brown",
    "email": "charlie@example.com",
    "major": "Computer Science",
    "gpa": 3.5
  },
  {
    "studentId": 4,
    "firstName": "Diana",
    "lastName": "White",
    "email": "diana@example.com",
    "major": "Physics",
    "gpa": 3.9
  }
]
```

**Status Code:** `200 OK`

**Screenshot:**

![Filter by GPA](screenshotq2/get%20by%20gpa.png)

---

### 5. Add New Student
**Endpoint:** `POST /api/students`

**Description:** Creates a new student record.

**Request:**
```http
POST http://localhost:8080/api/students
Content-Type: application/json

{
  "studentId": 6,
  "firstName": "Frank",
  "lastName": "Green",
  "email": "frank@example.com",
  "major": "Engineering",
  "gpa": 3.7
}
```

**Response:**
```json
{
  "studentId": 6,
  "firstName": "Frank",
  "lastName": "Green",
  "email": "frank@example.com",
  "major": "Engineering",
  "gpa": 3.7
}
```

**Status Code:** `201 CREATED`

**Screenshot:**

![Add New Student](screenshotq2/adding%20new%20student%20.png)

---

### 6. Update Student Information
**Endpoint:** `PUT /api/students/{studentId}`

**Description:** Updates an existing student's information.

**Request:**
```http
PUT http://localhost:8080/api/students/2
Content-Type: application/json

{
  "studentId": 2,
  "firstName": "Bob",
  "lastName": "Smith",
  "email": "bob.smith@example.com",
  "major": "Mathematics",
  "gpa": 3.6
}
```

**Response:**
```json
{
  "studentId": 2,
  "firstName": "Bob",
  "lastName": "Smith",
  "email": "bob.smith@example.com",
  "major": "Mathematics",
  "gpa": 3.6
}
```

**Status Code:** `200 OK` (if found) or `404 NOT FOUND` (if not found)

**Screenshot:**

![Update Student](screenshotq2/update%20student%20information.png)

---

## Testing

### Using Postman
All API endpoints have been tested using Postman. Screenshots of successful API calls are available in the `screenshotq2/` directory.

### Using cURL

**Get All Students:**
```bash
curl http://localhost:8080/api/students
```

**Get Student by ID:**
```bash
curl http://localhost:8080/api/students/1
```

**Add New Student:**
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d "{\"studentId\":6,\"firstName\":\"Frank\",\"lastName\":\"Green\",\"email\":\"frank@example.com\",\"major\":\"Engineering\",\"gpa\":3.7}"
```

**Update Student:**
```bash
curl -X PUT http://localhost:8080/api/students/2 \
  -H "Content-Type: application/json" \
  -d "{\"studentId\":2,\"firstName\":\"Bob\",\"lastName\":\"Smith\",\"email\":\"bob.smith@example.com\",\"major\":\"Mathematics\",\"gpa\":3.6}"
```

## Project Structure
```
question2-student-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/question2_student_api/
│   │   │   ├── controller/
│   │   │   │   └── StudentController.java    # REST API endpoints
│   │   │   ├── model/
│   │   │   │   └── Student.java              # Student entity model
│   │   │   └── Question2StudentApiApplication.java  # Main application class
│   │   └── resources/
│   │       └── application.properties         # Application configuration
│   └── test/
├── screenshotq2/                              # API testing screenshots
├── pom.xml                                    # Maven dependencies
└── README.md                                  # Project documentation
```

## Code Quality Features
- **Meaningful Variable Names:** All variables follow descriptive naming conventions
- **Java Naming Conventions:** Classes use PascalCase, methods use camelCase
- **Proper Indentation:** Consistent code formatting throughout
- **Comments:** Key sections include explanatory comments
- **RESTful Design:** Follows REST API best practices
- **HTTP Status Codes:** Appropriate status codes for different operations

## Sample Data
The application comes pre-loaded with 5 sample students:
1. Alice Johnson - Computer Science (GPA: 3.8)
2. Bob Smith - Mathematics (GPA: 3.2)
3. Charlie Brown - Computer Science (GPA: 3.5)
4. Diana White - Physics (GPA: 3.9)
5. Ethan Black - Chemistry (GPA: 2.9)

## Author
Spring Boot Student API Project

## License
This project is created for educational purposes.
