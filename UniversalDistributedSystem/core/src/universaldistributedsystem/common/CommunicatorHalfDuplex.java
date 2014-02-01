/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package universaldistributedsystem.common;

import universaldistributedsystem.common.logging.Log;
import universaldistributedsystem.common.logging.LogListener;
import universaldistributedsystem.common.message.MessageArrivedListener;
import universaldistributedsystem.common.message.MessageStr;
import universaldistributedsystem.common.message.Message;
import universaldistributedsystem.common.message.MessageArrivedEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nemanja
 */
public class CommunicatorHalfDuplex implements Runnable, ICommunicator {

    public enum MessageSendingType{
        Object,
        String,
        XML
    }

    private MessageSendingType messageSendingType;
    private Socket client;
    private Log log;    
    private boolean keepAlive;

    // Strimovi
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private XMLEncoder xmlEncoder;
    private XMLDecoder xmlDecoder;

    public CommunicatorHalfDuplex(Log log) {
        messageSendingType = MessageSendingType.Object;
        //log = new Log("log\\" + getClass().getName() + ".log");
        this.log = log;
        keepAlive = true;
    }

    public void connect(String host, int port) throws IOException, UnknownHostException {
        client = new Socket(host, port);
        initializeStreams();
    }

    public void close() {
        try {
            log.logInfo("Closing connection to " + getRemoteSocketAddress() + " ...");
            client.close();
            switch(messageSendingType){
                case Object:
                    if(objectOutputStream != null)
                        objectOutputStream.close();
                    if(objectInputStream != null)
                        objectInputStream.close();
                    break;
                case String:
                    if(dataOutputStream != null)
                        dataOutputStream.close();
                    if(dataInputStream != null)
                        dataInputStream.close();
                    break;
                case XML:
                    if(xmlEncoder != null)
                        xmlEncoder.close();
                    if(xmlDecoder != null)
                        xmlDecoder.close();
                    break;
            }
            log.logInfo("Connection to " + getRemoteSocketAddress() + " closed.");
        } catch (IOException ex) {
            log.LogError("Can't close socket: " + ex.getMessage(), this);
        }
    }

    /**
     * Glavna metoda za slanje poruke.
     * @param host Server koji se gadja.
     * @param port Port koji Server slusa.
     * @param message Poruka za slanje.
     */
    public void send(Message message) throws IOException {
        if(!isConnected())
            throw new IOException("Client is not connected.");
        message.setFrom(client.getLocalAddress());
        message.setTo(client.getInetAddress());
        message.setDateTime(new Date());
        if(message.getType() == null)
            message.setType(Message.MessageType.CUSTOM_MESSAGE);
        try {
            switch(messageSendingType){
                case Object:
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                    break;
                case String:
                    dataOutputStream.writeUTF(message.toString());
                    dataOutputStream.flush();
                    break;
                case XML:
                    xmlEncoder.writeObject(message);
                    xmlEncoder.flush();
                    break;
            }
        } catch (IOException ex) {
            log.LogError("Couldn't write to output stream: " + ex.getMessage());
        }
        if(!keepAlive)
            client.close();
    }

    // Create a listener list
    protected javax.swing.event.EventListenerList listenerList =
            new javax.swing.event.EventListenerList();

    // This method allow classes to register for MessageArrivedEvent
    public void addMessageArrivedListener(MessageArrivedListener listener) {
        listenerList.add(MessageArrivedListener.class, listener);
    }

    // This method allow classes to unregister for MessageArrivedEvent
    public void removeMessageArrivedListener(MessageArrivedListener listener) {
        listenerList.remove(MessageArrivedListener.class, listener);
    }

    // Fire MessageArrivedEvent
    private void fireMessageArrivedEvent(MessageArrivedEvent event) {
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        if(listeners.length > 0){
            log.logInfo(String.format("Fireing events on message arrived (Count = %d).", listeners.length / 2));
            for (int i = 0; i < listeners.length; i += 2) {
                if (listeners[i] == MessageArrivedListener.class) {
                    ((MessageArrivedListener) listeners[i + 1]).messageArrived(event);
                }
            }
            log.logInfo("Events on message arrived fired.");
        }
    }

    public InetAddress getFrom() {
        return client.getLocalAddress();
    }

    public InetAddress getTo() {
        return client.getInetAddress();
    }

    private int getBufferedOutputStreamSize() {
        return 1024 * 8;
    }

    private int getBufferedInputStreamSize(){
        return getBufferedOutputStreamSize();
    }

    public void run() {
        doRun();
    }

    private synchronized void doRun(){
        while (isConnected()) {
            receive();
        }
        // TODO Notify server group to free this simple server.
    }

    /**
     * Proverava da li je Soket povezan.
     * @return Vraca TRUE ako je Socket povezan.
     */
    public boolean isConnected() {
        return client!=null && client.isConnected() && client.isBound() && !client.isClosed();
    }

