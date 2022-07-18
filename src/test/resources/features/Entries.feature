  @Entries
  Feature: In this feature we will test /entries endpoint

    Scenario: Verify Entries response status code and categories count
      When Call Entries endpoint
      Then Verify that entries response have status code 200
      When Get count of entries
      Then Verify that entries count are 1421

      Scenario: Get a random record and verify that fields are not empty
        When Call Entries endpoint
        Then Verify that entries response have status code 200
        When Get a random record
        Then Verify that record's fields are not empty

        @111
        Scenario: Verification of content of response with parameters
          Given Call Entries endpoint
          When  Verify that entries response have status code 200
          And   Send request with parameters
          And   Get count of entries
          Then  Verify entries count is 27 and fields of entries are not empty

