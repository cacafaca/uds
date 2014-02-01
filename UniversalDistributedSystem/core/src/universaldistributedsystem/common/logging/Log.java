/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common.logging;

import java.util.Date;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import javax.swing.event.EventListenerList;
import universaldistributedsystem.common.Utils;

/**
 *
 * @author Nemanja
 */
public class Log {

    private String logFileName;
    private boolean writeToFile = true;

    public Log(String logFileName){
        setLogFileName(logFileName);
    }

    public Log(){
        setLogFileName("log\\default.log");
    }

    public Log(Object source){
        setLogFileName("log\\" + source.getClass().getName() + ".log");
    }

    private void writeToFile(String text) {
        if(writeToFile)
            try{
                FileWriter fstream = new FileWriter(logFileName, true);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(text+"\r\n");
                out.close();
            }
            catch (IOException ex){
                LogError("Cant open or write to file:" + ex.getMessage(), this);
            }
    }

    public enum LogType {Info, Warning, Error};

    /**
     * Log to default output and to a file.
     * @param logType
     * @param message
     * @param loggerClass
     */
    private void LogCustom(LogType logType, String message, Object loggerClass)
    {
        String className;
        if (loggerClass != null){
            className = String.format(" [%s]", loggerClass.getClass().getName());
        }
        else{
            className = "";
        }
        String strOut = String.format("%1$td.%1$tm.%1$tY %1$tH:%1$tM:%1$tS> %2$s:%3$s %4$s",
                new Date(), logType.toString(), className, message);
        System.out.println(strOut);
        writeToFile(strOut);
        fireLogEvent(new LogEvent(loggerClass, message, logType));
        switch(logType){
            case Info:
                // TODO Fire log event for info messages;
                break;
            case Warning:
                break;
            case Error:
                break;
        }
    }

    public void LogInfo(String message, Object loggedClass)
    {
        LogCustom(LogType.Info, message, loggedClass);
    }

    public void logInfo(String message)
    {
        LogCustom(LogType.Info, message, null);
    }

    public void LogWarrning(String message, Object loggedClass)
    {
        LogCustom(LogType.Warning, message, loggedClass);
    }

    public void LogWarrning(String message)
    {
        LogCustom(LogType.Warning, message, null);
    }

    public void LogError(String message, Object loggedClass)
    {
        LogCustom(LogType.Error, message, loggedClass);
    }

    public void LogError(String message)
    {
        LogCustom(LogType.Error, message, null);
    }

    protected EventListenerList listenerList = new EventListenerList();
    protected EventListenerList infoListenerList = new EventListenerList();
    protected EventListenerList warningListenerList = new EventListenerList();
    protected EventListenerList errorListenerList = new EventListenerList();

    public void addListener(LogListener listener){
        // provera da nema vec istih
        boolean listenerExists = false;
        Object[] listeners = listenerList.getListenerList();
        for(int i = 0; i < listeners.length; i += 2)
            if(listeners[i] == LogListener.class && ((LogListener)listeners[i+1]) == listener){
                listenerExists = true;
                break;
            }
        if(!listenerExists)
            listenerList.add(LogListener.class, listener);
    }

    public void addInfoListener(LogListener listener){
        infoListenerList.add(LogListener.class, listener);
        listenerList.add(LogListener.class, listener);
    }

    public void addWarningListener(LogListener listener){
        warningListenerList.add(LogListener.class, listener);
        listenerList.add(LogListener.class, listener);
    }

    public void addErrorListener(LogListener listener){
        errorListenerList.add(LogListener.class, listener);
        listenerList.add(LogListener.class, listener);
    }

    public void removeListener(LogListener listener){
        listenerList.remove(LogListener.class, listener);
    }

    public void removeInfoListener(LogListener listener){
        infoListenerList.remove(LogListener.class, listener);
        listenerList.remove(LogListener.class, listener);
    }

    public void removeWarningListener(LogListener listener){
        warningListenerList.remove(LogListener.class, listener);
        listenerList.remove(LogListener.class, listener);
    }

    public void removeErrorListener(LogListener listener){
        errorListenerList.remove(LogListener.class, listener);
        listenerList.remove(LogListener.class, listener);
    }

    public void fireLogEvent(LogEvent event){
        Object[] listeners = listenerList.getListenerList();
        for(int i = 0; i < listeners.length; i += 2)
            if(listeners[i] == LogListener.class)
                ((LogListener) listeners[i+1]).Logged(event);
    }

    public void setWriteToFile(boolean writeToFile){
        this.writeToFile = writeToFile;
        makeLogFolder();
    }

    private void makeLogFolder(){
        if(writeToFile){
            File logFile = new File(logFileName);
            File logFolder = logFile.getParentFile();
            if(!logFolder.exists()){
                writeToFile = logFolder.mkdirs();
            }
        }
    }

    public String getLogFileName(){
        return logFileName;
    }

    private void setLogFileName(String logFileName){
        File logFile = new File(logFileName);
        if(!logFile.isAbsolute())
            this.logFileName = Utils.getApplicationDirectory(this) + logFileName;
        else
            this.logFileName = logFileName;
        makeLogFolder();
    }
}
