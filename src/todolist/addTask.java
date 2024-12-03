package todolist;

import DAO.TDLdao;
import java.awt.*;
import java.util.Date;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.awt.event.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hp
 */
public class addTask extends javax.swing.JDialog {

    private FolderPanel fdPanel;
    private Date date = new Date();
    private TDLdao dao = new TDLdao();

    /**
     * Creates new form addTask
     */

    public addTask(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public addTask(java.awt.Frame parent, boolean modal, FolderPanel fdPanel) {
        super(parent, modal);
        this.fdPanel = fdPanel;
        initComponents();
        // InputDate cant Edit
        inputDate.setFormats("dd/MM/yyyy");
        inputDate.setDate(date);
        inputDate.getEditor().setEditable(false);

        //  Start cursor at Input Details
        InputDetails.requestFocus();

    }

    private void confirmed(Date date, String details) {
        fdPanel.UpdatePanel(date, details);
    }

    public void confirmed() {

        String formattedDate = "";
        Date selectedDate = inputDate.getDate(); // เก็บวันที่ที่เลือกไว้ใน selectedDate
        if (selectedDate != null) {
            // แปลงเป็น LocalDate และกำหนดรูปแบบวันที่ที่ต้องการ
            LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            formattedDate = localDate.format(formatter);
        }
        String details = InputDetails.getText();
        confirmed(selectedDate, details);

        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        dateLB = new javax.swing.JLabel();
        detailsScroll = new javax.swing.JScrollPane();
        InputDetails = new javax.swing.JTextArea();
        detailsLB = new javax.swing.JLabel();
        confirmBT = new javax.swing.JButton();
        cancelBT = new javax.swing.JButton();
        inputDate = new org.jdesktop.swingx.JXDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Task");
        setMinimumSize(new java.awt.Dimension(450, 200));
        setResizable(false);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        dateLB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dateLB.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dateLB.setText("Date :");

        detailsScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        InputDetails.setColumns(20);
        InputDetails.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        InputDetails.setLineWrap(true);
        InputDetails.setRows(5);
        InputDetails.setAlignmentX(1.0F);
        InputDetails.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InputDetailsKeyPressed(evt);
            }
        });
        detailsScroll.setViewportView(InputDetails);

        detailsLB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        detailsLB.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        detailsLB.setText("Details :");

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
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(detailsLB, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(dateLB, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(detailsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(confirmBT)
                        .addGap(18, 18, 18)
                        .addComponent(cancelBT)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLB, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detailsLB, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detailsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmBT)
                    .addComponent(cancelBT))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void confirmBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBTActionPerformed
        if (InputDetails.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please input the task details.");
        } else {
            confirmed();
            this.dispose();
        }
    }//GEN-LAST:event_confirmBTActionPerformed

    private void cancelBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBTActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelBTActionPerformed

    private void InputDetailsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InputDetailsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
              if (InputDetails.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please input the task details.");
        } else {
            confirmed();
            }
        }
    }//GEN-LAST:event_InputDetailsKeyPressed

    private void inputDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputDateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(addTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                addTask dialog = new addTask(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextArea InputDetails;
    private javax.swing.JButton cancelBT;
    private javax.swing.JButton confirmBT;
    private javax.swing.JLabel dateLB;
    private javax.swing.JLabel detailsLB;
    private javax.swing.JScrollPane detailsScroll;
    private org.jdesktop.swingx.JXDatePicker inputDate;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
