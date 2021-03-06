/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Server.java
 *
 * Created on 18.12.2011., 16.03.55
 */
package universaldistributedsystem.server;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import universaldistributedsystem.common.logging.Log;
import universaldistributedsystem.common.logging.LogEvent;
import universaldistributedsystem.common.logging.LogListener;
import universaldistributedsystem.common.Properties;
import universaldistributedsystem.common.PropertyDialog;
import universaldistributedsystem.common.Utils;
import universaldistributedsystem.plugin.Plugin;

/**
 *
 * @author Nemanja
 */
public class ServerGUI extends javax.swing.JFrame {

    //Fields
    private ServerGroup serverGroup;
    private Log log;
    private Style[] logStyles;

    /** Creates new form Server */
    public ServerGUI() {
        log = new Log(this);
        log.setWriteToFile(false);
        serverGroup = new ServerGroup(log);

        initComponents();
        initComponentsCustom();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jScrollPaneLogOld = new javax.swing.JScrollPane();
        jtaLog = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPaneLogNew = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jTextPane1 = new javax.swing.JTextPane();
        pluginPanel1 = new universaldistributedsystem.plugin.panel.PluginPanel();
        jPanelInfo = new javax.swing.JPanel();
        jLabelInfo = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonStartStopServer = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuPlugins = new javax.swing.JMenu();
        jMenuItemAddJobs = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UDS - Server");

        jScrollPaneLogOld.setBorder(javax.swing.BorderFactory.createTitledBorder("Log (old)"));
        jScrollPaneLogOld.setMinimumSize(new java.awt.Dimension(22, 22));

        jtaLog.setBackground(new java.awt.Color(153, 153, 153));
        jtaLog.setColumns(20);
        jtaLog.setEditable(false);
        jtaLog.setRows(5);
        jScrollPaneLogOld.setViewportView(jtaLog);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "gfsd", "gfds", "gfd", "sg" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jScrollPaneLogNew.setBorder(javax.swing.BorderFactory.createTitledBorder("Log (new)"));

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.setMinimumSize(new java.awt.Dimension(20, 20));
        jPanel2.setPreferredSize(new java.awt.Dimension(20, 96));

        jTextPane1.setBorder(null);
        jTextPane1.setEditable(false);
        jTextPane1.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jTextPane1.setMinimumSize(new java.awt.Dimension(18, 18));
        jTextPane1.setPreferredSize(new java.awt.Dimension(20, 20));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
        );

        jScrollPaneLogNew.setViewportView(jPanel2);

        pluginPanel1.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout pluginPanel1Layout = new javax.swing.GroupLayout(pluginPanel1);
        pluginPanel1.setLayout(pluginPanel1Layout);
        pluginPanel1Layout.setHorizontalGroup(
            pluginPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        pluginPanel1Layout.setVerticalGroup(
            pluginPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
            .addComponent(jScrollPaneLogNew, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
            .addComponent(jScrollPaneLogOld, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pluginPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneLogOld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pluginPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneLogNew, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pluginPanel1.getAccessibleContext().setAccessibleName("panelplugin\n");
        pluginPanel1.getAccessibleContext().setAccessibleDescription("opis");

        jPanelInfo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabelInfo.setText("Server stopped");

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addComponent(jLabelInfo)
                .addContainerGap(217, Short.MAX_VALUE))
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelInfo)
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButtonStartStopServer.setText("Start");
        jButtonStartStopServer.setMinimumSize(new java.awt.Dimension(70, 21));
        jButtonStartStopServer.setPreferredSize(new java.awt.Dimension(70, 21));
        jButtonStartStopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartStopServerActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonStartStopServer);

        jButtonExit.setText("Exit");
        jButtonExit.setMinimumSize(new java.awt.Dimension(70, 21));
        jButtonExit.setPreferredSize(new java.awt.Dimension(70, 21));
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonExit);

