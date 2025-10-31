Feature: Login Case for Demo App
Scenario: User Log In
  Given user launches the app
  When user taps on the hamburger button
  And user selects Log In from the menu
  And user enters username and password
  And user taps on the login button
  Then user should be redirected to the homepage