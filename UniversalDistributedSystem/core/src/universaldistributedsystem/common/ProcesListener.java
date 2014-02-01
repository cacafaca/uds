/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nemanja
 */
public class ProcesListener implements Runnable{
    private SocketBlockingQueue socketQueue;
    private ServerSocket serverSocket;
    public ProcesListener(SocketBlockingQueue sockets)
    {
        this.socketQueue = sockets;
    }
    public void run() {
        while(true)
        {
            try {
                Socket newSocket = serverSocket.accept(); // cekam novi soket. blokiram se dok ne stigne zahtev.
                //socketQueue.put(newSocket);
            } catch (IOException ex) {
                Logger.getLogger(ProcesListener.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
