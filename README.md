# clen678 CS732 Tech Demo Assignment

This is a RESTful api built with Springboot that serves as the backend for a simple shopping list. This api provides functionality for users to register and log in and to then create a shopping list that can be added/appended to and saved into MongoDB using CRUD operations.

## Prerequisites

Below are the prerequisites to run and use the application

- Java Development Kit (JDK17) 
- Maven
- IDE such as VS Code or IntelliJ IDEA (Reccomended)
- Postman HTTP client (Reccomended)

## Setup Instructions
1. Open a terminal and `cd` into the desired directory you wish to contain this application in

2. Clone this repository into your directory with `git clone https://github.com/clen678/cs732-tech-demo.git`

3. Configure environment variables by creating a file called `.env` inside the `tech_demo\api_spring\src\main\resources\` directory. Inside the .env file, include the following: 
    ```
    MONGO_DATABASE="[database name]"
    MONGO_USER="[database user]"
    MONGO_PASSWORD="[database password]"
    MONGO_CLUSTER="[database cluster]"
    ```
    If an .env file is provided, use this instead. Otherwise you must set up a MongoDB Atlas database to generate values for the environment variables. To do this, follow the MongoDB setup instructions below.

4. Navigate to the `tech_demo\api_spring\src\main\java\dev\clen678techdemo\api_spring\` directory and open `ApiSpringApplication.java`

5. Using VS Code or IntelliJ run the application by pressing the "play" or "run" button

## MongoDB Atlas Setup Instructions

guide

## Endpoints

This describes the API endpoints for interacting with the application.

### User Endpoints (`/api/users`)

* **`GET /api/users`**
    * **Description:** Retrieves a list of all users.
    * **Response Body:**
        * Success - HTTP 200 OK
        * Error - HTTP 500 Internal Server Error

* **`GET /api/users/{id}`**
    * **Description:** Retrieves a specific user by their ID.
    * **Path Parameter:**
        * `{id}`: The unique ID (ObjectId in MongoDB) of the user.
    * **Response Body:**
        * Success - HTTP 200 OK (Single User object)
        * Error - HTTP 404 Not Found (Message indicating user not found)

* **`POST /api/users`**
    * **Description:** Creates a new user.
    * **Request Body (application/json):** (Object containing `username` and `password`)
    * **Response Body:**
        * Success - HTTP 201 Created (The newly created User object)
        * Error - HTTP 409 Conflict (Message indicating username already exists)

* **`PATCH /api/users/{id}`**
    * **Description:** Updates an existing user's information.
    * **Path Parameter:**
        * `{id}`: The unique ID (ObjectId in MongoDB) of the user to update.
    * **Request Body (application/json):** (Object containing fields to update, e.g., `username`, `password`)
    * **Response Body:**
        * Success - HTTP 200 OK (The updated User object)
        * Error - HTTP 404 Not Found (Message indicating user not found)

* **`DELETE /api/users/{id}`**
    * **Description:** Deletes a user by their ID.
    * **Path Parameter:**
        * `{id}`: The unique ID (ObjectId in MongoDB) of the user to delete.
    * **Response Body:**
        * Success - HTTP 204 No Content (No response body)
        * Error - HTTP 404 Not Found (Message indicating user not found)

* **`POST /api/users/login`**
    * **Description:** Authenticates a user and logs them in.
    * **Request Body (application/json):** (Object containing `username` and `password`)
    * **Response Body:**
        * Success - HTTP 200 OK (The authenticated User object)
        * Error - HTTP 401 Unauthorized (Message indicating invalid password)
        * Error - HTTP 404 Not Found (Message indicating user not found)

### Shopping List Endpoints (`/api/users/{userId}/items`)

* **`GET /api/users/{userId}/items`**
    * **Description:** Retrieves all shopping items for a given user.
    * **Path Parameter:**
        * `{userId}`: The unique ID (ObjectId in MongoDB) of the user.
    * **Response Body:**
        * Success - HTTP 200 OK (List of ShoppingItem objects)
        * Error - HTTP 404 Not Found (Message indicating user not found)

* **`POST /api/users/{userId}/items`**
    * **Description:** Adds a new shopping item to a specific user's list.
    * **Path Parameter:**
        * `{userId}`: The unique ID (ObjectId in MongoDB) of the user.
    * **Request Body (application/json):** (Object containing `name`, `quantity`, `unit`)
    * **Response Body:**
        * Success - HTTP 201 Created (The newly created ShoppingItem object)
        * Error - HTTP 404 Not Found (Message indicating user not found)
        * Error - HTTP 409 Conflict (Message indicating item name already exists)

* **`DELETE /api/users/{userId}/items/{itemName}`**
    * **Description:** Deletes a specific shopping item from a user's list by its name.
    * **Path Parameters:**
        * `{userId}`: The unique ID (ObjectId in MongoDB) of the user.
        * `{itemName}`: The name of the shopping item to delete.
    * **Response Body:**
        * Success - HTTP 204 No Content (No response body)
        * Error - HTTP 404 Not Found (Message indicating user not found or shopping item not found)

## Packages

* **``tech_demo\api_spring\src\main\java\dev\clen678techdemo\api_spring\config``** This package typically contains configuration classes for the Spring Boot application. These classes are responsible for defining application-wide settings.

* **``tech_demo\api_spring\src\main\java\dev\clen678techdemo\api_spring\controllers``** This package houses the REST API controllers. Controllers are responsible for handling incoming HTTP requests from clients. 

* **``tech_demo\api_spring\src\main\java\dev\clen678techdemo\api_spring\domain``** This package contains the domain model classes, also known as entities. These classes represent the core data structures of your application.

* **``tech_demo\api_spring\src\main\java\dev\clen678techdemo\api_spring\exceptions``** This package contains custom exception classes defined specifically for this application. These are thrown and caught in the service and controller classes.

* **``tech_demo\api_spring\src\main\java\dev\clen678techdemo\api_spring\repositories``** This package contains the data access layer, specifically using Spring Data repositories. Repositories are interfaces that provide methods for interacting with the database.

* **``tech_demo\api_spring\src\main\java\dev\clen678techdemo\api_spring\service``** This package contains the service layer, which implements the business logic of the application. Service classes act as intermediaries between the controllers and the repositories.

* **``tech_demo\api_spring\src\main\resources\``** This package contains non-code resources used by the application: