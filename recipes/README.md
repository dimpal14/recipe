# Recipes Project

This is a demo project for managing recipes using Spring Boot.

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Running Tests](#running-tests)
- [License](#license)

## Requirements

- Java 17
- Maven
- H2 Database

## Installation

1. Clone the repository:
    ```sh
    cd recipes
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Usage

The application provides a REST API for managing recipes. You can use tools like `curl` or Postman to interact with the API.

## API Endpoints

### Load Recipes

- **URL:** `/recipes/load`
- **Method:** `POST`
- **Params:** `recipesApiUrl` (String)
- **Description:** Loads recipes from the specified API URL.

### Search Recipes

- **URL:** `/recipes/search`
- **Method:** `GET`
- **Params:** `keyword` (String)
- **Description:** Searches for recipes by keyword.

### Get Recipe by ID

- **URL:** `/recipes/{id}`
- **Method:** `GET`
- **Params:** `id` (Long)
- **Description:** Retrieves a recipe by its ID.

### Get All Recipes

- **URL:** `/recipes`
- **Method:** `GET`
- **Params:** `page` (int, default: 0), `size` (int, default: 10)
- **Description:** Retrieves a paginated list of all recipes.

## Running Tests

To run the tests, use the following command:
```sh
mvn test