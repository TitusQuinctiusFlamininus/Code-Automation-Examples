-----------------------------------------------------------------------------------------------------------

Author:    Ngumbah Michael Nyika
Language:  Java
Date:      June 2024


=======================================
HOW TO RUN THE OWASP CODING CHALLENGE
=======================================

1. Download and Install Maven: Follow instructions here (if using brew)

https://formulae.brew.sh/formula/maven

Make sure the executable "mvn" is in the System PATH environment variable


2. Install either a Java SDK or JDK (one can use the Oracle version as well):

https://www.oracle.com/java/technologies/downloads/ 

Make sure the executable "java" is in the System PATH environment variable


3. Clone the Repository: 

https://github.com/TitusQuinctiusFlamininus/InterviewCode.git


4. Open a Terminal and change into the directory the project was cloned


5. Add Execution Permission to two shell scripts, by running the following commands on a terminal:

chmod +x remove-port-hog.sh
chmod +x stop-container.sh


6. Run the command: 

mvn clean package

This command will run all the tests in the OWASP Datarade TestSuite


-----------------------------------------------------------------------------------------------------------

