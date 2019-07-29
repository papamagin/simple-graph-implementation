package maginab.graph.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import maginab.graph.GraphHelper;

abstract public class AbstractGraph<T> implements Graph<T> {
    protected final Map<T, Set<T>> mGraph = new ConcurrentHashMap<>();
    protected final Semaphore mutex = new Semaphore(1);

    @Override
    public boolean addVertex(T vertex) {
        try {
            mutex.acquire();
            try {
                if (vertex == null) {
                    throw new IllegalArgumentException("Vertex must be not null");
                }
                if (mGraph.containsKey(vertex)) {
                    return false;
                }
                mGraph.put(vertex, new HashSet<>());
                return true;
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                mutex.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<T> getEdgesFrom(T vertex) {
        Set<T> result = mGraph.get(vertex);
        if (result == null) {
            throw new NoSuchElementException("Vertex " + vertex + " is missing");
        }
        return Collections.unmodifiableSet(result);
    }

    @Override
    public Optional<List<T>> getPath(T fromVertex, T toVertex) {
        // TODO: pass here deep copy of this for thread-safe
        return GraphHelper.getPath(this, fromVertex, toVertex);
    }

    @Override
    public Set<T> getVertexes() {
        return Collections.unmodifiableSet(mGraph.keySet());
    }

    @Override
    public String toString() {
        return mGraph.toString();
    }
}
