# Water Crowdsourcing Platform

A **microservices-based web platform** for crowdsourcing reports and feedback on water-related issues (e.g., shortages, quality problems, department complaints). Built with **Spring Boot**, it uses an **API Gateway** for routing, **Eureka** for service discovery, and multiple independent services.

## Project Structure (Microservices)
- **WaterCrowdSourcingApiGateway** → Central entry point (Spring Cloud Gateway)
- **WaterCrowdSourcingServiceRegistry** → Eureka Server for service discovery
- **WaterCrowdSourcingDepartmentService** → Handles department-related logic
- **WaterCrowdSourcingFeedbackService** → Manages user feedback
- **WaterCrowdSourcingImageService** → Image upload/storage for reports
- **WaterCrowdSourcingIssueService** → Core issue reporting & tracking
- **WaterCrowdSourcingReportService** → Generates reports & analytics
- **WaterCrowdSourcingUmsService** → User Management System (authentication/authorization)

## Technologies Used
- Java 17/21 + **Spring Boot 3.x**
- **Spring Cloud** (Gateway, Eureka, OpenFeign)
- **MySQL** / PostgreSQL (or H2 for dev)
- **Maven** for build
- **Docker** (optional for containerization)
- JWT / OAuth (assumed for security in UMS)

## Features
- User registration/login
- Report water issues with photos
- Department-wise assignment & tracking
- Feedback submission
- Image handling & storage
- Admin reports & analytics
- Service discovery & load balancing

## How to Run Locally (Development)
1. **Prerequisites**
   - JDK 17+
   - Maven 3.8+
   - MySQL database (create schema `water_crowdsourcing`)

2. **Start Eureka Server first** (Service Registry)

3. **Start API Gateway**

4. **Start other services** (in separate terminals)
- Department, Feedback, Image, Issue, Report, UMS services similarly: `mvn spring-boot:run`

5. Access the app:
- API Gateway usually runs on `http://localhost:8080` (or check `application.yml`)
- Eureka dashboard: `http://localhost:8761`

## Configuration Notes
- Update `application.yml` in each service with your DB credentials, Eureka URL, etc.
- API routes are prefixed (e.g., `/department/**`, `/feedback/**`) via Gateway.

## Future Improvements
- Add Docker Compose for easy multi-container run
- Implement CI/CD with GitHub Actions
- Add frontend (React/Angular)
- Rate limiting & circuit breaker (Resilience4j)

## Team / Contributors
- **Nishant Mishra** (nishant35mishra) – [Your GitHub profile link]
- DevChavan19 – Repo owner & collaborator

## License
MIT License (or add your preferred license)

Feel free to contribute! Open issues or pull requests.