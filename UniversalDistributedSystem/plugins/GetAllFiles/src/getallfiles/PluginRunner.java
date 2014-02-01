/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package getallfiles;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import universaldistributedsystem.common.message.Message;
import universaldistributedsystem.plugin.Plugin;
import getallfiles.client.GetAllFilesApp;
import getallfiles.client2.GetAllFilesGUI;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import universaldistributedsystem.client.Client;
import universaldistributedsystem.common.Utils;
import universaldistributedsystem.plugin.PluginInfo;

/**
 * This class implements Plugin interface. It is used to run client, server and
 * workstation processes.
 * @author Nemanja
 */
public class PluginRunner implements Plugin{

    private class G extends GetAllFilesApp{

        @Override
        public void startup(){
            super.startup();
        }
    }

    private Client client;

    public void runClient(final Client client) {
        this.client = client;
        try{
            //GetAllFilesApp.main(new String[]{});

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new GetAllFilesGUI(client).setVisible(true);
                }
            });
        }catch(Exception ex){
            Utils.logException(this, ex);
        }
    }

    public void runServer(Client client) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void runWorkstation(Client client) {
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

    public PluginInfo getPluginInfo() {
        try {
            String pluginFileName = Utils.getApplicationDirectory(this);
            File pluginFile = new File(pluginFileName);
            String name = pluginFile.getParentFile().getName();
            String description = "Return directory structure of all workstations.";
            String version = "1.0.0.0";
            int size = (int)pluginFile.length();
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse("19.05.2012");
            return new PluginInfo(
                    name,
                    description,
                    version,
                    size,
                    date);
        } catch (ParseException ex) {
            Logger.getLogger(PluginRunner.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void getStatus(Message message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
