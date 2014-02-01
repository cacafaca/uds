/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sendmessagetoall.client;

import universaldistributedsystem.common.Message;
import universaldistributedsystem.plugin.Plugin;

/**
 *
 * @author Nemanja
 */
public class JobMessageToAll implements Plugin{

    private JobMessageToAllClientGUI clientGUI;

    public void runClient(Message message) {
        //throw new UnsupportedOperationException("Not supported yet.");
        clientGUI = new JobMessageToAllClientGUI();
        clientGUI.setVisible(true);
    }

    public void runServer(Message message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void runWorkstation(Message message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void stopClient() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void stopServer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void stopWorkstation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void getStatusClient(Message message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void getStatusServer(Message message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void getStatusWorkstation(Message message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
