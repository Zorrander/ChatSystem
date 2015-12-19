/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandre
 */
public class SocketListenerTCP implements Runnable {

    private final ChatNIController controller;
    private final ServerSocket socket;
    private final File myFile;
    private final float size;

    public SocketListenerTCP(ChatNIController controller, ServerSocket socket, float fileSize, String fileName, String absolutePath) {
        this.controller = controller;
        this.socket = socket;
        this.myFile = new File(absolutePath, fileName);
        this.size = fileSize;
    }

    @Override
    public void run() {

        try {
            try (Socket socketReceiver = socket.accept()) {
                byte[] mybytearray = new byte[(int) size];
                InputStream is = socketReceiver.getInputStream();
                FileOutputStream fos = new FileOutputStream(myFile.getAbsolutePath());
                try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                    int bytesRead;
                    while ((bytesRead = is.read(mybytearray, 0, mybytearray.length)) > 0) {
                        bos.write(mybytearray, 0, bytesRead);
                    }
                    is.close();
                    bos.close();
                }
                try {
                    this.notifyEndTCP();
                } catch (IOException ex) {
                    Logger.getLogger(SocketListenerTCP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketListenerTCP.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void notifyEndTCP() throws IOException {
        controller.stopTCP();
    }
}
