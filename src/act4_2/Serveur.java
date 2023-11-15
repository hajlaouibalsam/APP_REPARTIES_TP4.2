package act4_2;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Serveur 
{
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Création du socket serveur sur le port 1234
            socket = new DatagramSocket(1234);
            System.out.println("je suis un serveur en attente d'un client");

            byte[] buffer = new byte[1024];

            while (true) {
                // Attente de la réception d'une demande du client
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                // Traitement de la demande du client
                String requestMessage = new String(request.getData(), 0, request.getLength());

                if (requestMessage.equals("heure")) {
                    // Récupération de la date et de l'heure actuelles
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String responseMessage = dateFormat.format(new Date());

                    // Récupération de l'adresse et du port du client
                    InetAddress clientAddress = request.getAddress();
                    int clientPort = request.getPort();

                    // Envoi de la réponse au client
                    byte[] responseData = responseMessage.getBytes();
                    DatagramPacket reply = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                    socket.send(reply);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}