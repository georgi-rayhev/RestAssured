  @Entries
  Feature: In this feature we will test /entries endpoint

    Scenario: Verify Entries response status code and categories count
      Given Call Entries endpoint
      When Get count of entries
      Then Verify that entries count are 1425

      Scenario Outline: Send request with params
        Given Send request with parameters to entries endpoint where parameter is <parameter> and value is <value>
        When  Get count of entries
        Then  Verify count is <count>
          Examples:
            | parameter | value  | count |
            | Category  | Animals| 27    |
            | title     | cat    | 17    |

          Scenario: Verify that record's fields are not empty
            Given Call Entries endpoint
            When  Get a random record
            Then  Verify that record's fields are not empty
