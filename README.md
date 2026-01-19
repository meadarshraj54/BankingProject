# BankingProject

A console-based Banking Application built using Java.  
This project demonstrates core Java concepts such as OOP, records, interfaces, and service-based architecture.

## Features

- Open a new bank account
- Deposit money
- Withdraw money
- Transfer money between accounts
- View account statement
- List all accounts
- Search account by customer name
- Menu-driven console interface

## Technologies Used

- Java (JDK 21+ recommended)
- Object-Oriented Programming (OOP)
- Java Records
- Collections Framework
- VS Code / IntelliJ IDEA

## Project Structure
```
BankingProject/
├── src/
│   ├── app/
│   │   └── Main.java
│   │
│   ├── domain/
│   │   ├── Account.java
│   │   ├── Customer.java
│   │   ├── Transaction.java
│   │   └── Type.java
│   │
│   ├── service/
│   │   ├── BankService.java
│   │   └── implementation/
│   │       └── ServiceImplementation.java
│   │
│   ├── repository/
│   │   ├── AccountRepo.java
│   │   ├── CustomerRepo.java
│   │   └── TransactionRepo.java
│   │
│   ├── util/
│   │   └── Validation.java
│   │
│   └── exceptions/
│       ├── AccountNotFoundException.java
│       ├── InsufficientBalanceException.java
│       └── ValidationException.java
│
├── .gitignore
├── README.md
└── pom.xml

```

## Prerequisites

- Java JDK 16 or above (Java 17 preferred)
- VS Code with Java Extension Pack or any Java IDE

## How to Run

1. Clone the repository:
   git clone https://github.com/meadarshraj54/BankingProject.git

2. Open the project in your IDE.

3. Ensure Java 17 is configured:
   java -version

4. Run the Main class:
   src/app/Main.java

## Sample Menu

1) Open Account  
2) Deposit  
3) Withdraw  
4) Transfer  
5) Account Statement  
6) List Accounts  
7) Search Account by Customer Name  
0) Exit  

## Notes

- This project is intended for learning and practice.
- Records are used for immutable domain models.
- No database is used; data is stored in memory.

## Future Enhancements

- Database integration (MySQL / PostgreSQL)
- File-based persistence
- Spring Boot version
- REST API support
- Unit testing

## Author

Adarsh Raj  
GitHub: https://github.com/meadarshraj54
