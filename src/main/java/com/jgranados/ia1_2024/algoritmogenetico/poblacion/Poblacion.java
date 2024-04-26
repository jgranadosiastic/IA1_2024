/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.ia1_2024.algoritmogenetico.poblacion;

import com.jgranados.ia1_2024.algoritmogenetico.seleccion.FuncionAptitud;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 *
 * @author jose
 */
public class Poblacion {

    private int tamaño;
    private Individuo[] poblacionActual;
    private FuncionAptitud funcionAptitud = new FuncionAptitud();
    private long totalAptitud;
    private Individuo[] padresSeleccionados;

    public Poblacion(int tamaño) {
        this.tamaño = tamaño;
        poblacionActual = new Individuo[tamaño];
        for (int i = 0; i < poblacionActual.length; i++) {
            poblacionActual[i] = new Individuo(funcionAptitud);
        }
        totalAptitud = calcularAptitudTotal();
    }

    public void reemplazarPoblacion(Individuo[] poblacionNueva) {
        poblacionActual = poblacionNueva;
        totalAptitud = calcularAptitudTotal();
    }

    public int getTamaño() {
        return tamaño;
    }

    public Individuo[] getPoblacionActual() {
        return poblacionActual;
    }

    public Individuo obtenerMejorIndivudio() {
        return Stream.of(poblacionActual)
                .max(Comparator.comparingInt(Individuo::getAptitud))
                .get();
    }

    public Individuo obtenerPeorIndivudio() {
        return Stream.of(poblacionActual)
                .min(Comparator.comparingInt(Individuo::getAptitud))
                .get();
    }

    public long getTotalAptitud() {
        return totalAptitud;
    }

    public Individuo[] getPadresSeleccionados() {
        return padresSeleccionados;
    }

    public void setPadresSeleccionados(Individuo[] padresSeleccionados) {
        this.padresSeleccionados = padresSeleccionados;
    }

    private long calcularAptitudTotal() {
        return Stream.of(poblacionActual)
                .mapToLong(Individuo::getAptitud)
                .sum();
    }
}
