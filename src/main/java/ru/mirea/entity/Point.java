package ru.mirea.entity;

import java.time.Duration;

public class Point {
    private Double cost;
    private Duration duration;

    public Point(Double cost, Duration duration) {
        this.cost = cost;
        this.duration = duration;
    }


    public static Point getInstanceFromString(String str) {
        str = str.trim();
        String costStr = str.substring(str.indexOf('(') + 1, str.indexOf(';')).trim();
        String durationStr = str.substring(str.indexOf(';') + 1, str.indexOf(')')).trim();
        Double cost = Double.valueOf(costStr);
        Duration duration = Duration.parse(durationStr);
        return new Point(cost, duration);
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

}
