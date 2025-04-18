package com.example.calorie_tracker.model;

public enum Goal {
    LOSE(1.2), MAINTAIN(1.5), GAIN(1.8);

    public final double constant;

    Goal(double constant) {
        this.constant = constant;
    }
}
