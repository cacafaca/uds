/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package universaldistributedsystem.common;

import universaldistributedsystem.common.logging.Log;
import universaldistributedsystem.common.message.MessageArrivedListener;
import universaldistributedsystem.common.message.Message;
import universaldistributedsystem.common.message.MessageObj;
import universaldistributedsystem.common.message.MessageArrivedEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.Socket;
import java.util.EventListener;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;
import java.util.Queue;
import universaldistributedsystem.plugin.Plugin;
import universaldistributedsystem.plugin.PluginInfo;
import universaldistributedsystem.plugin.PluginsList;

/**
 * Basic class for client.
 * @author Nemanja
 */
public class SystemClient {

    private void sendCloseConnectionResponse() {
        try {
            send(new MessageObj(Message.MessageType.SYSTEM_MESSAGE,
                    new SystemMessage(SystemMessage.Command.CLOSE_CONNECTION_RESPONSE)));
        } catch (IOException ex) {
            log.LogError("Can't send close connection response: " + ex.getMessage(), this);
        }
    }

    public interface JobListReceivedListener extends EventListener {
        public void jobListReceived(EventObject source, PluginsList jobsList);
    }

    public interface JobReceivedListener extends EventListener {
        public void jobReceived(EventObject source, PluginInfo jobInfo);
    }

    private CommunicatorHalfDuplex communicator;
    private Thread runner;
    private PluginsList requestedJobList;
    private Log log;

    public SystemClient(Log log, ClientType clientType) {
        communicator = new CommunicatorHalfDuplex(log);
        this.log = log;
        this.clientType = clientType;
        registerMessageArrivedListenerAtCommunicator();
    }

    /**
     * Konstruktor kada je socket vec poznat, tj kada je veza uspostavljena.
     * Ovo se koristi kada server već ima Socket.
     * @param client
     */
    public SystemClient(Socket client, Log log, ClientType clientType){
        this(log, clientType);
        communicator.setClient(client);
        startReceiving();
    }

    public void connect(String host, int port) throws IOException {
        communicator.connect(host, port);
        startReceiving();
    }

    public void close() throws IOException {
        send(new MessageObj(Message.MessageType.SYSTEM_MESSAGE,
                new SystemMessage(SystemMessage.Command.CLOSE_CONNECTION_REQUEST, null)));
    }

    /**
     * Pokretanje primanja poruka klijenta.
     * @param threadName Naziv thread-a.
     */
    private void startReceiving() {
        if (runner == null || !runner.isAlive()) {
            String threadName = String.format("Client(%s)", getLocalSocketAddress());
            runner = new Thread(null, communicator, threadName);
            runner.start();
            log.logInfo(String.format("Thread '%s' started.", threadName));
        } else {
            log.LogWarrning("Trying to start thread witch is already started.");
        }
    }

    public void sendPluginGetListRequest() throws Exception {
        requestedJobList = null; // ponistavam prethodnu listu ako je psotojala
        send(new MessageObj(Message.MessageType.SYSTEM_MESSAGE,
                new SystemMessage(SystemMessage.Command.PLUGIN_GET_LIST_REQUEST)));
    }

    // Create a listener list
    protected javax.swing.event.EventListenerList listenerListForCustomMessages =
            new javax.swing.event.EventListenerList();

    public void addMessageArrivedListener(MessageArrivedListener listener) {
        //communicator.addMessageArrivedListener(listener);
        listenerListForCustomMessages.add(MessageArrivedListener.class, listener);
    }

    public void removeMessageArrivedListener(MessageArrivedListener listener) {
        //communicator.removeMessageArrivedListener(listener);
        listenerListForCustomMessages.remove(MessageArrivedListener.class, listener);
    }

