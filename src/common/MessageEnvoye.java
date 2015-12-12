/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Kray
 */
public class MessageEnvoye extends Message {
    
    int hourSent;
    int minuteSent;
    int secondsSent;
    String timeSent ;

    public MessageEnvoye(MsgType type, String content, String sender) {
        super(type, content, sender);
        hourSent = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        minuteSent = Calendar.getInstance().get(Calendar.MINUTE);
        secondsSent = Calendar.getInstance().get(Calendar.SECOND);
        timeSent=hourSent+":"+minuteSent+":"+secondsSent;
    }

    @Override
    public String toString() {
        return timeSent + " > " + this.getSender() + " : " + this.getContent();
    }

}
