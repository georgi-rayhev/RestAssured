  @Categories
  Feature: In this feature we will test content of /categories endpoint

    Scenario: Verify Categories response status code and categories count
      Given Call Categories endpoint
      When  Get count of categories
      Then  Verify that categories count are 51

     Scenario Outline: Testing Categories endpoint content
        Given Call Categories endpoint
        When  Get all categories
        Then  Verify that response contains <categories>
        Examples:
          | categories                     |
          | Animals                        |
          | Anime                          |
          | Anti-Malware                   |
          | Art & Design                   |
          | Authentication & Authorization |
          | Blockchain                     |
          | Books                          |
          | Business                       |
          | Calendar                       |
          | Cloud Storage & File Sharing   |
          | Continuous Integration         |
          | Cryptocurrency                 |
          | Currency Exchange              |
          | Data Validation                |
          | Development                    |
          | Dictionaries                   |
          | Documents & Productivity       |
          | Email                          |
          | Entertainment                  |
          | Environment                    |
          | Events                         |
          | Finance                        |
          | Food & Drink                   |
          | Games & Comics                 |
          | Geocoding                      |
          | Government                     |
          | Health                         |
          | Jobs                           |
          | Machine Learning               |
          | Music                          |
          | News                           |
          | Open Data                      |
          | Open Source Projects           |
          | Patent                         |
          | Personality                    |
          | Phone                          |
          | Photography                    |
          | Programming                    |
          | Science & Math                 |
          | Security                       |
          | Shopping                       |
          | Social                         |
          | Sports & Fitness               |
          | Test Data                      |
          | Text Analysis                  |
          | Tracking                       |
          | Transportation                 |
          | URL Shorteners                 |
          | Vehicle                        |
          | Video                          |
          | Weather                        |