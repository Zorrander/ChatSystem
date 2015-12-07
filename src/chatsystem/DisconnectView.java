package chatsystem;

import common.Message;
import java.io.IOException;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DisconnectView implements SuperView {
    
    private ChatNIController chatController = null ;
    private NicknameBox connectBox ;
    private Scene disconnectScene ;
    private Group root ; 
    
    public DisconnectView(ChatNIController chatController) {
        GridPane gridpane ;
        
        this.chatController = chatController ; 
        root = new Group();
        disconnectScene = new Scene(root, 800, 600, Color.LIGHTBLUE);
        connectBox = new NicknameBox(this) ;
        
        gridpane = new GridPane();
        Button tempButton = connectBox.getButtonConnect();
        TextField tempField = connectBox.getNicknameField();
        
        gridpane.add(tempField,1,1);     
        gridpane.add(tempButton,1,2);
        tempButton.setAlignment(Pos.CENTER);
        root.getChildren().add(gridpane);
        
        gridpane.setHalignment(tempField,HPos.CENTER);
        gridpane.setMargin(tempButton,new Insets(50,0,0,0));
        gridpane.setHalignment(tempButton,HPos.CENTER);
        
        root.setTranslateX(300);
        root.setTranslateY(200);
    }
    
    public void startView(Stage primaryStage){
        
        primaryStage.setTitle("FakeBook");
        primaryStage.setScene(disconnectScene);
        primaryStage.show();
    }
    
    public void connect(String nickname) throws IOException{
        
        //Update context state 
        
        
        //Broadcast message hello
        Message coucou;
        coucou = new Message(common.Message.MsgType.HELLO,"",nickname);
        chatController.send(coucou, "255.255.255.255");
        
        //Listening Hello replies and MAJ user list
            //reception
            
            //mise à jour
    }
}
