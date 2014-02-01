/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package universaldistributedsystem.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import universaldistributedsystem.common.SystemClient.ClientType;
import universaldistributedsystem.common.Utils;

/**
 * Klasa sadrži informacije o job-u.
 * @author Nemanja
 */
public class PluginInfo implements Serializable {

    private static final String pluginExtension = ".jar";

    private class PluginContent implements Serializable{
        public byte[] content;
        public String relativePath;
        public PluginContent(byte[] content, String relativePath){
            this.content = content;
            this.relativePath = relativePath;
        }
    }

    private String pluginName; // Plugin name wich is name of directory where plugin is.
    private String description;
    private String version;
    private int size;   // ovde treba da vraca ukupnu velicinu svih fajlova
    private Date date;
    private Queue<PluginContent> pluginContents;

    public PluginInfo(String name, String description, String version, int size, Date date) {
        this.pluginName = name;
        this.description = description;
        this.version = version;
        this.size = size;
        this.date = date;
    }

    public PluginInfo(File pluginFile) {
        pluginName = pluginFile.getName();
        try {
            Plugin plugin = getPlugin(pluginFile);
            description = plugin.getPluginInfo().getDescription();
        } catch (Throwable ex) {
            Logger.getLogger(PluginInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        return pluginName;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public int getSize() {
        return size;
    }

    public Date getDate() {
        return date;
    }

    public static Plugin getPlugin(File pluginFile) throws Throwable {
        Plugin plugin = null;
        if (pluginFile.exists()) {
            String defaultPackage = pluginFile.getName().toLowerCase().substring(0, pluginFile.getName().length() - 4);
            try {
                ClassLoader loader = URLClassLoader.newInstance(new URL[]{pluginFile.toURI().toURL()});
                plugin = (Plugin) loader.loadClass(defaultPackage + "." + Plugin.PLUGIN_IMPLEMENTATION_CLASS).newInstance();
            } catch (MalformedURLException ex) {
                Utils.logException(null, ex);
            }
        }
        return plugin;
    }

    public static PluginInfo getPluginInfo(File pluginFile) throws Throwable {
        return ((Plugin)getPlugin(pluginFile)).getPluginInfo();
    }

    public Plugin getPlugin(ClientType clientType) throws Throwable{
        File pluginFile = getMainFile(clientType);
        if(pluginFile != null){
            return getPlugin(pluginFile);
        }else{
            return null;
        }
    }

    public File getPluginFile(ClientType clientType){
        return new File(Utils.getPluginsPath(this, clientType) + getName() + "\\" + getName() + pluginExtension);
    }

    public String getRelativePath(){
        return "\\" + pluginName;
    }

    public String getAbsolutePath(Object source, ClientType clientType){
        return Utils.getPluginsPath(source, clientType) + getRelativePath();
    }

    public Queue<File> getPluginAllFiles(Object object, ClientType clientType){
        if(pluginName != null){
            File pluginFile = new File(getAbsolutePath(object, clientType));
            if(pluginFile.exists())
                return Utils.getAllFiles(pluginFile.getParentFile());
            else
                return null;
        }else{
            return null;
        }
    }

    public void loadContents(ClientType clientType){
        Queue<File> pluginFiles = getPluginAllFiles(this, clientType);
        String pluginRoot = getPluginRoot(clientType);
        pluginContents = new LinkedList<PluginContent>();
        for(File file : pluginFiles){
            String relativeName = file.getPath().substring(pluginRoot.length()-1);
            try {
                pluginContents.add(new PluginContent(Utils.readFileContent(file), relativeName));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PluginInfo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PluginInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void clearPluginContents(){
        pluginContents = null;
    }

    public void saveContents(ClientType clientType) throws IOException{
        String pluginRoot = getPluginRoot(clientType);
        for(PluginContent plCon : pluginContents){
            File file = new File(pluginRoot + plCon.relativePath);
            file.getParentFile().mkdirs();
            Utils.writeFileContent(file, plCon.content);
        }
    }

    private String getPluginRoot(ClientType clientType){
        return Utils.getPluginsPath(this, clientType) + getName() + "\\";
    }

    private File getMainFile(ClientType clientType){
        return new File(getPluginRoot(clientType) + getName() + "." + Plugin.PLUGIN_EXTENSION);
    }
}