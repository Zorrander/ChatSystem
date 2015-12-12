
package chatsystem;

public interface State {

    public void updateContext(String nickname);

    public boolean isConnected();

}


