/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author artem
 */
public class GraphTest {
    
    public GraphTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class Graph.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Edge e = new Edge(1, 2, 5.0);
        Graph g = new Graph();
        g.add(e);
        Edge[] res_e = g.get_edges(1);
        assertEquals(res_e.length, 1);
    }

    /**
     * Test of get_edges method, of class Graph.
     */
    @Test
    public void testGet_edges() {
        System.out.println("get_edges");
        Edge[] e = {
            new Edge(1, 2, 5.0),
            new Edge(1, 3, 2.5),
        };
        Graph g = new Graph(e);
        Edge[] res_e = g.get_edges(1);
        assertEquals(res_e.length, 2);
        assertArrayEquals(res_e, e);
    }

    /**
     * Test of get_vertexs method, of class Graph.
     */
    @Test
    public void testGet_vertexs() {
        System.out.println("get_vertexs");
        Edge[] e = {
            new Edge(1, 2, 5.0),
            new Edge(2, 5, 2.5),
            new Edge(3, 4, 2.5),
            new Edge(5, 3, 2.5)
        };
        Graph g = new Graph(e);
        int template_v[] = {1, 2, 3, 4, 5};
        int[] res_v = g.get_vertexs();
        assertArrayEquals(res_v, template_v);
    }

    /**
     * Test of rm_edge method, of class Graph.
     */
    @Test
    public void testRm_edge() {
        System.out.println("rm_edge");
        Edge[] e = {
            new Edge(1, 2, 5.0),
            new Edge(1, 5, 2.5),
            new Edge(1, 4, 2.5),
            new Edge(1, 3, 2.5)
        };
        Edge[] template_e = {
            new Edge(1, 2, 5.0),
            new Edge(1, 5, 2.5),
            new Edge(1, 3, 2.5)
        };
        Graph g = new Graph(e);
        Graph new_g = g.rm_edge(1, 4);
        Edge[] res_e = new_g.get_edges(1);
        assertEquals(template_e.length, res_e.length);
        for (int i = 0; i < res_e.length; i++){
            assertEquals(res_e[i].from, template_e[i].from);
            assertEquals(res_e[i].to, template_e[i].to);
            assertEquals(res_e[i].weight, template_e[i].weight, 0.00001);
        }
    }

    /**
     * Test of rm_edge method, of class Graph.
     */
    @Test
    public void testRm_vertex() {
        System.out.println("rm_edge");
        Edge[] e = {
            new Edge(1, 2, 5.0),
            new Edge(1, 5, 2.5),
            new Edge(1, 4, 2.5),
            new Edge(1, 3, 2.5)
        };
        int[] template_v = {1, 2, 3, 5};
        Graph g = new Graph(e);
        Graph new_g = g.rm_vertex(4);
        int[] res_v = new_g.get_vertexs();
        assertArrayEquals(template_v, res_v);
    }
    
}
