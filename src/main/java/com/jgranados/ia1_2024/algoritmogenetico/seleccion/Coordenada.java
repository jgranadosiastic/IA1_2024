/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.ia1_2024.algoritmogenetico.seleccion;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author jose
 */
public class Coordenada {

    private int fila;
    private int columna;

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fila)
                .append(columna)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordenada other = (Coordenada) obj;
        if (this.fila != other.fila) {
            return false;
        }
        return this.columna == other.columna;
    }

}
