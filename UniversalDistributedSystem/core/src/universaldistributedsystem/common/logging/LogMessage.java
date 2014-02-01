/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common.logging;

import universaldistributedsystem.common.message.Message;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nemanja
 */
public class LogMessage {
    private String messagePath;
    private long count;
    private Message lastMessage;
    
    public enum SaveType {XML, Text};
    private SaveType saveType;

    public LogMessage(String messagePath){
        this.messagePath = messagePath;
        count = 0;
        saveType = SaveType.XML;
    }

    public LogMessage(String messagePath, SaveType saveType){
        this(messagePath);
        this.saveType = saveType;
    }

    public void makeDirs() throws Exception{
        File f = new File(messagePath);
        if(!f.exists() && !f.mkdirs()){
            throw new Exception(String.format("Can't create path '%s'", messagePath));
        }
    }

    public void saveMessage(Message message) throws Exception{
        makeDirs();
        String newFileName = getNewFileName();
        switch(saveType){
            case XML:
                saveAsXML(newFileName, message);
                break;
            case Text:
                saveAsText(newFileName, message);
                break;
        }
        count++;
        lastMessage = message;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    private String getNewFileName(){
        String fileNamePattern = messagePath + "\\" + (new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")).format(new Date());
        String fileName;
        int counter = 0;
        boolean fileExists = false;
        do{
            fileName = fileNamePattern + "_" + counter + ".msg";
            File f = new File(fileName);
            fileExists = f.exists();
        }
        while(fileExists);
        return fileName;
    }

    public void saveAsXML(String newFileName, Message message){
        try{
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                    new FileOutputStream(newFileName)));
            encoder.writeObject(message);
            encoder.close();
        }
        catch(FileNotFoundException ex)
        {
            Logger.getLogger(LogMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveAsText(String newFileName, Message message) {
        try {
            BufferedWriter buffWr = new BufferedWriter(new FileWriter(newFileName));
            buffWr.write(message.toString());
            buffWr.close();
        } catch (IOException ex) {
            Logger.getLogger(LogMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}