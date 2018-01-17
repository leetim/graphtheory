package dijkstra;

import graph.Edge;
import graph.Graph;
import graph.WayGetter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra implements WayGetter {

	public static Double INF = Double.MAX_VALUE / 2; // "Бесконечность"

	public Dijkstra() {}

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

	@Override
	public double[] get_best_ways_len(int vert, Graph g) {
		return dijkstra_getMass(g, vert);
	}
}
