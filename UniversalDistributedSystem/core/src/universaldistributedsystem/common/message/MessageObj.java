/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common.message;

import java.net.InetAddress;
import java.util.Date;

/**
 * String reprezentacija poruke.
 * Svi objekti od kojih je sastavljena poruka su stringovi.
 *
 * @author Nemanja
 */
public class MessageObj implements Message{

    public static final String dateTimeFormat = "yyyy-MM-dd hh:mm:ss.SSS";

    private InetAddress from;
    private InetAddress to;
    private Date dateTime; // '2012-01-27 12:00:00.999'
    private MessageType msgtype;
    private Object body;

    public MessageObj(){
        // Za sada, za ovu implementaciju neka su sva polja prazna na pocetku
        from = null;
        to = null;
        dateTime = null;
        msgtype = null;
        body = null;
    }

    /**
     * This constructor sets default message type as MessageType.CUSTOM_MESSAGE.
     * @param body
     */
    public MessageObj(Object body){
        this();
        this.msgtype = MessageType.CUSTOM_MESSAGE;
        this.body = body;
    }

    public MessageObj(MessageType msgType, Object body){
        this();
        this.msgtype = msgType;
        this.body = body;
    }

    public InetAddress getFrom() {
        return from;
    }

    public void setFrom(InetAddress from) {
        this.from = from;
    }

    public InetAddress getTo() {
        return to;
    }

    public void setTo(InetAddress to) {
        this.to = to;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public MessageType getType() {
        return msgtype;
    }

    public void setType(MessageType msgtype) {
        this.msgtype = msgtype;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString(){
        return
                fromPrefix + ((from != null)? from.toString() : "") + "\n" +
                toPrefix + ((to != null)? to.toString() : "") + "\n" +
                datePrefix + ((dateTime != null)? dateTime.toString() : "") + "\n" +
                typePrefix + ((msgtype != null)? msgtype.toString() : "") + "\n" +
                ((body != null)? body.toString() : "");
    }

    public void setPluginId(String pluginId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getPluginId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}