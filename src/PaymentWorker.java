import BeansModele.ClientBean;
import BeansModele.EmployeBean;
import BeansModele.FactureBean;
import BeansModele.FonctionsTravBean;
import Protocoles.TCP;
import Protocoles.VESPAP;
import database.EmployeDao;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

public class PaymentWorker implements Runnable {
    private Socket clientSocket;
    private int messageCount;

    public PaymentWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.messageCount = 0;
    }

    @Override
    public void run() {
        try {
            System.out.println("Connexion réussie depuis " + clientSocket.getInetAddress());
            OutputStream outputStream = clientSocket.getOutputStream();
            int estLoge = 0;
            boolean jeContinue = true;

            while (jeContinue) {
                byte[] clientMessageData = new byte[1500]; // Taille maximale des données
                int bytesRead = TCP.receive(clientSocket, clientMessageData);

                if (bytesRead == -1) {
                    break; // Erreur de réception, fermez la connexion
                }

                if (bytesRead == 0) {
                    break; // Fin de la transmission, fermez la connexion
                }

                String clientMessage = new String(clientMessageData, 0, bytesRead);

                System.out.println("Message du client : " + clientMessage);
                VESPAP vespap = new VESPAP();
                int codeRetour = vespap.VESPAP(clientMessage);
                String reponse = "";

                switch (codeRetour) {
                    case 1:
                        System.out.println("J'ai recu une requete LOGIN");
                        EmployeBean employe2 = vespap.JeMeLogue(clientMessage);
                        if (employe2 != null) {
                            System.out.println(employe2.toStringLigne());
                            estLoge = 1;
                            reponse = "LOGIN2#OK";
                        } else {
                            System.out.println("Employé null");
                            reponse = "LOGIN2#KO";
                        }
                        break;
                    case 2:
                        System.out.println("Je suis serveur cas 2 : ");
                        if (estLoge == 1)
                        {
                            reponse = vespap.facturesClient(clientMessage);
                            ClientBean client = vespap.clientSelectionne(clientMessage);
                            if (client != null){
                            String nomClient = client.getNom();
                            reponse += "#" + nomClient;
                            }
                            else
                            {
                                reponse="ERROR";
                            }
                        }
                        else {
                            reponse = "ERROR"; // Pour la sécurité
                        }
                        break;
                    case 3:
                        System.out.println("Je suis serveur cas 3 : ");
                        reponse = vespap.paiementfacture(clientMessage);
                        break;
                    case 4:
                        // Traitement pour le code de retour 4 (LOGOUT)
                        break;
                    case 5:
                        jeContinue = false;
                        estLoge = 0;
                        break;
                    case -1:
                        // Traitement pour une commande inconnue ou une erreur
                        break;
                    default:
                        // Gérer d'autres codes de retour si nécessaire
                        break;
                }

                String responseMessage = reponse;
                byte[] responseData = responseMessage.getBytes();
                TCP.send(clientSocket, responseData, responseData.length);
            }
        } catch (IOException e) {
            System.err.println("Erreur de communication avec le client: " + e.getMessage());
            // Vous pouvez ajouter ici un traitement supplémentaire en cas d'erreur
        } finally {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close(); // Assurez-vous que la socket est fermée proprement
                }
            } catch (IOException e) {
                System.err.println("Erreur lors de la fermeture de la socket: " + e.getMessage());
            }
        }
    }
}
