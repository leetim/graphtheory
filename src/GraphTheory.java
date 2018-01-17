import dijkstra.Dijkstra;
import graph.Edge;
import graph.Graph;
import graph.WayGetter;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author artem
 */
public class GraphTheory {
    
    /**
     *
     * @param wg
     * @param g
     * @return Ребро, убрав из графа которое будет минимизирована LM метрика для графа.
     */
    
    public enum Method{
        DEFAULT,
        RM_EDGE,
        RM_VERTEX,
    }
    
    public static double get_LM(WayGetter wg, Graph g){
        double cur_len = 0.0;
        int[] vertexs = g.get_vertexs(); 
        int n = vertexs.length;
        int k = 0;
        for (int i: vertexs){
//            k++;
//            System.err.println(n - k);
            double[] ways_len = wg.get_best_ways_len(i, g);
            for (double f: ways_len){
                if (f < Dijkstra.INF && f != 0.0){
                    cur_len += 1.0/f;
                }
            }
        }
        cur_len *= 1.0/(vertexs.length*vertexs.length - vertexs.length);
        return cur_len;
    }
    
    public static Edge minimize_LM_by_edge(WayGetter wg, Graph g){
        Edge min_e = null;
        double min_len = Double.POSITIVE_INFINITY;
        int[] vertexs = g.get_vertexs();
        for (int v: vertexs){
            for (Edge e: g.get_edges(v)){
                System.err.println(String.format("Check %d -> %d: %f", e.from, e.to, e.weight));
                Graph new_g = g.rm_edge(e.from, e.to);
                double cur_len = get_LM(wg, new_g);
                System.err.println(String.format("Finish LM = %f newLM = %f", min_len, cur_len));
                if (cur_len < min_len){
                    min_e = e;
                    min_len = cur_len;
                }
            }
        }
        return min_e;
    }
    
    public static int minimize_LM_by_vert(WayGetter wg, Graph g){
        int min_v = -1;
        double min_len = Double.POSITIVE_INFINITY;
        int[] vertexs = g.get_vertexs();
        for (int v: vertexs){
            System.err.println(String.format("Check %d", v));
            Graph new_g = g.rm_vertex(v);
            double cur_len = get_LM(wg, new_g);
            System.err.println(String.format("Finish LM = %f newLM = %f", min_len, cur_len));
            if (cur_len < min_len){
                min_v = v;
                min_len = cur_len;
            }
        }
        return min_v;
    }
        
    public static void main(String argv[]) throws Exception{
//
//        Edge[] edges = {
//        new Edge(1, 2, 2.0),
//        new Edge(2, 3, 2.0),
//        new Edge(2, 14, 1.0),
//        new Edge(3, 4, 2.0),
//        new Edge(3, 8, 2.0),
//        new Edge(4, 5, 2.0),
//        new Edge(4, 17, 1.0),
//        new Edge(5, 19, 1.0),
//        new Edge(6, 7, 2.0),
//        new Edge(7, 8, 2.0),
//        new Edge(7, 17, 1.0),
//        new Edge(8, 3, 2.0),
//        new Edge(8, 9, 2.0),
//        new Edge(9, 10, 2.0),
//        new Edge(9, 14, 1.0),
//        new Edge(10, 12, 1.0),
//        new Edge(11, 12, 0.5),
//        new Edge(12, 1, 1.0),
//        new Edge(12, 11, 1.0),
//        new Edge(13, 14, 0.5),
//        new Edge(14, 2, 1.0),
//        new Edge(14, 9, 1.0),
//        new Edge(14, 13, 0.5),
//        new Edge(14, 15, 0.5),
//        new Edge(15, 14, 0.5),
//        new Edge(16, 17, 0.5),
//        new Edge(17, 4, 1.0),
//        new Edge(17, 7, 1.0),
//        new Edge(17, 16, 0.5),
//        new Edge(17, 18, 0.5),
//        new Edge(18, 17, 0.5),
//        new Edge(19, 6, 1.0),
//        new Edge(19, 20, 0.5),
//        new Edge(20, 19, 0.5),  
//    };
//    Graph g1 = new Graph(edges);
//    Dijkstra.dijkstra_getMass(g1, 1);
//    minimize_LM_by_edge(new Dijkstra(), g1);
//    if (true) return;
    int process_count = 1;
    Method m = Method.DEFAULT;
    for (int i = 0; i < argv.length; i++){
        switch(argv[i]){
            case "-v":
                m = Method.RM_VERTEX;
                break;
            case "-e":
                m = Method.RM_EDGE;
                break;
        }
    }
    Scanner in = new Scanner(new BufferedInputStream(System.in));
    Graph g = new Graph();
    try{                
        while (in.hasNextInt()){
            int v1, v2;
            double l;
            if (in.hasNextInt()){
                v1 = in.nextInt();
            }
            else{
                throw new Exception("Expected int!");
            }
            if (in.hasNextInt()){
                v2 = in.nextInt();
            }
            else{
                throw new Exception("Expected int!");
            }
            if (in.hasNextDouble()){
                l = in.nextDouble();
            }
            else{
                throw new Exception("Expected Double!");
            }
            g.add(new Edge(v1, v2, l));
        }
        switch(m){
            case DEFAULT:
            case RM_EDGE:{
                System.err.println("Start working!");
                Edge min_e = minimize_LM_by_edge(new Dijkstra(), g);
                System.out.println(String.format("Edge: %d -> %d: %f", min_e.from, min_e.to, min_e.weight));
                break;
            }
            case RM_VERTEX:{
                System.err.println("Start working!");
                int min_v = minimize_LM_by_vert(new Dijkstra(), g);
                System.out.println(String.format("Vertex: %d", min_v));
                break;
            }

        }
    }
    catch(Exception e){
        System.err.println(e.getMessage());
    }
}
}
