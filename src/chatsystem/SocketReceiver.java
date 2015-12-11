package chatsystem;

import common.Message;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SocketReceiver implements Runnable{
    
    private final DatagramPacket packet;
    private final SocketListener socketListener ;

    public SocketReceiver(DatagramPacket packet, SocketListener socketListener) {
        this.packet = packet;
        this.socketListener = socketListener ;
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
            if (messageRecu.getType() == common.Message.MsgType.HELLO_REPLY){
                User newUser;
                newUser = new User(messageRecu.getSender(),packet.getAddress().toString());
                socketListener.getController().getState().getUserList().addViewListener(newUser);
            }
            
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
