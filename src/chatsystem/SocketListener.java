/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import common.Message;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kray
 */
public class SocketListener implements Runnable {

    private final ChatNIController controller;
    private final DatagramSocket socket;

    public SocketListener(ChatNIController controller, DatagramSocket socket) {
        this.controller = controller;
        this.socket = socket;
    }

    @Override
    public void run() {
        byte[] buf = new byte[1500];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        while (true) {

            try {
                socket.receive(packet);

                if (packet.getLength() != 0) {
                    /**
                     * SocketReceiver socketReceiver = new
                     * SocketReceiver(packet, this) ; Thread t = new
                     * Thread(socketReceiver) ;
                    t.start();
                     */

                    ObjectInputStream ois = null;
                    try {
                        ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
                        Message messageRecu = (Message) ois.readObject();
                        /**
                         * TRAITEMENT DU MESSAGE EN FONCTION DE SON TYPE
                         */

                        /**
                         * Declaration de variables communes à certains cases,
                         * utiles pour recup les infos
                         */
                        User newUser;
                        if (messageRecu.getSender() == null) {
                            newUser = new User("anonyme", packet.getAddress().getHostAddress());
                        } else {
                            newUser = new User(messageRecu.getSender(), packet.getAddress().getHostAddress());
                        }
                        switch (messageRecu.getType()) {
                            case HELLO_REPLY:
                                //DEBUG
                                System.out.println("Recu un HELLO_REPLY de " + newUser.getName() + "@" + newUser.getAdress());

                                controller.getState().getUserList().addViewListener(newUser);
                                break;
                            case HELLO:
                                //DEBUG
                                System.out.println("Recu un HELLO de " + newUser.getName() + "@" + newUser.getAdress());

                                controller.getState().getUserList().addViewListener(newUser);
                                controller.sendHelloReply(newUser.getAdress());
                                //@TODO : Afficher une notification de connexion !
                                break;
                            case TEXT_MESSAGE:
                                System.out.println("Recu un TEXT_MESS de " + newUser.getName() + "@" + newUser.getAdress());

                                String text = messageRecu.getContent();
                                if ((text != null) && !text.isEmpty()) {
                                    User inList = controller.getState().getUserList().getUser(newUser);
                                    //Si l'USER existe bel et bien dans notre liste, on rajoute le message !
                                    if (inList != null) {
                                        inList.addMessage(messageRecu);
                                    }
                                }
                                break;
                            case BYE:
                                System.out.println("Recu un BYE de " + newUser.getName() + "@" + newUser.getAdress());

                                controller.getState().getUserList().deleteViewListener(newUser);
                                //@TODO : Afficher une notification de déconnection !
                                break;
                            case FILE_REQUEST:
                                //@TODO - Not Supported Yet
                                break;
                            case FILE_ACCEPT:
                                //@TODO - Not Supported Yet
                                break;
                            case FILE_REFUSE:
                                //@TODO - Not Supported Yet
                                break;
                        } 
                    
                        ois.close();
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(SocketReceiver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public ChatNIController getController() {
        return controller;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

}
