package com.ecologistica.domain;

import java.util.Objects;

/**
 * Representa um ponto no plano cartesiano (x, y).
 * Utilizado para cálculo de distância entre locais.
 */
public class Ponto {

    private final double x;
    private final double y;

    /**
     * Construtor que valida as coordenadas.
     */
    public Ponto(double x, double y) {
        validarCoordenada(x);
        validarCoordenada(y);
        this.x = x;
        this.y = y;
    }

    private void validarCoordenada(double valor) {
        if (Double.isNaN(valor) || Double.isInfinite(valor)) {
            throw new IllegalArgumentException("Coordenada inválida.");
        }
    }

    /**
     * Calcula a distância Euclidiana até outro ponto.
     */
    public double distanceTo(Ponto outro) {
        Objects.requireNonNull(outro, "O ponto de destino não pode ser nulo.");

        double dx = this.x - outro.x;
        double dy = this.y - outro.y;

        return Math.hypot(dx, dy);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("Ponto(x=%.2f, y=%.2f)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ponto)) return false;
        Ponto ponto = (Ponto) o;
        return Double.compare(ponto.x, x) == 0 &&
                Double.compare(ponto.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}