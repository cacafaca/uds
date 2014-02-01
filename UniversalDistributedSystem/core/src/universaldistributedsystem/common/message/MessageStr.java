/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common.message;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * String reprezentacija poruke.
 * Svi objekti od kojih je sastavljena poruka su stringovi.
 *
 * @author Nemanja
 */
public class MessageStr implements Message{

    public static final String dateTimeFormat = "yyyy-MM-dd hh:mm:ss.SSS";

    private String from;
    private String to;
    private String dateTime; // '2012-01-27 12:00:00.999'
    private String msgtype;
    private String body;



    public MessageStr(){
        // Za sada, za ovu implementaciju neka su sva polja prazna na pocetku
        from = "";
        to = "";
        dateTime = "";
        msgtype = "";
        body = "";
    }

    public InetAddress getFrom() {
        try {
            return InetAddress.getByName(from);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MessageStr.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void setFrom(InetAddress from) {
        this.from = from.getHostAddress();
    }

    public void setFrom(String from){
        this.from = from;
    }

    public InetAddress getTo() {
        try {
            return InetAddress.getByName(to);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MessageStr.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void setTo(InetAddress to) {
        this.to = to.getHostAddress();
    }

    public Date getDateTime() {
        try{
            return (Date)((DateFormat)(new SimpleDateFormat(dateTimeFormat))).parse(dateTime);
        }
        catch(Exception ex) {
            return null;
        }
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = (new SimpleDateFormat(dateTimeFormat)).format(dateTime);
    }

    public MessageType getType() {
        return MessageType.valueOf(msgtype);
    }

    public void setType(MessageType msgtype) {
        //this.msgtype = msgtype.toString();
        this.msgtype = msgtype.name();
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body.toString();
    }

    @Override
    public String toString(){
        return
                fromPrefix + from + "\n" +
                toPrefix + to + "\n" +
                datePrefix + dateTime + "\n" +
                typePrefix + msgtype + "\n" +
                body;
    }

    public void setPluginId(String pluginId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getPluginId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
