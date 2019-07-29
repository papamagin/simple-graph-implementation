# Graph
Simple graph implementation. <br />

###Requirements
Simple Graph lib: <br />

Should support 2 types of graphs - directed and undirected with 3 operations: <br />

 - addVertex - adds vertex to the graph

 - addEdge - adds edge to the graph

 - getPath - returns a list of edges between 2 vertices (path doesn’t have to be optimal)

Questions to be ready to answer (don’t have to implement): <br />

 - Add weighted edges support in your lib.

 - Add traverse function that will take a user defined function and apply it on every vertex of the graph.

 - Make you graphs thread safe.
 
###Restrictions
Due to time restrictions:
 - Path is always acyclic
 - Path from vertex to the same vertex is just list containing this vertex only
 - Path is shortest way represented with list starts with fromVertex and ends with toVertex
 - getPath() is not thread-safe. Need to pass deep copy of Graph to the Helper (using Jackson json read-write for example)

###How to build and run
mvn compile <br />
mvn package <br />
java -jar target/maginab-graph-0.1.jar <br />
 