#https://www.instantwebtools.net/fake-api/fake-rest-api/
Feature: As a user I want to test the FakeAPIs
  Make calls to the API endpoints and validate responses.

  @readAllAirlines
  Scenario: Read All Airline Details
    Given User has airlines endpoint
    When User from FakeAPI calls "GET_ALL_AIRLINES" with "GET" HTTP request
    Then The FakeAPI API returns status code 200
    And Total "6314" airlines are flying in the sky
    #And "_id" in FakeAPI response body is "252d3bca-d9bb-476c-9a97-562d685e235c"
