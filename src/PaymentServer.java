import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PaymentServer {
    private static final int PORT = 55407;
    private static final int NUM_THREADS = 5;
    private static final int MAX_PENDING_CLIENTS = 15; // Taille de la file d'attente

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            // Définit la taille de la file d'attente pour les connexions entrantes
            serverSocket = new ServerSocket(PORT, MAX_PENDING_CLIENTS);
            ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

            System.out.println("Serveur de paiement en attente de connexions...");

            while (true) {
                // Attendez une connexion entrante
                Socket clientSocket = serverSocket.accept();

                // Soumettez la tâche au pool de threads pour traitement
                executor.execute(new PaymentWorker(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
