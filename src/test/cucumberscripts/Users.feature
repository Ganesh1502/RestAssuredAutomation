Feature: create a sample post request

  Background: set the base uri
    Given base uri "https://reqres.in/"

  Scenario: Create users
    Given set end point "api/users"
    When payload is set from path "src/test/payloads/user.json"
    When "POST" request is sent
    Then log all the response
    Then the response code should be 201

    Scenario: Get the users list
      Given set end point "api/users"
      And set query param "page" as "1"
      When "get" request is sent
      Then log all the response
      Then the response code should be 200

  Scenario: Get the delayed response
    Given set end point "api/users"
    And set query param "delay" as "3"
    When "get" request is sent
    Then log all the response
    Then the response code should be 200

  Scenario: update users
    Given set end point "api/users/2"
    When payload is set from path "src/test/payloads/updateUser.json"
    When "put" request is sent
    Then log all the response
    Then the response code should be 200