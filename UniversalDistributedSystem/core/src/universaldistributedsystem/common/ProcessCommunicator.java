/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import universaldistributedsystem.common.message.MessageBox;

/**
 * Proces obavlja komunikaciju sa klijentom (ko god to on bio).
 * Uzima soket iz liste i zapocinje.
 * @author Nemanja
 */
public class ProcessCommunicator implements Runnable{
    private SocketQueue sockets;
    private MessageBox msgBox;
    public ProcessCommunicator(SocketBlockingQueue socketQueue, MessageBox messageQueue)
    {
        this.sockets = socketQueue;
        this.msgBox = messageQueue;
    }
    public void run() {
        Socket socket;
        while(true)
        {
            try {
                socket = sockets.take(); //messageQueue.push(receiveMessage())
                //messageQueue.push(receiveMessage())
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcessCommunicator.class.getName()).log(Level.SEVERE, null, ex);
            }
            //socket.
        }
    }

}
