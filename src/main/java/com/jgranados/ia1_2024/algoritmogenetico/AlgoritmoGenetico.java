/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.jgranados.ia1_2024.algoritmogenetico;

import com.jgranados.ia1_2024.algoritmogenetico.operadores.CrucePorUnPunto;
import com.jgranados.ia1_2024.algoritmogenetico.operadores.MutacionPorIntercambio;
import com.jgranados.ia1_2024.algoritmogenetico.poblacion.Individuo;
import com.jgranados.ia1_2024.algoritmogenetico.poblacion.Poblacion;
import com.jgranados.ia1_2024.algoritmogenetico.seleccion.FuncionAptitud;
import com.jgranados.ia1_2024.algoritmogenetico.seleccion.SeleccionPorRuleta;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author jose
 */
public class AlgoritmoGenetico extends Thread {

    private static final double PORCENTAGE_MUTACION = 0.1;
    private static final int CANTIDAD_POBLACION = 1000;
    private static final long CANTIDAD_GENERACIONES = 1000;

    private SeleccionPorRuleta seleccionPorRuleta = new SeleccionPorRuleta();
    private CrucePorUnPunto crucePorUnPunto = new CrucePorUnPunto();
    private MutacionPorIntercambio mutacionPorIntercambio = new MutacionPorIntercambio(PORCENTAGE_MUTACION);

    private Poblacion poblacion = new Poblacion(CANTIDAD_POBLACION);
    private Individuo[] nuevaGeneracion;
    private int generacion = 0;

    private AGMainFrame mainFrame;

    Optional<Individuo> solucion;

    public AlgoritmoGenetico(AGMainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void run() {
        solucion = obtenerSolucion();
        mainFrame.mostrarSolucion(solucion, generacion);
    }

    /*public static void main(String[] args) {
        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico();

        Optional<Individuo> solucion = algoritmoGenetico.obtenerSolucion();
        if (solucion.isPresent()) {
            System.out.println("Solucion: " + solucion.get());
        } else {
            System.out.println("Sin solucion");
        }
    }*/
    public Optional<Individuo> obtenerSolucion() {
        Optional<Individuo> solucion = evaluarPoblacion();

        if (solucion.isEmpty()) {
            System.out.println("No hay solucion en poblacion inicial.");
        }

        while (solucion.isEmpty() && generacion < CANTIDAD_GENERACIONES) {
            generacion++;
            System.out.println("Iniciando generacion No. " + generacion);

            seleccionarPadres();
            cruzarPadres();
            aplicarMutacion();
            redefinirPoblacion();

            Individuo mejor = poblacion.obtenerMejorIndivudio();
            Individuo peor = poblacion.obtenerPeorIndivudio();

            System.out.println("Peor  individuo de la generacion: " + peor);
            System.out.println("Mejor individuo de la generacion: " + mejor);
            //System.out.println("  Aptitud total de la generacion: " + poblacion.getTotalAptitud());
            System.out.println("---------------------------------------------------");

            mainFrame.agregarDato(peor.getAptitud(), "Peor Individuo", "" + generacion);
            mainFrame.agregarDato(mejor.getAptitud(), "Mejor Individuo", "" + generacion);
            //mainFrame.agregarDato(poblacion.getTotalAptitud(), "aptitud", "" + generacion);

            solucion = evaluarPoblacion();
        }
        System.out.println("Mutaciones aplicadas: " + mutacionPorIntercambio.getMutaciones());

        return solucion;
    }

    private Optional<Individuo> evaluarPoblacion() {
        return Arrays.asList(poblacion.getPoblacionActual()).stream()
                .filter(individuo -> individuo.getAptitud() == FuncionAptitud.CANTIDAD_PAREJAS_OBJETIVO)
                .findFirst();
    }

    private void seleccionarPadres() {
        Individuo[] padresSeleccionados = seleccionPorRuleta.seleccionarPadres(poblacion);
        poblacion.setPadresSeleccionados(padresSeleccionados);
    }

    private void cruzarPadres() {
        nuevaGeneracion = crucePorUnPunto.cruzarPadres(poblacion);
    }

    private void aplicarMutacion() {
        mutacionPorIntercambio.aplicarMutacion(nuevaGeneracion);
    }

    private void redefinirPoblacion() {
        poblacion.reemplazarPoblacion(nuevaGeneracion);
    }
}
