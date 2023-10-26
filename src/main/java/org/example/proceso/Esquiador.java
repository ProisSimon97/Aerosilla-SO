package org.example.proceso;

import java.util.concurrent.Semaphore;

import static org.example.Main.EN_SILLA_COUNT;

public class Esquiador implements Runnable {
    private int id;
    private Semaphore sillaDisponible;
    private Semaphore esquiadoresEnSilla;
    private Semaphore contador;

    public Esquiador(int id, Semaphore sillaDisponible, Semaphore esquiadoresEnSilla, Semaphore contador) {
        this.id = id;
        this.sillaDisponible = sillaDisponible;
        this.esquiadoresEnSilla = esquiadoresEnSilla;
        this.contador = contador;
    }

    @Override
    public void run() {
        System.out.println("Esquiador " + id + " esta en la fila");
        try {
            sillaDisponible.acquire(); //espera a que una silla este disponible

            contador.acquire();
            EN_SILLA_COUNT++;

            System.out.println("Esquiador " + id + " se sube a la silla");

            if(EN_SILLA_COUNT == 4) {
                EN_SILLA_COUNT = 0;
                contador.release();
                esquiadoresEnSilla.release(); //el ultimo en sentarse señaliza a la silla

            } else contador.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}