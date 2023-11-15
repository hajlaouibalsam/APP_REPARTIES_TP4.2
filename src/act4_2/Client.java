package act4_2;

import java.net.*;

public class Client 
{
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Création du socket client
            socket = new DatagramSocket();

            // Adresse et port du serveur
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 1234;

            // Message à envoyer au serveur pour demander l'heure
            String requestMessage = "heure";
            byte[] requestData = requestMessage.getBytes();

            // Envoi de la demande au serveur
            DatagramPacket request = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
            socket.send(request);

            // Attente de la réponse du serveur
            byte[] buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            // Affichage de la réponse du serveur (date et heure)
            String serverResponse = new String(response.getData(), 0, response.getLength());
            System.out.println("Date et heure reçues du serveur : " + serverResponse);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}