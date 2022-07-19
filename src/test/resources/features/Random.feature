  @Random
  Feature: In this feature we will test /random endpoint

    Scenario: Send a request to Random endpoint and verify response
      When Call Random endpoint
      Then Verify that Random endpoint return status code 200
      When Get count from response
      Then Verify that count is 1

      Scenario Outline: Send request with params and verify we receive only one object
        When Send request with parameters to random endpoint where parameter is <parameter> and value is <value>
        And  Get response count
        Then Verify response is 200 and count is <count>
        Examples:
          | parameter | value  | count |
          | Category  | Animals| 1     |

          Scenario: Verify that fields of returned object are not empty
            When Call Random endpoint
            Then Verify that Random endpoint return status code 200
            And  Get response object
            Then Verify fields of the object are not empty

