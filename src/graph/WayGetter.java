package graph;

import java.util.Map;

/**
 *
 * @author artem
 */
public interface WayGetter {

    /**
     *
     * @param vert
     * @param g
     * @return Массив расстояний
     */
    double[] get_best_ways_len(int vert, Graph g);
}
