# SportFlow - Plateforme de Gestion des Clubs et Entraîneurs

## Overview

**SportFlow** is a web application designed for the "SportConnect" sports club to efficiently manage member registrations and training session organization. Built with the **MVC architecture**, it ensures a clear separation between data management, business logic, and the user interface, providing an automated and structured solution for tracking members, coaches, and sessions.

This project was developed as part of **Sprint 3 Brief 5** from **March 10, 2025, to March 14, 2025**.

---

## Features

### Core Functionalities
- **Member Management (CRUD)**
  - Create: Register a new member (Name, Date of Birth, Sport Practiced).
  - Read: View the list of members.
  - Update: Edit member details.
  - Delete: Remove a member.
- **Coach Management (CRUD)**
  - Create: Add a coach (Name, Specialty).
  - Read: View the list of coaches.
  - Update: Edit coach details.
  - Delete: Remove a coach.
- **Training Session Management (CRUD)**
  - Create: Schedule a session (Member ID, Coach ID, Date & Time).
  - Read: View sessions by member or coach.
  - Update: Modify session details.
  - Delete: Cancel a session.

### Security
- **Authentication**: Secure login system using Java EE Filters.
- **Role-Based Access**:
  - Coaches can only view their own sessions.
  - Members can only view their reserved sessions.
- **Session Verification**: Redirects unauthenticated users to the login page.

---

## Technologies Used

- **Backend**: Java EE (Servlets, Filters, JSP, JDBC), Maven
- **Database**: MySQL
- **Frontend**: HTML, CSS, Bootstrap, JavaScript
- **Application Server**: Apache Tomcat
- **Development Tools**: IntelliJ IDEA

---

## Project Structure

The application adheres to the **MVC (Model-View-Controller)** pattern:
- **Model**: Manages entities (Member, Coach, Session) and database interactions via JDBC.
- **View**: Renders the UI using JSP, HTML, CSS, and Bootstrap.
- **Controller**: Handles requests and business logic with Servlets.

### UML Diagrams
The following UML diagrams are included in the `/diagrams` folder:
1. **Class Diagram**: Depicts entities (Member, Coach, Session) and their relationships.
2. **Use Case Diagram**: Shows interactions between actors (Members, Coaches, Admins) and features.
3. **Sequence Diagram**: Illustrates the flow of key processes (e.g., registration, session scheduling).

---

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Tomcat 9.x
- MySQL Server 5.7 or higher
- Maven
- IDE (Eclipse or IntelliJ IDEA)

### Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yassinehaa/sportflow.git
   cd sportflow
