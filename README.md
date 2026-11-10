# LearnGo Backend - Software Increment 1

Spring Boot 3.3 / Java 17 / MySQL. Entities in this increment: **Course** (parent) and **Lesson** (child).
Maven: `com.csit321section.groupname` : `projectname`.

## 1. Database
Install MySQL 8 and make sure it runs on port 3306. The app creates the `learngo_db` database and tables automatically
(`createDatabaseIfNotExist=true` + `ddl-auto=update`). To create it manually instead:

    CREATE DATABASE learngo_db;

Edit `src/main/resources/application.properties` if your MySQL username/password differ (default: `root` / empty).

## 2. Run
    cd projectname
    mvn clean install
    mvn spring-boot:run

Base URL: `http://localhost:8080/api`

## 3. Postman endpoints
| Method | URL | Body | Success |
|---|---|---|---|
| POST | /api/courses | `{"code":"CSIT321","title":"Applications Development","description":"..."}` | 201 |
| GET | /api/courses | - | 200 |
| GET | /api/courses/{id} | - | 200 |
| GET | /api/courses/{id}/lessons | - | 200 |
| PUT | /api/courses/{id} | same as POST | 200 |
| DELETE | /api/courses/{id} (also deletes its lessons) | - | 204 |
| POST | /api/lessons | `{"courseId":1,"title":"Intro","content":"...","orderNo":1}` | 201 |
| GET | /api/lessons | - | 200 |
| GET | /api/lessons/{id} | - | 200 |
| PUT | /api/lessons/{id} | same as POST | 200 |
| DELETE | /api/lessons/{id} | - | 204 |

Errors: 400 validation/bad JSON, 404 not found, 409 duplicate course code, 500 unexpected. Every error returns the same JSON shape.
