# POS System Project

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Postman Documentation](#postman-documentation)
- [Contributing](#contributing)
- [License](#license)

## Overview
This Point of Sale (POS) system is designed to manage customer orders, inventory, and sales transactions efficiently. The system includes functionalities for managing customers, items, orders, and order details, making it suitable for retail businesses.

## Features
- Manage customers (CRUD operations)
- Manage items (CRUD operations)
- Create and manage orders
- Check order details

## Technologies Used
- Java
- Spring Boot
- JPA/Hibernate
- MySQL
- Maven
- Lombok
- Postman (for API testing)

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/deshinikanchana/POS_SpringBoot_V1.git

2. Navigate to the project directory:
    ```bash
   cd POS_SpringBoot_V1
3. Update the application.properties file with your database configuration:
    ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
4. Build the project using Maven:
    ```bash
   mvn clean install

5. Run the application:
    ```bash
   mvn spring-boot:run

# Usage
Once the application is running, you can access it at http://localhost:8080. Here are some example code snippets for using the API endpoints:

## API Endpoints
### - Customers

+ POST /customers - Save a customer
+ GET /customers/{id} - Get a selected customer
+ PUT /customers/{id} - Update a customer
+ DELETE /customers/{id} - Delete a customer


### - Orders

+ POST /orders - Save an order
+ GET /orders/{id} - Get a selected order
+ PUT /orders/{id} - Update an order
+ DELETE /orders/{id} - Delete an order

### - Items

+ POST /items - Save an item
+ GET /items/{id} - Get a selected item
+ PUT /items/{id} - Update an item
+ DELETE /items/{id} - Delete an item

# API Documentation
You can access the detailed API documentation once the application is up and running. 
This documentation provides comprehensive information on the available endpoints and their functionalities.
For an easy way to test the API, you can import the Postman collection available at  [API Documentation](https://documenter.getpostman.com/view/35386217/2sAXxV69vo), 
which includes pre-defined requests for quick experimentation.


# License
This project is licensed under the MIT License. See the [LICENSE](LICENSE.txt) file for details.
    