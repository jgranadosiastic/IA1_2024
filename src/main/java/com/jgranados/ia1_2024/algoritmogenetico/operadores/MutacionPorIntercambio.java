/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.ia1_2024.algoritmogenetico.operadores;

import com.jgranados.ia1_2024.algoritmogenetico.poblacion.Individuo;
import java.util.Random;

/**
 *
 * @author jose
 */
public class MutacionPorIntercambio {

    private double porcentajeMutacion;
    private int mutaciones = 0;

    public MutacionPorIntercambio(double porcentajeMutacion) {
        this.porcentajeMutacion = porcentajeMutacion;
    }

    public void aplicarMutacion(Individuo[] nuevaGeneracion) {
        Random random = new Random();
        double porcentaje = random.nextDouble();
        if (porcentaje <= porcentajeMutacion) {
            mutaciones++;
            int randomIndividuo = random.nextInt(nuevaGeneracion.length);
            mutarIndividuo(nuevaGeneracion[randomIndividuo]);
        }
    }

    public int getMutaciones() {
        return mutaciones;
    }

    private Individuo mutarIndividuo(Individuo individuo) {
        System.out.println("Mutando individuo por intercambio: " + individuo);
        Random random = new Random();
        int gen1 = random.nextInt(Individuo.CANTIDAD_GENES);
        int gen2 = random.nextInt(Individuo.CANTIDAD_GENES);
        while (gen1 == gen2) {
            gen2 = random.nextInt(Individuo.CANTIDAD_GENES);
        }

        int valorGen1 = individuo.getGenes()[gen1];
        int valorGen2 = individuo.getGenes()[gen2];
        individuo.getGenes()[gen1] = valorGen2;
        individuo.getGenes()[gen2] = valorGen1;

        individuo.recalcularAptitud();

        System.out.println("Mutacion aplicada: " + individuo);

        return individuo;
    }

}
