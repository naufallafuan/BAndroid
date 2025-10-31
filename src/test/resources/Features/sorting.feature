Feature: Sort products

  Scenario: Verify sorting products descending name
    Given user launches the app
    When user clicks on the filter icon
    And user selects sorting option "Name - Descending"
    Then product list "name" should be sorted by "descending"

  Scenario: Verify sorting products ascending price
    Given user launches the app
    When user clicks on the filter icon
    And user selects sorting option "Price - Ascending"
    Then product list "price" should be sorted by "ascending"

  Scenario: Verify sorting products descending price
    Given user launches the app
    When user clicks on the filter icon
    And user selects sorting option "Price - Descending"
    Then product list "price" should be sorted by "descending"

  Scenario: Verify sorting products ascending name
    Given user launches the app
    When user clicks on the filter icon
    And user selects sorting option "Name - Ascending"
    Then product list "name" should be sorted by "ascending"