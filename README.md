# Spring Proof of Concept (PoC) Application

This is a proof of concept (PoC) application built using the Spring framework. It serves as a starting point and demonstration of various Spring features and best practices.

## Features

1. **Dependency Injection:** The application leverages Spring's powerful dependency injection mechanism to manage the dependencies between various components. This promotes loose coupling and easier unit testing.

2. **Spring Data:** The application integrates Spring Data, which provides a simplified and consistent way to interact with different data sources. Spring Data reduces boilerplate code and supports various databases and data access technologies.

3. **Spring MVC:** The application utilizes Spring MVC, a robust framework for building web applications. Spring MVC provides a flexible and scalable architecture for developing RESTful APIs and handling HTTP requests and responses.

4. **REST Support:** The application focuses on building RESTful services using Spring MVC. It demonstrates the implementation of REST endpoints, request handling, content negotiation, and response serialization.

5. **Aspect-Oriented Programming (AOP):** The application showcases the use of Spring's AOP capabilities. AOP allows the modularization of cross-cutting concerns such as logging, security, and performance monitoring. It enhances code modularity and improves maintainability.

## Technologies

- Spring Boot
- Spring MVC
- Spring Data
- Aspect-Oriented Programming (AOP)

## Usage
How to run

- Install jdk17, can be downloaded from oracles official website https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
- Clone the repo
- Run the application
- Use Postman to test the apis


1_Spring_DI showcases dependency Injection via a configuration file but also some Rest Implementation via Spring MVC.

2_Spring_Data showcases the difference in size and structure for the data repositories using JDBCTemplate. This also showcases dependecy injection via annotations and AOP via the ExceptionHandler and the LoggingAspect.

Provide instructions on how to run and use the application.
