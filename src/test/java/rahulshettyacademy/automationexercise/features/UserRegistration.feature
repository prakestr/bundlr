Feature: User Registration on demo.nopcommerce.com

  Scenario: Successful User Registration
    Given I navigate to the main page
    When I click on the Register link
    And I fill in the registration form with valid data
    Then I should see the "Your registration completed" message
    And I should be able to click the Continue button
