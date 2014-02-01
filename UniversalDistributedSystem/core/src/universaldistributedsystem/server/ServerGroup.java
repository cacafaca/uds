/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package universaldistributedsystem.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import universaldistributedsystem.common.logging.Log;
import universaldistributedsystem.common.logging.LogListener;
import universaldistributedsystem.common.Properties;
import universaldistributedsystem.common.Property;
import universaldistributedsystem.common.SystemClient.ClientType;
import universaldistributedsystem.common.Utils;

/**
 *
 * @author Nemanja
 */
public class ServerGroup implements IServerGroup, Runnable {

    //const fields
    private final String clientsServerName = "ClientServer";
    private final String workstationsServerName = "WorkstationServer";
    //private fields
    private List<SimpleServer> servers;
    private boolean serverGroupRunning;
    private ServerProperties properties;
    private Log log;

    /**
     * Ovo je glavni konstruktor.
     * @param srvProp Podesavanja servera.
     * @param log 
     */
    public ServerGroup(ServerProperties srvProp, Log log) {
        this.properties = srvProp;
        this.log = log;
        properties = new ServerProperties();
        servers = new ArrayList<SimpleServer>();
        servers.add(new SimpleServer(clientsServerName, (Integer) properties.get("clients_port").value, log));
        servers.add(new SimpleServer(workstationsServerName, (Integer) properties.get("workstatinons_port").value, log));
        serverGroupRunning = false;
    }

    /**
     * 
     * @param log
     */
    public ServerGroup(Log log) {
        this(null, log);
    }

    /**
     * Start all servers.
     * @throws Exception
     */
    public void start() throws Exception {
        if (serverGroupRunning) {
            throw new Exception("Server group is already running.");
        }
        for (SimpleServer ss : servers) {
            ss.start();
        }
        serverGroupRunning = true;
    }

    /**
     * Stop all servers.
     */
    public void stop() {
        serverGroupRunning = false;
        for (SimpleServer s : servers) {
            s.stop();
        }
    }

    /**
     *
     * @param simpleServerName
     * @return
     */
    public SimpleServer getSimpleServer(String simpleServerName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    public boolean isRunning() {
        return serverGroupRunning;
    }

    /**
     *
     * @return
     */
    public ServerProperties getProperties() {
        return properties;
    }

    /**
     *
     * @param properties
     * @throws Exception
     */
    public void setProperties(Properties properties) throws Exception {
        this.properties.assign(properties);
        applyProperties();
    }

    /**
     *
     * @throws Exception
     */
    public void applyProperties() throws Exception {
        for (SimpleServer s : servers) {
            if (s.getName().equals(clientsServerName)) {
                s.setPort((Integer) properties.get("clients_port").value);
            } else if (s.getName().equals(workstationsServerName)) {
                s.setPort((Integer) properties.get("workstatinons_port").value);
            }
        }
    }

    /**
     *
     * @param listener
     */
    public void addLogListener(LogListener listener) {
        for (SimpleServer srv : servers) {
            srv.addLogListener(listener);
        }
    }

    /**
     *
     * @param listener
     */
    public void removeLogListener(LogListener listener) {
        for (SimpleServer srv : servers) {
            srv.removeLogListener(listener);
        }
    }

    /**
     *
     * @return
     */
    public String getPluginsFolder() {
        return Utils.getPluginsPath(this, ClientType.Server);
    }

    /**
     * Ako hocu da startujem bez Gui-a.
     * @param args Prosledjuje se port za klijente i workstesne.
     */
    public static void main(String args[]) {
        Log log = new Log();
        ServerGroup srvGrp = new ServerGroup(log);
        try {
            srvGrp.loadServerProperties(args);
            srvGrp.start();
        } catch (Exception ex) {
            Logger.getLogger(ServerGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected ServerProperties getServerPropertiesFromCommandLine(String args[]) throws Exception {
        ServerProperties sp;
        if (args.length >= 2) {
            sp = new ServerProperties();
            sp.add(new Property(clientsServerName, args[0], "Server port for clients."));   // port za klijenta
            sp.add(new Property(workstationsServerName, args[1], "Server port for workstations."));   // port za workstation
        } else {
            throw new Exception("Expecting two parameters. Clients port and workstations port.");
        }
        return sp;
    }

    private ServerProperties getDefaultServerProperties() {
        ServerProperties spDef = new ServerProperties();
        spDef.add(new Property(clientsServerName, 4000, "Default server port for clients."));
        spDef.add(new Property(workstationsServerName, 4001, "Default server port for workstation."));
        return spDef;
    }

    private void loadServerProperties(String args[]) {
        if (args.length >= 2) {
            try 
            {
                properties = getServerPropertiesFromCommandLine(args);
            } 
            catch (Exception ex) 
            {
                log.LogError("Could't load server properties from command line arguments.", this);
                properties = getDefaultServerProperties();
            }
        }
    }
}
