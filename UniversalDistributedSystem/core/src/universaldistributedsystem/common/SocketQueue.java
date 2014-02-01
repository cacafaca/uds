/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common;

import java.net.Socket;

/**
 *
 * @author Nemanja
 */
public interface SocketQueue {
    public void put(Socket socket) throws InterruptedException ;
    public Socket take() throws InterruptedException;
    public int size();
}
