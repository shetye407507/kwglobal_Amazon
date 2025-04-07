# kwglobal_Amazon

## Overview

**kwglobal_Amazon** Project made for KW Global as apart of assignment

## Project Structure

- **.settings/**: Contains IDE-specific settings.
- **reports/**: Can find Extent report as HTML here.
- **src/**: The primary source code directory.
- **target/**: Commonly used for compiled bytecode or build artifacts.
- **test-output/**: Typically holds the results of test executions.
- **.classpath**: Defines the classpath for the project.
- **.gitattributes**: Manages Git attributes for the repository.
- **.project**: Eclipse project descriptor file.
- **pom.xml**: Maven Project Object Model file, indicating the use of Maven for build automation.
- **testng.xml**: Configuration file for TestNG, outlining test suites and configurations.

## Technologies Used

- **Java**: Core programming language.
- **Maven**: Build automation and dependency management.
- **TestNG**: Testing framework for Java.

## Getting Started

To set up the project locally:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/shetye407507/kwglobal_Amazon.git
   
**Pre requsite**
Java and maven must be installed if not Install and do below steps
1. **Set JAVA_HOME:**
   Make sure `JAVA_HOME` is set correctly in your system environment variables.

2. **Install Maven:**
   Extract Maven and add the `bin` folder path to the `PATH` environment variable.
   
3.Instal TestNG if IDE requires

**How to Execute**
mvn clean install
or 
Run Tests with TestNG: Ensure your IDE is configured to recognize TestNG, then execute the test suites as defined in testng.xml.

**Config File**
Use Config file to define Base URL  and Implicit wait


**Exception**
If Captcha screen is displayed manual intervention needed

**Reports**
After test execution, reports will be available under the /kwglobal/reports/ExtentReport.html to view the summary.




