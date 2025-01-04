# SafePass: Java Password Manager and Decryptor App

## Table of Contents
- [Introduction](#introduction)
- [Key Features](#key-features)
- [System Design](#system-design)
- [Technologies Used](#technologies-used)
- [Implementation Details](#implementation-details)
- [Graphical User Interface](#graphical-user-interface)
- [Testing and Evaluation](#testing-and-evaluation)
- [Future Improvements](#future-improvements)

---

## Introduction
SafePass is a Java-based desktop application that offers a secure and user-friendly solution for managing passwords. It is designed to address the growing need for secure password storage in an era of increasing online accounts.

The application includes:
1. A Password Manager to securely store and encrypt passwords.
2. A Decryptor App to decrypt stored passwords when required.

## Key Features
- **User Registration**: Users register with personal credentials and a unique security key.
- **Secure Login**: Username and password-based authentication.
- **Password Management**:
  - Add, update, and delete passwords.
  - Passwords are encrypted using a custom algorithm.
- **Encryption & Decryption**:
  - Secure encryption of passwords based on a combination of a user-defined security key and the main password.
  - Decryption functionality through the Decryptor App.

## System Design
SafePass is built with security and usability in mind. The architecture comprises two main components:
1. **Password Manager App**:
   - Handles user registration, login, and password management.
2. **Decryptor App**:
   - Provides decryption functionality.

### Design Patterns
- **Singleton**: Ensures a single instance of the database connection.
- **Factory**: Facilitates the creation of encryption and decryption objects.

## Technologies Used
- **Programming Language**: Java
- **Framework**: JavaFX for GUI
- **Database**: MySQL for secure local storage

## Implementation Details
### Modules
1. **User Module**: Handles registration and login processes.
2. **Password Module**: Manages CRUD operations for passwords.
3. **Encryption Module**: Implements custom encryption and decryption algorithms.

### Critical Algorithms
#### Encryption
A custom encryption process ensures passwords are securely stored in the database using a security key and ASCII transformations.
#### Decryption
Mirrors the encryption process to restore the original password.

## Graphical User Interface
SafePass features a user-friendly interface designed with JavaFX:
- **Registration Screen**: Allows new users to register.
- **Login Screen**: Authenticates users.
- **Dashboard**: Provides options to manage passwords.
- **Decryptor Screen**: Simplifies password decryption.

## Testing and Evaluation
### Strategies
- Identified and resolved issues with unprintable ASCII characters in the encryption process.
- Implemented robust validation mechanisms for user inputs.

### Challenges & Solutions
- **Challenge**: Ensuring strong encryption without compromising performance.
  - **Solution**: Optimized the custom algorithm for efficiency.
- **Challenge**: Handling diverse user inputs.
  - **Solution**: Integrated comprehensive validation techniques.

## Future Improvements
1. **Standardized Encryption**: Integrate AES or other standardized encryption algorithms.
2. **Cloud Storage**: Enable multi-device access.
3. **Password Strength Analysis**: Suggest stronger password options.
4. **Multi-Factor Authentication**: Enhance login security.

---
Developed by:
We 3 students from Islamic University of Technology, made this app as a project for our "CSE 4402: Visual Programming Lab" Course.
- Mueed Ibne Sami (210041149)
- Miraj Mahmud Mahee (210041101)
- Kazi Akib Zaoad (21004117)


