package com.ecologistica.domain;

public record Ponto(double x, double y) {

    public double distanceTo(Ponto outro) {
        double dx = outro.x() - this.x();
        double dy = outro.y() - this.y();
        return Math.sqrt(dx * dx + dy * dy);
    }

}