    /**
     * Wait for message to receive.
     */
    private void receive() {
        Message message = null; 
        // Wait for message.
        log.logInfo(String.format("Wait for message from %s ...", client.getRemoteSocketAddress()));
        try {
            switch(messageSendingType){
                case Object:
                    if(objectInputStream == null)
                        // Ovde se blokira prvi put
                        objectInputStream = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                    try {
                        // Ovde se blokira svaki sledeci put
                        message = (Message) objectInputStream.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CommunicatorHalfDuplex.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case String:
                    if(dataInputStream == null)
                        dataInputStream = new DataInputStream(new BufferedInputStream(client.getInputStream()));
                    String msg = dataInputStream.readUTF();
                    message = convertStringToMessage(msg);
                    break;
                case XML:
                    if(xmlDecoder == null)
                        xmlDecoder = new XMLDecoder(new BufferedInputStream(client.getInputStream()));
                    message = (MessageStr)xmlDecoder.readObject();
                    break;
            }
            log.logInfo(String.format("Message received from %s.", getRemoteSocketAddress()));
            fireMessageArrivedEvent(new MessageArrivedEvent(this, message));
        }
        catch (StreamCorruptedException ex){
            String logMessage = "Invalid stream header.";
            if(messageSendingType.equals(MessageSendingType.Object)){
                logMessage += " Probabli sender didn't send object. ";
            }
            log.LogError(logMessage + " Exception: " + ex.getMessage(), this);
        }
        catch (IOException ex) {
            Logger.getLogger(CommunicatorHalfDuplex.class.getName()).log(Level.SEVERE, null, ex);
            // TODO Implement receive error fire event
        }

    }

    private InetAddress getInetAddressFromText(String line, String prefixToRemove) {
        String delimiter = "/";
        String adr = line.replaceFirst(prefixToRemove, "");
        if(adr.contains(delimiter))
            adr = adr.substring(adr.indexOf("/"));
        InetAddress ia = null;
        try {
            ia = InetAddress.getByName(adr);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CommunicatorHalfDuplex.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ia;

    }
    private Message convertStringToMessage(String msg){
        BufferedReader reader = new BufferedReader(new StringReader(msg));

        Message message = new MessageStr();

        // From
        try {
            message.setFrom(getInetAddressFromText(reader.readLine(), Message.fromPrefix));
        } catch (IOException ex) {
            Logger.getLogger(CommunicatorHalfDuplex.class.getName()).log(Level.SEVERE, null, ex);
        }

        // To
        try {
            message.setTo(getInetAddressFromText(reader.readLine(), Message.toPrefix));
        } catch (IOException ex) {
            Logger.getLogger(CommunicatorHalfDuplex.class.getName()).log(Level.SEVERE, null, ex);
        }

        // DateTime
        try{
            String dateTime = reader.readLine();
            try {
                message.setDateTime((Date) ((DateFormat)(new SimpleDateFormat(MessageStr.dateTimeFormat))).parse(dateTime.replaceFirst(Message.datePrefix, "")));
            } catch (ParseException ex) {
                Logger.getLogger(CommunicatorHalfDuplex.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(CommunicatorHalfDuplex.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Message type
        try{
            String messageType = reader.readLine();
            message.setType(Message.MessageType.valueOf(messageType.replaceFirst(Message.typePrefix, "")));
        } catch (IOException ex) {
            Logger.getLogger(CommunicatorHalfDuplex.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Body
        String body = "";
        try{
            String line = "";
            while ((line = reader.readLine()) != null){
                body += line;
            }
        } catch (IOException ex) {
            Logger.getLogger(CommunicatorHalfDuplex.class.getName()).log(Level.SEVERE, null, ex);
        }
        message.setBody(body);

        return message;
    }

    public void setSendingType(MessageSendingType sendingType){
        this.messageSendingType = sendingType;
    }

    public MessageSendingType getSendingType(){
        return messageSendingType;
    }

    public void addLogListener(LogListener listener){
        log.addListener(listener);
    }

    public void removeLogListener(LogListener listener){
        log.removeListener(listener);
    }

    public void setClient(Socket client){
        this.client = client;
        try {
            initializeStreams();
        } catch (IOException ex) {
            log.LogError("Failed to initialize streams: " + ex.getMessage(), this);
        }
    }

    public String getRemoteSocketAddress(){
        return client.getRemoteSocketAddress().toString();
    }

    public String getLocalSocketAddress(){
        return client.getLocalSocketAddress().toString();
    }

    public Log getLog(){
        return log;
    }

    private void initializeStreams() throws IOException{
        switch(messageSendingType){
            case Object:
                objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
                //objectInputStream = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                objectInputStream = null;
                break;
            case String:
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
                //dataInputStream = new DataInputStream(new BufferedInputStream(client.getInputStream()));
                break;
            case XML:
                xmlEncoder = new XMLEncoder(new BufferedOutputStream(client.getOutputStream()));
                //xmlDecoder = new XMLDecoder(new BufferedInputStream(client.getInputStream()));
                break;
        }
    }
}
