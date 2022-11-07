package com.ninjaone;

import com.ninjaone.model.Movies;
import com.ninjaone.service.MoviesService;

import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static String mainActor = "Kevin Bacon";
    private static final Logger log = Logger.getLogger(MoviesService.class.getName());

    public static void main(String[] args) throws Exception {

        log.info(String.format("Searching degrees between %s and %s","algo", mainActor));
        MoviesService moviesService = new MoviesService();
        List<Movies> movies = moviesService.getMoviesByFilename("movies.json");
        moviesService.getKevinBaconNumber(movies);
        log.info("");
    }



}


