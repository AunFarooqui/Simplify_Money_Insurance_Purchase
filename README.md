# Simplify_Money_Insurance_Purchase
# Insurance Purchase API

A Spring Boot application for managing insurance purchases and policy document generation.

## 📌 Overview
- REST API for insurance listing/purchasing
- PDF policy document generation
- MySQL database integration
- Curated insurance recommendations based on user demographics

## 🛠️ Tech Stack
- **Backend**: Spring Boot 3.x
- **Database**: MySQL 8.x
- **PDF Generation**: Apache PDFBox 2.x
- **Build Tool**: Maven

## 🚀 Features
- Create insurance purchases
- Generate downloadable PDF policies
- Filter insurances by age/gender/income i.e Show curated insurances based on user details like age, gender, income
- Auto-generated purchase IDs

  ## 📋 API Endpoints

| Method | Endpoint                      | Description                          | Sample Request Body                 |
|--------|-------------------------------|--------------------------------------|--------------------------------------|
| POST   | `/api/insurances/curated`     | Get filtered insurances              | `{"age":25,"gender":"male","income":40000}` |
| POST   | `/api/purchases`              | Create purchase                      | `{"insuranceId":1,"userId":"user123"}` |
| GET    | `/api/purchases/{id}/policy`  | Download policy PDF                  | I have uploaded policy-1.pdf as an example  |
| GET    | `/api/insurances`             | Get all the available Insurances     | `{'Women health', 'Health', 100000.00, 18, 50, 'female', 50000}`  |

## application.yaml configuration
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/insurance_db
    username: your_username
    password: your_password


#I have used postman to test all the api endpoints

🧗 Challenges Faced
-null purchase_id problem while generating pdf faced hours of challenge as I could not use purchase_Id to generate pdf
File Path Handling

-Issue: PDF storage path differed between dev/prod environments
Solution: Used @Value("${policy.document.path}") with profile-specific configs

-Text Positioning
Issue: Manual coordinate calculations for text placement
Solution: Created helper methods for consistent line spacing

-Use of transactional
Essential when performing multiple DB operations in a single method (like saving a purchase and updating related data)
used it to basically perform save operation before generating pdf

-initially I thought It would be an easy developement but the problems I faced while generating pdf as I was not using h2 db for In 
memory storage was immense and solving it took time and help.

