# DEPI MarketSpace E-Commerce Testing Framework

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=Selenium&logoColor=white)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-FF7F00?style=for-the-badge&logo=testng&logoColor=white)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A professional automation testing framework for the **MarketSpace Demo E-commerce platform**, built with the **Page Object Model (POM)** design pattern for scalability, maintainability, and comprehensive test reporting.

## Features

- **ExtentReports Integration** — Dark-themed HTML test reports with detailed execution logs and timestamps.
- **Page Object Model (POM)** — Clean separation of test logic and UI interactions for maximum reusability.
- **TestNG Listeners** — Real-time console output with progress banners and summary tables.
- **Centralized Configuration** — Environment variables, timeouts, and credentials managed via `config.properties`.
- **Cross-Browser Ready** — Optimized for Google Chrome with Selenium 4 auto-driver detection.
- **Comprehensive Coverage** — Authentication, Search, Product Details, Cart Operations, and End-to-End Checkout.

## Project Structure

```
src
├── main
│   ├── java
│   │   ├── pages           # UI Page Objects
│   │   └── utils           # Configuration & Helpers
│   └── resources           # Environment Properties
└── test
    ├── java
    │   ├── listeners       # TestNG Custom Listeners
    │   └── tests           # Automated Test Scripts
    └── resources           # TestNG Suite XMLs
```

## Prerequisites

- **JDK 21** or higher
- **Maven 3.x**
- **Google Chrome** browser

## Installation

```bash
git clone https://github.com/MoaazBesher/Depi-e-commerce-testing.git
cd Depi-e-commerce-testing
```

## Running Tests

Execute the full regression suite:

```bash
mvn clean test
```

Or run via the TestNG suite XML at `src/test/resources/testng.xml`.

## Test Reports

After execution, find the HTML report at:

```
target/extent-reports/Report_YYYY-MM-DD_HH-mm-ss.html
```

Console output displays a summary table:

+========================================================================+
|  TEST SUITE SUMMARY                                                    |
+------------------------------------------------------------------------+
|  Total Tests  |  30                                                    |
|  PASSED       |  29                                                    |
|  FAILED       |  1                                                     |
|  Duration     |  4m 12s                                                |
+========================================================================+
```

## License

Distributed under the MIT License. See [LICENSE](LICENSE) for more information.

## Author

**Moaaz Besher** — Quality Assurance Automation Engineer
