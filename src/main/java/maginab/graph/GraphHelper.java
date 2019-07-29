package maginab.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import maginab.graph.model.Graph;

public class GraphHelper {

    public static <T> Optional<List<T>> getPath(Graph<T> graph, T fromVertex, T toVertex) {
        Set<T> vertexes = graph.getVertexes();

        if (fromVertex == null || toVertex == null) {
            return Optional.empty();
        }

        if (!vertexes.contains(fromVertex) || !vertexes.contains(toVertex)) {
            return Optional.empty();
        }

        if (fromVertex.equals(toVertex)) {
            return Optional.of(List.of(fromVertex));
        }

        Set<T> visited = new HashSet<>();
        Map<T, T> stored = new HashMap<>();
        Queue<T> queue = new ArrayDeque<>();
        queue.add(fromVertex);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            visited.add(current);
            graph.getEdgesFrom(current).stream()
                    .filter(next -> !visited.contains(next))
                    .forEach(next -> {
                        visited.add(next);
                        stored.put(next, current);
                        queue.add(next);
                    });
        }

        return restorePath(fromVertex, toVertex, stored, visited);
    }

    // assumes that stored map has correct history of explored graph
    private static <T> Optional<List<T>> restorePath(T fromVertex, T toVertex, Map<T, T> stored, Set<T> visited) {
        if (!visited.contains(toVertex)) {
            return Optional.empty();
        }

        List<T> result = new ArrayList<>();
        T current = toVertex;
        while (!fromVertex.equals(current)) {
            result.add(current);
            current = stored.get(current);
        }
        result.add(fromVertex);

        Collections.reverse(result);
        return Optional.of(Collections.unmodifiableList(result));
    }
}
