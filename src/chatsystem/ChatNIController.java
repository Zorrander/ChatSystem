package chatsystem;

import java.io.*;
import java.net.*;
import javafx.fxml.FXML;
import common.Message;
import static common.Message.MsgType.* ;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ChatNIController {

    private ConnectState state;
    private final int numPort = 2042;
    private DatagramSocket socket ;
    private SocketListener socketListener ;

    public ChatNIController(ConnectState state) {
        this.state = state ;
        try {
            this.socket = new DatagramSocket(numPort) ;
            this.socketListener = new SocketListener(this, socket) ;
            Thread t = new Thread(socketListener) ;
            t.start();
        } catch (SocketException ex) {
            Logger.getLogger(ChatNIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Envoi d'un HELLO à la communauté
        this.sendHello() ;
    }  


    /**
     * Send a message
     *
     * @param msg contient MsgType msgType
     * @param destinataire destinaire du message
     * @throws IOException
     */
    public void send(Message msg, String destinataire) throws IOException {
       
        InetAddress serveur = InetAddress.getByName(destinataire);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(msg);
        byte[] buf = baos.toByteArray();

        // création du datagramme         
        DatagramPacket dataToSent = new DatagramPacket(buf, buf.length, serveur, numPort);     

        // envoie du message         
        socket.send(dataToSent);      
    }

    
    
    private void sendHello() {
        Message hello = new Message(HELLO,"",state.getId()) ;
        try {
            this.send(hello,"255.255.255.255") ;
        } catch (IOException ex) {
            Logger.getLogger(ChatNIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void sendHelloReply(String ipAdress) {
        Message helloReply = new Message(HELLO_REPLY,"",state.getId()) ;
        try {
            this.send(helloReply,ipAdress) ;
        } catch (IOException ex) {
            Logger.getLogger(ChatNIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    public ConnectState getState() {
        return this.state;
    }
    
  

}
