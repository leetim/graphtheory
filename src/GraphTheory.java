import dijkstra.AdjacencyListGraph;
import static dijkstra.Dijkstra.dijkstra;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author artem
 */
public class GraphTheory {
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
