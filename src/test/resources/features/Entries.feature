  @Entries
  Feature: In this feature we will test /entries endpoint

    Scenario: Verify Entries response status code and categories count
      When Call Entries endpoint
      Then Verify that entries response have status code 200
      When Get count of entries
      Then Verify that entries count are 1421

      Scenario Outline: Send request with params
        When Send request with parameters to entries endpoint where parameter is <parameter> and value is <value>
        Then Verify that entries response have status code 200
        And  Get count of entries
        Then Verify count is <count>
        When Get a random record
        Then Verify that record's fields are not empty
          Examples:
            | parameter | value  | count |
            | Category  | Animals| 27    |
            | title     | cat    | 17    |


