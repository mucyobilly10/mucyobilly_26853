# RESTful API Assignment - Product CRUD

This project is a Spring Boot + PostgreSQL REST API for managing products.

## What Was Implemented

1. Created full CRUD operations for `Product`.
2. Configured PostgreSQL connection.
3. Set server port to `8089`.
4. Fixed `405 Method Not Allowed` issue by allowing create endpoint on:
   - `POST /api/products`
   - `POST /api/products/`
   - `POST /api/products/addProduct`
5. Verified CRUD behavior through Postman and direct PostgreSQL queries.

## Tech Stack

- Java + Spring Boot
- Spring Data JPA
- PostgreSQL
- Postman (for API testing)

## Project Configuration

From `src/main/resources/application.properties`:

- `spring.datasource.url=jdbc:postgresql://localhost:5432/restapi_db`
- `spring.datasource.username=postgres`
- `spring.datasource.password=mucyo10`
- `server.port=8089`
- `spring.jpa.hibernate.ddl-auto=update`

## Product API Endpoints

Base URL: `http://localhost:8089/api/products`

1. Create Product  
   `POST /api/products` (also supports `/api/products/` and `/api/products/addProduct`)
2. Get All Products  
   `GET /api/products`
3. Get Product By ID  
   `GET /api/products/{id}`
4. Update Product  
   `PUT /api/products/{id}`
5. Delete Product  
   `DELETE /api/products/{id}`

## Sample JSON (Create / Update)

```json
{
  "id": 1,
  "name": "Phone",
  "description": "Smartphone",
  "price": 350.0,
  "category": "Electronics",
  "stockQuantity": 10
}
```

## How CRUD Was Verified in PostgreSQL

1. Confirm active database/schema:
```sql
SELECT current_database(), current_schema();
```

2. Check inserted record:
```sql
SELECT * FROM public.product WHERE id = 9002;
```

3. Confirm update:
```sql
SELECT id, name, description, price, category, stock_quantity
FROM public.product
WHERE id = 9002;
```

4. Delete check:
```sql
DELETE FROM public.product WHERE id = 9002;
SELECT * FROM public.product WHERE id = 9002;
```

## Important Troubleshooting Notes

1. `405 Method Not Allowed` happened when posting to `/api/products` while create mapping only allowed `/addProduct`.  
   Fixed by mapping create method to both base and legacy path.

2. `ERROR: relation "product" does not exist` happened when querying the wrong DB/schema.  
   Correct table location is `public.product` in `restapi_db`.

## Screenshots (Evidence)

### Postman Tests

![Create product in Postman](../screenshots/create%20new%20product%20using%20postman.png)

![Get all products in Postman](../screenshots/postman%20get%20all%20product%20png.png)

![Update successful in Postman](../screenshots/update%20succesfull%20in%20postman.png)

### PostgreSQL Verification

![Select by id](../screenshots/select%20from%20id%20.png)

![Select by id (second check)](../screenshots/select%20from%20id%202%20.png)

![Check created product in Postgres](../screenshots/check%20created%20product%20in%20postgres.png)

![Check update in Postgres](../screenshots/check%20update%20in%20postgres.png)

![Delete query in Postgres](../screenshots/delete%20query%20in%20postgres.png)

![Check deleted product](../screenshots/checking%20the%20deleted%20product.png)

## Current Status

- Product CRUD API is implemented.
- Endpoints are reachable on port `8089`.
- Data is persisted in PostgreSQL (`restapi_db`) and verified through SQL queries.
