Feature: The Admin can create new workers

  Scenario: Create a new worker with valid data
    When the Admin enters valid data
    And the Admin clicks on the Create New worker button
    Then the Wroker data should be saved in the database

  Scenario: Create a new account with invalid data
    When the Admin enters invalid data
    And the Admin clicks on the Create New worker button
    Then the Wroker data should not be saved in the database