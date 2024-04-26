/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.ia1_2024.algoritmogenetico.seleccion;

import com.jgranados.ia1_2024.algoritmogenetico.poblacion.Individuo;
import com.jgranados.ia1_2024.algoritmogenetico.poblacion.Poblacion;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author jose
 */
public class SeleccionPorRuleta {

    public Individuo[] seleccionarPadres(Poblacion poblacion) {
        Individuo[] padresSeleccionados = new Individuo[poblacion.getTamaño()];

        long totalAptitud = poblacion.getTotalAptitud();

        for (int i = 0; i < poblacion.getTamaño(); i++) {
            long valor = ThreadLocalRandom.current().nextLong(totalAptitud + 1);
            long sumatoria = 0;
            for (Individuo individuo : poblacion.getPoblacionActual()) {
                sumatoria = sumatoria + individuo.getAptitud();
                if (sumatoria >= valor) {
                    padresSeleccionados[i] = individuo;
                    break;
                }
            }
        }

        return padresSeleccionados;
    }
}
