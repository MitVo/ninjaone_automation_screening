package com.ninjaone.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class Node {

    private String nameActor;
    private String title;
    @Builder.Default
    private List<String> movieTitles = new ArrayList<>();
    private Integer distance = 0;
    @Builder.Default
    private Map<Node, Integer> adjacentNodes = new HashMap<>();
    private Node actNode;

    public Node(String nameActor, String title) {
        this.nameActor = nameActor;
        this.title = title;
    }

    public Node(String nameActor){
        this.nameActor = nameActor;
    }

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public void addNode(Node node){
        this.actNode = node;
    }


}
