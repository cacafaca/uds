/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common.message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nemanja
 */
public class MessageBoxBlockingQueue implements MessageBox{
    BlockingQueue<Message> msgBox;
    public MessageBoxBlockingQueue(int capacity)
    {
        msgBox = new ArrayBlockingQueue<Message>(capacity);
    }
    public MessageBoxBlockingQueue()
    {
        // TODO Da se ucitava iz nekog konfiga
        this(100); 
    }
    /**
     * Dodajem, ako je pun blokiram se
     * @param message
     */
    public void put(Message message) {
        try {
            msgBox.put(message);
        } catch (InterruptedException ex) {
            Logger.getLogger(MessageBoxBlockingQueue.class.getName()).log(Level.SEVERE, null, ex); // TODO loguj
        }
    }
    /**
     * Uzimam, ako nema blokiram se.
     * @return
     */
    public Message take() {
        try {
            return msgBox.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(MessageBoxBlockingQueue.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int size() {
        return msgBox.size();
    }

}
