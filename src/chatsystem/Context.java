package chatsystem;

public class Context {
   private Chat chat;
   private State state;

   public Context(Chat chat){
      state = null;
      this.chat = chat ;
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
