
// Local: src/Simulador.java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Simulador {
    static final int N = 3;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        BlockingQueue<Object>[] queues = new BlockingQueue[N];
        for (int i = 0; i < N; i++) {
            queues[i] = new LinkedBlockingQueue<>();
        }

        System.out.println("--- INICIANDO SIMULAÇÃO: PARTE 3 - RELÓGIOS FÍSICOS ---");

        for (int i = 0; i < N; i++) {
            // A mágica acontece na classe Processo, que estamos instanciando aqui
            Processo processo = new Processo(i, N, queues);
            processo.start();
        }
    }
}