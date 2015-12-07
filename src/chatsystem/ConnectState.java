package chatsystem;

import java.io.IOException;

public class ConnectState implements State {
   private String id = null;
   private ConnectView chatView = null;

   public ConnectState(String nickname, ChatNIController controller) throws IOException{
       this.id = nickname ;
              
       //initialisation de la vue
        this.chatView = new ConnectView(controller);
        this.chatView.startView(Chat.window);
       
   }
   public String toString(){
      return "Stop State";
   }  
   public void updateContext(Context context,State newState){
       context.setState(newState);
   }

   public String getId(){
       return this.id;
   }
   
    @Override
    public void doAction(Context context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
