package chatsystem;

import java.io.*;
import java.net.*;
import common.Message;
import static common.Message.MsgType.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import org.controlsfx.control.Notifications;

public class ChatNIController {

    private ConnectState state;
    private final int numPort = 2042;
    private DatagramSocket socket;
    private SocketListener socketListener;
    private SocketListenerTCP socketListenerTCP;

    private Thread t;
    private File currentFile = null;

    public ChatNIController(ConnectState state) {
        this.state = state;
        try {
            this.socket = new DatagramSocket(numPort);
            this.socketListener = new SocketListener(this, socket);
            t = new Thread(socketListener);
            t.start();
        } catch (SocketException ex) {
            Logger.getLogger(ChatNIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Envoi d'un HELLO à la communauté
        this.sendHello();
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

    public void sendFile(String destinataire) throws IOException {
        InetAddress serveur = InetAddress.getByName(destinataire);
        Socket socket = new Socket( serveur, numPort);
        byte[] bytearray = new byte[(int) currentFile.length()];
        FileInputStream fin = new FileInputStream(currentFile);
        BufferedInputStream bin = new BufferedInputStream(fin);
        bin.read(bytearray, 0, bytearray.length);
        OutputStream os = socket.getOutputStream();
        System.out.println("Sending Files...");
        os.write(bytearray, 0, bytearray.length);
        os.flush();
        socket.close();

    }

    public void sendFileRequest(String destinataire) throws IOException {
        long sizeOfFile = this.currentFile.length();
        System.out.println(sizeOfFile);
        Message fileRequest = new Message(FILE_REQUEST, this.currentFile.getName(), state.getId(), (float) sizeOfFile);
        send(fileRequest, destinataire);
    }

    public void sendFileAccept(String nameFile, String destinataire) throws IOException {
        Message fileAccept = new Message(FILE_ACCEPT, nameFile, state.getId());
        state.getController().send(fileAccept, destinataire);
    }

    public void sendFileRefuse(String destinataire) throws IOException {
        Message fileRefuse = new Message(FILE_REFUSE, "", state.getId());
        state.getController().send(fileRefuse, destinataire);
    }

    private void sendHello() {
        Message hello = new Message(HELLO, "", state.getId());
        try {
            this.send(hello, "255.255.255.255");
        } catch (IOException ex) {
            Logger.getLogger(ChatNIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendHelloReply(String ipAdress) {
        Message helloReply = new Message(HELLO_REPLY, "", state.getId());
        try {
            this.send(helloReply, ipAdress);
        } catch (IOException ex) {
            Logger.getLogger(ChatNIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void sendBye() {
        Message bye = new Message(BYE, "", state.getId());
        try {
            this.send(bye, "255.255.255.255");
        } catch (IOException ex) {
            Logger.getLogger(ChatNIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stop() {
        socketListener.stopRunning();
        socket.close();
    }
    
    public void stopTCP() throws IOException{
        
        Platform.runLater( ()-> Notifications.create().title("File received").text("Fichier reçu !").darkStyle().showInformation()) ;       
        socketListener.getSocketTCP().close();
    }

    //Invoked by SocketListener
    void addUser(User newUser) {
        this.getState().addUser(newUser);
    }

    //Invoked by SocketListener
    void deleteUser(User newUser) {
        this.getState().deleteUser(newUser);
    }

    private ConnectState getState() {
        return this.state;
    }

    public User getUserSelected() {
        return state.getUserSelected();
    }

    public void setCurrentFile(File file) {
        this.currentFile = file;
    }

    //Invoked by SocketListener
    public User getUser(User newUser) {
        return this.getState().getUser(newUser);
    }

    public int getPort() {
        return this.numPort;
    }

    public SocketListenerTCP getSocketListenerTCP() {
        return socketListenerTCP;
    }

    public void setSocketListenerTCP(SocketListenerTCP socketListenerTCP) {
        this.socketListenerTCP = socketListenerTCP;
    }

}
