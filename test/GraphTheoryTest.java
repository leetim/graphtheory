/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dijkstra.Dijkstra;
import graph.Edge;
import graph.Graph;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author artem
 */
public class GraphTheoryTest {
    
    public GraphTheoryTest() {
    }

    /**
     * Test of minimize_LM method, of class GraphTheory.
     */
    @Test
    public void testMinimize_LM() {
        System.out.println("minimize_LM");
        Edge[] edges = {
            new Edge(1, 2, 2.0),
            new Edge(2, 3, 2.0),
            new Edge(2, 14, 1.0),
            new Edge(3, 4, 2.0),
            new Edge(3, 8, 2.0),
            new Edge(4, 5, 2.0),
            new Edge(4, 17, 1.0),
            new Edge(5, 19, 1.0),
            new Edge(6, 7, 2.0),
            new Edge(7, 8, 2.0),
            new Edge(7, 17, 1.0),
            new Edge(8, 3, 2.0),
            new Edge(8, 9, 2.0),
            new Edge(9, 10, 2.0),
            new Edge(9, 14, 1.0),
            new Edge(10, 12, 1.0),
            new Edge(11, 12, 0.5),
            new Edge(12, 1, 1.0),
            new Edge(12, 11, 1.0),
            new Edge(13, 14, 0.5),
            new Edge(14, 2, 1.0),
            new Edge(14, 9, 1.0),
            new Edge(14, 13, 0.5),
            new Edge(14, 15, 0.5),
            new Edge(15, 14, 0.5),
            new Edge(16, 17, 0.5),
            new Edge(17, 4, 1.0),
            new Edge(17, 7, 1.0),
            new Edge(17, 16, 0.5),
            new Edge(17, 18, 0.5),
            new Edge(18, 17, 0.5),
            new Edge(19, 6, 1.0),
            new Edge(19, 20, 0.5),
            new Edge(20, 19, 0.5),  
        };
        Graph g = new Graph(edges);
        Edge e = GraphTheory.minimize_LM_by_edge(new Dijkstra(), g);
        assertEquals(3, e.from);
        assertEquals(4, e.to);
        assertEquals(2.0, e.weight, 0.00001);
        System.out.println(String.format("%d %d %f", e.from, e.to, e.weight));
    }

//    /**
//     * Test of main method, of class GraphTheory.
//     */
//    @Test
//    public void testMain() throws Exception {
//    }
    
}
