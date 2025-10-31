Feature: Complete product checkout flow

  Scenario: Verify successful checkout process
    Given user launches the app
    When user clicks on product "Sauce Lab Back Packs"
    And user clicks on Add to Cart Button
    And user clicks on shopping cart icon
    And user clicks on Proceed To Checkout button
    And user enters username and password
    And user taps on the login button
    And user fills out shipping details
    And user fills out payment details
    And user presses Place Order button
    Then user should see Checkout Complete page
    When user presses Continue Shopping button
    Then user should be redirected to the homepage

