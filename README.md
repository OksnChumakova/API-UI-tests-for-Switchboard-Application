----------------------
       Purpose
----------------------

The tool is developed to test Switchboard application:
	- backend(API) and
	- frontend

----------------------
    Description
----------------------

The tool contains 2 suits:

1. API
2. UI


----------------------
Run Integration Tests
----------------------

mvn clean test 

	-Durl=http://switchboard.local
	
	-Dbrowser=CHROME (it is an ENUM value)
	
	-Dlogin=<usernameToSignIn>
	
	-Dpassword=<passwordToSignIn>

	-DpathToWebDriver=<src/test/resources/>
	

The properties can be instantiated in 3 ways:

- env variables (1st priority)
- command line (2d priority)
- properties set in envProperties.properties file (../src/test/resources/env.properties ) (3d priority)
 

 ----------------------
         Report
 ----------------------

 The report after each test run is generated and stored in the directory -  ../target/surefire-reports/html/index.html

 It is overwritten after each run
