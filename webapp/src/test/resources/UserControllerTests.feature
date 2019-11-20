@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario Outline: Add user succesfully 
    Given I have a valid user with "<username>", "<description>" and "<email>"
    When I post that user on the service
    Then The user will be created
    
    Examples:
    | username   | description      | email                  |
    | Johnyboy   | The one and only | johny@email.com        |
    | Fritz      | Ya know          | anotheremail@gmail.com |
    | Maple      | My description   | maple@maple.map        |
    
    

