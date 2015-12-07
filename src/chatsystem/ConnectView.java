/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

/**
 *
 * @author Alexandre
 */
public class ConnectView implements SuperView{
    private ChatNIController chatController = null ;
    
    public ConnectView(ChatNIController chatController) {
        this.chatController = chatController ; 
    }
}
