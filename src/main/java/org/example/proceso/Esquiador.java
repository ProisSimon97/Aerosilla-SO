package org.example.proceso;

import java.util.concurrent.Semaphore;

public class Esquiador implements Runnable {
    private int id;
    private Semaphore sillaDisponible;
    private Semaphore esquiadoresEnSilla;

    public Esquiador(int id, Semaphore sillaDisponible, Semaphore esquiadoresEnSilla) {
        this.id = id;
        this.sillaDisponible = sillaDisponible;
        this.esquiadoresEnSilla = esquiadoresEnSilla;
    }

    @Override
    public void run() {
        System.out.println("Esquiador " + id + " esta en la fila");
        try {
            sillaDisponible.acquire(); //espera a que una silla este disponible
            System.out.println("Esquiador " + id + " se sube a la silla");

            esquiadoresEnSilla.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}