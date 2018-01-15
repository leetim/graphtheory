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
    public Graph(){
        edges = new TreeMap();
    }
    
    public Graph(ArrayList<Edge> edge_list){
        this((Edge[])edge_list.toArray());
    }
    
    public Graph(Edge[] edge_list){
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
            ArrayList<Edge> temp = new ArrayList<Edge>(1);
            temp.add(0, e);
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
            ArrayList<Edge> temp = ((ArrayList<Edge>)edges.get(v));
            Edge[] res = new Edge[temp.size()];
            for (int i = 0; i < temp.size(); i++){
                res[i] = temp.get(i);
            }
            return res;
        }
        else{
            return new Edge[0];
        }
    }
    
    /**
     *
     * @return Массив номеров вершин графа.
     */
    public int[] get_vertexs(){
        Object[] verts = (edges.keySet().toArray());
        int res[] = new int[verts.length];
        for (int i = 0; i < verts.length; i++){
            res[i] = (int)verts[i];
        }
        return res;
    }
    
    /**
     *
     * @param v1
     * @param v2
     * @return Возврощает граф без ребер, соединяющих вершину v1 с v2.
     */
    public Graph rm_edge(int v1, int v2){
        Graph g = new Graph();
        for (int v: get_vertexs()){
            for (Edge e: get_edges(v)){
                if (e.from != v1 || e.to != v2){
                    g.add(e);
                }
            }
        }
        return g;
    }
}
