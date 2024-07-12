# Robot Framework Automation Use Example

License: THIS CODE IS AGPL LICENSED. 

---

## Stack Used

### Hardware
MacBook Pro 16-Inch (Apple M1 Max Silicon 64-Bit)

-----
### General Software and Configuration
#### IDE: IntelliJ Ultimate Edition 2024.1.2
#### Build Tool: Maven 4.0.0
#### Maven Test Runner: Surefire Plugin 2.22.1

----
### UI Automated Testing Software and Configuration
#### Robot Framework Maven Plugin Version: 2.1.0
#### Robot Framework Selenium Plugin: robotframework-seleniumlibrary 4.0

---
### API Automated Testing Software and Configuration
#### HTTP Client: OKHttp 4.12.0
#### Language: Java
#### JDK: Oracle OpenJDK 22.0.1
#### Test Framework: JUnit 4.8.1

---
## Robot Credentials Configuration Used
Credentials to log into th KNIME Hub were placed in a 
file called credentials.robot. It is found in the root
directory. Please replace the credentials in this file with 
your own. 

---
## Directory Structure Layout
1. All tests are located in the **src/main/test** directory
2. Robot tests are located in the **src/main/test/robotframework/acceptance** directory
3. API tests are located int the **src/main/test/java** directory, with package name **code.exercise.knime**
4. The root directory contains the **pom.xml maven configuration file**
5. The root directory also contain a file called **credentials.robot** . This is specifically for the robot tests.

---
## Installation Prerequisites
1. Install Maven and make sure the executable 'mvn' is in the $PATH
2. You may download Maven from here: https://maven.apache.org/download.cgi
3. You may also install Maven using Brew: https://formulae.brew.sh/formula/maven
4. Install a Java JDK, for example, the OpenJDK from Oracle: https://openjdk.org/install/
5. Install the Firefox Web Browser and make sure the executable is also in the $PATH
6. You may download it from here: https://www.mozilla.org/en-US/firefox/new/


---
## How to execute the ROBOT Tests
1. Open a Command Terminal and type the following command from the root directory of the project: **mvn clean verify**
2. Command will run all Robot tests

## How to execute the API Tests
1. In order to try and run the API tests as well, type and run the following command from the root directory of the project:
**mvn clean verify -Dusername=$USERNAME-Dpassword=$PASSWORD** . 
2. Replace the variables with your
own username and password combinations. The file credentials used by the robot tests are not 
used by the API tests. API tests received their own basic authentication username/password combination from the commandline variables, 

---
## Test Run Screenshots

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/Robot-Framework-Automation-Example/images/robot_acceptance_test_report.png)
![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/Robot-Framework-Automation-Example/images/robot_acceptance_test_log.png)



