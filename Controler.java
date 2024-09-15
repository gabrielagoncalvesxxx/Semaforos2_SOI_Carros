import java.util.concurrent.Semaphore;

public class Controler {
    private static final Semaphore semaforo = new Semaphore(1);

    public static void executarSimulacao() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Carro(i)).start();
        }
    }

    static class Carro implements Runnable {
        private final int id;

        Carro(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                semaforo.acquire();
                View.mostrarCarroPassando(id);
                Thread.sleep(1000); // Simula o tempo que o carro leva para passar
                semaforo.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
