/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common.logging;

import java.io.Serializable;

/**
 *
 * @author Nemanja
 */
public class LogEvent implements Serializable{

    // private fields
    private Object source;
    private String message;
    private Log.LogType logType;

    public LogEvent(Object source, String message, Log.LogType logType){
        this.source = source;
        this.message = message;
        this.logType = logType;
    }

    public Object getSource(){
        return source;
    }

    public String getMessage(){
        return message;
    }
    public Log.LogType getLogType(){
        return logType;
    }
}
