Feature: Discount Feature

  Scenario:
    When the price more than 400
    Then discount 10%

  Scenario:
    When the price less than 400
    Then no discount
