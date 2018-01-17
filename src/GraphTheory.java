import dijkstra.AdjacencyListGraph;
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
        for (int i: vertexs){
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
