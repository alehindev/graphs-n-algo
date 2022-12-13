package ru.mirea.entity;

import java.time.Duration;
import java.util.Vector;

public class WireMap {

    private Point[][] matrixOfPoints;
    private Vector<Vector<Pair>> matrixByDuration;
    private Vector<Vector<Pair>> matrixByValues;
    private Double[][] adjByValues;
    private Duration[][] adjByDurations;

    public WireMap() {
    }

    public WireMap(Point[][] matrixOfPoints) {
        this.matrixOfPoints = matrixOfPoints;
        createAdjMatrixByDuration();
        createAdjMatrixByVal();
        createMatrixOfEdges();
    }

    public Double[][] getAdjByValues() {
        return adjByValues;
    }

    public void setAdjByValues(Double[][] adjByValues) {
        this.adjByValues = adjByValues;
    }

    public Duration[][] getAdjByDurations() {
        return adjByDurations;
    }

    public void setAdjByDurations(Duration[][] adjByDurations) {
        this.adjByDurations = adjByDurations;
    }

    public Vector<Vector<Pair>> getMatrixByDuration() {
        return matrixByDuration;
    }

    public void setMatrixByDuration(Vector<Vector<Pair>> matrixByDuration) {
        this.matrixByDuration = matrixByDuration;
    }

    public Vector<Vector<Pair>> getMatrixByValues() {
        return matrixByValues;
    }

    public void setMatrixByValues(Vector<Vector<Pair>> matrixByValues) {
        this.matrixByValues = matrixByValues;
    }

    public void createAdjMatrixByVal() {
        Double[][] adjVal = new Double[matrixOfPoints.length][matrixOfPoints.length];
        for (int i = 0; i < matrixOfPoints.length; i++) {
            for (int j = 0; j < matrixOfPoints.length; j++) {
                adjVal[i][j] = Double.MAX_VALUE;
            }
        }
        for (int i = 0; i < matrixOfPoints.length; i++) {
            for (int j = 0; j < matrixOfPoints.length; j++) {
                Double val = matrixOfPoints[i][j].getCost();
                if (val != null && val != 0)
                    adjVal[i][j] = val;
            }
        }
    }

    public void createAdjMatrixByDuration() {
        Duration[][] adjDuration = new Duration[matrixOfPoints.length][matrixOfPoints.length];
        for (int i = 0; i < matrixOfPoints.length; i++) {
            for (int j = 0; j < matrixOfPoints.length; j++) {
                adjDuration[i][j] = Duration.ofDays(100000000);
            }
        }
        for (int i = 0; i < matrixOfPoints.length; i++) {
            for (int j = 0; j < matrixOfPoints.length; j++) {
                Duration duration = matrixOfPoints[i][j].getDuration();
                if (duration != null && !duration.equals(Duration.ZERO))
                    adjDuration[i][j] = duration;
            }
        }
    }

    public void createMatrixOfEdges() {
        matrixByDuration = new Vector<>(matrixOfPoints.length);
        matrixByValues = new Vector<>(matrixOfPoints.length);
        for (int i = 0; i < matrixOfPoints.length; i++) {
            for (int j = 0; j < matrixOfPoints.length; j++) {
                Point point = matrixOfPoints[i][j];
                if (point.getCost() != null && point.getCost() != 0 && point.getDuration() != null && point.getDuration().toSeconds() != 0) {
                    matrixByValues.get(i).add(new Pair(j, point.getCost()));
                    matrixByDuration.get(i).add(new Pair(j, point.getDuration()));
                }
            }
        }
    }


    public Point[][] getMatrixOfPoints() {
        return matrixOfPoints;
    }

    public void setMatrixOfPoints(Point[][] table) {
        this.matrixOfPoints = table;
    }
}
