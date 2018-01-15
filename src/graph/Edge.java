/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Comparator;

/**
 *
 * @author artem
 */
public class Edge {

    /**
     *Номер вершины из которой ведет ребро.
     */
    public int from;

    /**
     *Номер вершины в которую ведет ребро.
     */
    public int to;

    /**
     *вес ребра.
     */
    public double weight;
    
    public Edge(){
        this(0, 0, 0.0);
    }
    
    public Edge(int f, int t, double w){
        from = f;
        to = t;
        weight = w;
    }
    
    public static final Comparator<Edge> DEFAULT_COMPARE = new Comparator<Edge>() {
        @Override
        public int compare(Edge lhs, Edge rhs) {
            return lhs.to - rhs.to;
        }
    };
}
