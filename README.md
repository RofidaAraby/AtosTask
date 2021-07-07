# AtosTask
The main Frameworks included in the project:

- Selenium Webdriver
- TestNG
- Allure Report
- Apachi POI
- maven


Project Design:
- Page Object Model (POM) design pattern
- Data Driven framework

How to run the project main test cases locally:

- A properties file "automationPractice.properties" can be found it src/main/resources file path including all the configurations needed in the execution
- Can find the test cases in the src/test/java folder mainly in the tests packages
- Can find the  run test suite / detect the the browser (cross browsers) you want to run in paralleltestng.xml file
- After executing, you can easily generate the Allure Report by opening a command-line terminal on the project root path and type mvn allure:serve (needs to be able to execute mvn commands); Or you can find the Extent Report ExtentReports.html in the project root path for the latest execution



