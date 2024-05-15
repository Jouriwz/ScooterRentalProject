# Scooter Rental Project

This project is a web application designed for managing a fleet of electric scooters for shared use in urban areas. The application allows users to locate, unlock, and use electric scooters via a user-friendly interface. It was developed using Angular for the front end and Spring Boot for the back end as part of the Web Frameworks course.

## Features

- **User Registration and Authentication**: Users can sign up and log in to the application.
- **Scooter Management**: Admin can manage scooter data, including adding new scooters and updating their status.
- **Real-Time Location Tracking**: Users can view the real-time location of available scooters on a map.
- **Trip Management**: Users can start and end trips, with the application calculating the cost based on distance and duration.
- **Notifications**: Real-time notifications about scooter status changes using WebSockets.
- **Account Management**: Users can manage their account balance, view trip history, and handle payments.

## Technologies Used

- **Frontend**: Angular 12, HTML, CSS, TypeScript
- **Backend**: Spring Boot 2.5.2, JPA, Hibernate
- **Database**: H2 (for testing), MySQL (for production)
- **Other**: WebSockets for real-time notifications, JWT for authentication
