package com.ninjaone.model;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class Graph {

    private Set<Node> nodes = new HashSet<>();
    private Map<String, Set<Node>> graph = new HashMap<>();

    public void addNode(Node node){
        nodes.add(node);
    }
}
