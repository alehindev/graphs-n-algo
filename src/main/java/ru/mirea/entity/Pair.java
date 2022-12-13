package ru.mirea.entity;

import java.time.Duration;

public class Pair {
    public static final Duration DURATION_MAX = Duration.ofDays(1_000_000L);
    public Integer toPoint;
    public Integer currentPoint;
    public Double valueDouble;
    public Duration valueDuration;

    public Pair(Integer toPoint, Double valueDouble) {
        this.toPoint = toPoint;
        this.valueDouble = valueDouble;
    }

    public Pair(Integer toPoint, Duration valueDuration) {
        this.toPoint = toPoint;
        this.valueDuration = valueDuration;
    }

    public Integer getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Integer currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Integer getToPoint() {
        return toPoint;
    }

    public void setToPoint(Integer toPoint) {
        this.toPoint = toPoint;
    }

    public Double getValueDouble() {
        return valueDouble;
    }

    public void setValueDouble(Double valueDouble) {
        this.valueDouble = valueDouble;
    }

    public Duration getValueDuration() {
        return valueDuration;
    }

    public void setValueDuration(Duration valueDuration) {
        this.valueDuration = valueDuration;
    }
}
