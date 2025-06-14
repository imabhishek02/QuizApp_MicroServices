# 🧠 Quiz App - Microservices Architecture

This is a **Java Spring Boot-based Quiz Application** designed using a **microservices architecture**. The application is split into separate services for better scalability, maintainability, and modular development. Each service performs a specific responsibility and communicates via **Feign Clients**. Services are registered using **Eureka Discovery Server**, and routing is handled through **Spring Cloud Gateway**.

---

## 🏗️ Project Structure

### 1. **Question Service**
- Create, update, and delete questions.
- Stores all questions in a dedicated **Question DB**.
- Calculates quiz responses received from the Quiz Service.

### 2. **Quiz Service**
- Creates quizzes by picking questions from the Question Service based on **category** and **number of questions**.
- Sends questions to users with **4 options** each.
- Accepts user responses and forwards them to the Question Service for evaluation.

### 3. **API Gateway**
- Single entry point for all services.
- Uses **Spring Cloud Gateway** to route requests to appropriate services based on URI.
- Runs on a single port to simplify interaction with multiple services.

### 4. **Eureka Discovery Server**
- Registers all microservices.
- Enables service-to-service communication using service names.
- Simplifies dynamic scaling and decoupling.

---

## 🧰 Tech Stack

- Java 17  
- Spring Boot  
- Spring Cloud Gateway  
- Spring Cloud OpenFeign  
- Eureka Discovery Server  
- PostgreSQL   

---

## 🔁 Service Communication

All internal service calls (e.g., Quiz Service fetching or submitting to Question Service) are done using **Feign Clients**, allowing smooth and declarative HTTP requests between microservices.

---

## 🚀 How It Works

1. **Admin adds questions** to the Question Service.
2. **User creates a quiz** by providing:
   - Number of questions
   - Question category  
   The Quiz Service fetches suitable questions from the Question Service.
3. User attempts the quiz by answering MCQs.
4. **Responses are submitted** to the Quiz Service, which internally forwards them to the Question Service for **scoring and result generation**.
5. The result is returned to the user.

---

## 📦 Future Enhancements 
- Add User Authentication (Spring Security + JWT)
- Leaderboard & Score History
- Admin dashboard for managing quizzes

---

## ▶️ Run the App

Make sure Eureka Server is up and running before starting the services.

Start order:
1. Eureka Discovery Server
2. Question Service
3. Quiz Service
4. API Gateway

---

## 📬 API Endpoints (Sample)
-- Will Update with Correct EndPoints soon --
| Service         | Endpoint                          | Method | Description                    |
|-----------------|-----------------------------------|--------|--------------------------------|
| Question Service| `/questions`                      | POST   | Create a new question          |
| Question Service| `/questions/{id}`                 | PUT    | Update a question              |
| Question Service| `/questions/{id}`                 | DELETE | Delete a question              |
| Quiz Service    | `/quiz/create`                    | POST   | Create a new quiz              |
| Quiz Service    | `/quiz/submit`                    | POST   | Submit answers and get result  |

---

## 📖 License
This project is open source and available under the [MIT License](LICENSE).

