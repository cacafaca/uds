/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package universaldistributedsystem.common;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Nemanja
 */
public class Utils {

    // Constants
    private static final int COPY_FILE_BUFFER_SIZE = 1024 * 1024; //1MB

    public static String getApplicationDirectory(Object mainClass) {
        String appDir = mainClass.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replace("%20", " ");
        return appDir;
    }

    public static void copyFile(File sourceFile, File destinationFile, boolean makeDir) throws Exception {
        if (sourceFile.exists()) {
            if (makeDir && !destinationFile.getParentFile().exists()
                    && !destinationFile.getParentFile().mkdirs()) {
                throw new Exception(String.format("Can't create dir '%0'", destinationFile.getPath()));
            }
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destinationFile));
            byte[] buf = new byte[COPY_FILE_BUFFER_SIZE];
            int len;
            while ((len = bis.read(buf)) > 0) {
                bos.write(buf, 0, len);
            }
            bis.close();
            bos.close();
        } else {
            throw new Exception("File not exists.");
        }
    }

    public static void copyFile(String sourceFile, String destinationFile) throws Exception {
        copyFile(new File(sourceFile), new File(destinationFile), false);
    }

    /**
     * Copy file and make dir.
     *
     * @param sourceFile
     * @param destinationFile
     * @param makeDir Make dir if does not exists.
     * @throws Exception
     */
    public static void copyFile(String sourceFile, String destinationFile, boolean makeDir) throws Exception {
        copyFile(new File(sourceFile), new File(destinationFile), makeDir);
    }
    // Pravim razlicite foldere zbog testiranja na istoj masini.
    private final static String[] pluginFolder = new String[]{
        "plugins/", // None
        "plugins_server/", // Server
        "plugins_workstation/", // Workstation
        "plugins_client/" // Client
    };

    public static String getPluginsPath(Object mainClass, SystemClient.ClientType clientType) {
        String jobPath = Utils.getApplicationDirectory(mainClass)
                + pluginFolder[clientType.ordinal()];
        return jobPath;
    }

    public enum MessageType {
        Plain, Error, Info, Warrning, Question
    }

    public static void showMessage(String message, MessageType messageType) {

        JOptionPane.showMessageDialog(null, message, messageType.toString(),
                messageType.ordinal() - 1);
    }

    /**
     * Show standard INFO message.
     * @param message
     */
    public static void showMessage(String message) {
        //JOptionPane.showMessageDialog(null, message);
        showMessage(message, MessageType.Info);
    }

    public static void setIcon(JFrame frame, String iconPath) {
        java.net.URL url = ClassLoader.getSystemResource(iconPath);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        frame.setIconImage(img);
    }

    public static void logException(Object obj, Throwable ex) {
        Logger.getLogger(obj.getClass().getName()).log(Level.SEVERE, null, ex);
    }

    private static void getAllFilesLoop(Queue<File> fileList, File dir, FileFilter filter, int depth, boolean includeDirs) {
        if (depth >= 0) {
            if (dir.isFile()) {
                dir = dir.getParentFile();
            }
            File[] listFiles = dir.listFiles(filter);
            if(listFiles != null){
                for (File file : listFiles) {
                    if (file.isFile() || includeDirs) {
                        fileList.add(file);
                    }
                    if (file.isDirectory()) {
                        getAllFilesLoop(fileList, file, filter, depth - 1, includeDirs);
                    }
                }
            }
        }
    }

    public static Queue<File> getAllFiles(File dir, FileFilter filter, int depth, boolean includeDirs) {
        Queue<File> fileList = new LinkedList<File>();
        getAllFilesLoop(fileList, dir, filter, depth, includeDirs);
        return fileList;
    }

    public static Queue<File> getAllFiles(File dir, FileFilter filter, int depth) {
        return getAllFiles(dir, filter, depth, false);
    }

    public static Queue<File> getAllFiles(File dir) {
        return getAllFiles(dir, null, Integer.MAX_VALUE);
    }

    /**
     * Returns only file name. Without extension and path before file name.
     * @param file
     * @return file name without extension
     */
    public static String getFileNameOnly(File file) {
        String name = file.getName();
        int endPos = name.lastIndexOf(".");
        if (endPos >= 0) {
            return name.substring(0, endPos);
        } else {
            return name;
        }
    }

    public static String getFileNameOnly(String fileName) {
        return getFileNameOnly(new File(fileName));
    }

    public static void copyFolder(File sourceDir, File destDir) throws Exception {
        if (!sourceDir.exists()) {
            throw new Exception(String.format("The folder '%s' doesn't exist.", sourceDir.getPath()));
        }
        if (!sourceDir.isDirectory()) {
            throw new Exception(String.format("The '%s' isn't a folder.", sourceDir.getPath()));
        }
        Queue<File> sourceFiles = getAllFiles(sourceDir);
        for (File sourceFile : sourceFiles) {
            String destFilesRelPath = sourceFile.getPath().substring(sourceDir.getPath().length());
            File destFile = new File(destDir + destFilesRelPath);
            copyFile(sourceFile, destFile, true);
        }
    }

    public static byte[] readFileContent(File file) throws FileNotFoundException, IOException  {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] content = new byte[(int) file.length()];
        bis.read(content);
        bis.close();
        return content;
    }

    public static void writeFileContent(File file, byte[] content) throws IOException  {
        BufferedOutputStream bis = new BufferedOutputStream(new FileOutputStream(file));
        bis.write(content);
        bis.close();
    }

}
