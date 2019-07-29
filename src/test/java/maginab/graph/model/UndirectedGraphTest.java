package maginab.graph.model;

import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UndirectedGraphTest {

    @Test
    public void testEmptyGraph() {
        Graph<Integer> graph = new UndirectedGraph<>();
        assertTrue(graph.getVertexes().isEmpty());
    }

    @Test
    public void testAddVertex() {
        Graph<Integer> graph = new UndirectedGraph<>();

        assertTrue(graph.addVertex(1));
        assertEquals(graph.getVertexes(), Set.of(1));

        assertTrue(graph.addVertex(2));
        assertEquals(graph.getVertexes(), Set.of(1,2));

        assertTrue(graph.getEdgesFrom(1).isEmpty());
        assertTrue(graph.getEdgesFrom(2).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullVertex() {
        Graph<Integer> graph = new UndirectedGraph<>();
        graph.addVertex(null);
    }

    @Test
    public void testAddDuplicateVertex() {
        Graph<Integer> graph = new UndirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);

        assertFalse(graph.addVertex(1));
        assertEquals(graph.getVertexes(), Set.of(1,2));
    }

    @Test
    public void testAddEdge() {
        Graph<Integer> graph = new UndirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        assertEquals(graph.getEdgesFrom(1), Set.of(2, 3));
        assertEquals(graph.getEdgesFrom(2), Set.of(1));
        assertEquals(graph.getEdgesFrom(3), Set.of(1));
    }

    @Test
    public void testAddSelfEdge() {
        Graph<Integer> graph = new UndirectedGraph<>();
        graph.addVertex(1);
        graph.addEdge(1, 1);
        assertEquals(graph.getVertexes(), Set.of(1));
        assertEquals(graph.getEdgesFrom(1), Set.of(1));

        graph.addEdge(1, 1);
        assertEquals(graph.getEdgesFrom(1), Set.of(1));
    }

    @Test
    public void testAddExistingEdge() {
        Graph<Integer> graph = new UndirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 2);

        assertEquals(graph.getEdgesFrom(1), Set.of(2));
        assertEquals(graph.getEdgesFrom(2), Set.of(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetEdgesFromMissingVertex() {
        Graph<Integer> graph = new UndirectedGraph<>();
        graph.addVertex(1);
        Set<Integer> edges = graph.getEdgesFrom(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void testAddEdgeForEmptyGraph() {
        Graph<Integer> graph = new UndirectedGraph<>();
        graph.addEdge(1, 2);
    }

    @Test(expected = NoSuchElementException.class)
    public void testAddEdgeForMissingVertex() {
        Graph<Integer> graph = new UndirectedGraph<>();
        graph.addVertex(1);
        graph.addEdge(1, 2);
    }
}
