package org.example.proceso;

import java.util.concurrent.Semaphore;

public class Aerosilla implements Runnable {
    private int id;
    private Semaphore esquiadoresEnSilla;
    private Semaphore sillaDisponible;
    private Semaphore sillaPorOcupar;

    public Aerosilla(int id, Semaphore sillaDisponible, Semaphore esquiadores, Semaphore sillaPorOcupar) {
        this.id = id;
        this.esquiadoresEnSilla = esquiadores;
        this.sillaDisponible = sillaDisponible;
        this.sillaPorOcupar = sillaPorOcupar;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Silla " + id + " en fila");
                sillaPorOcupar.acquire();

                System.out.println("Silla " + id + " en espera de esquiadores");
                sillaDisponible.release(4);

                esquiadoresEnSilla.acquire();

                System.out.println("La silla "+ id + " esta completa. Se eleva");

                Thread.sleep(2000); //simula el tiempo de viaje hacia la cima

                System.out.println("La silla llego a la cima. Esquiadores se bajan");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sillaPorOcupar.release();
            }
        }
    }
}