/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package getallfiles.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import javax.swing.filechooser.FileSystemView;
import universaldistributedsystem.common.Utils;

/**
 *
 * @author Nemanja
 */
public class GetAllFilesServer {

    public List<File> getAllFiles(){
        List<File> list = new ArrayList<File>();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        for(File root : fsv.getRoots()){
            Queue<File> fl = Utils.getAllFiles(root);
            list.addAll(fl);
        }
        return list;
    }
}