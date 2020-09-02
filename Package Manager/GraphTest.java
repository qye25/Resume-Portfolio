/**
 * Filename: GraphTest.java 
 * Project: p4 
 * Authors: Qinglang Ye
 * Course:  cs400
 * Email:   qye25@wisc.edu   
 * 
 * JUnit Test of directed and unweighted graph implementation
 */

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {

    Graph graph;

    // TODO: add code that runs before each GraphTest method
    @BeforeEach
    public void setUp() throws Exception {
        graph = new Graph();

    }

    // TODO: add code that runs after each GraphTest method
    @AfterEach
    public void tearDown() throws Exception {
        graph = null;
    }

    /**
     * Test the constructor a new graph should be initialized with no vertices and edges
     */
    @Test
    void test0_default_test() {
        Graph graph = new Graph();
        assertEquals(0, graph.size());
        assertEquals(0, graph.order());
    }

    /*
     * Test addVertex, should only increase order
     */
    @Test
    void test1_adding_vertex_increase_order_but_not_size() {
        graph.addVertex("a");
        assertEquals(0, graph.size());
        assertEquals(1, graph.order());
    }

    /*
     * adding duplicate vertices should not throw any exception or make changes to the graph
     */
    @Test
    void test2_adding_duplicate_vertex_not_increase_order_or_size() {
        graph.addVertex("a");
        assertEquals(0, graph.size());
        assertEquals(1, graph.order());
        graph.addVertex("b");
        assertEquals(0, graph.size());
        assertEquals(2, graph.order());
        graph.addVertex("a");
        assertEquals(0, graph.size());
        assertEquals(2, graph.order());
    }

    /**
     * Test add directed edges edge a-b should not be the same as edge b-a
     */
    @Test
    void test3_adding_edges_increase_size() {
        graph.addVertex("a");
        assertEquals(0, graph.size());
        assertEquals(1, graph.order());
        graph.addVertex("b");
        graph.addEdge("a", "b");
        assertEquals(1, graph.size());
        assertEquals(2, graph.order());
        graph.addEdge("b", "a");
        assertEquals(2, graph.size());
        assertEquals(2, graph.order());
    }

    /**
     * test add duplicate edges 
     * test add edge when either or both vertices not exist should not
     * throw any exception or change the graph
     */
    @Test
    void test4_adding_unvalid_edges_add_vertices() {
        try {
            graph.addVertex("a");
            graph.addVertex("b");
            graph.addEdge("a", "b");
            graph.addEdge("c", "b");
            graph.addEdge("c", "e");
            graph.addEdge("b", "b");
           // System.out.println(graph.size());
            assertEquals(3, graph.size());
            Set<String> expVertices=new HashSet<>();
            expVertices.add("a");
            expVertices.add("b");
            expVertices.add("c");
            expVertices.add("e");
            assertEquals(expVertices, graph.getAllVertices());
           // System.out.println(graph.order());
            assertEquals(4, graph.order());
        } catch (Exception e) {
            fail(""); // TODO: handle exception
        }
    }
    
    /*
     * Test remove valid vertex
     */
    @Test
    void test5_remove_vertex_reduce_order_and_all_linking_edges() {
      try {  graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addEdge("a", "b");
        graph.addEdge("c", "b");
        graph.addEdge("c", "d");
        graph.addEdge("b", "d");
        graph.addEdge("a", "c");
        graph.removeVertex("c");
        assertEquals(2, graph.size());
        List<String> adj=new ArrayList<>();
        adj.add("b");
        //System.out.println(graph.getAdjacentVerticesOf("a"));
        assertEquals(adj, graph.getAdjacentVerticesOf("a"));}catch (Exception e) {
            fail("");// TODO: handle exception
        }
    }
    
    /**
     * Test remove invalid vertex and edge should not change the graph
     */
    @Test
    void test6_remove_invalid_vertex() {
       try { graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
       
        Set<String> expAllVertices=new HashSet<>();
        expAllVertices.add("a");
        expAllVertices.add("b");
        expAllVertices.add("c");
        expAllVertices.add("d");
        
        graph.removeVertex("h");
        assertEquals(expAllVertices, graph.getAllVertices());
        
        graph.removeEdge("a", "c");
        assertEquals(expAllVertices, graph.getAllVertices());}catch (Exception e) {
            fail("");// TODO: handle exception
        }
    }
    
   
}
