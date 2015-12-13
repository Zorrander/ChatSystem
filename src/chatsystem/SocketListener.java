/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;


import common.Message;
import static common.Message.MsgType.TEXT_MESSAGE;
import common.MessageRecu;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javax.management.Notification;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Kray
 */
public class SocketListener implements Runnable {

    private final ChatNIController controller;
    private final DatagramSocket socket;
    private boolean running;

    public SocketListener(ChatNIController controller, DatagramSocket socket) {
        this.controller = controller;
        this.socket = socket;
        this.running = true;
    }

    @Override
    public void run() {
        byte[] buf = new byte[1500];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        while (running) {

            try {

                try {
                    socket.receive(packet);
                    boolean isOurs = (packet.getAddress().getHostAddress().equals(InetAddress.getLocalHost().getHostAddress()));

                    if ((packet.getLength() != 0) && (!isOurs)) {
                        /**
                         * SocketReceiver socketReceiver = new
                         * SocketReceiver(packet, this) ; Thread t = new
                         * Thread(socketReceiver) ; t.start();
                         */

                        ObjectInputStream ois;
                        try {
                            ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
                            Message messageRecu = (Message) ois.readObject();

                            /**
                             * TRAITEMENT DU MESSAGE EN FONCTION DE SON TYPE
                             */
                            /**
                             * Declaration de variables communes à certains
                             * cases, utiles pour recup les infos
                             */
                            User newUser;

                            if (messageRecu.getSender().isEmpty()) {
                                newUser = new User("anonyme", packet.getAddress().getHostAddress());
                            } else {
                                newUser = new User(messageRecu.getSender(), packet.getAddress().getHostAddress());
                            }

                            switch (messageRecu.getType()) {
                                case HELLO_REPLY:
                                    //DEBUG
                                    System.out.println("Recu un HELLO_REPLY de " + newUser.getName() + "@" + newUser.getAdress());

                                    Platform.runLater(() -> controller.addUser(newUser));
                                    break;
                                case HELLO:
                                    //DEBUG
                                    System.out.println("Recu un HELLO de " + newUser.getName() + "@" + newUser.getAdress());

                                    Platform.runLater(() -> controller.addUser(newUser));
                                    Platform.runLater(() -> controller.sendHelloReply(newUser.getAdress()));
                                    //@TODO : Afficher une notification de connexion !
                                    break;
                                case TEXT_MESSAGE: 
                                    if (getUserConversation() == null){
                                             Platform.runLater(() -> 
                                                 Notifications.create().title("Nouveau message").text("Vous avez un nouveau message de "+ newUser.getName()).darkStyle().showInformation());
                                    }else if(!(getUserConversation().getName().equals(newUser.getName())) || !(getUserConversation().getName().equals(newUser.getName()))) {
                                    Platform.runLater(() -> 
                                           Notifications.create().title("Nouveau message").text("Vous avez un nouveau message de "+ newUser.getName()).darkStyle().showInformation());
                                    }
                                    System.out.println("Recu un TEXT_MESS de " + newUser.getName() + "@" + newUser.getAdress());
                                    String text = messageRecu.getContent();
                                    if ((text != null) && !text.isEmpty()) {
                                        User inList = controller.getUser(newUser);
                                        MessageRecu messageAStocker = new MessageRecu(TEXT_MESSAGE, messageRecu.getContent(), messageRecu.getSender());
                                        //Si l'USER existe bel et bien dans notre liste, on rajoute le message !
                                        if (inList != null) {

                                            Platform.runLater(() -> inList.addMessage(messageAStocker));
                                        } else {
                                            controller.addUser(newUser);
                                            Platform.runLater(() -> newUser.addMessage(messageAStocker));
                                        }
                                    }
                                    break;
                                case BYE:
                                    System.out.println("Recu un BYE de " + newUser.getName() + "@" + newUser.getAdress());
                                    Platform.runLater(() -> controller.deleteUser(newUser));
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
                            Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
                }

                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stopRunning() {
        this.running = false;
    }

    public ChatNIController getController() {
        return controller;
    }
    
    public User getUserConversation(){
        return controller.getUserSelected();
    }

    public DatagramSocket getSocket() {
        return socket;
    }

}
