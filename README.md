# 🛍️ DEPI MarketSpace E-Commerce Testing Framework

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=Selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF7F00?style=for-the-badge&logo=testng&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

A professional, high-end automation testing framework built for the **MarketSpace Demo E-commerce platform**. This project implements the **Page Object Model (POM)** design pattern to ensure scalability, maintainability, and visual excellence in test reporting.

---

## ✨ Key Features

*   **🏆 Professional Reporting**: Integrated **ExtentReports** for beautiful, dark-themed HTML test reports with detailed execution logs and timestamps.
*   **🏗️ Page Object Model (POM)**: Clean separation of test logic and UI interactions for maximum code reusability.
*   **🚦 Smart Execution**: Custom **TestNG Listeners** provide real-time, professional console output with progress banners and summary tables.
*   **🔧 Centralized Config**: Management of environment variables, timeouts, and credentials via `config.properties`.
*   **🌐 Cross-Browser Ready**: Optimized for **Microsoft Edge** with seamless Selenium 4 auto-driver detection.
*   **🛒 Comprehensive Coverage**:
    *   Authentication (Login/Signup)
    *   Search Functionality
    *   Product Details & Categories
    *   Cart Operations
    *   End-to-End Checkout Flow

---

## 🛠️ Project Structure

```text
src
├── main
│   ├── java
│   │   ├── pages       # UI Page Objects
│   │   └── utils       # Configuration & Helpers
│   └── resources       # Environment Properties
└── test
    ├── java
    │   ├── listeners   # TestNG Custom Listeners
    │   └── tests       # Automated Test Scripts
    └── resources       # TestNG Suite XMLs
```

---

## 🚀 Getting Started

### Prerequisites
*   **JDK 21** or higher
*   **Maven** 3.x
*   **Microsoft Edge** browser

### Installation
1.  Clone the repository:
    ```bash
    git clone https://github.com/MoaazBesher/Depi-e-commerce-testing.git
    ```
2.  Navigate to the project directory:
    ```bash
    cd Depi-e-commerce-testing
    ```

### Running Tests
Run the full regression suite using Maven:
```bash
mvn clean test
```
Or use the professional TestNG suite XML file:
`src/test/resources/testng.xml`

---

## 📊 Beautiful Test Results

After execution, find your premium HTML report here:
`target/extent-reports/Report_YYYY-MM-DD_HH-mm-ss.html`

The console output will display a premium summary like this:
```text
  +========================================================================+
  |  TEST SUITE SUMMARY                                                    |
  +------------------------------------------------------------------------+
  |  Total Tests  |  30                                                    |
  |  PASSED       |  29                                                    |
  |  FAILED       |  1                                                     |
  |  Duration     |  4m 12s                                                |
  +========================================================================+
```

---

## 👨‍💻 Author
**Moaaz Besher**  
*Quality Assurance Automation Engineer*

---
> Made with ❤️ for the DEPI Project.
