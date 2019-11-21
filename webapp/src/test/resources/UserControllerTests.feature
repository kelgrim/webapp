Feature: Testing the Rest API for users

  Scenario Outline: Create user - Add user succesfully
    Given the user of the service wants to send a request to URL "/api/v1/user"
    When that user provides correct values for  "<username>", "<description>" and "<email>"
    And that user makes a POST Request
    Then the service returns statuscode 200
    And the service will return a Json with that same data

    Examples: 
      | username | description      | email                  |
      | Johnyboy | The one and only | johny@email.com        |
      | Fritz    | Ya know          | anotheremail@gmail.com |
      | Maple    | My description   | maple@maple.map        |

  Scenario: Create user - Provide invalid user data
    Given the user of the service wants to send a request to URL "/api/v1/user"
    When that user provides an incorrect value for the username
    And that user makes a POST Request
    Then the service returns statuscode 400

  Scenario: Get users - Receive list of all users
    Given the user of the service wants to send a request to URL "/api/v1/user"
    When that user makes a GET Request
    Then the service returns statuscode 200
    And the response contains a JSON with users including ID 1 2 and 3

  Scenario Outline: Get user - get details for specific user
    Given the user of the service wants to send a request to URL "/api/v1/user"
    And the user provides id <id>
    When that user makes a GET Request
    Then the service returns statuscode 200
    And the response contains the correct values for "<username>", "<description>" and "<email>"

    Examples: 
      | id | username | description                         | email          |
      |  1 | Kelgrim  | Developer of this Rest service      | abc@gmail.com  |
      |  2 | Dolphin  | Just another testuser               | xyz@email.com  |
      |  3 | Barney   | Stole captain marvels email address | cap@marvel.com |

  Scenario: Get user - unknown user
    Given the user of the service wants to send a request to URL "/api/v1/user"
    And the user provides id 99901
    When that user makes a GET Request
    Then the service returns statuscode 404

  Scenario: Delete user - deletion succesfull
    Given the user of the service wants to send a request to URL "/api/v1/user"
    And the user provides an existing id
    When that user makes a DELETE Request
    Then the service returns statuscode 200
    And the service returns a ServiceResponseMessage with operation "Delete User"
    
    Scenario: Delete user - deletion failed
    Given the user of the service wants to send a request to URL "/api/v1/user"
    And the user provides id 99901
    When that user makes a DELETE Request
    Then the service returns statuscode 404
