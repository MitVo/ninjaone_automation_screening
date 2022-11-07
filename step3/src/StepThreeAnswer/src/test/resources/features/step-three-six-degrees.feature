Feature: Six Degrees of Separation

  Scenario: I enter one actor's name
    Given I run the application
    And  "80s-movies.json" exists from step one
    When  I provide an actor(s) name(s) Tom Cruise as parameters
    Then I should see the number 2 of degrees of separation
    And I see the result "list of movies describing the degree steps"

# There are 2 degrees of separation between Tom Cruise and Kevin Bacon
# Ralph Macchio starred with Tom Cruise in The Outsiders
# John Lithgow starred with Ralph Macchio in Distant Thunders
# Kevin Bacon starred with John Lithgow in Footloose.

  Scenario: I enter two actors' name
    Given I run the application
    And  "80s-movies.json" exists from step one
    When  I provide an actor(s) name(s) Tom Cruise,Sylvester Stallone as a parameters
    Then I should see the number 2 of degrees of separation
    And I see the result "list of movies describing the degree steps"

# There are 2 degrees of separation between Tom Cruise and Sylvester Stallone
# Tom Cruise starred with Tom Skerrit in Top Gun.
# Tom Skerrit starred with Dolly Parton in Steel Magnolias
# Dolly Parton starred with Sylvester Stallone in Rhinestone

  Scenario: I enter one actor's name that did not star in a movie
    Given I run the application
    And  "80s-movies.json" exists from step one
    When  I provide an actor(s) name(s) Buzz Lightyear as a parameters
    Then I should see the number -1 of degrees of separation
    And I see the result "Buzz Lightyear did not star in a movie in the data provided"

# Buzz Lightyear did not star in a movie in the data provided.

  Scenario: I enter one actor's name that did not star in a movie
    Given I run the application
    And  "80s-movies.json" exists from step one
    When  I provide an actor(s) name(s) Tom Cruise,Buzz Lightyear as a parameters
    Then I should see the number -1 of degrees of separation
    And I see the result "Buzz Lightyear did not star in a movie in the data provided"

# Buzz Lightyear did not star in a movie in the data provided.