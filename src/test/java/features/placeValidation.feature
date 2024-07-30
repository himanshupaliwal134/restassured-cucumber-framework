Feature: Validate Place APIs

@addPlace @Regression
  Scenario Outline: Verify if place is getting added using AddPlace API
    Given Add Place Payload with arguments "<name>" "<language>" "<address>"
    When User calls "ADD_PLACE_API" with "POST" HTTP request
    Then The API returns status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GET_PLACE_API"

    Examples: 
      | name           | language | address           |
      | Kumar Parishad | Marathi  | Plot 8, First Row |
      | Pebbles 2      | Hindi    | 706, D1 Block     |

@deletePlace @Regression
  Scenario: Verify functionality of Delete Place API
    Given User has DeletePlace Payload
    When User calls "DELETE_PLACE_API" with "POST" HTTP request
    Then The API returns status code 200
    And "status" in response body is "OK"
