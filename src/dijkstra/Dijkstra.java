package dijkstra;

import graph.Edge;
import graph.Graph;
import graph.WayGetter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Dijkstra implements WayGetter {

	public static Double INF = Double.POSITIVE_INFINITY; // "Бесконечность"

	public Dijkstra() {}

	public static double[] dijkstra_getMass(Graph graph, int start) {
            int[] vertexs = graph.get_vertexs();
            Map<Integer, Double> dists = new TreeMap<Integer, Double>();
            Set<Integer> visited = new TreeSet<Integer>();
            for (int i: vertexs){
                dists.put(i, INF);
                if (i == start){
                    dists.put(i, 0.0);
                }
            }
            PriorityQueue<Pair<Double, Integer>> q = new PriorityQueue<Pair<Double, Integer>>();
            q.add(new Pair(0.0, start));
            while (!q.isEmpty()){
                Pair<Double, Integer> cur_p = q.poll();
                int cur_v = cur_p.snd;
                if (visited.contains(cur_v)){
                    continue;
                }
                dists.put(cur_p.snd, cur_p.fst);
                visited.add(cur_v);
                for (Edge e: graph.get_edges(cur_v)){
                    q.add(new Pair(cur_p.fst + e.weight, e.to));
                }
            }
            ArrayList<Double> arr = new ArrayList(dists.values());
            Collections.sort(arr);
            double[] res = new double[arr.size()];
            for (int i = 0; i < arr.size(); i++){
                res[i] = arr.get(i);
            }
            return res;
        }

	@Override
	public double[] get_best_ways_len(int vert, Graph g) {
		return dijkstra_getMass(g, vert);
	}
}
