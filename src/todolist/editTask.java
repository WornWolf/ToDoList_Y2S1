package todolist;

import DAO.TDLdao;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class editTask extends javax.swing.JDialog {
    private Task task;
    private FolderPanel fdPanel;
    private TDLdao dao = new TDLdao();

    /**
     * Creates new form editTask
     */
    public editTask(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public editTask(java.awt.Frame parent, boolean modal, FolderPanel fdPanel, Task task, Date date) {
        super(parent, modal);
        this.task = task;
        this.fdPanel = fdPanel;
        initComponents();
        fdPanel.formatDate(task.getDate());

        // Format Date
        inputDate.setFormats("dd/MM/yyyy");
        inputDate.setDate(date);
         inputDate.getEditor().setEditable(false);
         
         //  Start cursor at Input Details
         inputDetails.requestFocus();
        
    }

    private void editConfirmed(String formatDate, String details) {
        task.editDataToBT(formatDate, details);
        fdPanel.UpdatePanel();
        
        
    }

    public void editConfirmed() {
        // New date data
        Date selectedDate = inputDate.getDate(); 
        String formattedDate = fdPanel.formatDate(selectedDate);  // Newdate

        // String DateFormat to task
        task.setFormatDate(fdPanel.formatDate(selectedDate)); // New String DATE

        // Set to Button in Tasks
        String oldDetails =task.getDetails();
        String newdetails = inputDetails.getText(); // new details
        task.setDetails(newdetails);
        task.setDate(selectedDate);
        
        task.TaskUpdate();
        editConfirmed(formattedDate, newdetails);
        dao.editData(fdPanel.getFolderName(), oldDetails, formattedDate, newdetails, task.getCheckState());
        
         // Update in NotiArea
            for(Task t : fdPanel.getAllTasks()){
                NotiTask NT = t.getNTask();
                fdPanel.getHomepage().removefromNotiArea(NT);
                t.UpdateNotiPanel();
                
            }
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        dateLB = new javax.swing.JLabel();
        detailsLB = new javax.swing.JLabel();
        detailsScroll = new javax.swing.JScrollPane();
        inputDetails = new javax.swing.JTextArea();
        confirmBT = new javax.swing.JButton();
        cancelBT = new javax.swing.JButton();
        inputDate = new org.jdesktop.swingx.JXDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Task");

        panel.setBackground(new java.awt.Color(255, 255, 255));

        dateLB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dateLB.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dateLB.setText("Date :");
        dateLB.setFocusable(false);

        detailsLB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        detailsLB.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        detailsLB.setText("Details :");
        detailsLB.setFocusable(false);

        detailsScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        inputDetails.setColumns(20);
        inputDetails.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inputDetails.setRows(5);
        inputDetails.setText(task.getDetails());
        inputDetails.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputDetailsKeyPressed(evt);
            }
        });
        detailsScroll.setViewportView(inputDetails);

        confirmBT.setBackground(new java.awt.Color(255, 49, 49));
        confirmBT.setForeground(new java.awt.Color(255, 255, 255));
        confirmBT.setText("Confirm");
        confirmBT.setFocusPainted(false);
        confirmBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBTActionPerformed(evt);
            }
        });

        cancelBT.setBackground(new java.awt.Color(254, 254, 254));
        cancelBT.setText("Cancel");
        cancelBT.setFocusPainted(false);
        cancelBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBTActionPerformed(evt);
            }
        });

        inputDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(detailsLB, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLB, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(detailsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(confirmBT)
                        .addGap(18, 18, 18)
                        .addComponent(cancelBT)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLB, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detailsLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detailsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelBT)
                    .addComponent(confirmBT))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelBTActionPerformed
        this.dispose();
    }// GEN-LAST:event_cancelBTActionPerformed

    private void confirmBTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_confirmBTActionPerformed
         if (inputDetails.getText().length() ==  0) {
            JOptionPane.showMessageDialog(null, "Please input the task details.");
        } else {
            editConfirmed();
            this.dispose();
        }
    }// GEN-LAST:event_confirmBTActionPerformed

    private void inputDetailsKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_inputDetailsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (inputDetails.getText().length() ==  0) {
            JOptionPane.showMessageDialog(null, "Please input the task details.");
        } else {
            editConfirmed();
            }
        }
    }// GEN-LAST:event_inputDetailsKeyPressed

    private void inputDateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_inputDateActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_inputDateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                editTask dialog = new editTask(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBT;
    private javax.swing.JButton confirmBT;
    private javax.swing.JLabel dateLB;
    private javax.swing.JLabel detailsLB;
    private javax.swing.JScrollPane detailsScroll;
    private org.jdesktop.swingx.JXDatePicker inputDate;
    private javax.swing.JTextArea inputDetails;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}