# HomeTaskLuminor

## Running Tests with Shell Scripts

Three scripts are available to run the tests:

- `run_all.sh` – runs all tests:

- `run_task1.sh`– runs only Task1 tests:

- `run_task2.sh` – runs only Task2 tests:

# Tasks
1. Make exploratory testing and provide a testing report for a Factorial calculation page: https://qainterview.pythonanywhere.com/
2. Automate below test steps as you would automate a regression test case:
   1. Open imdb.com
   2. Search for "QA" with the search bar 
   3. When dropdown opens, save the name of the first title 
   4. Click on the first title 
   5. Verify that page title matches the one saved from the dropdown 
   6. Verify there are more than 3 members in the "top cast section"
   7. Click on the 3rd profile in the "top cast section"
   8. Verify that correct profile have opened

# Decisions
As for the Task 1, it was specified that exploratory testing should be done. Although, this should relate to manual testing and generate a report with testers feedback a different approach has been made. 

The Task1 was also done in an automated way simply because I wanted to check how Java deals with inheritance and how the decorators for @BeforeSuite and @Before class work. 

As such,. both task results were placed in the same report for easy access of both test results.

**NOTE:** Due to tight schedule, some tests where defined but not written for task 1.
