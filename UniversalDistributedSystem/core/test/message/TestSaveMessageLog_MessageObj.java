/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import universaldistributedsystem.common.message.Message;
import universaldistributedsystem.common.logging.LogMessage;
import universaldistributedsystem.common.message.MessageObj;

/**
 *
 * @author Nemanja
 */
public class TestSaveMessageLog_MessageObj {
    public static void main(String args[]){
        Message m = new MessageObj();
        m.setType(Message.MessageType.SYSTEM_MESSAGE);
        m.setBody("Test message.");
        m.setDateTime(new Date());

        LogMessage l = new LogMessage("log\\TestMessage", LogMessage.SaveType.Text);
        //LogMessage l = new LogMessage("log\\TestMessage");
        try {
            l.saveMessage(m);
        } catch (Exception ex) {
            Logger.getLogger(TestSaveMessageLog_MessageStr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}