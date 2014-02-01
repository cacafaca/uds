/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.miscellaneous;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author Nemanja
 */
public class SocketTest {
    public static void main(String args[]) throws IOException{
        Socket s = new Socket();

        System.out.println("isConnected: " + s.isConnected() +
                          " isBound: "     + s.isBound() +
                          " isClosed: "    + s.isClosed());

        s.connect(new InetSocketAddress("google.com", 80));

        System.out.println("isConnected: " + s.isConnected() +
                           " isBound: "    + s.isBound() +
                           " isClosed: "   + s.isClosed());

        s.close();

        System.out.println("isConnected: " + s.isConnected() +
                           " isBound: "    + s.isBound() +
                           " isClosed: "   + s.isClosed());

        System.out.println("isConnected: " + s.isConnected() +
                          " isBound: "     + s.isBound() +
                          " isClosed: "    + s.isClosed());

        System.out.println(s.getInetAddress().toString());

        s.connect(new InetSocketAddress("google.com", 80));

        System.out.println("isConnected: " + s.isConnected() +
                           " isBound: "    + s.isBound() +
                           " isClosed: "   + s.isClosed());

        s.close();

        System.out.println("isConnected: " + s.isConnected() +
                           " isBound: "    + s.isBound() +
                           " isClosed: "   + s.isClosed());
    }
}
