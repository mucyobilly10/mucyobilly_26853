# Task Management API

A RESTful API built with Spring Boot for managing tasks with features like priority levels, completion status, and due dates.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [How to Run the Application](#how-to-run-the-application)
- [API Endpoints](#api-endpoints)
- [Sample Requests and Responses](#sample-requests-and-responses)
- [Testing Screenshots](#testing-screenshots)

## Features

- Create, read, update, and delete tasks
- Filter tasks by completion status
- Filter tasks by priority level (LOW, MEDIUM, HIGH)
- Mark tasks as completed
- Track task due dates
- RESTful API design with proper HTTP methods and status codes

## Technologies Used

- **Java 17**
- **Spring Boot 4.0.2**
- **Spring Web MVC**
- **Maven** - Dependency management

## How to Run the Application

### Prerequisites
- Java 17 or higher installed
- Maven installed (or use the included Maven wrapper)

### Steps to Run

1. **Clone or navigate to the project directory:**
   ```bash
   cd c:\springboot-practicals\question5-task-management-api
   ```

2. **Build the project using Maven:**
   ```bash
   mvnw clean install
   ```
   Or on Unix/Linux/Mac:
   ```bash
   ./mvnw clean install
   ```

3. **Run the application:**
   ```bash
   mvnw spring-boot:run
   ```
   Or on Unix/Linux/Mac:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **The application will start on port 8080:**
   ```
   http://localhost:8080
   ```

5. **Test the API using Postman, cURL, or any REST client**

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{taskId}` | Get a specific task by ID |
| GET | `/api/tasks/status?completed={true/false}` | Get tasks by completion status |
| GET | `/api/tasks/priority/{priority}` | Get tasks by priority (LOW, MEDIUM, HIGH) |
| POST | `/api/tasks` | Create a new task |
| PUT | `/api/tasks/{taskId}` | Update an existing task |
| PATCH | `/api/tasks/{taskId}/complete` | Mark a task as completed |
| DELETE | `/api/tasks/{taskId}` | Delete a task |

## Sample Requests and Responses

### 1. GET /api/tasks - Get All Tasks

**Request:**
```http
GET http://localhost:8080/api/tasks
```

**Response:** `200 OK`
```json
[
  {
    "taskId": 1,
    "title": "Complete Spring Boot assignment",
    "description": "Finish all 5 questions for the REST API assignment",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-15"
  },
  {
    "taskId": 2,
    "title": "Buy groceries",
    "description": "Get milk, eggs, bread, and vegetables",
    "completed": false,
    "priority": "MEDIUM",
    "dueDate": "2026-02-11"
  }
]
```

### 2. GET /api/tasks/{taskId} - Get Task by ID

**Request:**
```http
GET http://localhost:8080/api/tasks/1
```

**Response:** `200 OK`
```json
{
  "taskId": 1,
  "title": "Complete Spring Boot assignment",
  "description": "Finish all 5 questions for the REST API assignment",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-15"
}
```

**Response (Task Not Found):** `404 NOT FOUND`
```json
null
```

### 3. GET /api/tasks/status?completed={true/false} - Get Tasks by Status

**Request:**
```http
GET http://localhost:8080/api/tasks/status?completed=false
```

**Response:** `200 OK`
```json
[
  {
    "taskId": 1,
    "title": "Complete Spring Boot assignment",
    "description": "Finish all 5 questions for the REST API assignment",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-15"
  },
  {
    "taskId": 2,
    "title": "Buy groceries",
    "description": "Get milk, eggs, bread, and vegetables",
    "completed": false,
    "priority": "MEDIUM",
    "dueDate": "2026-02-11"
  }
]
```

### 4. GET /api/tasks/priority/{priority} - Get Tasks by Priority

**Request:**
```http
GET http://localhost:8080/api/tasks/priority/HIGH
```

**Response:** `200 OK`
```json
[
  {
    "taskId": 1,
    "title": "Complete Spring Boot assignment",
    "description": "Finish all 5 questions for the REST API assignment",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-15"
  },
  {
    "taskId": 3,
    "title": "Study for exam",
    "description": "Review chapters 5-8 for database exam",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-14"
  }
]
```

### 5. POST /api/tasks - Create a New Task

**Request:**
```http
POST http://localhost:8080/api/tasks
Content-Type: application/json

{
  "title": "Prepare presentation",
  "description": "Create slides for project demo",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-16"
}
```

**Response:** `201 CREATED`
```json
{
  "taskId": 9,
  "title": "Prepare presentation",
  "description": "Create slides for project demo",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-16"
}
```

### 6. PUT /api/tasks/{taskId} - Update a Task

**Request:**
```http
PUT http://localhost:8080/api/tasks/1
Content-Type: application/json

{
  "title": "Complete Spring Boot assignment - Updated",
  "description": "Finish all 5 questions and add documentation",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-16"
}
```

**Response:** `200 OK`
```json
{
  "taskId": 1,
  "title": "Complete Spring Boot assignment - Updated",
  "description": "Finish all 5 questions and add documentation",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-16"
}
```

**Response (Task Not Found):** `404 NOT FOUND`
```json
null
```

### 7. PATCH /api/tasks/{taskId}/complete - Mark Task as Completed

**Request:**
```http
PATCH http://localhost:8080/api/tasks/1/complete
```

**Response:** `200 OK`
```json
{
  "taskId": 1,
  "title": "Complete Spring Boot assignment",
  "description": "Finish all 5 questions for the REST API assignment",
  "completed": true,
  "priority": "HIGH",
  "dueDate": "2026-02-15"
}
```

**Response (Task Not Found):** `404 NOT FOUND`
```json
null
```

### 8. DELETE /api/tasks/{taskId} - Delete a Task

**Request:**
```http
DELETE http://localhost:8080/api/tasks/1
```

**Response:** `204 NO CONTENT`
```
(No response body)
```

**Response (Task Not Found):** `404 NOT FOUND`
```
(No response body)
```

## Testing Screenshots

All API endpoints have been tested using Postman. Screenshots of successful API calls are available in the `screenshotq5` folder:

### 1. Get All Tasks
![Get All Tasks](screenshotq5/get%20all%20tasks.png)

### 2. Get Task by ID
![Get Task by ID](screenshotq5/get%20all%20task%20by%20id.png)

### 3. Get Tasks by Status
![Get Tasks by Status](screenshotq5/task%20by%20status.png)

### 4. Get Tasks by Priority
![Get Tasks by Priority](screenshotq5/all%20task%20by%20priority.png)

### 5. Create New Task
![Create New Task](screenshotq5/creating%20new%20task.png)

### 6. Update Task
![Update Task](screenshotq5/update%20task.png)

### 7. Mark Task as Completed
![Mark Task Completed](screenshotq5/mark%20task%20completed%20.png)

### 8. Delete Task
![Delete Task](screenshotq5/delete%20task.png)

## Project Structure

```
question5-task-management-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/question5_task_management_api/
│   │   │       ├── controller/
│   │   │       │   └── TaskController.java
│   │   │       ├── model/
│   │   │       │   └── Task.java
│   │   │       └── Question5TaskManagementApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── screenshotq5/
├── pom.xml
└── README.md
```

## Code Quality Standards

This project follows Java best practices:

- **Meaningful Variable Names**: All variables use descriptive names (e.g., `taskList`, `taskId`, `updatedTask`)
- **Java Naming Conventions**: 
  - Classes use PascalCase (e.g., `TaskController`, `Task`)
  - Methods use camelCase (e.g., `getAllTasks`, `createTask`)
  - Constants would use UPPER_SNAKE_CASE
- **Proper Indentation**: Consistent 4-space indentation throughout
- **Comments**: Added where necessary to explain business logic
- **RESTful Design**: Proper use of HTTP methods and status codes
- **Clean Code**: Single responsibility principle applied to methods

## API Design Principles

- **RESTful conventions** followed for all endpoints
- **Proper HTTP status codes**:
  - `200 OK` - Successful GET/PUT/PATCH
  - `201 CREATED` - Successful POST
  - `204 NO CONTENT` - Successful DELETE
  - `404 NOT FOUND` - Resource not found
- **Consistent response format** across all endpoints
- **Query parameters** for filtering operations
- **Path variables** for resource identification

## Author

Spring Boot REST API Assignment - Question 5

## License

This project is created for educational purposes.
