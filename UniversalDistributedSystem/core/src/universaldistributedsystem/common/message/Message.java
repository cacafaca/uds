/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common.message;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Date;

/**
 *
 * @author Nemanja
 */
public interface Message extends Serializable{

    // Prefiksi
    public static String fromPrefix = "From:";
    public static String toPrefix = "To:";
    public static String datePrefix = "Date:";
    public static String typePrefix = "Type:";

    //From
    InetAddress getFrom();
    void setFrom(InetAddress from);

    //To
    InetAddress getTo();
    void setTo(InetAddress to);

    //Date
    Date getDateTime();
    void setDateTime(Date dateTime);

    //Type
    /**
     * Message type can be one of the following:<br>
     *   - SYSTEM_MESSAGE - Type of message used to sent sismtem messages.<br>
     *   - CUSTOM_MESSAGE -
     */
    public enum MessageType {
        /**
         * Sistemska poruka
         */
        SYSTEM_MESSAGE, // mozda moze da se prepravi na SYSTEM_MESSAGE_REQUEST i SYSTEM_MESSAGE_SESPONS ?
        CUSTOM_MESSAGE
    }
    public MessageType getType();
    public void setType(MessageType type);

    //Body
    Object getBody();
    void setBody(Object body);

    //Raw message
    @Override
    String toString();

    //plugin id
    public void setPluginId(String pluginId);
    public String getPluginId();

    //socket
}