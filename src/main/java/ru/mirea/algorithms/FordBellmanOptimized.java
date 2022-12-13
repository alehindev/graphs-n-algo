package ru.mirea.algorithms;

import ru.mirea.entity.Pair;

import java.time.Duration;
import java.util.Collections;
import java.util.Vector;

import static java.time.Duration.ZERO;
import static ru.mirea.entity.Pair.DURATION_MAX;

public class FordBellmanOptimized extends AbstractAlgorithm {


    public void makeBestPriceProcessing() throws Exception {
        Vector<Vector<Pair>> graph = wireMap.getMatrixByValues();

        //Arrange
        int countV = graph.size();
        int countE = 0;
        Vector<Pair> edges = new Vector<>();
        for (Vector<Pair> v : graph) {
            edges.addAll(v);
            countE += v.size();
        }


        if (countV < 1) {
            throw new Exception("Empty matrix");
        }
        double[] distance = new double[countV];
        int[] parents = new int[countV];

        for (int i = 0; i < countV; i++) {
            distance[i] = Double.MAX_VALUE;
            parents[i] = -1;
        }
        distance[source] = 0;

        for (; ; ) {
            boolean any = false;
            for (int j = 0; j < countE; j++) {
                if (distance[edges.get(j).getCurrentPoint()] < Double.MAX_VALUE) {
                    if (distance[edges.get(j).getToPoint()] > distance[edges.get(j).getCurrentPoint()] + edges.get(j).getValueDouble()) {
                        distance[edges.get(j).getToPoint()] = distance[edges.get(j).getCurrentPoint()] + edges.get(j).getValueDouble();
                        parents[edges.get(j).getToPoint()] = edges.get(j).getCurrentPoint();
                        any = true;
                    }
                }
            }
            if (any == false) {
                break;
            }
        }
        Vector<Integer> path = new Vector<>();
        path.add(dest);
        while (dest != source) {
            if (parents[dest] == -1) {
                throw new Exception("No way to dest point!");
            }
            path.add(parents[dest]);
            dest = parents[dest];
        }
        Collections.reverse(path);
        this.path = path;
    }

    public void makeBestDurationProcessing() throws Exception {
        Vector<Vector<Pair>> graph = wireMap.getMatrixByValues();

        //Arrange
        int countV = graph.size();
        int countE = 0;
        Vector<Pair> edges = new Vector<>();
        for (Vector<Pair> v : graph) {
            edges.addAll(v);
            countE += v.size();
        }


        if (countV < 1) {
            throw new Exception("Empty matrix");
        }
        Duration[] distance = new Duration[countV];
        int[] parents = new int[countV];

        for (int i = 0; i < countV; i++) {
            distance[i] = DURATION_MAX;
            parents[i] = -1;
        }
        distance[source] = ZERO;

        for (; ; ) {
            boolean any = false;
            for (int j = 0; j < countE; j++) {
                if (distance[edges.get(j).getCurrentPoint()].compareTo(DURATION_MAX) < 0) {
                    if (distance[edges.get(j).getToPoint()].compareTo(distance[edges.get(j).getCurrentPoint()].plus(edges.get(j).getValueDuration())) > 0) {
                        distance[edges.get(j).getToPoint()] = distance[edges.get(j).getCurrentPoint()].plus(edges.get(j).getValueDuration());
                        parents[edges.get(j).getToPoint()] = edges.get(j).getCurrentPoint();
                    }
                }
            }
            if (any == false) {
                break;
            }
        }
      
        Vector<Integer> path = new Vector<>();
        path.add(dest);
        while (dest != source) {
            if (parents[dest] == -1) {
                throw new Exception("No way to dest point!");
            }
            path.add(parents[dest]);
            dest = parents[dest];
        }
        Collections.reverse(path);
        this.path = path;
    }

    @Override
    public void makeBalancedProcessing() throws Exception {
        //todo
    }
}
