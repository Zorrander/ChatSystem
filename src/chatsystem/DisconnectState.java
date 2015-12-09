/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

public class DisconnectState implements State{
    
    private DisconnectView chatView = null;
    
    public DisconnectState(ChatNIController controller) {
        
        //initialisation de la vue
        this.chatView = new DisconnectView(controller);
        this.chatView.startView(Chat.window);
    }
    
    @Override
    public void doAction(Context context) {
      System.out.println("Player is in start state");
      context.setState(this);	
   }

   public String toString(){
      return "Disconnected";
   }
   
   public void updateContext(Context context,State newState){
       context.setState(newState);
   }

    @Override
    public UserListViewController getUserListViewController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
