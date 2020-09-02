import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Filename: Graph.java Project: p4 Authors: Qinglang Ye Course: cs400 Email: qye25@wisc.edu
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {

    /**
     * 
     * GraphNode class stores name of a vertex and a list of adjacent vertices
     *
     */
    class GraphNode {
        private String name;
        private ArrayList<GraphNode> adjList;

        /*
         * constructor initialize the name of a vertex and create an empty adjacency list
         */
        private GraphNode(String vertex) {
            this.name = vertex;
            adjList = new ArrayList<GraphNode>();
        }

        /*
         * add edge between current node and node v do nothing if node v is null or already linked
         * with current node
         */
        private void addEdge(GraphNode v) {
            if (!adjList.contains(v) && v != null) {
                adjList.add(v);
            }
        }

        /*
         * remove edge between current node and node v do nothing if node v is null or does not
         * exist in the adjacency list
         */
        private void removeEdge(GraphNode v) {
            if (v != null && adjList.contains(v)) {
                adjList.remove(v);
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         * 
         * @returns true if two nodes have the same name
         */
        @Override
        public boolean equals(Object v) {
            if (v instanceof GraphNode) {
                GraphNode v2 = (GraphNode) v;
                if (v2.name.equals(this.name)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    private List<GraphNode> vertices;

    /*
     * Default no-argument constructor
     */
    public Graph() {
        vertices = new ArrayList<GraphNode>();
    }

    /**
     * Add new vertex to the graph.
     *
     * If vertex is null or already exists, method ends without adding a vertex or throwing an
     * exception.
     * 
     * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
     */
    public void addVertex(String vertex) {
        if (vertex == null) {
            return;
        }
        GraphNode v = new GraphNode(vertex);
        if (vertices.contains(v)) {
            return;
        } else {
            vertices.add(v);
        }
    }

    /**
     * Remove a vertex and all associated edges from the graph.
     * 
     * If vertex is null or does not exist, method ends without removing a vertex, edges, or
     * throwing an exception.
     * 
     * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
     */
    public void removeVertex(String vertex) {

        if (vertex == null) {
            return;
        } else if (!contains(vertex)) {
            return;
        } else {
            GraphNode remove = getVertex(vertex);
            vertices.remove(remove); // remove vertex
            for (GraphNode v : vertices) {
                v.removeEdge(remove); // remove linking edges
            }
        }
    }

    /**
     * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted) If
     * either vertex does not exist, no edge is added and no exception is thrown. If the edge exists
     * in the graph, no edge is added and no exception is thrown.
     * 
     * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
     * edge is not in the graph
     */
    public void addEdge(String vertex1, String vertex2) {
        if (vertex1.equals(vertex2)) {
            return;
        }
     if (!contains(vertex1)) {
        addVertex(vertex1);
    }
     if (!contains(vertex2)) {
        addVertex(vertex2);
    }
     GraphNode start =getVertex(vertex1);
     GraphNode end=getVertex(vertex2);
     start.addEdge(end);
    }

    /**
     * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed and unweighted) If
     * either vertex does not exist, or if an edge from vertex1 to vertex2 does not exist, no edge
     * is removed and no exception is thrown.
     * 
     * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
     * edge from vertex1 to vertex2 is in the graph
     */
    public void removeEdge(String vertex1, String vertex2) {

        try {
            GraphNode start = getVertex(vertex1);
            GraphNode end = getVertex(vertex2);
            if (start == null || end == null || vertex1.equals(vertex2)) {
                return;
            }
            start.removeEdge(end);

        } catch (Exception e) {
            return;// TODO: handle exception
        }
    }

    /**
     * Returns a Set that contains all the vertices
     * 
     */
    public Set<String> getAllVertices() {

        Set<String> set = new HashSet<String>();
        for (GraphNode v : vertices) {
            set.add(v.name);
        }
        return set;
    }

    /**
     * Get all the neighbor (adjacent) vertices of a vertex
     *
     */
    public List<String> getAdjacentVerticesOf(String vertex) {
        GraphNode node = getVertex(vertex);
        List<String> list = new ArrayList<String>();
        for (GraphNode v : node.adjList) {
            list.add(v.name);
        }
        return list;
    }

    /**
     * Returns the number of edges in this graph.
     */
    public int size() {
        if (vertices.isEmpty()) {
            return 0;
        } else {
            int size = 0;
            for (GraphNode v : vertices) {
                size += v.adjList.size();
            }
            return size;
        }
    }

    /**
     * Returns the number of vertices in this graph.
     */
    public int order() {

        return vertices.size();

    }

    /**
     * 
     * @return true if the graph contains a vertex
     */
    private boolean contains(String vertex) {
        for (GraphNode v : vertices) {
            if (v.name.equals(vertex)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param vertex
     * @return the graphnode with key vertex
     * @throws Exception if vertex not exists in the graph
     */
    private GraphNode getVertex(String vertex) {
        for (GraphNode v : vertices) {
            if (v.name.equals(vertex)) {
                return v;
            }
        }
        return null;
    }
}
