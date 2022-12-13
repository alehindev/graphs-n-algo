package ru.mirea.algorithms;

import ru.mirea.entity.Pair;

import java.util.Collections;
import java.util.Vector;

public class Dijkstra extends AbstractAlgorithm {
    @Override
    public void makeBestPriceProcessing() throws Exception {
        Vector<Vector<Pair>> graph = wireMap.getMatrixByValues();
        int n = graph.size();
        int s = source;// стартовая вершина

        Vector<Double> distance = new Vector<>(n, Integer.MAX_VALUE);
        Vector<Integer> parents = new Vector<>(n);
        distance.set(s, (double) 0);
        Vector<Boolean> used = new Vector<>(n);

        for (int i = 0; i < n; ++i) {
            int v = -1;
            for (int j = 0; j < n; ++j)
                if (!used.get(j) && (v == -1 || distance.get(j) < distance.get(v)))
                    v = j;
            if (distance.get(v).equals(Integer.MAX_VALUE))
                break;
            used.set(v, true);

            for (int j = 0; j < graph.get(v).size(); ++j) {
                int to = graph.get(v).get(j).getToPoint();
                Double len = graph.get(v).get(j).getValueDouble();
                if (distance.get(v) + len < distance.get(to)) {
                    distance.set(to, distance.get(v) + len);
                    parents.set(to, v);
                }
            }
        }
        {
            Integer dest = this.dest;
            Vector<Integer> pathLocal = new Vector<>();
            pathLocal.add(dest);
            while (dest != source) {
                if (parents.get(dest) == -1) {
                    throw new Exception("No way to dest point!");
                }
                pathLocal.add(parents.get(dest));
                dest = parents.get(dest);
            }
            Collections.reverse(pathLocal);
            path = pathLocal;
        }
    }

    @Override
    public void makeBestDurationProcessing() throws Exception {
        Vector<Vector<Pair>> graph = wireMap.getMatrixByDuration();
        int n = graph.size();

        // стартовая вершина
        int s = source;

        Vector<Double> distance = new Vector<>(n, Integer.MAX_VALUE);
        Vector<Integer> parents = new Vector<>(n);
        distance.set(s, (double) 0);
        Vector<Boolean> used = new Vector<>(n);

        for (int i = 0; i < n; ++i) {
            int v = -1;
            for (int j = 0; j < n; ++j)
                if (!used.get(j) && (v == -1 || distance.get(j) < distance.get(v)))
                    v = j;
            if (distance.get(v).equals(Integer.MAX_VALUE))
                break;
            used.set(v, true);

            for (int j = 0; j < graph.get(v).size(); ++j) {
                int to = graph.get(v).get(j).getToPoint();
                Double len = graph.get(v).get(j).getValueDouble();
                if (distance.get(v) + len < distance.get(to)) {
                    distance.set(to, distance.get(v) + len);
                    parents.set(to, v);
                }
            }
        }
        {
            Integer dest = this.dest;
            Vector<Integer> pathLocal = new Vector<>();
            pathLocal.add(dest);
            while (dest != source) {
                if (parents.get(dest) == -1) {
                    throw new Exception("No way to dest point!");
                }
                pathLocal.add(parents.get(dest));
                dest = parents.get(dest);
            }
            Collections.reverse(pathLocal);
            path = pathLocal;
        }
    }

    @Override
    public void makeBalancedProcessing() throws Exception {
        throw new IllegalStateException("Illegal call for dijkstra algorithm");
    }
}
