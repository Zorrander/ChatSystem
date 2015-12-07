/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import java.io.*;
import java.net.*;

import common.Message;

public class ChatNIController {

    private Chat chatSystem;
    /**
     * Constructeur
     */
    public ChatNIController(Chat localChatSystem) {
        this.chatSystem = localChatSystem;
    }


    /**
     * Send a message
     *
     * @param msg contient MsgType msgType 
     * @param destinataire destinaire du message 
     * @throws IOException
     */
    public void send(Message msg, String destinataire) throws IOException {

        InetAddress serveur = InetAddress.getByName("destinataire");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(msg);
        byte[] buf = baos.toByteArray();

        /*
         * création du datagramme
         */
        DatagramPacket dataToSent = new DatagramPacket(buf, buf.length, serveur, chatSystem.getNumPort());
        DatagramSocket socket = new DatagramSocket();

        /*
	 * envoie du message
         */
        socket.send(dataToSent);

        /*
	 * fermeture du socket
         */
        socket.close();
    }

    /**
     * Receive a message
     *
     * @param socket le socket est défini dans le main  
     * @return Message l'objet Message reçu
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Message receive(DatagramSocket socket) throws IOException, ClassNotFoundException {

        /*
        * Définition de la taille du buffer
         */
        byte[] buf = new byte[1500];

        /*
        * Création du packet à recevoir
         */
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        /*
        * Recepetion du paquet		 
         */
        socket.receive(packet);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
        Message messageRecu = (Message) ois.readObject();
        ois.close();

        return messageRecu;
    }

}
