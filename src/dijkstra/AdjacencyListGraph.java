package dijkstra;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph {

	private static int INF = Integer.MAX_VALUE / 2; // "Бесконечность"
	public ArrayList<Integer> adjacencyList;
	public ArrayList<Double> weight;

	public double get(int n) {
		return weight.get(n);
	}

	public int getPoint(int n) {
		return adjacencyList.get(n);
	}

	public int size() {
		return weight.size();
	}

	public void add(int n, double w) {
		adjacencyList.add(n);
		weight.add(w);
	}

	public AdjacencyListGraph() {
		adjacencyList = new ArrayList<>();
		weight = new ArrayList<>();
	}

	public static ArrayList<AdjacencyListGraph> InitAdjacencyListGraph(double[][] graph) {
		ArrayList<AdjacencyListGraph> ret = new ArrayList<>();
		for (int i = 0; i < graph.length; i++) {
			AdjacencyListGraph tmp = new AdjacencyListGraph();
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j] != INF) {
					tmp.add(j, graph[i][j]);
				}
			}
			ret.add(tmp);
		}
		return ret;
	}

	public static ArrayList<AdjacencyListGraph> InitAdjacencyListGraph(List<List<Double> > graph) {
		ArrayList<AdjacencyListGraph> ret = new ArrayList<>();
		for (int i = 0; i < graph.size(); i++) {
			AdjacencyListGraph tmp = new AdjacencyListGraph();
			for (int j = 0; j < graph.size(); j++) {
				if (graph.get(i).get(j) != INF) {
					tmp.add(j, graph.get(i).get(j));
				}
			}
			ret.add(tmp);
		}
		return ret;
	}
}
