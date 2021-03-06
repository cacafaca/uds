/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClientGUI.java
 *
 * Created on 12.01.2012., 20.11.41
 */

package universaldistributedsystem.client;

import java.io.File;
import java.io.IOException;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import universaldistributedsystem.common.Constants;
import universaldistributedsystem.common.SystemClient.ClientType;
import universaldistributedsystem.common.SystemClient.JobListReceivedListener;
import universaldistributedsystem.common.SystemClient.JobReceivedListener;
import universaldistributedsystem.common.logging.Log;
import universaldistributedsystem.common.Utils;
import universaldistributedsystem.plugin.PluginInfo;
import universaldistributedsystem.plugin.PluginsList;

/**
 *
 * @author Nemanja
 */
public class ClientGUI extends javax.swing.JFrame {

    private Client client;
    private Log log;

    /** Creates new form ClientGUI */
    public ClientGUI() {
        log = new Log(this);
        initComponents();
        client = new Client(log, ClientType.Client);
        initComponentsExtra();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jPanelSettings = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtPort = new javax.swing.JTextField();
        jButtonConnect = new javax.swing.JButton();
        jcbHost = new javax.swing.JComboBox();
        jlConnectionStatus = new javax.swing.JLabel();
        jPanelJobs = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableJobs = new javax.swing.JTable();
        jButtonGetJobList = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jbGetJob = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemCheckServer = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UDS - Client");
        setForeground(new java.awt.Color(0, 255, 0));

        jToolBar1.setRollover(true);

        jButton1.setText("jButton1");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jPanelSettings.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));
        jPanelSettings.setMinimumSize(new java.awt.Dimension(100, 20));

        jLabel2.setText("Host");

        jLabel1.setText("Port");

        jtPort.setText("4000");
        jtPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtPortActionPerformed(evt);
            }
        });

        jButtonConnect.setText("Connect");
        jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnectActionPerformed(evt);
            }
        });

        jcbHost.setEditable(true);
        jcbHost.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "localhost", "webserver", "teodora" }));

        jlConnectionStatus.setBackground(new java.awt.Color(255, 102, 102));
        jlConnectionStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlConnectionStatus.setText("Not connected");
        jlConnectionStatus.setOpaque(true);

        javax.swing.GroupLayout jPanelSettingsLayout = new javax.swing.GroupLayout(jPanelSettings);
        jPanelSettings.setLayout(jPanelSettingsLayout);
        jPanelSettingsLayout.setHorizontalGroup(
            jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlConnectionStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                    .addGroup(jPanelSettingsLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbHost, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonConnect, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSettingsLayout.setVerticalGroup(
            jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsLayout.createSequentialGroup()
                .addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcbHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonConnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlConnectionStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelJobs.setBorder(javax.swing.BorderFactory.createTitledBorder("Jobs"));

        jTableJobs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Description", "Version", "Size"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableJobs.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(jTableJobs);
        jTableJobs.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jButtonGetJobList.setText("Get list");
        jButtonGetJobList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGetJobListActionPerformed(evt);
            }
        });

        jButton2.setText("Run");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jbGetJob.setText("Get job");
        jbGetJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGetJobActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelJobsLayout = new javax.swing.GroupLayout(jPanelJobs);
        jPanelJobs.setLayout(jPanelJobsLayout);
        jPanelJobsLayout.setHorizontalGroup(
            jPanelJobsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJobsLayout.createSequentialGroup()
                .addComponent(jButtonGetJobList)
                .addGap(6, 6, 6)
                .addComponent(jbGetJob)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(224, 224, 224))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
        );
        jPanelJobsLayout.setVerticalGroup(
            jPanelJobsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelJobsLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJobsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGetJobList)
                    .addComponent(jButton2)
                    .addComponent(jbGetJob))
                .addGap(44, 44, 44))
        );

        jMenu2.setText("Client");

        jMenuItem1.setText("Settings");
        jMenu2.add(jMenuItem1);

        jMenuItemCheckServer.setText("Connect to server");
        jMenu2.add(jMenuItemCheckServer);

        jMenuItem4.setText("Send custom message");
        jMenu2.add(jMenuItem4);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Jobs");

        jMenuItem3.setText("Get list");
        jMenu1.add(jMenuItem3);

        jMenu3.setText("System");
        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
            .addComponent(jPanelSettings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelJobs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelJobs, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        exit();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jtPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtPortActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        runSelectedJob();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnectActionPerformed
        if(!client.isConnected()){
            try {
                client.connect(getHost(), getPort());
                ((JButton)evt.getSource()).setText("Disconnect");
            } catch (IOException ex) {
                log.LogError(ex.toString());
                Utils.showMessage("Couldn't connect to server.\n"+ex.toString());
            }
        }
        else{
            try {
                client.close();
                ((JButton)evt.getSource()).setText("Connect");
            } catch (IOException ex) {
                log.LogError(ex.toString());
                Utils.showMessage("Couldn't close connection.");
            }
        }
        setConnectionLabel(client.isConnected());
    }//GEN-LAST:event_jButtonConnectActionPerformed

    private void jButtonGetJobListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGetJobListActionPerformed
        try {
            client.sendPluginGetListRequest();
        } catch (Exception ex) {
            log.LogError("Can't get jobs list: " + ex.getMessage(), this);
        }
    }//GEN-LAST:event_jButtonGetJobListActionPerformed

    private void jbGetJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGetJobActionPerformed
        getSelectedJob();
    }//GEN-LAST:event_jbGetJobActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JButton jButtonGetJobList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItemCheckServer;
    private javax.swing.JPanel jPanelJobs;
    private javax.swing.JPanel jPanelSettings;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableJobs;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbGetJob;
    private javax.swing.JComboBox jcbHost;
    private javax.swing.JLabel jlConnectionStatus;
    private javax.swing.JTextField jtPort;
    // End of variables declaration//GEN-END:variables

    private void exit(){
        System.exit(0);
    }

    private String getHost(){
        return jcbHost.getModel().getSelectedItem().toString();
    }

    private int getPort(){
        return Integer.parseInt(jtPort.getText());
    }

    private void registerJobsListReceivedListener(){
        client.addJobListReceivedEvent(new JobListReceivedListener() {

            public void jobListReceived(EventObject source, PluginsList jobsList) {
                Utils.showMessage("Plugins list received!\n" + jobsList.size() + " plugin(s) total.",
                        (jobsList.size() > 0 ? Utils.MessageType.Info : Utils.MessageType.Warrning));

                emptyJobsTable();
                DefaultTableModel tableModel = (DefaultTableModel) jTableJobs.getModel();
                for(Object[] o: jobsList.getJobsListData())
                    tableModel.addRow(o);
            }
        });
    }

    private void initComponentsExtra() {
        emptyJobsTable();
        registerJobsListReceivedListener(); // Registruje dogadjaj kada stigne lista jobova.
        registerJobReceivedListener();
        Utils.setIcon(this, "universaldistributedsystem/client/resources/client_icon.png");
        //jPanelSettings.setBackground(Color.red);
        jlConnectionStatus.setBackground(Constants.COLOR_NOT_CONNECTED);
    }

    private void getSelectedJob(){
        int selectedRow = jTableJobs.getSelectedRow();
        if(selectedRow >= 0){
            PluginInfo selectedJobInfo = new PluginInfo(
                    (String)jTableJobs.getValueAt(selectedRow, 0),
                    (String)jTableJobs.getValueAt(selectedRow, 1),
                    (String)jTableJobs.getValueAt(selectedRow, 2),
                    (Integer)jTableJobs.getValueAt(selectedRow, 3),
                    null);
            try {
                client.sendPluginGetRequest(selectedJobInfo);
            } catch (Exception ex) {
                Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
         Utils.showMessage("No row selected.");
        }
    }

    private void registerJobReceivedListener(){
        client.addJobReceivedEvent(new JobReceivedListener() {

            public void jobReceived(EventObject source, PluginInfo jobInfo) {
                Utils.showMessage("Job file received: " + jobInfo.getName());
            }

        });
    }

    private void emptyJobsTable(){
        DefaultTableModel tableModel = (DefaultTableModel)jTableJobs.getModel();
        while(tableModel.getRowCount() > 0 )
            tableModel.removeRow(0);

    }

    private void runSelectedJob(){
        int selectedRow = jTableJobs.getSelectedRow();
        if(selectedRow >= 0){
            PluginInfo selectedJobInfo = new PluginInfo(
                    (String)jTableJobs.getValueAt(selectedRow, 0),
                    (String)jTableJobs.getValueAt(selectedRow, 1),
                    (String)jTableJobs.getValueAt(selectedRow, 2),
                    (Integer)jTableJobs.getValueAt(selectedRow, 3),
                    null);
            try {

                PluginInfo.getPlugin(selectedJobInfo.getPluginFile(ClientType.Client)).runClient(client);
                //runJob(selectedJobInfo);
            } catch (Throwable ex) {
                Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
         Utils.showMessage("No row selected.");
        }
    }

    public void setConnectionLabel(boolean connected){
        if(connected){
            jlConnectionStatus.setText("Connected");
            jlConnectionStatus.setBackground(Constants.COLOR_CONNECTED);
        }else{
            jlConnectionStatus.setText("Not connected");
            jlConnectionStatus.setBackground(Constants.COLOR_NOT_CONNECTED);
        }
    }
}