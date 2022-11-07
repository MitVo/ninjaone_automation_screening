package com.ninjaone.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ninjaone.model.Graph;
import com.ninjaone.model.Movies;
import com.ninjaone.model.Node;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoviesService {

    private final Logger log = Logger.getLogger(MoviesService.class.getName());
    private List<Movies> movies;
    private String sourceFilename;
    private List<String> actorsToFind;
    private String message;
    private int kbValue;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getKbValue() {
        return kbValue;
    }

    public void setKbValue(int kbValue) {
        this.kbValue = kbValue;
    }

    public List<String> getActorsToFind() {
        return actorsToFind;
    }

    public void setActorsToFind(List<String> actorsToFind) {
        this.actorsToFind = actorsToFind;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }

    public String getSourceFilename() {
        return sourceFilename;
    }

    public void setSourceFilename(String sourceFilename) {
        this.sourceFilename = sourceFilename;
    }


    public List<Movies> getMoviesByFilename(String filename){
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try {
            return mapper.readValue(classloader.getResource(filename), new TypeReference<List<Movies>>(){});
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error reading file :", e);
        }

        return Collections.emptyList();
    }


    public void getKevinBaconNumber(List<Movies> movies) throws Exception {
        if (actorsToFind.isEmpty()){
            this.kbValue = -1;
            this.message = "Not valid actors";
            throw new Exception("It seems you did not enter any name of actor to find... Please try again!");
        }

        if (actorsToFind.size() == 1){
            log.info(String.format("Finding degrees between %s and Kevin Bacon...",actorsToFind.get(0)));
        } else {
            log.info(String.format("Finding degrees between %s and %s ...", actorsToFind.get(0), actorsToFind.get(1)));
        }

        this.kbValue = 0;
    }

    /**
     * Base on BFS Algorithm
     * @param movies
     */
    public void createGraph(List<Movies> movies){
        Set<String> actors = new HashSet<>();

        Node kevinBaconNode = new Node();
        Graph graph = new Graph();


        //Fill the graph with the movie's information
        for (Movies movie: movies){

            for (String actor: movie.getCast()){

                //Check if the actor exist, and then verify if it is already in the graph
                if (!actors.contains(actor)){

                    actors.add(actor);
                    Set<Node> nodeList =  graph.getGraph().get(actor);
                    if (!nodeList.isEmpty()){
                        for (Node node: nodeList){
                            if (node.getNameActor().equals(actor)) {
                                node.getMovieTitles().add(movie.getTitle());
                                break;
                            }
                        }
                    } else {
                        Node newActor = new Node();
                        newActor.setNameActor(actor);
                        newActor.getMovieTitles().add(movie.getTitle());
                        //graph.getGraph().put(actor, )
                    }
                } else {
                    actors.add(actor);
                    addMovieTitleToActor(graph.getGraph().get(actor), actor, movie);

                }


            }


            /*if(movie.getCast().stream().anyMatch(s -> s.equals(mainActor))){
                mainActorMovies.add(movie);
                kevinBaconNode.addDestination(new Node(mainActor, movie.getTitle()), 0);
                movie.getCast().stream().filter(s -> !s.equals(mainActor))
                        .forEach(s -> kevinBaconNode.addDestination(new Node(s, movie.getTitle()), 1));
                graph.addNode(kevinBaconNode);
            } else{
                otherMovies.add(movie);
            }*/
        }
    }


    public void addMovieTitleToActor(Set<Node> nodeList, String actor, Movies movie){
        if (!nodeList.isEmpty()){
            for (Node node: nodeList){
                if (node.getNameActor().equals(actor)) {
                    node.getMovieTitles().add(movie.getTitle());
                    break;
                }
            }
        }
    }
}
