Feature: As a customer, I wish to view the contents of my bag prior to checkout


  Background:
    Given I am using Firefox
    And I am on the site http://www.marksandspencer.com

  Scenario: Add shirt to bag and view bag

    Given I have added a shirt to my bag
    When I view the contents of my bag
    Then I can see the contents of the bag include a shirt
