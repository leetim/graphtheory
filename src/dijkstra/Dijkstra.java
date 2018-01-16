package dijkstra;

import graph.Edge;
import graph.Graph;
import graph.WayGetter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra implements WayGetter {

	public static Double INF = Double.MAX_VALUE / 2; // "Бесконечность"

	public Dijkstra() {
	}
	
	private static boolean log = false;
	
	private static void println(String msg)
	{
		if (log) System.out.println(msg);
	}
	
	public static double[] dijkstra_getMassHeap(Graph graph, int start) {
		println("==== dijkstra_getMassHeap ====");
		int vertexs[] = graph.get_vertexs();
        int v_max = -1;
        for (int v: vertexs){
            v_max = Math.max(v_max, v);
        }
        ArrayList<Pair<Double, Integer>> q = new ArrayList<>();
        ArrayList<Pair<Double, Integer>> distPairs = new ArrayList<>();
		double[] dist = new double[v_max + 1];
		Arrays.fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
        for (int i = 0; i < v_max + 1; i++)
        {
        	distPairs.add(new Pair<Double, Integer>(INF, i));
        }
        distPairs.get(start).setFirstKey(0.0);
		
        for (int v: vertexs)
        {
        	q.add(distPairs.get(v));
        }
        Collections.sort(q);
		Edge edges[] = null;

		for(Pair<Double, Integer> tmp : q)
		{
			println("<" + tmp.getSecondKey() + " " + tmp.getFirstKey() + ">");
		}
		println("");
		while(!q.isEmpty()) {
			//int v = -1;
			//for (int nv = 0; nv < used.length; nv++) // перебираем вершины
			//	if (!used[nv] && dist.get(0).getFirstKey() < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую
																					// непомеченную вершину
			//		v = nv;
			Pair<Double, Integer> cur = q.iterator().next();
			dist[cur.getSecondKey()] = Math.min(cur.getFirstKey(), dist[cur.getSecondKey()]);
			q.remove(cur);
			println("<" + cur.getSecondKey() + " " + cur.getFirstKey() + "> - cursor");
			if (cur.getFirstKey() == INF)
				break; // ближайшая вершина не найдена
			//used[v] = true; // помечаем ее
			edges = graph.get_edges(cur.getSecondKey());
			for (Edge e: edges) {
				//dist[e.to] = Math.min(dist[e.to], dist[e.from] + e.weight); // улучшаем оценку
				if (distPairs.get(e.from).getFirstKey() + e.weight < distPairs.get(e.to).getFirstKey())
				{
					Pair<Double, Integer> tmp = distPairs.get(e.to);
					q.remove(tmp);
					println("<" + tmp.getSecondKey() + " " + tmp.getFirstKey() + "> removed");
					distPairs.get(e.to).setFirstKey((distPairs.get(e.from).getFirstKey() + e.weight));
					tmp = new Pair<Double, Integer>(distPairs.get(e.to).getFirstKey(), e.to);
					q.add(new Pair<Double, Integer>(distPairs.get(e.to).getFirstKey(), e.to));
					println("<" + tmp.getSecondKey() + " " + tmp.getFirstKey() + "> added");
					println("");
			        Collections.sort(q);
				}
			}
			for(Pair<Double, Integer> tmp : q)
			{
				println("<" + tmp.getSecondKey() + " " + tmp.getFirstKey() + ">");
			}
			println("");
		}
		return dist;
	}
	
	public static Map<Integer, Double> dijkstra_getMapHeap(Graph graph, int start) {
		double[] dist = dijkstra_getMassHeap(graph, start);
		Map<Integer, Double> ret = new HashMap<>();
		for (int i = 0; i < dist.length; i++)
			if (dist[i] < INF) ret.put(i, dist[i]);
		return ret;
	}


	public static double[] dijkstra_getMass(Graph graph, int start) {
		int vertexs[] = graph.get_vertexs();
                int v_max = -1;
                for (int v: vertexs){
                    v_max = Math.max(v_max, v);
                }
		Edge edges[] = null;
		boolean[] used = new boolean[v_max + 1]; // массив пометок
		double[] dist = new double[v_max + 1]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)

		Arrays.fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
		dist[start] = 0; // для начальной вершины положим 0

		for (;;) {
			int v = -1;
			for (int nv = 0; nv < used.length; nv++) // перебираем вершины
				if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую
																					// непомеченную вершину
					v = nv;
			if (v == -1)
				break; // ближайшая вершина не найдена
			used[v] = true; // помечаем ее
			edges = graph.get_edges(v);
			for (Edge e: edges) {
				dist[e.to] = Math.min(dist[e.to], dist[e.from] + e.weight); // улучшаем оценку
																								// расстояния
																								// (релаксация)
			}
		}
		return dist;
	}


	public static Map<Integer, Double> dijkstra_getMap(Graph graph, int start) {
		double[] dist = dijkstra_getMass(graph, start);
		Map<Integer, Double> ret = new HashMap<>();
		for (int i = 0; i < dist.length; i++)
			if (dist[i] < INF) ret.put(i, dist[i]);
		return ret;
	}

	@Override
	public Map<?, ?> get_best_ways(int vert, Graph g) {
		return dijkstra_getMapHeap(g, vert);
	}

	@Override
	public double[] get_best_ways_len(int vert, Graph g) {
		return dijkstra_getMassHeap(g, vert);
	}
}
