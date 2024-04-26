/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgranados.ia1_2024.algoritmogenetico.seleccion;

import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author jose
 */
public class FuncionAptitudTest {
    
    @Test
    void testCentro() {
        FuncionAptitud funcionAptitud = new FuncionAptitud();
        
        Set<Coordenada> result = funcionAptitud.obtenerCasillasAtacadas(4, 4);
        
        Assertions.assertEquals(20, result.size());
    }
    
    @Test
    void testEsquinaIzqArriba() {
        FuncionAptitud funcionAptitud = new FuncionAptitud();
        
        Set<Coordenada> result = funcionAptitud.obtenerCasillasAtacadas(8, 1);
        
        Assertions.assertEquals(14, result.size());
    }
    
    @Test
    void testEsquinaDerArriba() {
        FuncionAptitud funcionAptitud = new FuncionAptitud();
        
        Set<Coordenada> result = funcionAptitud.obtenerCasillasAtacadas(8, 8);
        
        Assertions.assertEquals(14, result.size());
    }
    
    @Test
    void testEsquinaIzqAbajo() {
        FuncionAptitud funcionAptitud = new FuncionAptitud();
        
        Set<Coordenada> result = funcionAptitud.obtenerCasillasAtacadas(1, 1);
        
        Assertions.assertEquals(14, result.size());
    }
    
    @Test
    void testEsquinaDerAbajo() {
        FuncionAptitud funcionAptitud = new FuncionAptitud();
        
        Set<Coordenada> result = funcionAptitud.obtenerCasillasAtacadas(1, 8);
        
        Assertions.assertEquals(14, result.size());
    }
}
