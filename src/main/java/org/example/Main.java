package org.example;

import org.example.proceso.Aerosilla;
import org.example.proceso.Esquiador;

import java.util.concurrent.Semaphore;

public class Main {
    private static final int NUM_ESQUIADORES = 8;
    private static final int NUM_SILLAS = 2;

    public static void main(String[] args) {
        Semaphore sillaDisponible = new Semaphore(0);
        Semaphore esquiadoresEnSilla = new Semaphore(0);
        Semaphore sillaPorOcupar = new Semaphore(1); //controla que haya solo una silla esperando esquiadores por vez

        for (int i = 1; i <= NUM_ESQUIADORES; i++) {
            new Thread(new Esquiador(i, sillaDisponible, esquiadoresEnSilla)).start();
        }

        for (int i = 1; i <= NUM_SILLAS; i++) {
            new Thread(new Aerosilla(i, sillaDisponible, esquiadoresEnSilla, sillaPorOcupar)).start();

        }
    }
}