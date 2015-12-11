package chatsystem;

import common.Message;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketReceiver implements Runnable {

    private final DatagramPacket packet;
    private final SocketListener socketListener;

    public SocketReceiver(DatagramPacket packet, SocketListener socketListener) {
        this.packet = packet;
        this.socketListener = socketListener;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
            Message messageRecu = (Message) ois.readObject();
            /**
             * TRAITEMENT DU MESSAGE EN FONCTION DE SON TYPE
             */

            /**
             * Declaration de variables communes à certains cases, utiles pour
             * recup les infos
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
                    System.out.println("Recu un HELLO_REPLY de "+ newUser.getName() +"@"+ newUser.getAdress()) ;
                    
                    
                    socketListener.getController().getState().getUserList().addViewListener(newUser);
                    break;
                case HELLO:
                    //DEBUG
                    System.out.println("Recu un HELLO de "+ newUser.getName() +"@"+ newUser.getAdress()) ;
                    
                    
                    socketListener.getController().getState().getUserList().addViewListener(newUser);
                    socketListener.getController().sendHelloReply(newUser.getAdress());
                    //@TODO : Afficher une notification de connexion !
                    break;
                case TEXT_MESSAGE:
                    System.out.println("Recu un TEXT_MESS de "+ newUser.getName() +"@"+ newUser.getAdress()) ;
                    
                    String text = messageRecu.getContent();
                    if ((text != null) && !text.isEmpty()) {
                        User inList = socketListener.getController().getState().getUserList().getUser(newUser);
                        //Si l'USER existe bel et bien dans notre liste, on rajoute le message !
                        if (inList != null) {
                            inList.addMessage(messageRecu);
                        }
                    }
                    break;
                case BYE:
                    System.out.println("Recu un BYE de "+ newUser.getName() +"@"+ newUser.getAdress()) ;
                    
                    socketListener.getController().getState().getUserList().deleteViewListener(newUser);
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
        } catch (IOException ex) {
            Logger.getLogger(SocketReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
