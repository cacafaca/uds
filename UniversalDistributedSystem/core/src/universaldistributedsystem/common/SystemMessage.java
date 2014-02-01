/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common;

import java.io.Serializable;

/**
 * Class for exchanging system messages.
 * @author Nemanja
 */
public class SystemMessage implements Serializable{

    private Command command;
    private Object systemObject;

    public SystemMessage(Command command, Object systemObject){
        this.command = command;
        this.systemObject = systemObject;
    }

    public SystemMessage(Command command){
        this.command = command;
    }

    public enum Command{
        CLOSE_CONNECTION_REQUEST,
        CLOSE_CONNECTION_RESPONSE,
        PLUGIN_GET_LIST_REQUEST,
        PLUGIN_GET_LIST_RESPONSE,
        PLUGIN_GET_REQUEST,
        PLUGIN_GET_RESPONSE
    };

    public void setCommnad(Command commnad){
        this.command = commnad;
    }

    public Command getCommand(){
        return command;
    }

    public void setSystemObject(Object systemObject){
        this.systemObject = systemObject;
    }

    public Object getSystemObject(){
        return systemObject;
    }
}
