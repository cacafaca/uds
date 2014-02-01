/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common.message;

import java.util.EventObject;

/**
 * This class represents message event.
 *
 * @author Nemanja
 */
public class MessageArrivedEvent extends EventObject{

    private Message message;

    // Main constructor for event.
    public MessageArrivedEvent(Object source){
        super(source);
    }

    public MessageArrivedEvent(Object source, Message message){
        super(source);
        this.message = message;
    }

    public Message getMessage(){
        return message;
    }
}