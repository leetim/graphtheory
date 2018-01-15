import dijkstra.AdjacencyListGraph;
import static dijkstra.Dijkstra.dijkstra;
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
    
    
        public static void main1(String args[]) {
		double[] dist = dijkstra(
				new double[][] { { 0, 5, 28, 30 }, { 5, 0, 10, 14 }, { 28, 10, 0, 15 }, { 30, 14, 15, 0 } }, 0);
		for (int i = 0; i < dist.length; i++)
			System.out.println(dist[i]);
		System.out.println();

		dist = dijkstra(new ArrayList<>(Arrays.asList(
				new ArrayList<>(Arrays.asList(0.0,  5.0,  28.0, 30.0)),
				new ArrayList<>(Arrays.asList(5.0,  0.0,  10.0, 14.0)),
				new ArrayList<>(Arrays.asList(28.0, 10.0, 0.0,  15.0)),
				new ArrayList<>(Arrays.asList(30.0, 14.0, 15.0, 0.0)))), 0);
		for (int i = 0; i < dist.length; i++)
			System.out.println(dist[i]);
		System.out.println();

		ArrayList<AdjacencyListGraph> tmp = AdjacencyListGraph.InitAdjacencyListGraph(
				new double[][] { { 0, 5, 28, 30 }, { 5, 0, 10, 14 }, { 28, 10, 0, 15 }, { 30, 14, 15, 0 } });
		dist = dijkstra(tmp, 0);
		for (int i = 0; i < dist.length; i++)
			System.out.println(dist[i]);
		System.out.println();

		tmp = AdjacencyListGraph
				.InitAdjacencyListGraph(Arrays.asList(
						new ArrayList<>(Arrays.asList(0.0,  5.0,  28.0, 30.0)),
						new ArrayList<>(Arrays.asList(5.0,  0.0,  10.0, 14.0)),
						new ArrayList<>(Arrays.asList(28.0, 10.0, 0.0,  15.0)),
						new ArrayList<>(Arrays.asList(30.0, 14.0, 15.0, 0.0))));
		dist = dijkstra(tmp, 0);
		for (int i = 0; i < dist.length; i++)
			System.out.println(dist[i]);
		System.out.println();
	}

        public static Edge minimize_LM(WayGetter wg, Graph g){
            Edge min_e = null;
            double min_len = 1E300;
            int[] vertexs = g.get_vertexs();
            for (int v: vertexs){
                for (Edge e: g.get_edges(v)){
                    Graph new_g = g.rm_edge(e.from, e.to);
                    double cur_len = 0.0;
                    for (int i: vertexs){
                        double[] ways_len = wg.get_best_ways_len(i, new_g);
                        for (double f: ways_len){
                            cur_len += 1.0/f;
                        }
                    }
                    cur_len *= 1.0/(vertexs.length*vertexs.length - vertexs.length);
                    if (cur_len < min_len){
                        min_e = e;
                        min_len = cur_len;
                    }
                }
            }
            return min_e;
        }
        
        public static void main(String argv[]){
        
            Scanner in = new Scanner(System.in);
        }
}
