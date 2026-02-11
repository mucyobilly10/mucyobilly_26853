# Question 6 - User Profile API

A simple Spring Boot REST API for managing user profiles. Data is stored in-memory (sample data loaded at startup).

## Base URL

`http://localhost:8080/api/users`

## Endpoints

- `GET /api/users` — Get all user profiles
- `GET /api/users/{userId}` — Get a user by ID
- `GET /api/users/search/username?username={username}` — Search user by username
- `GET /api/users/search/country/{country}` — Search users by country
- `GET /api/users/search/age-range?min={min}&max={max}` — Search users by age range
- `GET /api/users/active` — Get active users only
- `POST /api/users` — Create a new user profile
- `PUT /api/users/{userId}` — Update a user profile
- `PATCH /api/users/{userId}/activate` — Activate a user profile
- `PATCH /api/users/{userId}/deactivate` — Deactivate a user profile
- `DELETE /api/users/{userId}` — Delete a user profile

## Sample Requests and Responses

### 1) Get all users

Request:

```bash
curl -X GET http://localhost:8080/api/users
```

Response (200 OK):

```json
{
  "success": true,
  "message": "User profiles retrieved successfully",
  "data": [
    {
      "userId": 1,
      "username": "john_doe",
      "email": "john@example.com",
      "fullName": "John Doe",
      "age": 25,
      "country": "USA",
      "bio": "Software developer passionate about coding",
      "active": true
    }
  ]
}
```

### 2) Get user by ID

Request:

```bash
curl -X GET http://localhost:8080/api/users/1
```

Response (200 OK):

```json
{
  "success": true,
  "message": "User profile found",
  "data": {
    "userId": 1,
    "username": "john_doe",
    "email": "john@example.com",
    "fullName": "John Doe",
    "age": 25,
    "country": "USA",
    "bio": "Software developer passionate about coding",
    "active": true
  }
}
```

### 3) Search by username

Request:

```bash
curl -X GET "http://localhost:8080/api/users/search/username?username=jane_smith"
```

Response (200 OK):

```json
{
  "success": true,
  "message": "User found by username",
  "data": {
    "userId": 2,
    "username": "jane_smith",
    "email": "jane@example.com",
    "fullName": "Jane Smith",
    "age": 30,
    "country": "Canada",
    "bio": "Data scientist and AI enthusiast",
    "active": true
  }
}
```

### 4) Search by country

Request:

```bash
curl -X GET http://localhost:8080/api/users/search/country/USA
```

Response (200 OK):

```json
{
  "success": true,
  "message": "Found 3 user(s) from USA",
  "data": [
    {
      "userId": 1,
      "username": "john_doe",
      "email": "john@example.com",
      "fullName": "John Doe",
      "age": 25,
      "country": "USA",
      "bio": "Software developer passionate about coding",
      "active": true
    }
  ]
}
```

### 5) Search by age range

Request:

```bash
curl -X GET "http://localhost:8080/api/users/search/age-range?min=24&max=30"
```

Response (200 OK):

```json
{
  "success": true,
  "message": "Found 5 user(s) in age range 24-30",
  "data": [
    {
      "userId": 1,
      "username": "john_doe",
      "email": "john@example.com",
      "fullName": "John Doe",
      "age": 25,
      "country": "USA",
      "bio": "Software developer passionate about coding",
      "active": true
    }
  ]
}
```

### 6) Get active users

Request:

```bash
curl -X GET http://localhost:8080/api/users/active
```

Response (200 OK):

```json
{
  "success": true,
  "message": "Active users retrieved successfully",
  "data": [
    {
      "userId": 1,
      "username": "john_doe",
      "email": "john@example.com",
      "fullName": "John Doe",
      "age": 25,
      "country": "USA",
      "bio": "Software developer passionate about coding",
      "active": true
    }
  ]
}
```

### 7) Create user

Request:

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"new_user\",\"email\":\"new@example.com\",\"fullName\":\"New User\",\"age\":26,\"country\":\"USA\",\"bio\":\"New profile\",\"active\":true}"
```

Response (201 Created):

```json
{
  "success": true,
  "message": "User profile created successfully",
  "data": {
    "userId": 9,
    "username": "new_user",
    "email": "new@example.com",
    "fullName": "New User",
    "age": 26,
    "country": "USA",
    "bio": "New profile",
    "active": true
  }
}
```

### 8) Update user

Request:

```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"john_doe\",\"email\":\"john@newmail.com\",\"fullName\":\"John Doe\",\"age\":26,\"country\":\"USA\",\"bio\":\"Updated bio\",\"active\":true}"
```

Response (200 OK):

```json
{
  "success": true,
  "message": "User profile updated successfully",
  "data": {
    "userId": 1,
    "username": "john_doe",
    "email": "john@newmail.com",
    "fullName": "John Doe",
    "age": 26,
    "country": "USA",
    "bio": "Updated bio",
    "active": true
  }
}
```

### 9) Activate user

Request:

```bash
curl -X PATCH http://localhost:8080/api/users/4/activate
```

Response (200 OK):

```json
{
  "success": true,
  "message": "User profile activated successfully",
  "data": {
    "userId": 4,
    "username": "sarah_williams",
    "email": "sarah@example.com",
    "fullName": "Sarah Williams",
    "age": 28,
    "country": "Australia",
    "bio": "Full-stack developer and tech blogger",
    "active": true
  }
}
```

### 10) Deactivate user

Request:

```bash
curl -X PATCH http://localhost:8080/api/users/1/deactivate
```

Response (200 OK):

```json
{
  "success": true,
  "message": "User profile deactivated successfully",
  "data": {
    "userId": 1,
    "username": "john_doe",
    "email": "john@example.com",
    "fullName": "John Doe",
    "age": 25,
    "country": "USA",
    "bio": "Software developer passionate about coding",
    "active": false
  }
}
```

### 11) Delete user

Request:

```bash
curl -X DELETE http://localhost:8080/api/users/1
```

Response (200 OK):

```json
{
  "success": true,
  "message": "User profile deleted successfully",
  "data": null
}
```

## How to Run

### Prerequisites

- Java 17+ (or the version configured in the project)
- Maven (or use the Maven wrapper included in the project)

### Run with Maven Wrapper (recommended)

```bash
./mvnw spring-boot:run
```

### Run with Maven

```bash
mvn spring-boot:run
```

The application starts on port `8080` by default.

## Testing Evidence (Screenshots)

### Get all users

![Get all users](screenshotq6/get%20all%20users.png)

### Get user by ID

![Get user by ID](screenshotq6/get%20by%20user%20id.png)

### Search by username

![Search by username](screenshotq6/search%20by%20user%20name.png)

### Search by country

![Search by country](screenshotq6/search%20by%20country.png)

### Search by age range

![Search by age range](screenshotq6/search%20by%20age%20range.png)

### Get active users

![Get active users](screenshotq6/get%20active%20users.png)

### Create new user

![Create new user](screenshotq6/create%20new%20user.png)

### Update user

![Update user](screenshotq6/update%20user.png)

### Activate user

![Activate user](screenshotq6/activate%20user.png)

### Deactivate user

![Deactivate user](screenshotq6/deactivate%20user.png)

### Delete user

![Delete user](screenshotq6/delete%20user.png)

## Code Quality Notes

- Meaningful variable names are used throughout the controller and model classes.
- Comments are added where helpful for clarity.
- Java naming conventions and proper indentation are followed.
