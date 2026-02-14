# 🖥️ Online Examination System (Java GUI)

![Java](https://img.shields.io/badge/Java-17+-blue)
![GUI](https://img.shields.io/badge/GUI-Swing-orange)
![Type](https://img.shields.io/badge/Application-Desktop-brightgreen)
![Status](https://img.shields.io/badge/Project-Completed-success)
---

## 📌 Project Overview

The **Online Examination System** is a Java-based desktop application developed using **Java Swing GUI**.  
This system simulates a real-world online examination platform where a user can log in, manage their profile, take a timed MCQ test, and receive instant results.

This project demonstrates:

- Object-Oriented Programming (OOP)
- GUI Development using Swing
- Event-Driven Programming
- Timer Implementation
- Basic Session Management

---

## 🔐 Default Login Credentials

| Field      | Value     |
|------------|----------|
| Username   | student  |
| Password   | 1234     |

> Credentials are hardcoded in the `User` class for academic demonstration purposes.

---

## 🧩 System Modules

### 1️⃣ Login Module
- Validates username and password
- Displays error message for invalid credentials
- Redirects to dashboard upon successful login

### 2️⃣ Dashboard Module
After login, user can:
- Update Profile
- Update Password
- Start Exam
- Logout

### 3️⃣ Profile Management
- Update display name
- Change password with old password verification

### 4️⃣ Examination Module

**Exam Details:**
- Total Questions: 15
- Type: Multiple Choice Questions (MCQs)
- Options per question: 4
- Timer Duration: 120 seconds (2 Minutes)

**Exam Flow:**
1. User clicks "Start Exam"
2. Timer begins countdown
3. User selects answers
4. Clicks "Next" to move forward
5. Exam auto-submits when time expires or questions finish

---

## ⏳ Timer Functionality

- Implemented using `javax.swing.Timer`
- Countdown updates every second
- Auto-submits when time reaches zero

---

## 📊 Result System

After submission, the system displays:
- Total Score (e.g., 12/15)
- Percentage (e.g., 80%)

Then redirects back to dashboard.

---

## 🚪 Logout Functionality

- Ends session securely
- Returns to login screen
- Resets exam state

---

## 🏗️ Project Architecture

```
OnlineExamSystem.java
 ├── User Class
 ├── Question Class
 ├── Login Screen
 ├── Dashboard Screen
 ├── Profile & Password Update
 ├── Exam Interface
 ├── Timer Logic
 └── Result Calculation
```

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|----------|
| Java | Core Programming |
| Swing | GUI Development |
| AWT | Event Handling |
| OOP | System Structure |
| Timer | Countdown Logic |

---

## 📂 Project Structure

```
Online-Examination-System/
 ├── OnlineExamSystem.java
 └── README.md
```

---

## ▶️ How to Run

### Step 1: Compile
```
javac OnlineExamSystem.java
```

### Step 2: Run
```
java OnlineExamSystem
```

---

## 🎯 Concepts Implemented

- Classes & Objects
- Encapsulation
- ActionListener Events
- Swing Components:
  - JFrame
  - JButton
  - JLabel
  - JTextField
  - JPasswordField
  - JRadioButton
  - ButtonGroup
- Layout Management
- Timer Handling
- Session Control

---

## 🔒 Security Note

- Password stored as plain text (for educational use only)
- No database integration
- Single-user simulation
- Not production-ready

---

## 🚀 Future Enhancements

- MySQL Database Integration
- Admin Panel for Question Management
- Randomized Questions
- Negative Marking
- Result History Storage
- PDF Result Export
- Web Version using Servlet or Spring Boot
