

# AdvancedMD Backend - Spring Boot JWT Authentication with Spring Security & Spring Data JPA

## Technologies

- **JWT-based Authentication**: Issue tokens for secure communication.
- **Spring Security Integration**: Secures API endpoints.
- **Role-Based Authorization**: Restricts access to endpoints based on user roles (e.g., `DOCTOR`, `ADMIN`).
- **Spring Data JPA**: Simplified database interactions using JPA and Hibernate.
- **User Registration & Login**: Allows users to sign up and log in to get JWT tokens.
- **Database Support**: Pre-configured for MySQL (can be switched to other databases like H2, PostgreSQL, etc.).

## Prerequisites

Before starting, ensure you have the following installed on your machine:

- Java 17 or higher
- MySQL Server 8.0 or higher
- Maven 3.6+
- Docker (optional, if you're using containerized MySQL)



## Project Structure

The project structure follows a typical Maven-based Spring Boot application. Here's a brief overview:

```
.
├── authentication-service/
│   ├── src/
│   └── pom.xml
├── .github/
│   └── workflows/
│       └── build.yml
├── target/
├── pom.xml
└── ...
```

Each service (`authentication-service`, etc.) represents a distinct microservice, following the microservice architecture approach. Additional services can be added depending on the requirements.

## GIThub Setup

 **Clone the repository**:
   ```bash
   git clone https://github.com/sosaidi/AdvancedMD-backend.git  
   ```


## MySQL Configuration

Ensure that MySQL is installed and running on 127.0.0.1:3306. You can either use MySQL installed on your local machine or run MySQL in a Docker container.

### MySQL Setup

1. Create a new MySQL database:

```sql
CREATE DATABASE advancedMD;
```

2. Set up a MySQL user with the necessary privileges, or use the root user:

```sql
CREATE USER 'root'@'localhost' IDENTIFIED BY 'root123456';
GRANT ALL PRIVILEGES ON advancedMD.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

> **Note:** The `spring.jpa.hibernate.ddl-auto=update` setting will automatically update the database schema based on your entities. For production, it is recommended to change this to `validate` or manage migrations manually using tools like Flyway or Liquibase.

## Maven Commands

The project uses Maven as the build tool. Below are some commonly used Maven commands:

- **Build the Project:**

    ```bash
    mvn clean install
    ```

- **Run the Application:**

    ```bash
    mvn spring-boot:run
    ```

- **Run Unit Tests:**

    ```bash
    mvn test
    ```

- **Run Integration Tests:**

    ```bash
    mvn verify
    ```

- **Package the Application as a JAR:**

    ```bash
    mvn clean package
    ```

## Running the Application

After configuring MySQL and running the Maven commands, you can start the Spring Boot application with the following command:

```bash
mvn spring-boot:run
```

The application will be accessible at `http://localhost:8080`.
