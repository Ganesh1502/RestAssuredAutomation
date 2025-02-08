
# **BDD Automation Framework with Rest Assured**

This is a sample Behavior-Driven Development (BDD) framework built using **Rest Assured** and **Cucumber** for API testing. The framework demonstrates how to automate API tests using BDD principles, making tests readable and maintainable.

---

## **Features**
- **Rest Assured Integration**: Simplifies API testing with a fluent interface.
- **Cucumber BDD**: Enables writing test cases in Gherkin syntax for better collaboration.
- **Data-Driven Testing**: Supports payloads from JSON files for dynamic test data.
- **Logging**: Logs request and response details for debugging.
- **Validation**: Validates response status codes and other attributes.
- **Modular Design**: Easy to extend and maintain.
- **Test Report Generation**: Detailed test report generation
- **Refer the Test Report document for the overview on test reports**

---

## **Technologies Used**
- **Rest Assured**: For API testing.
- **Cucumber**: For BDD implementation.
- **Java**: Programming language.
- **JUnit**: For assertions and test execution.
- **Maven**: Build automation tools.
- **Git**: Version control.

---

## **Prerequisites**
Before running the framework, ensure you have the following installed:
- Java JDK (version 8 or higher)
- Maven
- IDE (IntelliJ IDEA, Eclipse, etc.)
- Cucumber plugin (for IDE, if needed)

---

## **Setup Instructions**
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Ganesh1502/RestAssuredAutomation
   cd master
   ```

2. **Install Dependencies**:
   If using Maven:
   ```bash
   mvn clean install
   ```

3. **Run the Tests**:
   - To run all tests:
     ```bash
     mvn clean verify
     ```
   - To run specific feature files:
     ```bash
     mvn test -Dcucumber.options="<filepath to feature files>"
     ```
	 
4 **Refer the Test Report document for the overview on test reports**

---


## **Step Definitions**
The step definitions are implemented in the `MsResassuredSteps` class. Below are the key methods:

### **1. Setting Base URI**

### **2. Setting Endpoint**

### **3. Sending Requests**

### **4. Validating Response Code**


---

## **Example Feature File**
Below is an example of a feature file written in Gherkin syntax:

```gherkin
Feature: API Testing with Rest Assured

  Scenario: Verify GET request
    Given base uri "https://api.example.com"
    And set end point "/users"
    When "GET" request is sent
    Then the response code should be 200
    And log all the response

  Scenario: Verify POST request
    Given base uri "https://api.example.com"
    And set end point "/users"
    And payload is set from path "src/test/resources/payloads/user.json"
    When "POST" request is sent
    Then the response code should be 201
    And log all the response
```

---


## **Contact**
For questions or feedback, feel free to reach out:
- **Name**: Ganesh M
- **Email/mobile**: ganesh86sep12@gmail.com/9600044258

---
