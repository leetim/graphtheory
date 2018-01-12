package graph;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author artem
 */
public final class Graph {
    private Map edges;
    Graph(){
        edges = new TreeMap();
    }
    
    Graph(ArrayList<Edge> edge_list){
        this((Edge[])edge_list.toArray());
    }
    
    Graph(Edge[] edge_list){
        this();
        for (Edge e: edge_list) {
            add(e);
        }
    }
    
    /**
     *
     * @param e -- ребро графа
     * Добавляет ребро в граф.
     */
    public void add(Edge e){
        if (edges.containsKey(e.from)){
            ((ArrayList<Edge>)edges.get(e.from)).add(e);
        }
        else{
            ArrayList<Edge> temp = new ArrayList<>(1);
            temp.set(0, e);
            edges.put(e.from, temp);
        }
    }
    
    /**
     *
     * @param v -- номер вершины
     * @return Возвращает массив ребер, которые ведут из вешины с номером v.
     */
    public Edge[] get_edges(int v){
        if (edges.containsKey(v)){
            return (Edge[])((ArrayList<Edge>)edges.get(v)).toArray();
        }
        else{
            return new Edge[0];
        }
    }
}
