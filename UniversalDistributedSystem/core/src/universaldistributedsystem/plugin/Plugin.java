/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.plugin;

import java.io.Serializable;
import universaldistributedsystem.client.Client;
import universaldistributedsystem.common.message.Message;
/**
 *
 * @author Nemanja
 */
public interface Plugin extends Serializable{

    //run
    public void runClient(Client client);
    public void runServer(Client client);
    public void runWorkstation(Client client);

    //stop
    public void stopClient();
    public void stopServer();
    public void stopWorkstation();

    // Get description from plugin.
    public PluginInfo getPluginInfo();

    //getStatus
    public void getStatus(Message message);

    // Constants
    public final static String PLUGIN_IMPLEMENTATION_CLASS = "PluginRunner";
    public final static String PLUGIN_EXTENSION = "jar";

}