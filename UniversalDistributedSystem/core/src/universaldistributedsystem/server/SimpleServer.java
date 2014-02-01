/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.server;

//import universaldistributedsystem.common.Utils;
import universaldistributedsystem.common.logging.Log;
import universaldistributedsystem.common.logging.LogListener;
import universaldistributedsystem.common.logging.LogMessage;
import universaldistributedsystem.common.message.MessageArrivedListener;
import universaldistributedsystem.common.message.Message;
import universaldistributedsystem.common.message.MessageArrivedEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.BindException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import universaldistributedsystem.common.*;
/**
 * This is main Server thread that listen for connection on
 * defined port.
 * 
 * @author Nemanja
 */
public class SimpleServer implements Runnable{

    //consts
    private final int MAX_CLIENTS = 10;  // maksimalni broj klijenata do daljnjeg

    //Private fields
    private boolean serverRunning;  // thread variable
    private ServerSocket simpleServer;  // listening socket
    private Log log;
    private List<SystemClient> customClientsList;
    private LogMessage messageLog;
    private String simpleServerName;
    private Thread runner;
    private int port;
    private int serverClientId;

    public SimpleServer(String simpleServerName, int port){
        serverRunning = false;
        this.simpleServerName = simpleServerName;
        log = new Log("log\\" + simpleServerName + ".log");
        //log = new Log();
        customClientsList = new ArrayList<SystemClient>();
        this.port = port;
        messageLog = new LogMessage("log\\" + simpleServerName + "_Messages");
        serverClientId = 0;
            // TODO Sredi MessageLog
            //clientMessages = new MessageLog(MessagePath);
    }

    public SimpleServer(String simpleServerName, int port, Log log){
        this(simpleServerName, port);
        this.log = log;
    }

    @Override
    public void run(){
        doRun();
    }

    private synchronized void doRun(){
        while(true){
            if(serverRunning){
                if(customClientsList.size() >= MAX_CLIENTS){
                    try{
                        log.LogWarrning("Waiting for free client slot.");
                        // TODO fire event - max clients reached
                        wait(); // Blokiraj se dok se ne oslobodi neki klijent
                    }
                    catch(InterruptedException ex){
                        log.LogError(ex.getMessage(), ex);
                    }
                }
                else{
                    try{
                        Socket client = simpleServer.accept(); // blokira se ovde. čeka na klijenta
                        log.logInfo("Client conected: " + client.toString());

                        SystemClient customClient = new SystemClient(client, log, SystemClient.ClientType.Server);
                        customClient.addMessageArrivedListener(new MessageArrivedListener() {
                            public void messageArrived(MessageArrivedEvent event) {
                                processMessage(event);
                            }
                        });
                        customClientsList.add(customClient);

                        fireClientConnectedEvent(new EventObject(this));
                        // TODO Delete customCleints that are no longer connected.
                    }
                    catch (IOException ex){
                        log.LogError(ex.toString(), this);
                    }
                }
            }
            else // if server is not running block thread
            {
                try{
                    wait();
                } catch (InterruptedException ex){
                    log.LogError(ex.toString(), this);
                }
            }
        }
    }

    public int getPort()
    {
        return simpleServer.getLocalPort();
    }

    public void setPort(int port){
        this.port = port;
    }

    /**
     * Process messgaes that arive to server.
     * @param event
     */
    private void processMessage(MessageArrivedEvent event)
    {
        Message message = event.getMessage();
        try{
            // Log message first.
            messageLog.saveMessage(message);
        }
        catch (Exception ex) {
            log.LogError("Can't save message: " + ex.getMessage(), this);
        }

        // TODO Finish processinbg arrived message.
        fireMessageArrivedEvent(event);
    }

