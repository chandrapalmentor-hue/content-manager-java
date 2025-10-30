# Content Manager Java

Lightweight Java 11 data-processing library: parser, transformer, and CLI with JUnit5 tests and GitHub Actions CI

## Overview

Content Manager is a Spring Boot-based application that provides content processing capabilities including parsing, transformation, and REST API endpoints for content management.

## Features

- **Content Parsing**: Parse raw content into structured Content objects
- **Content Transformation**: Transform and process content data
- **REST API**: RESTful endpoints for content processing
- **CLI Support**: Command-line interface for batch processing
- **Comprehensive Testing**: Full test coverage with JUnit5
- **CI/CD**: GitHub Actions workflow for automated build and test

## Technology Stack

- **Java 11**: Core programming language
- **Spring Boot 2.7.18**: Application framework
- **Maven**: Build and dependency management
- **JUnit5**: Testing framework
- **Mockito**: Mocking framework for tests
- **Lombok**: Reducing boilerplate code

## Project Structure

```
content-manager-java/
├── src/
│   ├── main/
│   │   ├── java/com/contentmanager/
│   │   │   ├── ContentManagerApplication.java    # Main application entry point
│   │   │   ├── model/
│   │   │   │   └── Content.java                  # Content data model
│   │   │   ├── parser/
│   │   │   │   └── ContentParser.java            # Content parsing logic
│   │   │   ├── transformer/
│   │   │   │   └── ContentTransformer.java       # Content transformation logic
│   │   │   ├── service/
│   │   │   │   └── ContentService.java           # Business logic layer
│   │   │   ├── controller/
│   │   │   │   └── ContentController.java        # REST API endpoints
│   │   │   └── cli/
│   │   │       └── ContentCLI.java               # Command-line interface
│   │   └── resources/
│   │       └── application.properties            # Application configuration
│   └── test/
│       └── java/com/contentmanager/
│           ├── ContentManagerApplicationTests.java
│           ├── parser/ContentParserTest.java
│           ├── transformer/ContentTransformerTest.java
│           ├── service/ContentServiceTest.java
│           └── controller/ContentControllerTest.java
├── pom.xml                                       # Maven configuration
└── .github/workflows/
    └── maven-build.yml                           # CI/CD workflow
```

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Building the Project

```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Build JAR package
mvn clean package
```

### Running the Application

```bash
# Run the application
java -jar target/content-manager-java-1.0.0-SNAPSHOT.jar

# Or use Maven
mvn spring-boot:run
```

The application will start on port 8080 by default.

## API Endpoints

### Health Check
```bash
GET http://localhost:8080/api/content/health
```

### Process Content
```bash
POST http://localhost:8080/api/content/process
Content-Type: text/plain

This is sample content to process
```

### Process Content with Format
```bash
POST http://localhost:8080/api/content/process/{format}
Content-Type: text/plain

This is sample content to process
```

## Testing

The project includes comprehensive unit tests for all components:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ContentParserTest

# Run with coverage
mvn clean test jacoco:report
```

## Configuration

Application configuration can be modified in `src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=8080

# Logging Configuration
logging.level.root=INFO
logging.level.com.contentmanager=DEBUG
```

## CI/CD

The project uses GitHub Actions for continuous integration. The workflow:
- Builds the project with Maven
- Runs all unit tests
- Generates test reports
- Creates JAR artifacts

## Development

### Code Style
- Follow Java naming conventions
- Use Lombok to reduce boilerplate code
- Write tests for all new features

### Adding New Features
1. Create feature branch
2. Implement feature with tests
3. Run `mvn clean test` to verify
4. Submit pull request

## License

This project is open source and available under standard licensing terms.

## Contributors

See GitHub contributors for the list of contributors to this project.
