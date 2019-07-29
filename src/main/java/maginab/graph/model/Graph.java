package maginab.graph.model;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public interface Graph<T> {

    /**
     * @return false if vertex already exists
     */
    boolean addVertex(T vertex);

    /**
     * @throws NoSuchElementException if at least one of vertexes is missing
     */
    void addEdge(T fromVertex, T toVertex) throws NoSuchElementException;

    /**
     * Due to time restrictions:
     * 1. Path is always acyclic
     * 2. Path from vertex to the same vertex is just list of this vertex
     * 3. Path is shortest way represented with list starts with fromVertex and ends with toVertex
     *
     * @return optional list with sequens of vertexes which represents the path.
     * Optional.empty() if there are no path from fromVertex to toVertex
     */
    Optional<List<T>> getPath(T fromVertex, T toVertex);

    /**
     * @throws NoSuchElementException if vertex is missing
     */
    Set<T> getEdgesFrom(T vertex) throws NoSuchElementException;

    Set<T> getVertexes();

    GraphType getGraphType();
}
