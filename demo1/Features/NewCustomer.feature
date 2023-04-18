Feature: the user can create his own account

  Scenario: Create a new account
  When the user clicks on the New Customer button
  Then the user should see the New Customer page

  Scenario: Create a new account with valid data
    When the user enters valid data
    And the user clicks on the Sign up button
    Then the user data should be saved in the database

    Scenario: Create a new account with invalid data
    When the user enters invalid data
    And the user clicks on the Sign up button
    Then the user should see a warning message
