# 💈 Figaro Salon — Backend Management System & REST API

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-6DB33F?style=flat-square&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk)](https://www.oracle.com/java/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![Spring Security](https://img.shields.io/badge/Spring_Security-JWT_Stateless-6DB33F?style=flat-square&logo=spring-security)](https://spring.io/projects/spring-security)

The robust backend REST API service powering the **Figaro Salon Management System**. Built with Java 21, Spring Boot, Spring Data JPA, and Spring Security with stateless JWT authentication, this service manages authentication, role-based authorization, appointments, stylists, services, and client profiles.

---

## ✨ Key Features

- **🔐 Stateless JWT Authentication & RBAC**: Secure authentication supporting three distinct roles: `CUSTOMER`, `STYLIST`, and `ADMIN`.
- **📅 Appointment Lifecycle Management**: Complete booking workflow, stylist assignments, customer lookups, status transitions (`PENDING`, `CONFIRMED`, `COMPLETED`, `CANCELLED`).
- **✂️ Stylist & Roster Management**: Admin APIs for adding, editing, and managing stylist profiles and specialty tags.
- **💇 Salon Services Catalog**: Dynamic service items with pricing, duration in minutes, and service descriptions.
- **👤 Profile Management**: Self-service user profile and password update endpoints.
- **🌱 Automatic Database Seeding**: Pre-loaded default accounts (Admin, Stylist, Customer), services, and stylists on initial startup.
- **🌐 CORS Configured**: Seamless integration out-of-the-box with the Next.js frontend running on `http://localhost:3000`.

---

## 🛠️ Tech Stack

- **Framework**: [Spring Boot 4.1.0](https://spring.io/projects/spring-boot)
- **Language**: Java 21
- **Persistence**: Spring Data JPA / Hibernate ORM
- **Database**: PostgreSQL
- **Security**: Spring Security + JJWT (JSON Web Token `0.11.5`) + BCrypt password hashing
- **Build Tool**: Maven (with `mvnw` wrapper included)
- **Boilerplate Reduction**: Project Lombok

---

## 📁 Project Architecture

```
salon-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/figaro/salon_management_system/
│   │   │   ├── config/
│   │   │   │   ├── CorsConfig.java           # Cross-Origin configuration (Next.js frontend)
│   │   │   │   ├── DatabaseSeeder.java       # Initial DB population runner
│   │   │   │   └── SecurityConfig.java       # Spring Security filter chain & permissions
│   │   │   ├── controller/
│   │   │   │   ├── AdminStylistController.java # Admin stylist CRUD endpoints
│   │   │   │   ├── AppointmentController.java  # Booking & appointment management endpoints
│   │   │   │   ├── AuthController.java         # Register & login authentication endpoints
│   │   │   │   ├── ServiceController.java      # Public & admin service catalog endpoints
│   │   │   │   ├── StylistController.java      # Public stylist list endpoints
│   │   │   │   └── UserController.java         # Authenticated user profile endpoints
│   │   │   ├── dto/                          # Request & Response Data Transfer Objects
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── LoginResponse.java
│   │   │   │   └── RegisterRequest.java
│   │   │   ├── model/                        # JPA Entities
│   │   │   │   ├── Appointment.java
│   │   │   │   ├── Role.java                 # CUSTOMER, STYLIST, ADMIN
│   │   │   │   ├── ServiceItem.java
│   │   │   │   ├── Stylist.java
│   │   │   │   └── User.java
│   │   │   ├── repository/                   # Spring Data JPA Repositories
│   │   │   │   ├── AppointmentRepository.java
│   │   │   │   ├── ServiceRepository.java
│   │   │   │   ├── StylistRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── security/                     # JWT Utilities & Authentication Filter
│   │   │   │   ├── CustomUserDetailsService.java
│   │   │   │   ├── JwtAuthFilter.java
│   │   │   │   └── JwtUtils.java
│   │   │   ├── service/                      # Business logic layer
│   │   │   │   ├── AppointmentService.java
│   │   │   │   ├── AuthService.java
│   │   │   │   ├── ServiceService.java
│   │   │   │   └── StylistService.java
│   │   │   └── SalonManagementSystemApplication.java
│   │   └── resources/
│   │       └── application.properties        # App properties & database configuration
├── pom.xml                                   # Maven dependencies & build configuration
└── .env                                      # Environment variable definitions
```

---

## 🚦 Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: `21` or higher
- **PostgreSQL**: Installed and running locally or on a cloud provider
- **Maven**: (Optional, Maven wrapper `./mvnw` is included)

### 1. Environment Configuration

Create or update `.env` in the root of `salon-management-system` (or export environment variables in your system):

```env
DB_URL=jdbc:postgresql://localhost:5432/figaro_db
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
SECURITY_USER_NAME=admin
SECURITY_USER_PASSWORD=admin_password
```

Ensure your PostgreSQL instance has a database matching `DB_URL` (e.g., `figaro_db`).

### 2. Build and Run the Application

Using the Maven wrapper:

**Windows (PowerShell / Command Prompt):**
```powershell
.\mvnw.cmd clean spring-boot:run
```

**Linux / macOS:**
```bash
./mvnw clean spring-boot:run
```

The server will start on port `8080` (accessible at `http://localhost:8080`).

---

## 🔑 Default Seeded Accounts

When initialized against a fresh database, `DatabaseSeeder` automatically provisions the following default accounts and data:

| Role | Email | Password | Access / Capabilities |
|---|---|---|---|
| **Admin** | `admin@figarosalon.com` | `admin123` | Full system access, all bookings, analytics, stylist CRUD |
| **Stylist** | `stylist@figarosalon.com` | `stylist123` | Stylist queue, view assigned bookings, update status |
| **Customer** | `customer@figarosalon.com` | `customer123` | Book services, view appointment history, update profile |

*Note: Default services (Haircut & Trim, Hair Coloring, Beard Styling, Blow Wash & Style, Facial Treatment) and stylists (Sarah Connor, Marcus Aurelius, Jane Doe) are also seeded automatically.*

---

## 📡 REST API Reference

All API routes are prefixed with `/api`.

### 1. Authentication (`/api/auth`)
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `POST` | `/api/auth/register` | Public | Register a new customer user account |
| `POST` | `/api/auth/login` | Public | Authenticate user and obtain JWT token |

### 2. Services (`/api/services`)
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `GET` | `/api/services` | Public | List all salon services and pricing |
| `POST` | `/api/services` | Authenticated | Create a new service item |

### 3. Stylists (`/api/stylists` & `/api/admin/stylists`)
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `GET` | `/api/stylists` | Public | List all active stylists and specialties |
| `POST` | `/api/admin/stylists` | `ADMIN` | Create a new stylist |
| `PUT` | `/api/admin/stylists/{id}` | `ADMIN` | Update stylist details |
| `DELETE` | `/api/admin/stylists/{id}` | `ADMIN` | Delete stylist |

### 4. Appointments (`/api/appointments`)
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `POST` | `/api/appointments` | `CUSTOMER`, `ADMIN` | Create a new appointment booking |
| `GET` | `/api/appointments` | `ADMIN` | Fetch all appointments across the salon |
| `GET` | `/api/appointments/customer/{email}` | `CUSTOMER`, `ADMIN` | Get appointments for a specific customer |
| `GET` | `/api/appointments/stylist/{name}` | `STYLIST`, `ADMIN` | Get appointments assigned to a stylist |
| `PUT` | `/api/appointments/{id}/status?status={STATUS}` | `STYLIST`, `ADMIN` | Update status (`CONFIRMED`, `COMPLETED`, `CANCELLED`) |
| `PUT` | `/api/appointments/{id}/assign?stylistId={id}` | `ADMIN` | Assign or reassign stylist to an appointment |

### 5. User Profile (`/api/users`)
| Method | Endpoint | Access | Description |
|---|---|---|---|
| `GET` | `/api/users/profile` | Authenticated | Get current authenticated user details |
| `PUT` | `/api/users/profile` | Authenticated | Update full name and/or password |

---

## 🧪 Testing

Run test suites using the Maven wrapper:

```powershell
.\mvnw.cmd test
```

---

## 📄 License

Part of the Figaro Salon Management System. All rights reserved.
