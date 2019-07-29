package maginab.graph.model;

import java.util.NoSuchElementException;

public class UndirectedGraph<T> extends AbstractGraph<T> {

    @Override
    public void addEdge(T fromVertex, T toVertex) {
        try {
            mutex.acquire();
            try {
                if (!mGraph.containsKey(fromVertex) || !mGraph.containsKey(toVertex)) {
                    throw new NoSuchElementException(
                            "Either fromVertex " + fromVertex + " or toVertex " + toVertex + " is missing");
                }
                mGraph.get(fromVertex).add(toVertex);
                mGraph.get(toVertex).add(fromVertex);
            } catch (NoSuchElementException e) {
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
    public GraphType getGraphType() {
        return GraphType.UNDIRECTED;
    }
}
