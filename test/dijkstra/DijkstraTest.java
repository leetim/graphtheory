/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import graph.Edge;
import graph.Graph;
import java.util.ArrayList;
import java.util.Map;
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
public class DijkstraTest {
    
    private static Dijkstra d;
    
    public DijkstraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        d = new Dijkstra();
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
     * Test of dijkstra_getMass method, of class Dijkstra.
     */
    @Test
    public void testDijkstra_getMass_Graph_int() {
        System.out.println("dijkstra_getMass");
        Edge[] edges = {
            new Edge(1, 2, 0.1),
            new Edge(1, 5, 0.2),
            new Edge(2, 6, 2.1),
            new Edge(3, 4, 3.0),
            new Edge(3, 5, 2.0),
            new Edge(4, 6, 1.0),
            new Edge(5, 4, 0.2),
            new Edge(4, 6, 1.0),
            new Edge(6, 2, 1.2),
            new Edge(6, 3, 1.0),
        };
        Graph graph = new Graph(edges);
        int start = 1;
        double[] expResult = {Dijkstra.INF, 0, 0.1, 2.4, 0.4, 0.2, 1.4};
        double[] result = Dijkstra.dijkstra_getMass(graph, start);
        assertArrayEquals(expResult, result, 0.00001);
    }
}
