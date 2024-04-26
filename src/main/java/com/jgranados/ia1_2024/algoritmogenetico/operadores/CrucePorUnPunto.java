/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.ia1_2024.algoritmogenetico.operadores;

import com.jgranados.ia1_2024.algoritmogenetico.poblacion.Individuo;
import com.jgranados.ia1_2024.algoritmogenetico.poblacion.Poblacion;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author jose
 */
public class CrucePorUnPunto {

    public Individuo[] cruzarPadres(Poblacion poblacion) {
        Individuo[] padresSeleccionados = poblacion.getPadresSeleccionados();
        Individuo[] hijos = new Individuo[poblacion.getTamaño()];

        Individuo padreA;
        Individuo padreB;

        for (int i = 0; i < padresSeleccionados.length; i = i + 2) {
            padreA = padresSeleccionados[i];
            padreB = padresSeleccionados[i + 1];

            Individuo[] nuevosHijos = cruzar(padreA, padreB);
            agregarHijos(nuevosHijos, i, hijos);
        }

        return hijos;
    }

    private void agregarHijos(Individuo[] nuevosHijos, int posicion, Individuo[] hijos) {
        hijos[posicion] = nuevosHijos[0];
        hijos[posicion + 1] = nuevosHijos[1];
    }

    private Individuo[] cruzar(Individuo padreA, Individuo padreB) {
        Random random = new Random();
        int puntoCruce = random.nextInt(Individuo.CANTIDAD_GENES);

        int[] grupo1PadreA = Arrays.copyOfRange(padreA.getGenes(), 0, puntoCruce);
        int[] grupo2PadreA = Arrays.copyOfRange(padreA.getGenes(), puntoCruce, Individuo.CANTIDAD_GENES);
        int[] grupo1PadreB = Arrays.copyOfRange(padreB.getGenes(), 0, puntoCruce);
        int[] grupo2PadreB = Arrays.copyOfRange(padreB.getGenes(), puntoCruce, Individuo.CANTIDAD_GENES);

        Individuo[] nuevosIndividuos = new Individuo[2];
        nuevosIndividuos[0] = new Individuo(recombinar(grupo1PadreA, grupo2PadreB), padreA.getFuncionAptitud());
        nuevosIndividuos[1] = new Individuo(recombinar(grupo1PadreB, grupo2PadreA), padreA.getFuncionAptitud());

        return nuevosIndividuos;
    }

    private int[] recombinar(int[] grupoPadreA, int[] grupoPadreB) {
        return IntStream.concat(Arrays.stream(grupoPadreA), Arrays.stream(grupoPadreB))
                .toArray();
    }
}
