# Testing Automated Pipeline with JUnit, JMeter, Postman and Selenium.

### Automated Jenkins pipeline with Java-Spring microservice. Developed with JUnit, JMeter load tests, Postman collection and Selenium Chrome Driver.

## Prerequisites:

*  NodeJs: https://nodejs.org/es/download/
*  Newman: npm install -g newman
*  Htmlextra: npm install -g newman-reporter-htmlextra

## Windows

### Compile Code
* ./mvnw.cmd clean compile -e

### Jar Code
* ./mvnw.cmd clean package -e

### Run Jar
* Local:      ./mvnw.cmd spring-boot:run 
* Background: start mvnw.cmd spring-boot:run

### Test Code & JMeter & Selenium Driver
* ./mvnw.cmd verify -Pperformance

### Testing Application
* Abrir navegador: http://localhost:8080/

## Linux

### Compile Code
* ./mvnw clean compile -e

### Jar Code
* ./mvnw clean package -e

### Run Jar
* Local:      ./mvnw spring-boot:run 
* Background: nohup bash mvnw spring-boot:run &

### Test Code & JMeter & Selenium Driver
* ./mvnw.cmd verify -Pperformance

### Testing Application
* Abrir navegador: http://localhost:8080/

Project developed in collaboration with [Grupo3-DevOpsUsach](https://github.com/Grupo3-Devops-Usach/TrabajoFinalM4SWD).
