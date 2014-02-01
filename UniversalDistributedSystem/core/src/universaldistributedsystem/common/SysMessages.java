/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.common;

import universaldistributedsystem.common.message.MessageStr;
import universaldistributedsystem.common.message.Message;
import java.io.IOException;
import universaldistributedsystem.plugin.PluginsList;

/**
 *
 * @author Nemanja
 */
public class SysMessages {
    private CommunicatorHalfDuplex com;
    public SysMessages(CommunicatorHalfDuplex com){
        this.com = com;
    }

    private PluginsList getJobInfoList(CommunicatorHalfDuplex communicator){
        String command = "getjoblist";
        Message msg = new MessageStr();
        msg.setType(Message.MessageType.SYSTEM_MESSAGE);
        msg.setBody(command);
        try{
                communicator.send(msg);
        }catch(IOException ex){
            
        }
        communicator.close();
        return null;
    }

    private PluginsList getJobInfoList(){
        getJobInfoList(com);
        return null;
    }
}
