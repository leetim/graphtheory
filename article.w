\documentclass[12pt]{article}
\usepackage{graphicx}
\usepackage{amssymb}
\usepackage{listings}
\usepackage{amsmath}
\usepackage{graphicx}
\usepackage{float}
\usepackage[section]{placeins}
% Russian specicfic
% -------------------------
\usepackage[T2A]{fontenc}
\usepackage[utf8]{inputenc}
\usepackage[russian]{babel}
\begin{document}

\title{Алгоритмическая теория графов \\ Эффективность сети. Критические вершины/ребра}

\author{
  Батраков А. (реализация основного алгоритма, тестирование)
  \and
  Богданов Д. (документация)
  \and
  Лю Е. (реализация алгоритма Дейкстры)
  \and
  Павленко Д. (реализация алгоритма Дейкстры)
  \and
  Пинчук И. (визуализация, документация)
  \and
  Трофимова О. (документация)
}

\maketitle
\thispagestyle{empty}
\newpage


\section{Постановка задачи}
Для графа $ G = (V, E, W) $ с множеством вершин V, и множеством ребер $ E \subset V \times V $ найти ребро $ e* $ и вершину $ v* $ такие, что при их удалении из графа $LM$-метрика \\
$$ \rho_{LM} = \frac{1}{n(n-1)} \sum_{i, j} d_{ij}^{-1} $$ \\
минимизируется. В определении $ LM$-метрики $ d_{ij} $- сетевое расстояние между вершинами $i$ и $j$. Расчеты привести для графа Владивостока-2012.
\section{Алгоритмика}
Для минимизации метрики с помощью перебора поочередно удаляются ребра из графа, после чего из каждой вершины запускается алгоритм Дейкстры для вычисления $ LM$-метрики в графе без этого ребра.\\
После каждого запуска алгоритма значение метрики сравнивается с вычисленным ранее и в случае, если новое значение оказывается меньше, перезаписывается. \\
По завершению работы программы в результате сохраняется минимальное значение метрики и данные об удаленном ребре (вершины, которые соединяло ребро, и его вес).

\section{Реализация}
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
Для подсчета значений $ LM$-метрики в графе использовался алгоритм Дейкстры. \\

@o Dijkstra.java @{
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

    @< dijkstra getMass @>

    @< get best ways len @>
}
@}

