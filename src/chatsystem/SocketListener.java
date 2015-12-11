/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

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
                    SocketReceiver socketReceiver = new SocketReceiver(packet, this) ;
                    Thread t = new Thread(socketReceiver) ;
                    t.start();
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
