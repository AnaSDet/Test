
Feature: User Login


  @Test
  Scenario: Positive case. Successful login with valid business domain and credentials
    Given the user is on the login page "loginPageURL"
    When the user enters a valid company domain "validDomain"
    And the user clicks continue button and proceeds to the authentication page
    And enters valid email "validEmail" and password "validPassword" and clicks the Sign In button
    Then the user should be logged in successfully and directed to the dashboard "homePageUrlPart"
