# Content Manager Java

Lightweight Java Spring Boot application: content parser, transformer, and REST API with JUnit5 tests and CI/CD ready

## Overview

A Spring Boot-based content management application providing RESTful APIs for managing content data. The application follows a clean MVC architecture with comprehensive test coverage.

## Features

- ✅ Spring Boot 2.7.18 (Java 11 compatible)
- ✅ RESTful API for content CRUD operations
- ✅ Data parsing and transformation capabilities
- ✅ Comprehensive unit and integration tests
- ✅ Maven build system
- ✅ Lombok for boilerplate reduction
- ✅ Spring DevTools for development productivity

## Project Structure

```
content-manager-java/
├── src/
│   ├── main/
│   │   ├── java/com/contentmanager/app/
│   │   │   ├── ContentManagerApplication.java    # Main Spring Boot application
│   │   │   ├── controller/
│   │   │   │   └── ContentController.java        # REST API endpoints
│   │   │   ├── service/
│   │   │   │   └── ContentService.java           # Business logic
│   │   │   └── model/
│   │   │       └── Content.java                  # Data model
│   │   └── resources/
│   │       └── application.properties            # Configuration
│   └── test/
│       └── java/com/contentmanager/app/
│           ├── ContentManagerApplicationTests.java
│           ├── controller/
│           │   └── ContentControllerTest.java
│           └── service/
│               └── ContentServiceTest.java
└── pom.xml                                       # Maven configuration
```

## Requirements

- Java 11 or higher
- Maven 3.6+

## Getting Started

### Build the Project

```bash
mvn clean install
```

### Run Tests

```bash
mvn test
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Package the Application

```bash
mvn clean package
java -jar target/content-manager-java-1.0.0-SNAPSHOT.jar
```

## API Endpoints

### Content Management

- `GET /api/content` - Get all content
- `GET /api/content/{id}` - Get content by ID
- `POST /api/content` - Create new content
- `PUT /api/content/{id}` - Update existing content
- `DELETE /api/content/{id}` - Delete content
- `POST /api/content/parse` - Parse and transform data

### Example Usage

**Create Content:**
```bash
curl -X POST http://localhost:8080/api/content \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Sample Content",
    "description": "This is a sample content",
    "type": "text",
    "data": "Sample data here"
  }'
```

**Get All Content:**
```bash
curl http://localhost:8080/api/content
```

**Parse Data:**
```bash
curl -X POST http://localhost:8080/api/content/parse \
  -H "Content-Type: application/json" \
  -d "hello world"
```

## Technology Stack

- **Framework:** Spring Boot 2.7.18
- **Build Tool:** Maven
- **Testing:** JUnit 5, Spring Test, MockMvc
- **Utilities:** Lombok, Spring DevTools
- **Server:** Embedded Tomcat

## Development

The project uses Spring DevTools for automatic restart during development. Any changes to the code will trigger an automatic restart of the application.

## Testing

The project includes comprehensive tests:
- Unit tests for service layer
- Integration tests for controller layer  
- Application context loading tests

Current test coverage: **20 passing tests**

## License

This project is part of a learning exercise for Java Spring Boot development.