        jMenu1.setText("Server");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem.setText("Properties");
        jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem);

        jMenuItem2.setText("Start");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Stop");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenuPlugins.setText("Plugins");

        jMenuItemAddJobs.setText("Add");
        jMenuItemAddJobs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddJobsActionPerformed(evt);
            }
        });
        jMenuPlugins.add(jMenuItemAddJobs);

        jMenu3.setText("Remove");
        jMenuPlugins.add(jMenu3);

        jMenuItem6.setText("List");
        jMenuPlugins.add(jMenuItem6);

        jMenuBar1.add(jMenuPlugins);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
            .addComponent(jPanelMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startServer() {
        try {
            serverGroup.start();
            jButtonStartStopServer.setText("Stop");
        } catch (Exception ex) {
            log.LogError("Couldn't start servers: " + ex.getMessage(), this);
        }
    }

    private void stopServer() {
        try {
            serverGroup.stop();
            jButtonStartStopServer.setText("Start");
        } catch (Exception ex) {
            log.LogError("Couldn't stop servers: " + ex.getMessage(), this);
        }
    }

    private void jButtonStartStopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartStopServerActionPerformed
        if (!serverGroup.isRunning()) {
            startServer();
        } else {
            stopServer();
        }
    }//GEN-LAST:event_jButtonStartStopServerActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        exit();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        startServer();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        stopServer();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        exit();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItemAddJobsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddJobsActionPerformed
        pluginAddDialog();
    }//GEN-LAST:event_jMenuItemAddJobsActionPerformed

    private void jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemActionPerformed
        Properties pr = PropertyDialog.loadAndShow(serverGroup.getProperties());
        try {
            serverGroup.setProperties(pr);
        } catch (Exception ex) {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ServerGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonStartStopServer;
    private javax.swing.JLabel jLabelInfo;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItemAddJobs;
    private javax.swing.JMenu jMenuPlugins;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelInfo;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneLogNew;
    private javax.swing.JScrollPane jScrollPaneLogOld;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextArea jtaLog;
    private universaldistributedsystem.plugin.panel.PluginPanel pluginPanel1;
    // End of variables declaration//GEN-END:variables

    Color[] fontColors = new Color[] {Color.black, Color.green, Color.yellow, Color.red};

    public void addLog(LogEvent event) {
        String timeStamp = String.format("%1$td.%1$tm.%1$tY %1$tH:%1$tM:%1$tS> ",
                new Date());
        Color fontColor = Color.green;
        switch (event.getLogType()) {
            case Info:
                fontColor = Color.green;
                break;
            case Warning:
                fontColor = Color.yellow;
                break;
            case Error:
                fontColor = Color.red;
                break;
        }
        fontColor = fontColors[event.getLogType().ordinal() + 1];

        jtaLog.setForeground(fontColor);
        jtaLog.append(timeStamp + event.getMessage() + "\n\r");
        try {
            Rectangle r1 = jtaLog.modelToView(jtaLog.getDocument().getLength());
            if (r1 != null)
                jtaLog.scrollRectToVisible(r1);
        } catch (BadLocationException ex) {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
            /*if (r != null)
                jTextPane1.scrollRectToVisible(r);*/

        try {
            jTextPane1.getStyledDocument().insertString(jTextPane1.getStyledDocument().getLength(),
                    event.getMessage() + "\n\r",
                    StyleContext.getDefaultStyleContext().getStyle("InfoStyle"));

            // Convert the new end location
            // to view co-ordinates
            Rectangle r = jTextPane1.modelToView(jTextPane1.getDocument().getLength());
            // Finally, scroll so that the new text is visible
            /*if (r != null)
                jTextPane1.scrollRectToVisible(r);*/
        } catch (BadLocationException ex) {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void exit() {
        serverGroup.stop();
        System.exit(0);
    }

    private void pluginAddDialog() {
        JFileChooser fc = new JFileChooser();

        // For testing purposes.
        fc.setCurrentDirectory(new File("C:\\Documents and Settings\\Nemanja\\My Documents\\NetBeansProjects\\UniversalDistributedSystem\\plugins\\GetAllFiles\\dist"));

        fc.setMultiSelectionEnabled(false);  // da se odabere vise foldera
        fc.setDialogTitle("Choose plugin folders");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                if(f.isDirectory())
                    return true;
                return f.getName().toLowerCase().endsWith(Plugin.PLUGIN_EXTENSION.toLowerCase());
            }

            @Override
            public String getDescription() {
                return "Plugin files (*.jar)";
            }
        });
        int DlgResult = fc.showOpenDialog(jMenuItemAddJobs);
        if (DlgResult == JFileChooser.APPROVE_OPTION) {
            try {
                addPlugin(fc.getSelectedFile());
            } catch (Exception ex) {
                Utils.showMessage(String.format("Couldn't add plugin '%s'. Error: %s", fc.getSelectedFile().getPath(), ex.getMessage()),
                        Utils.MessageType.Error);
            }
            Utils.showMessage(String.format("Plugin '%s' added.", fc.getSelectedFile().getPath()),
                    Utils.MessageType.Info);
        }
    }

    /**
     * Copy single job into plugin directory.
     * @param pluginSourceFolder
     * @throws pluginMainFile
     */
    private void addPlugin(File pluginMainFile) throws Exception {
        if (!pluginMainFile.exists()){
            throw new Exception("File does not exists.");
        }
        if(!pluginMainFile.isFile()){
            throw new Exception("'" + pluginMainFile.getPath() + "' is not a file.");
        }
        File pluginDestFolder = new File(serverGroup.getPluginsFolder() + "\\" +
                Utils.getFileNameOnly(pluginMainFile));
        Utils.copyFolder(pluginMainFile.getParentFile(), pluginDestFolder);
    }

    private void addListeners() {
        serverGroup.addLogListener(new LogListener() {

            public void Logged(LogEvent event) {
                addLog(event);
            }

            public void InfoLogged(LogEvent event) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void WarnningLogged(LogEvent event) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void ErrorLogged(LogEvent event) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    private void initComponentsCustom() {
        setLocationRelativeTo(null); // Center screen
        addListeners();
        Utils.setIcon(this, "universaldistributedsystem/server/resources/server_icon.png");

        // Set styles
        StyleContext sc = new StyleContext();
        logStyles = new Style[Log.LogType.values().length + 1];

        // Default style
        logStyles[0] = sc.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setFontFamily(logStyles[0], "Courier New");

        // Info style
        logStyles[1] = sc.addStyle("InfoStyle", logStyles[0]);
        StyleConstants.setForeground(logStyles[1], fontColors[Log.LogType.Info.ordinal() + 1]);


        // Warrning style
        logStyles[2] = sc.addStyle("WarrningStyle", logStyles[0]);
        StyleConstants.setForeground(logStyles[2], fontColors[Log.LogType.Warning.ordinal() + 1]);

        // Error style
        logStyles[3] = sc.addStyle("ErrorStyle", logStyles[0]);
        StyleConstants.setForeground(logStyles[3], fontColors[Log.LogType.Error.ordinal() + 1]);
    }
}