@d dijkstra getMass @{
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
            PriorityQueue<Pair<Double, Integer>> q =
                new PriorityQueue<Pair<Double, Integer>>();
            q.add(new Pair(0.0, start));
            while (!q.isEmpty()){
                Pair<Double, Integer> cur_p = q.poll();
                Pair<Double, Integer> temp_p = new Pair(2.1, 7);
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
@}

@d get best ways len @{
    @@Override
    public double[] get_best_ways_len(int vert, Graph g) {
        return dijkstra_getMass(g, vert);
    }
@}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
В приведенном ниже файле задается описание структуры вершин.
@o Edge.java @{
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package graph;

    import java.util.Comparator;

    /**
     *
     * @@author artem
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
    }
@}




%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
В стандартной библиотеке Openjdk нет подходящего класса Pair, поэтому в сети была найдена реализация данного класса и отредактирована.
@o Pair.java @{
    /*
    * To change this license header, choose License Headers in Project Properties.
    * To change this template file, choose Tools | Templates
    * and open the template in the editor.
    */
    package dijkstra;

    /**
    *
    * @@author kiniry
    * @@param <A>
    * @@param <B>
    */
    public class Pair<A, B> implements Comparable{

        @< kiniry`s pairs @>

        @< compare @>
    }
@}

@d kiniry`s pairs @{
       public final A fst;
       public final B snd;

       public Pair(A fst, B snd) {
           this.fst = fst;
           this.snd = snd;
       }

       public String toString() {
           return "Pair[" + fst + "," + snd + "]";
       }

       private static boolean equals(Object x, Object y) {
           return (x == null && y == null) || (x != null && x.equals(y));
       }

       public boolean equals(Object other) {
           return
               other instanceof Pair<?,?> &&
               equals(fst, ((Pair<?,?>)other).fst) &&
               equals(snd, ((Pair<?,?>)other).snd);
       }

       public int hashCode() {
           if (fst == null) return (snd == null) ? 0 : snd.hashCode() + 1;
           else if (snd == null) return fst.hashCode() + 2;
           else return fst.hashCode() * 17 + snd.hashCode();
       }

       public static <A,B> Pair<A,B> of(A a, B b) {
           return new Pair<A,B>(a,b);
       }

@}

@d compare @{
  @@Override
  public int compareTo(Object o) {
    if (this.equals(o)){
       return 0;
    }
    Pair<A, B> other = (Pair<A, B>) o;
    Comparable lf = (Comparable) this.fst;
    Comparable rf = (Comparable) other.fst;
    Comparable ls = (Comparable) this.snd;
    Comparable rs = (Comparable) other.snd;
    if (lf.compareTo(rf) == 0){
       return ls.compareTo(rs);
    }
    if (lf.compareTo(rf) == 1){
       return 1;
    }
    else{
       return -1;
    }
  }
@}



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
Файл Graph.java реализует интерфейс работы с графом.
@o Graph.java @{
    package graph;

    import java.util.ArrayList;
    import java.util.Map;
    import java.util.TreeMap;

    /**
     *
     * @@author artem
     */
    public final class Graph {
        private Map edges;

        @< Graph constructors @>

        @< Adding edge to graph function @>

        @< Getting edges emanating from v vertex @>

        @< Getting vertexs` numbers @>

        @< Getting graph without v1<->v2 edge @>

        @< Getting graph without including v vertex edge @>
    }
@}

@d Graph constructors  @{
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
@}

@d Adding edge to graph function @{
    /**
     *
     * @@param e -- ребро графа
     * Добавляет ребро в граф.
     */
    public void add(Edge e){
        if (!edges.containsKey(e.to)){
            ArrayList<Edge> temp = new ArrayList<Edge>();
            edges.put(e.to, temp);

        }
        if (edges.containsKey(e.from)){
            ((ArrayList<Edge>)edges.get(e.from)).add(e);
        }
        else{
            ArrayList<Edge> temp = new ArrayList<Edge>();
            temp.add(e);
            edges.put(e.from, temp);
        }
    }
@}

@d Getting edges emanating from v vertex @{
    /**
     *
     * @@param v -- номер вершины
     * @@return Возвращает массив ребер, которые ведут из вешины с номером v.
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
@}

@d Getting vertexs` numbers @{
    /**
     *
     * @@return Массив номеров вершин графа.
     */
    public int[] get_vertexs(){
        Object[] verts = (edges.keySet().toArray());
        int res[] = new int[verts.length];
        for (int i = 0; i < verts.length; i++){
            res[i] = (int)verts[i];
        }
        return res;
    }
@}

@d Getting graph without v1<->v2 edge @{
    /**
     *
     * @@param v1
     * @@param v2
     * @@return Возвращает граф без ребер, соединяющих вершину v1 с v2
     */
    public Graph rm_edge(int v1, int v2){
        Graph g = new Graph();
        //перебор всех вершин
        for (int v: get_vertexs()){
        //перебор всех ребер, которые выходят из вершины v
            for (Edge e: get_edges(v)){
                if (e.from != v1 || e.to != v2){
                    g.add(e);
                }
            }
        }
        return g;
    }
@}

@d Getting graph without including v vertex edge @{
    /**
     *
     * @@param v
     * @@return Возвращает граф без ребер, входящих/выходящих в/из вершины v
     */
    public Graph rm_vertex(int vert){
        Graph g = new Graph();
        //перебор всех вершин
        for (int v: get_vertexs()){
        //перебор всех ребер, которые выходят из вершины v
            for (Edge e: get_edges(v)){
                if (e.from != vert && e.to != vert){
                    g.add(e);
                }
            }
        }
        return g;
    }
@}
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
Результатом приведенного ниже кода является ребро, убрав из графа которое будет минимизирована $ LM $-метрика. \\
Код содержит функции получения значения LM метрики и минимизации этой метрики по ребру/вершине.
@o GraphTheory.java @{
    import dijkstra.Dijkstra;
    import graph.Edge;
    import graph.Graph;
    import graph.WayGetter;
    import java.io.BufferedInputStream;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Scanner;

    /**
     *
     * @@author artem
     */
    public class GraphTheory {

        /**
         *
         * @@param wg
         * @@param g
         * @@return Ребро, убрав из графа которое будет
         * минимизирована LM метрика для графа.
         */

        public enum Method{
            DEFAULT,
            RM_EDGE,
            RM_VERTEX,
        }

        @< Getting LM value @>

        @< Minimize LM by edge @>

        @< Minimize LM by vert @>

        @< Main method @>
    }
@}

@d Getting LM value @{
    public static double get_LM(WayGetter wg, Graph g){
        double cur_len = 0.0;
        int[] vertexs = g.get_vertexs();
        int n = vertexs.length;
        int k = 0;
        for (int i: vertexs){
            double[] ways_len = wg.get_best_ways_len(i, g);
            for (double f: ways_len){
                if (f < Dijkstra.INF && f != 0.0){
                    cur_len += 1.0/f;
                }
            }
        }
        cur_len *= 1.0/(vertexs.length*vertexs.length - vertexs.length);
        return cur_len;
    }
@}

@d Minimize LM by edge @{
    public static Edge minimize_LM_by_edge(WayGetter wg, Graph g){
        Edge min_e = null;
        double min_len = Double.POSITIVE_INFINITY;
        int[] vertexs = g.get_vertexs();
        for (int v: vertexs){
            for (Edge e: g.get_edges(v)){
                System.err.println(String.format(
                    "Check %d -> %d: %f",
                    e.from,
                    e.to,
                    e.weight
                ));
                Graph new_g = g.rm_edge(e.from, e.to);
                double cur_len = get_LM(wg, new_g);
                System.err.println(String.format(
                    "Finish LM = %f newLM = %f",
                    min_len,
                    cur_len
                ));
                if (cur_len < min_len){
                    min_e = e;
                    min_len = cur_len;
                }
            }
        }
        return min_e;
    }
@}

@d Minimize LM by vert @{
    public static int minimize_LM_by_vert(WayGetter wg, Graph g){
        int min_v = -1;
        double min_len = Double.POSITIVE_INFINITY;
        int[] vertexs = g.get_vertexs();
        for (int v: vertexs){
            System.err.println(String.format("Check %d", v));
            Graph new_g = g.rm_vertex(v);
            double cur_len = get_LM(wg, new_g);
            System.err.println(String.format(
                "Finish LM = %f newLM = %f",
                min_len,
                cur_len
            ));
            if (cur_len < min_len){
                min_v = v;
                min_len = cur_len;
            }
        }
        return min_v;
    }
@}

@d Main method @{
    public static void main(String argv[]) throws Exception{
            int process_count = 1;
            Method m = Method.DEFAULT;
            for (int i = 0; i < argv.length; i++){
                switch(argv[i]){
                    case "-v":
                        m = Method.RM_VERTEX;
                        break;
                    case "-e":
                        m = Method.RM_EDGE;
                        break;
                }
            }
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Graph g = new Graph();
            try{
                while (in.hasNextInt()){
                    int v1, v2;
                    double l;
                    if (in.hasNextInt()){
                        v1 = in.nextInt();
                    }
                    else{
                        throw new Exception("Expected int!");
                    }
                    if (in.hasNextInt()){
                        v2 = in.nextInt();
                    }
                    else{
                        throw new Exception("Expected int!");
                    }
                    if (in.hasNextDouble()){
                        l = in.nextDouble();
                    }
                    else{
                        throw new Exception("Expected Double!");
                    }
                    g.add(new Edge(v1, v2, l));
                }
                switch(m){
                    case DEFAULT:
                    case RM_EDGE:{
                        System.err.println("Start working!");
                        Edge min_e = minimize_LM_by_edge(new Dijkstra(), g);
                        System.out.println(String.format(
                            "Edge: %d -> %d: %f",
                            min_e.from,
                            min_e.to,
                            min_e.weight
                        ));
                        break;
                    }
                    case RM_VERTEX:{
                        System.err.println("Start working!");
                        int min_v = minimize_LM_by_vert(new Dijkstra(), g);
                        System.out.println(String.format("Vertex: %d", min_v));
                        break;
                    }

                }
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
@}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
Интерфейс:
@o WayGetter.java @{
    package graph;

    import java.util.Map;

    /**
     *
     * @@author artem
     */
    public interface WayGetter {

        /**
         *
         * @@param vert
         * @@param g
         * @@return Массив расстояний
         */
        double[] get_best_ways_len(int vert, Graph g);
    }
@}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
Тестирование входящих в алгоритм методов проводилось с использованием маленького графа.
@o GraphTest.java @{
    /*
     * To change this license header, choose License Headers in
     * Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package graph;

    import org.junit.After;
    import org.junit.AfterClass;
    import org.junit.Before;
    import org.junit.BeforeClass;
    import org.junit.Test;
    import static org.junit.Assert.*;

    /**
     *
     * @@author artem
     */
    public class GraphTest {

        @< Prepare graph test @>

        @< Test of add method of class Graph @>

        @< Test of "get edges method" of class Graph @>

        @< Test of "get vertexs" method of class Graph @>

        @< Test of "rm edge" method of class Graph @>

        @< Test of "rm vertex" method of class Graph @>
    }
@}

@d Prepare graph test @{
    public GraphTest() {
    }

    @@BeforeClass
    public static void setUpClass() {
    }

    @@AfterClass
    public static void tearDownClass() {
    }

    @@Before
    public void setUp() {
    }

    @@After
    public void tearDown() {
    }
@}

@d Test of add method of class Graph @{
    /**
     * Test of add method, of class Graph.
     */
    @@Test
    public void testAdd() {
        System.out.println("add");
        Edge e = new Edge(1, 2, 5.0);
        Graph g = new Graph();
        g.add(e);
        Edge[] res_e = g.get_edges(1);
        assertEquals(res_e.length, 1);
    }

@}

@d Test of "get edges method" of class Graph @{
    /**
     * Test of get_edges method, of class Graph.
     */
    @@Test
    public void testGet_edges() {
        System.out.println("get_edges");
        Edge[] e = {
            new Edge(1, 2, 5.0),
            new Edge(1, 3, 2.5),
        };
        Graph g = new Graph(e);
        Edge[] res_e = g.get_edges(1);
        assertEquals(res_e.length, 2);
        assertArrayEquals(res_e, e);
    }
@}

@d Test of "get vertexs" method of class Graph @{
    /**
     * Test of get_vertexs method, of class Graph.
     */
    @@Test
    public void testGet_vertexs() {
        System.out.println("get_vertexs");
        Edge[] e = {
            new Edge(1, 2, 5.0),
            new Edge(2, 5, 2.5),
            new Edge(3, 4, 2.5),
            new Edge(5, 3, 2.5)
        };
        Graph g = new Graph(e);
        int template_v[] = {1, 2, 3, 4, 5};
        int[] res_v = g.get_vertexs();
        assertArrayEquals(res_v, template_v);
    }
@}

@d Test of "rm edge" method of class Graph @{
    /**
     * Test of rm_edge method, of class Graph.
     */
    @@Test
    public void testRm_edge() {
        System.out.println("rm_edge");
        Edge[] e = {
            new Edge(1, 2, 5.0),
            new Edge(1, 5, 2.5),
            new Edge(1, 4, 2.5),
            new Edge(1, 3, 2.5)
        };
        Edge[] template_e = {
            new Edge(1, 2, 5.0),
            new Edge(1, 5, 2.5),
            new Edge(1, 3, 2.5)
        };
        Graph g = new Graph(e);
        Graph new_g = g.rm_edge(1, 4);
        Edge[] res_e = new_g.get_edges(1);
        assertEquals(template_e.length, res_e.length);
        for (int i = 0; i < res_e.length; i++){
            assertEquals(res_e[i].from, template_e[i].from);
            assertEquals(res_e[i].to, template_e[i].to);
            assertEquals(res_e[i].weight, template_e[i].weight, 0.00001);
        }
    }
@}

@d Test of "rm vertex" method of class Graph @{
    /**
     * Test of rm_vertex method, of class Graph.
     */
    @@Test
    public void testRm_vertex() {
        System.out.println("rm_edge");
        Edge[] e = {
            new Edge(1, 2, 5.0),
            new Edge(1, 5, 2.5),
            new Edge(1, 4, 2.5),
            new Edge(1, 3, 2.5)
        };
        int[] template_v = {1, 2, 3, 5};
        Graph g = new Graph(e);
        Graph new_g = g.rm_vertex(4);
        int[] res_v = new_g.get_vertexs();
        assertArrayEquals(template_v, res_v);
    }
@}
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\section{Результаты}
На простом графе порядка 10-20 вершин с подробным разбором работы алгоритма, \\
на реалистических примерах с порядка 1000-5000 вершин с оценкой вычислительной сложности алгоритма, \\
на реальном примере максимальной размерности.

\section{Заключение}



\end{document}
