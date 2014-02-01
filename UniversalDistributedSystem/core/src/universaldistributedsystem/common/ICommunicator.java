/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common;

import universaldistributedsystem.common.message.Message;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 * @author Nemanja
 */
public interface ICommunicator {
    /**
     * Connect to host.
     * @param host Name or IP.
     * @param port Number (int).
     * @throws IOException
     * @throws UnknownHostException
     */
    public void connect(String host, int port) throws IOException, UnknownHostException;
    public void close();
    public void send(Message message) throws IOException;
}
