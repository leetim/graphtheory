import dijkstra.AdjacencyListGraph;
import dijkstra.Dijkstra;
import graph.Edge;
import graph.Graph;
import graph.WayGetter;
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
    
    public static Edge minimize_LM(WayGetter wg, Graph g){
            Edge min_e = null;
            double min_len = Double.POSITIVE_INFINITY;
            int[] vertexs = g.get_vertexs();
            for (int v: vertexs){
                for (Edge e: g.get_edges(v)){
                    Graph new_g = g.rm_edge(e.from, e.to);
                    double cur_len = get_LM(wg, new_g);
                    if (cur_len < min_len){
                        min_e = e;
                        min_len = cur_len;
                    }
                }
            }
            return min_e;
        }
        
        public static void main(String argv[]) throws Exception{
            Scanner in = new Scanner(System.in);
            Edge[] edges = {
                new Edge(1, 2, 0.1),
                new Edge(1, 4, 0.2),
                new Edge(1, 5, 2.0),
                new Edge(2, 4, 0.2),
                new Edge(4, 5, 0.3),
                new Edge(5, 3, 0.1),
            };
            Graph g = new Graph(edges);
            double[] dist = (new Dijkstra()).get_best_ways_len(1, g);
            Edge[] ale = g.get_edges(1);
            int[] vers = g.get_vertexs();
            minimize_LM(new Dijkstra(), g);
            if (true){
                return;
            }
            try{                
                while (in.hasNext()){
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
                Edge min_e = minimize_LM(new Dijkstra(), g);
                System.out.println(String.format("Edge: %d -> %d: %f", min_e.from, min_e.to, min_e.weight));
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
}
