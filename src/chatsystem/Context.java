package chatsystem;

public class Context {
   private final Chat chat;
   private State state;

   public Context(Chat chat){
      state = null;
      this.chat = chat ;
   }
   
      boolean isConnected() {
       return state.isConnected() ;
    }

    void disconnect() {
        if (this.isConnected()) {
        //On est sur un connect state, on passe donc en deconnectstate
        state.updateContext(""); 
        }
        // ELSE : On est déjà déconnecté !
    }

   public Chat getChat(){
       return this.chat;
   }
   
   public void setState(State state){
      this.state = state;		
   }

   public State getState(){
      return state;
   }
 
}
