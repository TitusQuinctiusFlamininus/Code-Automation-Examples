
# D-RE-J-R : Loan Amortization Calculator
*d*jango + *re*act + *j*est + *r*obot
------

A Web Application that calculates the Loan Amortization Monthly Payments for Existing Customers. 
Among the required inputs are the *Loan Principal Amount*, the *Loan Period* (in months), the *Interest Rate* (as a percentage) and 
the *Associated Customer*. The monthly payment is part of a Loan Offer. 

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/loanCalculator.png)

------

## Table Of Contents

1. [License](#license)
2. [Hardware](#hardware)
3. [Software](#software)
4. [Prerequisite Installations](#prerequisite-installations)
5. [Directory Structure](#directory-structure)
6. [Pre-Setup Steps](#pre-setup-steps)
7. [Setup](#setup)
8. [Running Database Migrations from Scratch](#running-database-migrations-from-scratch)
9. [Running Jest Unit or Integration Tests](#running-jest-unit-or-integration-tests)
10. [Running Robot UAT Tests](#running-robot-uAT-tests)
11. [The Loan Calculator Web Interface](#the-loan-calculator-web-interface)
12. [The Loan Calculator API Interface](#the-loan-calculator-aPI-interface)

------

## License
![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/agpl.png)
#### All Example code is **APGL Licensed**. 
This is a strong **Copy-Left** License. Please do not just clone, copy, paste and edit. Be respectful and honest.

------
## Hardware
MacBook Pro 16-Inch (Apple M1 Max Silicon 64-Bit)


## Software

### Web Framework
##### Django version : 4.2.14

### Web User Interface
##### React version : 15.0.6
##### React-dom version: 18..3.1
##### React-scripts version: 5.0.1

Please see package.json for more details

### Persistent Storage
##### Sqlite version : 3

### Unit / Integration Testing
##### Jest version : 27.5.1
##### Jest Dev Dependency version: 27.5.1
##### testing-library/jest-dom version : 5.17.0

### Build Runner for UAT (User Acceptance Tests)
##### Build Tool: Maven version : 4.0.0
##### Maven Test Runner: Surefire Plugin version : 2.22.1

### UAT Test Framework
##### Robot Framework Maven Plugin Version : 2.1.0
##### Robot Framework Selenium Plugin: robotframework-seleniumlibrary version : 4.0

### IDE Editor
##### Visual Studio Code version: 1.91.1 

------

## Prerequisite Installations

### Python
Purpose: This is needed to run the undelying Web Server managed by the Django Framework

Make sure the executable *python* (or *python3*) is in your System $PATH
1. One may install it from here: https://www.python.org/downloads/
2. An alternative is to use Brew to install it: https://docs.brew.sh/Homebrew-and-Python

*Important*: Python comes with pip, so use this to further install pipenv like this: 

```bash
pip install pipenv
```

### NPM
Purpose: This is needed to install many valuable javascript libraries

1. One may obtain it here: https://www.npmjs.com/
2. One may also choose to install it with Brew: https://formulae.brew.sh/formula/node
 
### Git Command-Line Tools (Optional)
Purpose: This is needed to clone the Github Repository

One may also choose Git UI support tool like Github Desktop to clone the repository.

Github Desktop downloads: https://desktop.github.com/download/

One may install Git command-line tools with brew: https://formulae.brew.sh/formula/git

The full source could also simply be downloaded from the Git Repository (under **Code** button -> **Download Zip**)

### Maven
Purpose: This is needed to run the Automated UAT tests 

1. Install Maven and make sure the executable 'mvn' is in the System $PATH
2. One may download Maven from here: https://maven.apache.org/download.cgi
3. One may also install Maven using Brew: https://formulae.brew.sh/formula/maven

### Firefox Web Browser
Purpose: This is needed as the target browser for the UAT tests. 

1. Install the Firefox Web Browser and make sure the executable is also in the System $PATH
2. One may download it from here: https://www.mozilla.org/en-US/firefox/new/

**Note**: If you would prefer to run UAT with Chrome, please change **Line 10 in loan-uat-tests.robot file to Chrome** . 
Just make sure the Chrome executable is also in your System $PATH. 


------

## Directory Structure

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/directory_structure.png)


### React Files
Most files are located in the **frontend** directory. These include javascript files, component files, css files and other helper files

### Django Files
Most files are located in the **loanbackend** directory. These include database migration files and CRUD modelling files.
The sqlite database is also located here

### Maven Files
Most files are located in the **Root** directory. The **pom.xml** file represents Maven's build and run configuration. It also
contains information about how the UAT tests are run and managed.

### Test Files

There are 2 kinds of tests: 

1. **Jest Tests**: These are Unit/Integration tests that do not require a browser to run.
2. **Robot Automated Tests**: These are User Acceptance Tests (UAT or AT) that use a browser to execute use-case flows to verify UI cases

Jest tests are located in the : frontend/src directory (App.test.js)
UAT tests are located in the :  frontend/src/tests/robotframework directory

### UAT Test Reports Directory 
After UAT tests run, one can find a full report in the **target/robotframework-reports** directory. Other Maven default test
reports (from the surefire plugin) are also located in the target directory. 


------

## Pre-Setup Steps

The project comes with some sqlite data already loaded in the **db.sqllite3** file. This makes it easier to simply
run the UAT tests, since they run assuming there is at least 1 Customer Record saved. If you wish to run with clean data,
then delete this file and run the database migrations step below ("Run Database Migrations from Scratch")

## Setup

1. Clone the Git Repository: https://github.com/TitusQuinctiusFlamininus/drejr-loan-calculator-webapp

2. Open a Terminal and change into the **drejr-loan-calculator-webapp** directory (or the directory you cloned it to on the filesystem).

3. Change into the **loanbackend** directory.

4. Run the following command:

```bash
pip install pipenv
```
If you haven't yet, run it now. 

5. Run: 

```bash
pipenv shell
``` 

This will provide a virtual shell from which to run further django related commands.

6. Run: 

```bash
pipenv install django
``` 
This will install the django web framework.

7. If you wanted to run with clean database data, see below (Run Database Migrations from Scratch). Otherwise, go to (8).

8. Run the following to start up the server: 

```bash
python manage.py runserver
``` 

or: 

```bash
python3 manage.py runserver
``` 


9. Now the API endpoints are available here: http://localhost:8000/api . Note the 8000 port for API endpoints. 

Note: (For Mac/Linux Platforms): If you already have a process running on Port 8000, free it by running:  

```bash
netstat -vanp tcp | grep 8000
```
and tthen searching for the PID, and then running: 

```bash
kill -9 [PID]
```

(For Windows): Try the command: 

```bash
taskkill /IM process name /F
```

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/API_Endpoint_Root.png)

Feel free to add new Loan Customers with the *customers* endpoint.

10. Change directory to frontend (if you are in the Root directory);

```bash
cd frontend
```

11. Run the command: 

```bash
npm install
```
 
 This will install all the necessary javascript libraries such as React, Jest and 
other dependencies. At the end, a **node_modules** directory will be created. If you need to refresh dependencies, or 
change version values in the package.json file manually, it may be a good idea to delete this directory and re-run the 
command to obtain proper versions. Individual packages are usually installed with variants of the **npm install ..**. 
Read more on the npm installation procedures for best practices here: https://docs.npmjs.com/cli/v10/commands/npm-install

11. Now run the command: 

```bash
npm start
```

This starts the application on port 3000. 

The Loan application can now be accessed here: http://localhost:3000/

Again, if port 3000 is already in use, you can use netstat to identify the process and kill it first. 

------

## Running Database Migrations from Scratch
You would like to napalm the existing trial customer and data in the database and add your own. 

1. Delete the file **db.sqlite3** from the **loanbackend** directory.

2. Run: 

```bash
python manage.py migrate
```
or : 

```bash
python3 manage.py migrate
```

depending on your python version. 

The database structure created are 2 tables: **amort_customer** and **amort_loanoffer** . 

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/amort_customer.png)


![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/amort_loanoffer.png)

Note: Customer ID is used as a Foreign Key in the LoanOffer Table


------

## Running Jest Unit or Integration Tests

1. Open a Terminal and change to the **frontend** directory. 

2. Run the command: 

```bash
npm test
```

The results will be displayed in the terminal.

The Web server does not have to be running for a successful run. 

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/jest_report.png)

------

## Running Robot UAT Tests

1. Make sure the Web Appication is accessible on Port 3000. If not, run *npm start* from the *frontend* directory. 

2. Open a Terminal and change directory to **Root** of the project.

3. Run the command: 

```bash
mvn verify
```

This command will automatically run the Robot Tests. Maven may have to 
download a number of dependencies into the $USER_HOME/.m2/repository directory before any tests actually run. 

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/uat_testing_1.png)

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/uat_testing_2.png)

A full Test HTML Report will be located in the **target/robotframework-reports** directory, showing all the test-suites and 
individual tests run. 

##### UAT Report Overview 
![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/uat_report_3.png)


##### UAT Report Test Listing
![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/uat_report_2.png)

##### UAT Report Individual Test Details
![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/uat_report_1.png)

------

## The Loan Calculator Web Interface
It is only possible to compute a Monthly Amortization Payment for a specific customer. 

![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/web_customer_association.png)

This is also true for the API Endpoint where one can directly add a new Load Offer. 



![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/api_customer_association.png)

There is field validation for both the Web and API interfaces as well (input needs to be numbers, or 
numerals with specific decimal places, etc)


------

## The Loan Calculator API Interface

### Creating a Customer
![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/api_endpoint_Create_Customer.png)

### Creating a Loan Offer
![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/API_Endpoint_Create_LoanOffer.png)


### Display a Customer By ID
![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/API_Endpoint_Display_Customer_By_Id.png)


### Display a Loan Offer By ID
![](https://github.com/TitusQuinctiusFlamininus/Code-Automation-Examples/blob/main/loan-calculator-webapp/images/API_Endpoint_Display_LoanOffer_By_Id.png)


Enjoy!

------






