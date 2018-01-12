package graph;

import java.util.Map;

/**
 *
 * @author artem
 */
public interface WayGetter {

    /**
     *
     * @param vert - вершина графа
     * @param g - сам граф
     * @return Map расстояний, где ключ это вершина, а значение - это собственно расстояние
     * до нее. Если вершины нет в Map'е, то предпологается считать, что её достичь не возможно.
     */
    Map get_best_ways(int vert, Graph g);
}
