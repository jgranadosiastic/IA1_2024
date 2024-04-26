/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.ia1_2024.algoritmogenetico.seleccion;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author jose
 */
public class FuncionAptitud {

    public static final int CANTIDAD_PAREJAS_OBJETIVO = 28;
    public static final int CANTIDAD_FILAS = 8;
    public static final int CANTIDAD_COLUMNAS = 8;

    public int calcularAptitud(int[] genes) {
        int parejasQueNoSeAtacan = 0;

        // solo evaluamos las reinas de la columna 1 a la 7.
        // no necesitamos evaluar la ultima porque ya fue evaluada por las anteriores
        for (int columnaGenActual = 1; columnaGenActual < genes.length; columnaGenActual++) {
            int filaGenActual = genes[columnaGenActual - 1];

            Set<Coordenada> coordenadasAtacables = obtenerCasillasAtacadas(filaGenActual, columnaGenActual);

            // solo verificamos las reinas siguientes a la reina del gen actual
            for (int columnaAtacable = columnaGenActual + 1; columnaAtacable <= genes.length; columnaAtacable++) {
                int filaAtacable = genes[columnaAtacable - 1];
                Coordenada coordenadaAtacable = new Coordenada(filaAtacable, columnaAtacable);

                if (!coordenadasAtacables.contains(coordenadaAtacable)) {
                    parejasQueNoSeAtacan++;
                }
            }
        }

        return parejasQueNoSeAtacan;
    }

    public Set<Coordenada> obtenerCasillasAtacadas(int fila, int columna) {
        Set<Coordenada> coordendasAtacables = new HashSet<>();
        // casillas izquierda
        for (int i = columna - 1; i > 0; i--) {
            coordendasAtacables.add(new Coordenada(fila, i));
        }

        // casillas derecha
        for (int i = columna + 1; i <= CANTIDAD_COLUMNAS; i++) {
            coordendasAtacables.add(new Coordenada(fila, i));
        }

        // casillas diagonales
        int filaActual;
        int columnaActual;
        // diagonal izq-arriba
        filaActual = fila + 1;
        columnaActual = columna - 1;
        while (filaActual <= CANTIDAD_FILAS && columnaActual > 0) {
            coordendasAtacables.add(new Coordenada(filaActual, columnaActual));
            filaActual++;
            columnaActual--;
        }

        // diagonal der-arriba
        filaActual = fila + 1;
        columnaActual = columna + 1;
        while (filaActual <= CANTIDAD_FILAS && columnaActual <= CANTIDAD_COLUMNAS) {
            coordendasAtacables.add(new Coordenada(filaActual, columnaActual));
            filaActual++;
            columnaActual++;
        }

        // diagonal izq-abajo
        filaActual = fila - 1;
        columnaActual = columna - 1;
        while (filaActual > 0 && columnaActual > 0) {
            coordendasAtacables.add(new Coordenada(filaActual, columnaActual));
            filaActual--;
            columnaActual--;
        }

        // diagonal der-abajo
        filaActual = fila - 1;
        columnaActual = columna + 1;
        while (filaActual > 0 && columnaActual <= CANTIDAD_COLUMNAS) {
            coordendasAtacables.add(new Coordenada(filaActual, columnaActual));
            filaActual--;
            columnaActual++;
        }

        return coordendasAtacables;
    }
}
