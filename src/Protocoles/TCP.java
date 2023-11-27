package Protocoles;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP {

    private static final int TAILLE_MAX_DATA = 1500; // Définis la taille maximale des données à envoyer/recevoir

    public static ServerSocket createServerSocket(int port) {
        try {
            return new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Socket acceptConnection(ServerSocket serverSocket) {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Socket createClientSocket(String serverIP, int serverPort) {
        try {
            InetAddress serverAddress = InetAddress.getByName(serverIP);
            return new Socket(serverAddress, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int send(Socket socket, byte[] data, int size) {
        if (size > TAILLE_MAX_DATA) {
            return -1;
        }

        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data, 0, size);
            outputStream.write('#'); // Ajout du caractère '#' à la fin
            outputStream.write(')'); // Ajout du caractère ')' à la fin
            outputStream.flush();
            return size;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int receive(Socket socket, byte[] data) {
        boolean finished = false;
        int bytesRead;
        int i = 0;

        try {
            InputStream inputStream = socket.getInputStream();

            while (!finished) {
                bytesRead = inputStream.read();

                if (bytesRead == -1) {
                    return i;
                }

                char currentChar = (char) bytesRead;

                if (currentChar == '#') {
                    bytesRead = inputStream.read();

                    if (bytesRead == -1) {
                        return i;
                    }

                    char nextChar = (char) bytesRead;

                    if (nextChar == ')') {
                        data[i] = '\0'; // Null-terminate the string
                        finished = true;
                    } else {
                        data[i] = (byte) currentChar;
                        data[i + 1] = (byte) nextChar;
                        i += 2;
                    }
                } else {
                    data[i] = (byte) currentChar;
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return i;
    }
}
