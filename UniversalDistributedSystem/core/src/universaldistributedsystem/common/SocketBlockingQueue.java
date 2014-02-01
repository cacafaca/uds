/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package universaldistributedsystem.common;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Nemanja
 */
public class SocketBlockingQueue implements SocketQueue {
    private BlockingQueue<Socket> sockets;
    public SocketBlockingQueue(int maxSockets) {
        sockets = new ArrayBlockingQueue<Socket>(maxSockets);
    }
    public SocketBlockingQueue()
    {
        this(100);
    }
    /**
     * Dodaje novu konekciju u deljeni objekat. Treba da blokira ako je lista
     * puna. Dodaje se po FIFO redosledu.
     * @param socket Dodaje se soket u listu.
     */
    public void put(Socket socket) throws InterruptedException {
        sockets.put(socket);
    }

    public Socket take() throws InterruptedException {
        return sockets.take();
    }
    public int size()
    {
        return sockets.size();
    }
}
