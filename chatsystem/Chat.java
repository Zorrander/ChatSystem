/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Alexandre
 */
public class Chat {

    private int numPort = 2042;

    private static Chat instance = null;

    private Chat() throws SocketException {
        /*
	 * Création d'un socket pour envoyer ET recevoir 
         */
        DatagramSocket socket = new DatagramSocket(numPort);
    }

    public static Chat getInstance() throws SocketException {

        if (instance == null) {
            instance = new Chat();
        }

        return instance;

    }

    /**
     * @return le numéro de port
     */
    public int getNumPort() {
        return this.numPort;
    }

}
