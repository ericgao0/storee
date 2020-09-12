# storee
Storee, an ecommerce application.

## Clone Project

```bash
$ git clone https://github.com/julianjupiter/storee.git
```

## Create Database

Copy SQL script from `database.sql` and execute it on MySQL.

```sql
CREATE DATABASE storee;
USE storee;
CREATE TABLE IF NOT EXISTS product (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	description TEXT,
	price DECIMAL(19,2) NOT NULL,
	quantity BIGINT NOT NULL,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP,
	CONSTRAINT pk_product_id PRIMARY KEY(id)
);
```

## Product Service

```bash
$ cd storee-product-service
$ ./mvnw package 
$ java -jar ./target/storee-product-service-0.0.1-SNAPSHOT.jar
```

### Test

- Get all products

GET: http://localhost:7000/storee/api/v1/products

- Create a product

POST: http://localhost:7000/storee/api/v1/products

Body:

```json
{
	"name": "Samsung Galaxy S20",
	"description": "Samsung Galaxy S20",
	"price": 48000.00,
	"quantity": 28
}
 ```

- Get a product by ID, e.g. 1

GET: http://localhost:7000/storee/api/v1/products/1

- Update a product by ID, e.g. 1

PUT: http://localhost:7000/storee/api/v1/products/1

Body:

```json
{
	"name": "Samsung Galaxy S20",
	"description": "Samsung Galaxy S20",
	"price": 48000.00,
	"quantity": 30
}
 ```

- Delete a product by ID, e.g. 1

DELETE: http://localhost:7000/storee/api/v1/products/1