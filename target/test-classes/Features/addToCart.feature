Feature: Add multiple products to cart and verify cart count persists

  Scenario: Add two different products and verify cart count
    Given user launches the app
    When user clicks on product "Sauce Lab Back Packs"
    And user selects color "Blue"
    And user increases product quantity by 1
    And user clicks on Add to Cart Button
    Then cart badge should show count "2"

    When user taps on the hamburger button
    And user selects Catalogs from the menu
    Then cart badge should show count "2"

    When user clicks on product "Sauce Lab Bolt T-Shirt"
    And user clicks on Add to Cart Button
  #uncomment this to intentionally fail the test
#    Then cart badge should show count "3"
    Then cart badge should show count "12"

    When user taps on the hamburger button
    And user selects Catalogs from the menu
    #uncomment this to intentionally fail the test
#    Then cart badge should show count "3"
    Then cart badge should show count "12"

    #uncomment this to intentionally crash the app and fail the test
#    When user clicks on product "Sauce Lab Bike Light"
#    And user clicks on Add to Cart Button
#    Then cart badge should show count "4"