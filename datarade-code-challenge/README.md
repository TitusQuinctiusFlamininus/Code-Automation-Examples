-----------------------------------------------------------------------------------------------------------

# OWASP CODING CHALLENGE


![](https://github.com/TitusQuinctiusFlamininus/InterviewCode/blob/main/datarade-code-challenge/screenshots/challenge_ss_7.png)

#### Author:    Ngumbah Michael Nyika
#### Language:  Java
#### Date:      June 2024
------------------------------------------

### Tech Stack Used

## Software

Language              :   Java
JDK                   :   OpenJDK 22
IDE                   :   IntelliJ 2024.1.2 (Ultimate Edition)
Build-Tool            :   Apache Maven 3.9.7
Test Lib              :   JUnit 4.8.1
Brower-Test-Framework : Selenium 4.22.0
HTTP Lib              : OKHttp 4.12.0

## Hardware
Macbook Pro Apple Silicon 
Type: M1 Max 16-Inch Laptop

------------------------------------------


### HOW TO RUN THE OWASP CODING CHALLENGE
------------------------------------------

1. **Download and Install Maven**: Follow instructions here (if using brew)

https://formulae.brew.sh/formula/maven

Make sure the executable "mvn" is in the System PATH environment variable


2. **Install either a Java SDK or JDK** (one can use the Oracle version as well):

https://www.oracle.com/java/technologies/downloads/ 

Make sure the executable "java" is in the System PATH environment variable

3. Install Docker Desktop (to run the Docker Daemon)

https://www.docker.com/products/docker-desktop/


4. **Clone the Code Challenge Repository**: 

https://github.com/TitusQuinctiusFlamininus/InterviewCode.git


5. **Open a Terminal and change into the directory the project was cloned**


6. Add Execution Permission to two shell scripts, by running the following commands on a terminal:

**chmod +x remove-port-hog.sh**
**chmod +x stop-container.sh**


7. **Run all the Tests** by running the command: 

7.1 First, start up the Docker Daemon by starting the Docker Desktop App

7.2. Run maven: 

**mvn clean package**

This command will run all the tests in the OWASP Datarade TestSuite



### RUNNING TESTS (Some Screenshots)
------------------------------------------

![](https://github.com/TitusQuinctiusFlamininus/InterviewCode/blob/main/datarade-code-challenge/screenshots/challenge_ss_5.png)

![](https://github.com/TitusQuinctiusFlamininus/InterviewCode/blob/main/datarade-code-challenge/screenshots/challenge_ss_9.png)

![](https://github.com/TitusQuinctiusFlamininus/InterviewCode/blob/main/datarade-code-challenge/screenshots/challenge_ss_1.png)


-----------------------------------------------------------------------------------------------------------


