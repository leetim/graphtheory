package dijkstra;

import graph.Edge;
import graph.Graph;
import graph.WayGetter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra implements WayGetter {

	private static Double INF = Double.MAX_VALUE / 2; // "Бесконечность"

	public Dijkstra() {
	}

	@SuppressWarnings("unchecked")
	public static double[] dijkstra_getMass(ArrayList<?> graph, int start) {
		boolean[] used = new boolean[graph.size()]; // массив пометок
		double[] dist = new double[graph.size()]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)

		Arrays.fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
		dist[start] = 0; // для начальной вершины положим 0

		for (;;) {
			int v = -1;
			for (int nv = 0; nv < graph.size(); nv++) // перебираем вершины
				if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую
																					// непомеченную вершину
					v = nv;
			if (v == -1)
				break; // ближайшая вершина не найдена
			used[v] = true; // помечаем ее
			if (graph.get(v) instanceof ArrayList<?>)
				for (int nv = 0; nv < graph.size(); nv++) {
					if (!used[nv] && ((ArrayList<Double>) graph.get(v)).get(nv) < INF) // для всех непомеченных смежных
						dist[nv] = Math.min(dist[nv], dist[v] + ((ArrayList<Double>) graph.get(v)).get(nv)); // улучшаем
																												// оценку
																												// расстояния
																												// (релаксация)
				}
			else if (graph.get(v) instanceof AdjacencyListGraph) {
				for (int nv = 0; nv < ((AdjacencyListGraph) graph.get(v)).size(); nv++) {
					if (!used[((AdjacencyListGraph) graph.get(v)).getPoint(nv)]
							&& ((AdjacencyListGraph) graph.get(v)).get(nv) < INF) // для всех непомеченных смежных
						dist[((AdjacencyListGraph) graph.get(v)).getPoint(nv)] = Math.min(
								dist[((AdjacencyListGraph) graph.get(v)).getPoint(nv)],
								dist[v] + ((AdjacencyListGraph) graph.get(v)).get(nv)); // улучшаем оценку расстояния
																						// (релаксация)
				}
			}
		}
		return dist;
	}

	@SuppressWarnings("unchecked")
	public static Map<Integer, Double> dijkstra_getMap(ArrayList<?> graph, int start) {
		boolean[] used = new boolean[graph.size()]; // массив пометок
		Map<Integer, Double> dist = new HashMap<Integer, Double>(); // массив расстояния. dist[v] =
																	// минимальное_расстояние(start, v)

		// Arrays.fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
		for (int i = 0; i < graph.size(); i++)
			dist.put(i, INF);
		dist.replace(start, 0.0); // для начальной вершины положим 0

		for (;;) {
			int v = -1;
			for (int nv = 0; nv < graph.size(); nv++) // перебираем вершины
				if (!used[nv] && dist.get(nv) < INF && (v == -1 || dist.get(v) > dist.get(nv))) // выбираем самую
																								// близкую
					// непомеченную вершину
					v = nv;
			if (v == -1)
				break; // ближайшая вершина не найдена
			used[v] = true; // помечаем ее
			if (graph.get(v) instanceof ArrayList<?>)
				for (int nv = 0; nv < graph.size(); nv++) {
					if (!used[nv] && ((ArrayList<Double>) graph.get(v)).get(nv) < INF) // для всех непомеченных смежных
						dist.replace(nv,
								Math.min(dist.get(nv), dist.get(v) + ((ArrayList<Double>) graph.get(v)).get(nv))); // улучшаем
					// оценку
					// расстояния
					// (релаксация)
				}
			else if (graph.get(v) instanceof AdjacencyListGraph) {
				for (int nv = 0; nv < ((AdjacencyListGraph) graph.get(v)).size(); nv++) {
					if (!used[((AdjacencyListGraph) graph.get(v)).getPoint(nv)]
							&& ((AdjacencyListGraph) graph.get(v)).get(nv) < INF) // для всех непомеченных смежных
						dist.replace(((AdjacencyListGraph) graph.get(v)).getPoint(nv),
								Math.min(dist.get(((AdjacencyListGraph) graph.get(v)).getPoint(nv)),
										dist.get(v) + ((AdjacencyListGraph) graph.get(v)).get(nv))); // улучшаем оценку
																										// расстояния
					// (релаксация)
				}
			}
		}
		return dist;
	}


	public static double[] dijkstra_getMass(Graph graph, int start) {
		int vertexs[] = graph.get_vertexs();
		Edge edges[] = null;
		boolean[] used = new boolean[vertexs.length]; // массив пометок
		double[] dist = new double[vertexs.length]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)

		Arrays.fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
		dist[start] = 0; // для начальной вершины положим 0

		for (;;) {
			int v = -1;
			for (int nv = 0; nv < vertexs.length; nv++) // перебираем вершины
				if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую
																					// непомеченную вершину
					v = nv;
			if (v == -1)
				break; // ближайшая вершина не найдена
			used[v] = true; // помечаем ее
			edges = graph.get_edges(v);
			for (int nv = 0; nv < edges.length; nv++) {
				if (!used[v] && edges[nv].weight < INF) // для всех непомеченных смежных
					dist[edges[nv].to] = Math.min(dist[edges[nv].to], dist[v] + edges[nv].to); // улучшаем оценку
																								// расстояния
																								// (релаксация)
			}
		}
		return dist;
	}


	public static Map<Integer, Double> dijkstra_getMap(Graph graph, int start) {
		int vertexs[] = graph.get_vertexs();
		Edge edges[] = null;
		boolean[] used = new boolean[vertexs.length]; // массив пометок
		Map<Integer, Double> dist = new HashMap<Integer, Double>(); // массив расстояния. dist[v] =
																	// минимальное_расстояние(start, v)

		// Arrays.fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
		for (int i = 0; i < vertexs.length; i++)
			dist.put(i, INF);
		dist.replace(start, 0.0); // для начальной вершины положим 0

		for (;;) {
			int v = -1;
			for (int nv = 0; nv < vertexs.length; nv++) // перебираем вершины
				if (!used[nv] && dist.get(nv) < INF && (v == -1 || dist.get(v) > dist.get(nv))) // выбираем самую
																								// близкую
					// непомеченную вершину
					v = nv;
			if (v == -1)
				break; // ближайшая вершина не найдена
			used[v] = true; // помечаем ее
			edges = graph.get_edges(v);
			for (int nv = 0; nv < edges.length; nv++) {
				if (!used[v] && edges[nv].weight < INF) // для всех непомеченных смежных
					dist.replace(edges[nv].to, Math.min(dist.get(edges[nv].to), dist.get(v) + edges[nv].to)); // улучшаем оценку
																								// расстояния
																								// (релаксация)
			}
		}
		return dist;
	}

	@Override
	public Map get_best_ways(int vert, Graph g) {
		return dijkstra_getMap(g, vert);
	}

	@Override
	public double[] get_best_ways_len(int vert, Graph g) {
		return dijkstra_getMass(g, vert);
	}
}