    /**
     * This procedure should be called only when custom message arrives.
     * @param event
     */
    private void fireMessageArrivedListenerForCustomMessages(MessageArrivedEvent event) {
        Object[] listeners = listenerListForCustomMessages.getListenerList();
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] == MessageArrivedListener.class) {
                ((MessageArrivedListener) listeners[i + 1]).messageArrived(event);
            }
        }
    }

    public boolean isConnected() {
        return communicator.isConnected();
    }

    public void send(Message message) throws IOException {
        communicator.send(message);
    }

    public String getRemoteSocketAddress() {
        return communicator.getRemoteSocketAddress();
    }

    public String getLocalSocketAddress() {
        return communicator.getLocalSocketAddress();
    }

    // Listener za primanje job liste
    protected javax.swing.event.EventListenerList jobListListenerList =
            new EventListenerList();

    public void addJobListReceivedEvent(JobListReceivedListener listener) {
        jobListListenerList.add(JobListReceivedListener.class, listener);
    }

    public void removeJobListReceivedEvent(JobListReceivedListener listener) {
        jobListListenerList.remove(JobListReceivedListener.class, listener);
    }

    public void fireJobListReceivedEvent(PluginsList jobList) {
        Object[] listeners = jobListListenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == JobListReceivedListener.class) {
                ((JobListReceivedListener) listeners[i + 1]).jobListReceived(new EventObject(this), jobList);
            }
        }
    }

    public PluginsList getPluginList() {
        return requestedJobList;
    }

    public void sendPluginGetRequest(PluginInfo pluginInfo) throws Exception {
        send(new MessageObj(Message.MessageType.SYSTEM_MESSAGE,
                new SystemMessage(SystemMessage.Command.PLUGIN_GET_REQUEST, pluginInfo)));
    }

    /**
     * This procedure should be called from server when responding on jobs list
     * request. It should read from plugin_server folder.
     */
    private void sendPluginGetListResponse() {
        String pluginsPath = Utils.getPluginsPath(this, clientType);
        PluginsList jobsList = new PluginsList();
        Queue<File> pluginsDir = Utils.getAllFiles(new File(pluginsPath), new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        }, 0, true);
        for(File pluginDir : pluginsDir){
            if(pluginDir.exists()){
                try {
                    File pluginFile = new File(pluginDir.getPath() + "\\" + pluginDir.getName() + "." + Plugin.PLUGIN_EXTENSION);
                    PluginInfo pluginInfo = PluginInfo.getPluginInfo(pluginFile);
                    //pluginInfo.setName()
                    jobsList.add(pluginInfo);
                } catch (Throwable ex) {
                    Logger.getLogger(SystemClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        SystemMessage systemMessage = new SystemMessage(SystemMessage.Command.PLUGIN_GET_LIST_RESPONSE,
                jobsList);
        Message msgResponse = new MessageObj(Message.MessageType.SYSTEM_MESSAGE, systemMessage);
        try {
            send(msgResponse);
            log.logInfo("Jobs list sent to " + communicator.getRemoteSocketAddress() + ".");
        } catch (IOException ex) {
            log.LogError("Couldn't send jobs list to " + communicator.getRemoteSocketAddress() + ".", ex);
        }
    }

    private void sendPluginGetResponse(SystemMessage systemMessage) {
        PluginInfo pluginInfo = (PluginInfo) systemMessage.getSystemObject();
        pluginInfo.loadContents(clientType);
        try {
            send(new MessageObj(Message.MessageType.SYSTEM_MESSAGE,
                    new SystemMessage(SystemMessage.Command.PLUGIN_GET_RESPONSE, pluginInfo)));
        } catch (IOException ex) {
            Logger.getLogger(SystemClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Listener za primanje job liste
    protected javax.swing.event.EventListenerList jobListenerList =
            new EventListenerList();

    public void addJobReceivedEvent(JobReceivedListener listener) {
        jobListenerList.add(JobReceivedListener.class, listener);
    }

    public void removeJobReceivedEvent(JobReceivedListener listener) {
        jobListenerList.remove(JobReceivedListener.class, listener);
    }

    public void fireJobReceivedEvent(PluginInfo jobInfo) {
        Object[] listeners = jobListenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == JobReceivedListener.class) {
                ((JobReceivedListener) listeners[i + 1]).jobReceived(new EventObject(this), jobInfo);
            }
        }
    }

    public void setLog(Log log) {
        this.log = log;
    }

    /**
     * Ova procedura ce da registruje proceduru kada od communicatora stigne poruka.
     * Ona treba da filtrira systemske poruke, tj da ih ne prosledjuje. Samo treba da prodju
     * custom messages.
     */
    private void registerMessageArrivedListenerAtCommunicator() {
        communicator.addMessageArrivedListener(new MessageArrivedListener() {

            public void messageArrived(MessageArrivedEvent event) {
                Message message = event.getMessage();
                if(message != null){
                    switch (message.getType()) {
                        case SYSTEM_MESSAGE:
                            processSystemMessages(message);
                            break;
                        case CUSTOM_MESSAGE:
                            fireMessageArrivedListenerForCustomMessages(event);
                            break;
                    }
                }
            }
        });
    }

    private void processSystemMessages(Message msg) {
        try {
            SystemMessage systemMessage = (SystemMessage) msg.getBody();
            switch (systemMessage.getCommand()) {
                case PLUGIN_GET_LIST_REQUEST:      // Jobs list requested
                    sendPluginGetListResponse();  // Send jobs list response
                    break;
                case PLUGIN_GET_LIST_RESPONSE:
                    notifyPluginListResponse(systemMessage);
                    break;
                case PLUGIN_GET_REQUEST:   // Get specific job
                    sendPluginGetResponse(systemMessage);
                    break;
                case PLUGIN_GET_RESPONSE:
                    notifyPluginGetResponse(systemMessage);
                    break;
                case CLOSE_CONNECTION_REQUEST:
                    sendCloseConnectionResponse();
                    notifyCloseResponse();
                    break;
                case CLOSE_CONNECTION_RESPONSE:
                    notifyCloseResponse();
                    break;
            }
        } catch (ClassCastException ex) {
            log.LogError("Wrong class sent. Expected SystemMessage class: " + ex.getMessage(), this);
        } catch (Exception ex) {
            log.LogError(ex.getMessage(), this);
        }
    }

    private void notifyPluginListResponse(SystemMessage systemMessage) {
        try {
            requestedJobList = (PluginsList) systemMessage.getSystemObject();
            fireJobListReceivedEvent(requestedJobList);
        } catch (Exception ex) {
            log.LogError("Nisam primio objekat tipa JobList: " + ex.getMessage(), this);
        }
    }

    /**
     * This porcedure should be called from client or workstation.
     * @param systemMessage
     */
    private void notifyPluginGetResponse(SystemMessage systemMessage){
        try {
            PluginInfo pluginInfo = (PluginInfo)systemMessage.getSystemObject();
            pluginInfo.saveContents(clientType);
            log.logInfo(String.format("Plugin '%s' received.", pluginInfo.getName()));
            fireJobReceivedEvent(pluginInfo);
        } catch (ClassCastException ex) {
            log.LogError(ex.getMessage(), this);
        } catch (Exception ex) {
            log.LogError(ex.getMessage(), this);
        }
    }

    private void notifyCloseResponse() {
        communicator.close();
    }

    public enum ClientType {None, Server, Workstation, Client}
    private ClientType clientType = ClientType.None;

    public ClientType getClientType(){
        return clientType;
    }

    public String getPluginsPath(){
        return Utils.getPluginsPath(this, clientType);
    }
}
