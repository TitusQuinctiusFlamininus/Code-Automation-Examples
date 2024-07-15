-----------------------------------------------------------------------------------------------------------

# OWASP JUICE SHOP SELENIUM AUTOMATION EXAMPLE


![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/Selenium-Java-Example/screenshots/challenge_ss_7.png)

The aim is to run some UI automation against 
this project: https://owasp.org/www-project-juice-shop/

The OWASP web application is in a docker image.

#### Author:    Ngumbah Michael Nyika
#### Language:  Java
#### Date:      June 2024

------------------------------------------

## Tech Stack Used

### Software

##### Language:    Java
##### JDK:    OpenJDK 22
##### IDE:    IntelliJ Community Edition
##### Build-Tool:    Apache Maven 3.9.7
##### Test Lib:    JUnit 4.8.1
##### Brower-Test-Framework:    Selenium 4.22.0
##### HTTP Lib:    OKHttp 4.12.0
##### Browser Tested: Google Chrome 126.0.6478.63 (Official Build) (arm64)


### Hardware

Macbook Pro Apple Silicon 
Type: M1 Max 16-Inch Laptop

------------------------------------------


### HOW TO RUN THE AUTOMATION CODE


1. **Download and Install Maven**: Follow instructions here (if using brew)

https://formulae.brew.sh/formula/maven

Make sure the executable "mvn" is in the System PATH environment variable


2. **Install a Java SDK** (one can use the Oracle version as well):

https://www.oracle.com/java/technologies/downloads/ 

Make sure the executable "java" is in the System PATH environment variable

3. Install Docker Desktop (to run the Docker Daemon)

https://www.docker.com/products/docker-desktop/


4. **Clone the Repository**: 

https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples.git


5. **Open a Terminal and change into the directory the project was cloned**


6. Add Execution Permission to two shell scripts, by running the following commands on a terminal:

#### **chmod +x remove-port-hog.sh**
#### **chmod +x stop-container.sh**


7. **Run all the Tests**

First, **start up the Docker Daemon by starting the Docker Desktop App**; then run Maven: 

**mvn clean package**

This command will run all the tests in the OWAS TestSuite

Docker Hub Login Credentials Note : You have the option to change the Docker Hub Login Credentials in the maven pom.xml file (Lines 48 through 52) with your own Hub credentials. They are used to login to the Docker Hub and download the latest OWASP docker image for the web application.

### RUNNING TESTS (Some Screenshots)
------------------------------------------

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/Selenium-Java-Example/screenshots/challenge_ss_5.png)

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/Selenium-Java-Example/screenshots/challenge_ss_9.png)

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/Selenium-Java-Example/screenshots/challenge_ss_1.png)


-----------------------------------------------------------------------------------------------------------


