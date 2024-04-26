/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.ia1_2024.algoritmogenetico.poblacion;

import com.jgranados.ia1_2024.algoritmogenetico.seleccion.FuncionAptitud;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author jose
 */
public class Individuo {

    public static final int CANTIDAD_GENES = 8;
    public static final int VALOR_MAX_GEN = 8;
    private int[] genes;
    private int aptitud;
    private FuncionAptitud funcionAptitud;

    public Individuo(FuncionAptitud funcionAptitud) {
        this.funcionAptitud = funcionAptitud;
        genes = new int[CANTIDAD_GENES];
        Random random = new Random();
        for (int i = 0; i < genes.length; i++) {
            genes[i] = random.nextInt(VALOR_MAX_GEN) + 1;
        }

        aptitud = funcionAptitud.calcularAptitud(genes);
    }

    public Individuo(int[] genes, FuncionAptitud funcionAptitud) {
        this.funcionAptitud = funcionAptitud;
        this.genes = genes;

        aptitud = funcionAptitud.calcularAptitud(genes);
    }

    public int[] getGenes() {
        return genes;
    }

    public int getAptitud() {
        return aptitud;
    }

    public void recalcularAptitud() {
        aptitud = funcionAptitud.calcularAptitud(genes);
    }

    public FuncionAptitud getFuncionAptitud() {
        return funcionAptitud;
    }

    @Override
    public String toString() {
        return "Individuo{" + "genes=" + Arrays.toString(genes) + ", aptitud=" + aptitud + '}';
    }

}
