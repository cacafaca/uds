/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.server;

//import java.util.Date;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import universaldistributedsystem.common.CommunicatorHalfDuplex;
import universaldistributedsystem.common.logging.Log;
import universaldistributedsystem.common.message.Message;
import universaldistributedsystem.common.message.MessageStr;

/**
 *
 * @author Nemanja
 */
public class ServerTest {
    public static void main(String[] args){
        Message m = new MessageStr();
        m.setType(Message.MessageType.CUSTOM_MESSAGE);
        m.setBody("Test.");
        try {
            CommunicatorHalfDuplex c = new CommunicatorHalfDuplex(new Log());
            c.send(m);
        } catch (IOException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
