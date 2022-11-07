package com.ninjaone.cucumber;

import com.ninjaone.service.MoviesService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {


    MoviesService moviesService = new MoviesService();
    Integer degrees;

    @Given("I run the application")
    public void andRunApp(){
        System.out.println("Running the app...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("{string} exists from step one")
    public void givenAFile(String filename) {
        moviesService.setSourceFilename(filename);
        moviesService.setMovies(moviesService.getMoviesByFilename(filename));
    }


    @When("I provide an actor(s) name(s) (.*) as a parameter")
    public void whenProvideNameActors(List<String> actors) {
        moviesService.setActorsToFind(actors);
    }

    @Then("I should see the number {int} of degrees of separation")
    public void thenSeeNumberDegrees(int expectedDegrees) throws Exception {
        moviesService.getKevinBaconNumber(moviesService.getMovies());
        degrees = expectedDegrees;
    }

    @And("I see the result {string} ")
    public void andFileContent(String expectedValue) {
        assertEquals(expectedValue, moviesService.getMessage());
        assertEquals(degrees.intValue(), moviesService.getKbValue());
    }
}
