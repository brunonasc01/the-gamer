package br.com.method.the.gamer.core.api.model;

public enum Difficult {
    EASY(0.75), VERY_EASY(0.5), NORMAL(1.00), HARD(1.25), VERY_HARD(1.50);

    private final Double factor;

    Difficult(Double factor) {
        this.factor = factor;
    }

    public Double getFactor() {
        return factor;
    }
}
