

@SmokeTests
Feature: In this feature we will tests that all endpoints return 200 status code

  Background:
    Given We have a base URL : https://api.publicapis.org/

    Scenario Outline: Verify that all endpoints return status code 200
      When Endpoint is <endpoint>
      And  Get response status code
      Then Verify that status code is 200
      Examples:
      | endpoint   |
      | health     |
      | random     |
      | categories |
      | entries    |