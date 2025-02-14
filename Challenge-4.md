# Challenge 4 – Convert powershellscript to bash

## 1. Setup Your Development Environment

### Setup vscode with powershell extension:
- Use IntelliJ IDEA, or Visual Studio Code (with Java support and Copilot enabled).

## 2. Create an empty folder for the challenge if it not exists
e.g. challenge-4

## 3. create new ps1. file
e.g. start.ps1

## 4. let copilot create our starting point
ask copilot in the new file to create a new powershell function, which calculates for a given file (input param is the path to the file) 
the CRC32 checksum and returns the checksum as a string 

## 3. Add Required Dependencies

Dependencies are external libraries that your project needs to function. They are managed through `pom.xml` for Maven or `build.gradle` for Gradle.

### Spring Boot:
- Add Spring Boot, a framework for building RESTful APIs in Java.
- For Maven, add the `spring-boot-starter-web` dependency to your `pom.xml`.
- Spring Boot simplifies application development by providing pre-configured templates.

## 4. Create the Model Class for the Following JSON

```json
[
    {
        "id": "1",
        "name": "Laptop",
        "description": "A high-performance laptop suitable for gaming and work.",
        "price": 1200.00
    },
    {
        "id": "2",
        "name": "Smartphone",
        "description": "A latest-generation smartphone with a large display and powerful camera.",
        "price": 800.00
    },
    {
        "id": "3",
        "name": "Wireless Headphones",
        "description": "Noise-cancelling wireless headphones with long battery life.",
        "price": 200.00
    },
    {
        "id": "4",
        "name": "Smartwatch",
        "description": "A smartwatch with fitness tracking and customizable watch faces.",
        "price": 150.00
    },
    {
        "id": "5",
        "name": "Tablet",
        "description": "A lightweight tablet with a sharp display, ideal for reading and browsing.",
        "price": 300.00
    }
]
```
### 5. Read and Write to the JSON file 
### 6. Perform CRUD operations using REST Controller 
GET, PUT, POST, DELETE using the annotations 
•	GET /api/items: Retrieve all items. 
•	GET /api/items/{id}: Retrieve an item by its ID. 
•	POST /api/items: Create a new item. 
•	PUT /api/items/{id}: Update an existing item. 
•	DELETE /api/items/{id}: Delete an item. 
### 7. Add Exception handling
### 8. You are free to use a database instead of a json file for crud operations.
 
## Challenge 4 – Improve UI
1.	Display the data In the table format on UI 
2.	Perform CRUD operations 

## Challenge 5 – Add Test Cases
1.	Write the test cases for the GET, POST, PUT and DELETE operations 
2.	Include edge cases and null conditions 
3.	Run the test cases 


![image](https://github.com/user-attachments/assets/81b38ee8-78ef-4139-8217-fbf052a34f20)
