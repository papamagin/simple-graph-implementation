package maginab.graph;

import maginab.graph.model.DirectedGraph;
import maginab.graph.model.UndirectedGraph;

public class Main {
    public static void main(String[] args) throws Exception {
        DirectedGraph<Long> directedGraph = new DirectedGraph<>();
        directedGraph.addVertex(1L);
        directedGraph.addVertex(2L);
        directedGraph.addEdge(1L, 2L);
        System.out.println("directed graph: " + directedGraph);
        System.out.println("path from 2 to 1: " + directedGraph.getPath(2L, 1L));

        UndirectedGraph<Long> undirectedGraph = new UndirectedGraph<>();
        undirectedGraph.addVertex(1L);
        undirectedGraph.addVertex(2L);
        undirectedGraph.addVertex(3L);
        undirectedGraph.addEdge(1L, 2L);
        undirectedGraph.addEdge(2L, 3L);
        System.out.println("undirected graph: " + undirectedGraph);
        System.out.println("path from 3 to 1: " + undirectedGraph.getPath(3L, 1L));
    }
}