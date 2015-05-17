# MS_BDD_Example
Example BDD story implemented with Cucumber and Selenium.

Feature: As a customer, I wish to view the contents of my bag prior to checkout

    Scenario: Add shirt to bag and view bag
        Given I have added a shirt to my bag
        When I view the contents of my bag
        Then I can see the contents of the bag include a shirt

To run this example, please clone this repository and run the test from the command line with "mvn clean test".


