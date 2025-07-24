
// Local: src/Processo.java
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Processo extends Thread {
    private final int id;
    private final int numProcessos;
    private final BlockingQueue<Object>[] queues;
    private final Random random = new Random();

    // Relógio Vetorial
    private int[] vectorClock;

    public Processo(int id, int numProcessos, BlockingQueue<Object>[] queues) {
        this.id = id;
        this.numProcessos = numProcessos;
        this.queues = queues;
        this.vectorClock = new int[numProcessos]; // Vetor inicializado com [0, 0, 0]
    }

    // Regra 1: Incrementar a posição do próprio processo
    private void eventoLocal() {
        vectorClock[this.id]++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 7; i++) {
            try {
                Thread.sleep(random.nextInt(2000));

                if (random.nextBoolean()) {
                    eventoLocal();
                    log("realizou um evento interno.");
                } else {
                    eventoLocal(); // Incrementa para o evento de envio

                    int destId = random.nextInt(numProcessos);
                    while (destId == this.id)
                        destId = random.nextInt(numProcessos);

                    // Regra 2: Envia uma CÓPIA do vetor
                    MensagemVetor msg = new MensagemVetor(this.vectorClock.clone(), "Olá de P" + id);
                    queues[destId].put(msg);
                    log("enviou mensagem para P" + destId);
                }

                MensagemVetor receivedMsg = (MensagemVetor) queues[this.id].poll();
                if (receivedMsg != null) {
                    // Regra 3: Atualiza o vetor com o máximo de cada posição
                    for (int j = 0; j < numProcessos; j++) {
                        this.vectorClock[j] = Math.max(this.vectorClock[j], receivedMsg.vetor[j]);
                    }
                    eventoLocal(); // Incrementa para o evento de recebimento
                    log("recebeu mensagem com vetor " + Arrays.toString(receivedMsg.vetor));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void log(String evento) {
        System.out.printf("[P%d] [Vetor: %s] [Físico: %d] -> %s\n", this.id, Arrays.toString(this.vectorClock),
                System.currentTimeMillis(), evento);
    }
}