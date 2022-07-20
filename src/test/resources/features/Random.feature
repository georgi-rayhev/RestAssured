  @Random
  Feature: In this feature we will test /random endpoint

    Scenario: Send a request to Random endpoint and verify response
      Given Call Random endpoint
      When Get count from response
      Then Verify that count is 1

      Scenario Outline: Send request with params and verify we receive only one object
        Given Send request with parameters to random endpoint where parameter is <parameter> and value is <value>
        When  Get response count
        Then  Verify Count is <count>
        Examples:
          | parameter | value  | count |
          | Category  | Animals| 1     |

          Scenario: Verify that fields of returned object are not empty
            Given Call Random endpoint
            When  Get response object
            Then  Verify fields of the object are not empty

