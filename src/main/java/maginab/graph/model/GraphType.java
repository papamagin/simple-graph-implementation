package maginab.graph.model;

public enum GraphType {

    DIRECTED("directed"),
    UNDIRECTED("undirected");

    private String value;

    GraphType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
