package org.example;

import org.example.proceso.Aerosilla;
import org.example.proceso.Esquiador;

import java.util.concurrent.Semaphore;

public class Main {
    private static final int NUM_ESQUIADORES = 8;
    private static final int NUM_SILLAS = 2;
    public static int EN_SILLA_COUNT = 0; //contador que, una vez alcanzado el maximo de permitidos por silla, permite al ultimo proceso esquiador señalizar a la silla

    public static void main(String[] args) {
        Semaphore sillaDisponible = new Semaphore(0);
        Semaphore esquiadoresEnSilla = new Semaphore(0);
        Semaphore sillaPorOcupar = new Semaphore(1); //controla que haya solo una silla esperando esquiadores por vez
        Semaphore mtxContador = new Semaphore(1); //para controlar el incremento del contador

        for (int i = 1; i <= NUM_ESQUIADORES; i++) {
            new Thread(new Esquiador(i, sillaDisponible, esquiadoresEnSilla, mtxContador)).start();
        }

        for (int i = 1; i <= NUM_SILLAS; i++) {
            new Thread(new Aerosilla(i, sillaDisponible, esquiadoresEnSilla, sillaPorOcupar)).start();

        }
    }
}