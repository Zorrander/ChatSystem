/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import common.Message;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ConnectView implements SuperView{
    private ChatNIController chatController = null ;
    private Scene connectScene ;
    private Group root ;
    
    private MessageBox mb ;
    private UserListView usrView ;
    private Prompter prptr;  
    
    public ConnectView(ChatNIController chatController) throws IOException {
        this.chatController = chatController ; 
        root = new Group();
        connectScene = new Scene(root, 800, 600, Color.LIGHTBLUE);
        
        //création des composants
        mb = new MessageBox(this);
        usrView = new UserListView(this);
        prptr = new Prompter(this);
        
        Group mbgrp = mb.getGroupe();
        Group usrViewgrp = usrView.getGroupe();
        Group prptrgrp = prptr.getGroupe();
        
        GridPane gridpane = new GridPane() ;
        
        gridpane.add(mbgrp,1,1);     
        gridpane.add(usrViewgrp,1,2);
        gridpane.add(prptrgrp,1,3);
        
        root.getChildren().add(gridpane);       
        
      /*  //Broadcast message hello
        Message coucou;
        coucou = new Message(common.Message.MsgType.HELLO,"",nickname);
        chatController.send(coucou, "255.255.255.255");*/
    }
    
     public void startView(Stage primaryStage){
        
        primaryStage.setTitle("FakeBook");
        primaryStage.setScene(connectScene);
        primaryStage.show();
    }
}
