import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

	private static int INF = Integer.MAX_VALUE / 2; // "Бесконечность"

	public static double[] dijkstra(double[][] graph, int start) {
		boolean[] used = new boolean[graph.length]; // массив пометок
		double[] dist = new double[graph.length]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)

		Arrays.fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
		dist[start] = 0; // для начальной вершины положим 0

		for (;;) {
			int v = -1;
			for (int nv = 0; nv < graph.length; nv++) // перебираем вершины
				if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую
																					// непомеченную вершину
					v = nv;
			if (v == -1)
				break; // ближайшая вершина не найдена
			used[v] = true; // помечаем ее
			for (int nv = 0; nv < graph.length; nv++)
				if (!used[nv] && graph[v][nv] < INF) // для всех непомеченных смежных
					dist[nv] = Math.min(dist[nv], dist[v] + graph[v][nv]); // улучшаем оценку расстояния (релаксация)
		}
		return dist;
	}

	@SuppressWarnings("unchecked")
	public static double[] dijkstra(ArrayList<?> graph, int start) {
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

	public static void main(String args[]) {
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

}
