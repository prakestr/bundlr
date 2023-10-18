Feature: Adding a product to cart and proceeding to checkout

  Scenario Outline: Add a product to cart and checkout successfully
    Given I launch the application with email "<email>" and password "<password>"
    Then I should be logged in
    And I add the product "<productName>" to the cart
    Then I should see a toast message "Product Added To Cart"
    When I navigate to the cart
    Then I should see the product "<productName>" in the cart
    When I proceed to checkout
    And I select the country "<country>"
    Then I should see a confirmation message "THANKYOU FOR THE ORDER."
    And I should see a toast message "Order Placed Successfully"
    And I click on the Orders button
    Then I should see the product "<productName>" in the orders

    Examples:
      | email            | password | productName     | country       |
      | phil@hotmail.com | Klm9000! | adidas original | South Africa  |
      | shonj@hotmail.com | Qradar.1 | iphone 13 pro   | United States |
