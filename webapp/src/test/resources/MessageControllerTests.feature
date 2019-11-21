Feature: Cases for the MessageController

  Scenario: Get Message details - Sunny day
    Given the user of the message service wants to send a request to "/api/v1/message"
    And provides the id of a message with sender_id: 1, recipient_id: 3, message contents: "This is a test message"
    When the user does a GET request
    Then the message service returns statuscode 200
    And the message contains the same details

  Scenario: Get Message details - Rainy day - Message unknown
    Given the user of the message service wants to send a request to "/api/v1/message"
    And provides the an invalid id with value 99901
    When the user does a GET request
    Then the message service returns statuscode 404

  Scenario: Post a new message - Sunny day
    Given the user of the message service wants to send a request to "/api/v1/message"
    And provides valid message contents: sender_id: 1, recipient_id: 3, message contents: "This is a test message"
    When the user does a POST request
    Then the message service returns statuscode 200
    And the response message contains the same details

  Scenario Outline: Post a new message - Rainy day - Invalid sender or recipient
    Given the user of the message service wants to send a request to "/api/v1/message"
    And provides a message with wrong values for <senderId> and/or <recipientId>
    When the user does a POST request
    Then the message service returns statuscode 400

    Examples: 
      | senderId | recipientId |
      |        0 |           1 |
      |        1 |           0 |
      |        0 |           0 |
      |    99901 |           1 |
      |        1 |       99901 |
      |    99901 |       99901 |

  Scenario: Delete a message - Sunny day
    Given the user of the message service wants to send a request to "/api/v1/message"
    And provides the id of a message with sender_id: 1, recipient_id: 3, message contents: "This is a test message"
    When the user does a DELETE request
    Then the message service returns statuscode 200
    And the message service returns a ServiceResponseMessage with operation "Delete Message"

  Scenario: Delete a message - Rainy day - Message doesn't exist
    Given the user of the message service wants to send a request to "/api/v1/message"
    And provides the an invalid id with value 99901
    When the user does a DELETE request
    Then the message service returns statuscode 404

  Scenario: Update a message - Sunny day
    Given the user of the message service wants to send a request to "/api/v1/message"
    And provides the id of a message with sender_id: 1, recipient_id: 3, message contents: "This is a test message"
    And provides valid message contents: sender_id: 2, recipient_id: 1, message contents: "This is the changed message"
    When the user does a PUT request
    Then the message service returns statuscode 200
    And the response message contains the same details

  Scenario Outline: Update a message - Rainy day - wrong sender and recipient
    Given the user of the message service wants to send a request to "/api/v1/message"
    And provides the id of a message with sender_id: 1, recipient_id: 3, message contents: "This is a test message"
    And provides a message with wrong values for <senderId> and/or <recipientId>
    When the user does a PUT request
    Then the message service returns statuscode 400

    Examples: 
      | senderId | recipientId |
      |        0 |           1 |
      |        1 |           0 |
      |        0 |           0 |
      |    99901 |           1 |
      |        1 |       99901 |
      |    99901 |       99901 |

  Scenario: Update a message - Rainy day - wrong id
    Given the user of the message service wants to send a request to "/api/v1/message"
    And provides the an invalid id with value 99901
    And provides valid message contents: sender_id: 2, recipient_id: 1, message contents: "The message"
    When the user does a PUT request
    Then the message service returns statuscode 404

  Scenario: getUserInbox - Sunny day
    Given the user of the message service wants to send a request to "/api/v1/user/1/inbox"
    When the user does a GET request
    Then He will find 3 items with ids 3 4 and 5

  Scenario: getUserSentItems - Sunny day
    Given the user of the message service wants to send a request to "/api/v1/user/1/sent"
    When the user does a GET request
    Then he will find 1 item with id 1
