# Reloadly Email and SMS Notification API

This API is responsible for Sending Email and SMS Notification to customer.

### This project is created using
- Java (Spring Boot)
- In-Memory Database with JPA
- JUnit


### Running the application locally
- mvn clean package (To reinstall plugins)
- mvn spring-boot:run (To start the project)


### Email Notification Request:
```json
{
  "to": "string",
  "subject": "string",
  "body": "string"
}
```

Email notification response
```json
{
  "status": true,
  "message": "Success",
  "data": {
    "id": 47,
    "to": "hello@samsonoyetola.com",
    "subject": "Hi",
    "body": "Hello world",
    "status": "SENT",
    "createdAt": "2021-09-07T13:41:49.039676",
    "updatedAt": "2021-09-07T13:41:49.0397"
  }
}
```

### Sms Notification Request:
```json
{
  "to": "+23439240844",
  "message": "Hello world"
}
```

Sms notification response
```json
{
  "status": true,
  "message": "Success",
  "data": {
    "id": 129,
    "to": "+23439240844",
    "message": "Hello world",
    "status": "SENT",
    "createdAt": "2021-09-07T13:45:27.193016",
    "updatedAt": "2021-09-07T13:45:27.193032"
  }
}
```

## Other Links:

### Customer Account API
> **Github**: https://github.com/samtax01/reloadly-customer-account

> **Heroku**: https://reloadly-quiz-customer-api.herokuapp.com/swagger-ui.html


#### Transaction API
> **Github**: https://github.com/samtax01/reloadly-transaction-api

> **Heroku**: https://reloadly-quiz-transaction-api.herokuapp.com/swagger-ui.html

#### Notification API
> **Github**: https://github.com/samtax01/reloadly-notification-api

> **Heroku**: https://reloadly-quiz-notification-api.herokuapp.com/swagger-ui.html

