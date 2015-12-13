/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import java.net.DatagramSocket;
import java.net.ServerSocket;

/**
 *
 * @author Alexandre
 */
public class SocketListenerTCP implements Runnable {

    private final ChatNIController controller;
    private final ServerSocket socket;
    private boolean running;

    public SocketListenerTCP(ChatNIController controller, ServerSocket socket) {
        this.controller = controller;
        this.socket = socket;
        this.running = true;
    }
    
    
    @Override
    public void run() {
    
    }
    
}