    public synchronized void start(int port) throws Exception{
        if(serverRunning){
            String warnningMsg = "Server alredy started.";
            log.LogWarrning(warnningMsg, this);
            throw new Exception(warnningMsg);
        }
        if(runner == null){
            runner = new Thread(this, simpleServerName);
            runner.start();
        }

        log.logInfo(String.format("Opening port %d ...", port));
        try {
            simpleServer = new ServerSocket(port);  // Start listening
            log.logInfo(String.format("Port %d opened.", port));
            serverRunning = true;
            notifyAll(); // wake all threads
        }
        catch(BindException ex){
            log.LogError(ex.toString(), this);
            Utils.showMessage(String.format("Can't connect to port %d. The address is already taken. " + ex.getMessage(), port));
        }
        catch(IOException ex){
            log.LogError(ex.toString(), this);
        }
    }

    public synchronized void start() throws Exception{
        start(port);
    }

    public /*synchronized*/ void stop(){
        serverRunning = false;        
        try{
            if(simpleServer != null){
                if(!simpleServer.isClosed()){
                    String localSocketAddress = simpleServer.getLocalSocketAddress().toString();
                    log.logInfo("Closing socket " + localSocketAddress);
                    simpleServer.close();
                    log.logInfo("Socket " + localSocketAddress + " closed.");
                }
                else {
                    log.logInfo(String.format("Socket (%s) is already closed.", simpleServer.getLocalSocketAddress()));
                }
            }
        }
        catch(IOException ex) {
            log.LogError(ex.toString(), this);
        }
    }

    public boolean isBound()
    {
        return simpleServer.isBound();
    }

    public boolean isClosed()
    {
        return simpleServer.isClosed();
    }

    public String getName(){
        return simpleServerName;
    }

    // Create a listener list for message arrived event
    protected javax.swing.event.EventListenerList messageArrivedListenerList =
            new javax.swing.event.EventListenerList();

    // This method allow classes to register for MessageArrivedEvent
    public void addMessageArrivedListener(MessageArrivedListener listener) {
        messageArrivedListenerList.add(MessageArrivedListener.class, listener);
    }

    // This method allow classes to unregister for MessageArrivedEvent
    public void removeMessageArrivedListener(MessageArrivedListener listener) {
        messageArrivedListenerList.remove(MessageArrivedListener.class, listener);
    }

    // Fire MessageArrivedEvent
    private void fireMessageArrivedEvent(MessageArrivedEvent event) {
        //event = new MessageArrivedEvent(this, message);
        Object[] listeners = messageArrivedListenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == MessageArrivedListener.class) {
                ((MessageArrivedListener) listeners[i + 1]).messageArrived(event);
            }
        }
    }

    public void addLogListener(LogListener listener){
        log.addListener(listener);
        /*for(CommunicatorHalfDuplex c : customClientsList)
            c.addLogListener(listener);*/
    }

    public void removeLogListener(LogListener listener){
        log.removeListener(listener);
        /*for(CommunicatorHalfDuplex c : customClientsList)
            c.addLogListener(listener);*/
    }

    // Create a listener list for client connected event
    protected javax.swing.event.EventListenerList clientConnectedListenerList =
            new javax.swing.event.EventListenerList();

    // This method allow classes to register for client connected event.
    public void addClientConnectedListener(MessageArrivedListener listener) {
        clientConnectedListenerList.add(MessageArrivedListener.class, listener);
    }

    // This method allow classes to unregister for client connected event.
    public void removeClientConnectedListener(MessageArrivedListener listener) {
        messageArrivedListenerList.remove(MessageArrivedListener.class, listener);
    }

    private void fireClientConnectedEvent(EventObject event){
        //event = new MessageArrivedEvent(this, message);
        Object[] listeners = clientConnectedListenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ClientConnectedListener.class) {
                ((ClientConnectedListener) listeners[i + 1]).clientConnected(event);
            }
        }
    }

    public StringBuilder getConnectedClients(){
        StringBuilder sb = new StringBuilder();
        for(SystemClient cc: customClientsList)
            if(cc.isConnected())
                sb.append(cc.getRemoteSocketAddress());
        return sb;
    }
}