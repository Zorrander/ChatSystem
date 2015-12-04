/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

public class Message implements Serializable{

    public enum MsgType {HELLO, HELLO_REPLY, TEXT_MESSAGE, BYE, FILE_REQUEST, FILE_ACCEPT, FILE_REFUSE};

    private MsgType msgType;
    private String textContent;
    private String sender; // celui qui a envoyé le message, l'IP étant récupérée via le datagram (peut être inutile)
    
    public Message(MsgType type, String content, String sender){
        this.msgType = type;
        this.textContent = content;
        this.sender = sender;
    }
    
    public Message(MsgType type, String content){
        this.msgType = type;
        this.textContent = content;
        this.sender = "";
    }
    
    /**
     * @return the content
     */
    public String getContent(){
        return textContent;
    }

    /**
     * @return the type
     */
    public MsgType getType(){
        return msgType;
    }

    /**
     * @return the sender’s username
     */
    public String getSender() {
        return sender;
    }    
}
