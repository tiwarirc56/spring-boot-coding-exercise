# See
# https://github.com/intuit/karate#syntax-guide
# for how to write feature scenarios
Feature: As an api user I want to retrieve some hottest repositories in created in last week.

  Scenario: Get an Error
    Given url microserviceUrl
    And path '/hottest/repo'
    * param count = 'a'
    When method GET
    Then status 400


  Scenario: Get given number of records
    Given url microserviceUrl
    And path '/hottest/repo'
    * param count = '7'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    * def hotRepos = { watchers_count : '#string', language : '#string', description : '#string', name : '#string', html_url : '#string' }
    # The response should have an array of 7 repo objects
    And match response == '#[7] hotRepos'
