# Demoblaze Automation Framework 🚀

## 📌 Project Overview
This project is a **real-world end-to-end UI automation framework** built to automate the
**Demoblaze e-commerce application** using Selenium.

It demonstrates practical automation skills such as:
- Page Object Model (POM)
- Data-driven testing
- Randomized product selection
- Handling dynamic UI behavior
- CI/CD-ready execution

---

## 🧪 Test Scenario Covered
The automated test performs the following flow:

1. Launch Demoblaze application
2. Login using valid credentials
3. Select a **random product category** (Phones / Laptops / Monitors)
4. Add **1–2 random products** to the cart
5. Navigate to Cart
6. Place order using **dynamic test data**
7. Handle confirmation popup
8. Logout safely

---

## 🛠️ Tech Stack
- **Java 17**
- **Selenium WebDriver 4**
- **TestNG**
- **Maven**
- **Page Object Model (POM)**

---

## 📂 Framework Structure
src/test/java
├── base → WebDriver setup & teardown
├── pages → Page Object classes (UI actions & locators)
├── tests → Test classes (TestNG @Test)
├── data → TestNG DataProvider (multiple iterations)
└── utils → Reporting utilities (Extent)


---

## 🔁 Execution Strategy
- Uses **TestNG DataProvider** to run multiple iterations
- Each iteration uses **different test data**
- Random category & product selection per run
- Designed to be **stable and CI/CD friendly**

---

## ▶️ How to Run the Tests (STEP-BY-STEP)

### ✅ Prerequisites
Make sure the following are installed on your system:
- Java **17**
- Maven
- Google Chrome browser
- Internet connection

---

### ▶️ Run from IntelliJ IDEA
1. Open the project in **IntelliJ IDEA**
2. Make sure `pom.xml` is detected
3. Open **Terminal** inside IntelliJ
4. Run:
   ```bash
   mvn clean test
