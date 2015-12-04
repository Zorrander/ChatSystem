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
public class NicknameBox extends ViewComponent {
    
    private String name ; 
    private DisconnectView parentView ; 
    
    public NicknameBox(){
        
    }
    
    public onButtonConnectClick() {
        parentView.connect(name);
    }
    
}
