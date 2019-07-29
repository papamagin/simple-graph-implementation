package maginab.graph.model;

import java.util.List;

import maginab.graph.GraphHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphHelperTest {

    @Test
    public void testGetPathForEmptyGraph() {
        Graph<Integer> directedGraph = new DirectedGraph<>();
        assertTrue(GraphHelper.getPath(directedGraph, 1, 2).isEmpty());

        Graph<Integer> undirectedGraph = new UndirectedGraph<>();
        assertTrue(GraphHelper.getPath(undirectedGraph, 1, 2).isEmpty());
    }

    @Test
    public void testGetPathForNonexistentVertex() {
        Graph<Integer> directedGraph = new DirectedGraph<>();
        directedGraph.addVertex(1);
        assertTrue(GraphHelper.getPath(directedGraph, 1, 2).isEmpty());

        Graph<Integer> undirectedGraph = new UndirectedGraph<>();
        undirectedGraph.addVertex(1);
        assertTrue(GraphHelper.getPath(undirectedGraph, 1, 2).isEmpty());
    }

    @Test
    public void getPathForTrivialGraph(){
        Graph<Integer> directedGraph = new DirectedGraph<>();
        directedGraph.addVertex(1);
        assertEquals(GraphHelper.getPath(directedGraph, 1, 1).get(), List.of(1));

        Graph<Integer> undirectedGraph = new UndirectedGraph<>();
        undirectedGraph.addVertex(1);
        assertEquals(GraphHelper.getPath(undirectedGraph, 1, 1).get(), List.of(1));
    }

    @Test
    public void testGetLoopPath() {
        Graph<Integer> directedGraph = new DirectedGraph<>();
        directedGraph.addVertex(1);
        directedGraph.addEdge(1, 1);
        assertEquals(GraphHelper.getPath(directedGraph, 1, 1).get(), List.of(1));

        Graph<Integer> undirectedGraph = new UndirectedGraph<>();
        undirectedGraph.addVertex(1);
        undirectedGraph.addEdge(1, 1);
        assertEquals(GraphHelper.getPath(undirectedGraph, 1, 1).get(), List.of(1));
    }

    @Test
    public void testGetPathForSimpleUnlinkedVertexes() {
        Graph<Integer> directedGraph = new DirectedGraph<>();
        directedGraph.addVertex(1);
        directedGraph.addVertex(2);
        directedGraph.addVertex(3);
        directedGraph.addEdge(2, 3);
        assertTrue(GraphHelper.getPath(directedGraph, 1, 2).isEmpty());

        Graph<Integer> undirectedGraph = new UndirectedGraph<>();
        undirectedGraph.addVertex(1);
        undirectedGraph.addVertex(2);
        undirectedGraph.addVertex(3);
        undirectedGraph.addEdge(2, 3);
        assertTrue(GraphHelper.getPath(undirectedGraph, 1, 2).isEmpty());
    }

    @Test
    public void testGetPathForSimpleDirectedGraph() {
        Graph<Integer> graph = new DirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        assertEquals(GraphHelper.getPath(graph, 1, 2).get(), List.of(1, 2));
        assertTrue(GraphHelper.getPath(graph, 2, 1).isEmpty());

        graph.addVertex(3);
        graph.addEdge(2, 3);
        assertEquals(GraphHelper.getPath(graph, 1, 3).get(), List.of(1, 2, 3));
        assertTrue(GraphHelper.getPath(graph, 2, 1).isEmpty());
        assertTrue(GraphHelper.getPath(graph, 3, 1).isEmpty());
        assertTrue(GraphHelper.getPath(graph, 3, 2).isEmpty());

        graph.addEdge(1, 3);
        assertEquals(GraphHelper.getPath(graph, 1, 3).get(), List.of(1, 3));

        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addEdge(5, 4);
        graph.addEdge(5, 6);
        assertEquals(GraphHelper.getPath(graph, 5, 4).get(), List.of(5, 4));
        assertEquals(GraphHelper.getPath(graph, 5, 6).get(), List.of(5, 6));
        assertTrue(GraphHelper.getPath(graph, 4, 5).isEmpty());
        assertTrue(GraphHelper.getPath(graph, 4, 6).isEmpty());
        assertTrue(GraphHelper.getPath(graph, 6, 5).isEmpty());
        assertTrue(GraphHelper.getPath(graph, 6, 4).isEmpty());

        assertTrue(GraphHelper.getPath(graph, 1, 5).isEmpty());
    }

    @Test
    public void testGetPathForSimpleUndirectedGraph() {
        Graph<Integer> graph = new UndirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        assertEquals(GraphHelper.getPath(graph, 1, 2).get(), List.of(1, 2));
        assertEquals(GraphHelper.getPath(graph, 2, 1).get(), List.of(2, 1));

        graph.addVertex(3);
        graph.addEdge(2, 3);
        assertEquals(GraphHelper.getPath(graph, 1, 3).get(), List.of(1, 2, 3));
        assertEquals(GraphHelper.getPath(graph, 3, 1).get(), List.of(3, 2, 1));
        assertEquals(GraphHelper.getPath(graph, 2, 3).get(), List.of(2, 3));
        assertEquals(GraphHelper.getPath(graph, 3, 2).get(), List.of(3, 2));

        graph.addEdge(1, 3);
        assertEquals(GraphHelper.getPath(graph, 1, 3).get(), List.of(1, 3));
    }

    @Test
    public void testGetPathForCyclicDirectGraph() {
        Graph<Integer> squareGraph = new DirectedGraph<>();
        squareGraph.addVertex(1);
        squareGraph.addVertex(2);
        squareGraph.addVertex(3);
        squareGraph.addVertex(4);
        squareGraph.addEdge(1, 2);
        squareGraph.addEdge(2, 3);
        squareGraph.addEdge(3, 4);
        squareGraph.addEdge(4, 1);
        assertEquals(GraphHelper.getPath(squareGraph, 1, 4).get(), List.of(1, 2, 3, 4));
        assertEquals(GraphHelper.getPath(squareGraph, 2, 1).get(), List.of(2, 3, 4 , 1));

        squareGraph.addEdge(2, 4);
        assertEquals(GraphHelper.getPath(squareGraph, 1, 4).get(), List.of(1, 2, 4));
    }

    @Test
    public void testGetPathForCyclicUndirectGraph() {
        Graph<Integer> squareGraph = new UndirectedGraph<>();
        squareGraph.addVertex(1);
        squareGraph.addVertex(2);
        squareGraph.addVertex(3);
        squareGraph.addVertex(4);
        squareGraph.addEdge(1, 2);
        squareGraph.addEdge(2, 3);
        squareGraph.addEdge(3, 4);
        squareGraph.addEdge(4, 1);
        assertEquals(GraphHelper.getPath(squareGraph, 1, 2).get(), List.of(1, 2));
        assertEquals(GraphHelper.getPath(squareGraph, 1, 3).get().size(), 3);
        assertTrue(GraphHelper.getPath(squareGraph, 1, 3).get().containsAll(List.of(1, 3)));
        assertEquals(GraphHelper.getPath(squareGraph, 1, 4).get(), List.of(1, 4));
        assertEquals(GraphHelper.getPath(squareGraph, 2, 4).get().size(), 3);
        assertTrue(GraphHelper.getPath(squareGraph, 2, 4).get().containsAll(List.of(2, 4)));

        squareGraph.addEdge(2, 4);
        assertEquals(GraphHelper.getPath(squareGraph, 2, 4).get(), List.of(2, 4));

        Graph<Integer> pentaGraph = new UndirectedGraph<>();
        pentaGraph.addVertex(1);
        pentaGraph.addVertex(2);
        pentaGraph.addVertex(3);
        pentaGraph.addVertex(4);
        pentaGraph.addVertex(5);
        pentaGraph.addEdge(1, 2);
        pentaGraph.addEdge(2, 3);
        pentaGraph.addEdge(3, 4);
        pentaGraph.addEdge(4, 5);
        pentaGraph.addEdge(5, 1);
        assertEquals(GraphHelper.getPath(pentaGraph, 1, 2).get(), List.of(1, 2));
        assertEquals(GraphHelper.getPath(pentaGraph, 1, 3).get(), List.of(1, 2, 3));
        assertEquals(GraphHelper.getPath(pentaGraph, 1, 4).get(), List.of(1, 5, 4));
        assertEquals(GraphHelper.getPath(pentaGraph, 1, 5).get(), List.of(1, 5));

        pentaGraph.addEdge(1, 4);
        assertEquals(GraphHelper.getPath(pentaGraph, 1, 4).get(), List.of(1, 4));
    }
}
