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
public class MessageRecu extends Message {

    int hourReceived;
    int minuteReceived;
    int secondsReceived;
    String timeReceived ;

    public MessageRecu(MsgType type, String content, String sender) {
        super(type, content, sender);
        hourReceived = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        minuteReceived = Calendar.getInstance().get(Calendar.MINUTE);
        secondsReceived = Calendar.getInstance().get(Calendar.SECOND);
        timeReceived=hourReceived+":"+minuteReceived+":"+secondsReceived ;
    }

    @Override
    public String toString() {
        return timeReceived + " >> " + this.getSender() + " : " + this.getContent();
    }

}
