package ru.mirea.algorithms;

import java.time.Duration;
import java.util.Vector;

public class Floyd extends AbstractAlgorithm {

    public void makeBestPriceProcessing() throws Exception {
        //arrange
        Double[][] distance = wireMap.getAdjByValues();

        int countV = distance.length;
        if (countV < 1) {
            throw new Exception("Empty matrix");
        }
        int[][] parents = new int[countV][countV];
        for (int i = 0; i < countV; i++) {
            for (int j = 0; j < countV; j++) {
                parents[i][j] = i;
            }
        }
        distance[source][source] = Double.valueOf(0);
        for (int k = 0; k < countV; k++) {
            for (int i = 0; i < countV; i++) {
                for (int j = 0; j < countV; j++) {
                    if (distance[i][k] < Double.MAX_VALUE && distance[k][j] < Double.MAX_VALUE) {
                        if (distance[i][k] + distance[k][j] < distance[i][j]) {
                            distance[i][j] = distance[i][k] + distance[k][j];
                            parents[i][j] = parents[k][j];
                        }
                    }
                }
            }
        }
        Vector<Integer> path = new Vector<>();
        if (distance[source][dest] == Double.MAX_VALUE) {
            throw new Exception("No path!");
        }
        int dst = dest;
        path.add(dst);
        while (dst != source) {
            path.add(parents[source][dst]);
            dst = (parents[source][dst]);
        }

        this.path = path;
    }

    public void makeBestDurationProcessing() throws Exception {
        //arrange
        Duration[][] distance = wireMap.getAdjByDurations();

        int countV = distance.length;
        if (countV < 1) {
            throw new Exception("Empty matrix");
        }
        int[][] parents = new int[countV][countV];
        for (int i = 0; i < countV; i++) {
            for (int j = 0; j < countV; j++) {
                parents[i][j] = i;
            }
        }
        distance[source][source] = Duration.ZERO;
        for (int k = 0; k < countV; k++) {
            for (int i = 0; i < countV; i++) {
                for (int j = 0; j < countV; j++) {
                    if (distance[i][k].compareTo(Duration.ofDays(100l)) < 0 && distance[k][j].compareTo(Duration.ofDays(100l)) < 0) {
                        if (distance[i][k].plus(distance[k][j]).toSeconds() < distance[i][j].toSeconds()) {
                            distance[i][j] = distance[i][k].plus(distance[k][j]);
                            parents[i][j] = parents[k][j];
                        }
                    }
                }
            }
        }
        Vector<Integer> path = new Vector<>();
        if (distance[source][dest].getSeconds() > 1000000000L) {
            throw new Exception("No path!");
        }
        int dst = dest;
        path.add(dst);
        while (dst != source) {
            path.add(parents[source][dst]);
            dst = (parents[source][dst]);
        }

        this.path = path;
    }

    @Override
    public void makeBalancedProcessing() throws Exception {
        throw new IllegalStateException("Illegal call, cause balanced ");
    }
}
