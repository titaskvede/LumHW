# Task:
1. Make exploratory testing and provide a testing report for a Factorial calculation page: https://qainterview.pythonanywhere.com/
2. Automate below test steps as you would automate a regression test case:
      *Open imdb.com
      *Search for "QA" with the search bar
      *When dropdown opens, save the name of the first title
      *Click on the first title
      *Verify that page title matches the one saved from the dropdown
      *Verify there are more than 3 members in the "top cast section"
      *Click on the 3rd profile in the "top cast section"
      *Verify that correct profile have opened
TODO: 
- [x] Set up Java 17
- [x] Check what 'exploratory testing' entails.
- [x] Find tool responsibility, documentation and best practices 
  - [x] TestNG        https://testng.org
    - https://www.youtube.com/watch?v=Y6M1bz8jtT8
    - v7.6.0 and above
    - Testing framework
  - [x] Selenide      https://selenide.org/documentation.html
    - Browser control
  - [ ] Gradle        https://docs.gradle.org/current/samples/sample_building_java_applications.html
    -  Its .exe file creator? Like Pyinstaller?
    -  Kinda, but I need to build application to run like cpp
      - Debugging?
    -  Application creator
    - Environment creator. Set it up once and forget vibe
  - [x] Allure-report https://allurereport.org
    - Report generation
    - Allure produces depricated warnings. Can I remove them?
- [ ] Check for best practices testing webpages

# Notes:
* Java 17
  * Released 2021 September 14.
  * Python 3.10 release on 2021 October 4, SOOOOOOOOOOOO I USE NEWER PROGRAMMING LANGUAGE IN MY CURRENT JOB

* Different browser check?
* Different OS check?
  * PC
  * Phone

* Check for different types numbers:
    * Float
    * Str
    * Negative
    * 0
    * Int

* Python has a better venv set up, no doubt

* Create report with 'Allure-report'

* Git repo
    * Leave it for the end