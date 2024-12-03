package todolist;

import DAO.TDLdao;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static todolist.HomePanel.currentFolder;

public class addFolder extends javax.swing.JDialog {

    private TDLdao dao = new TDLdao();
    private HomePanel homepanel;
    private String name = "";

    public addFolder(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public addFolder(java.awt.Frame parent, boolean modal, HomePanel panel) {
        this(parent, modal);
        homepanel = panel;
        inputName.setText("Untitled" + (homepanel.getAllFolders().size() + 1));
        countInput.setText(inputName.getText().length() + "/20");
    }

    public void confirmed() {
        // Regex ที่อนุญาตเฉพาะ 0-9, A-Z, a-z, ก-ฮ และ วรรณยุกต์ไทย
        String regex = "[0-9A-Za-zก-ฮ\\u0E31-\\u0E4C ]+";

        if (homepanel.currentFolder != homepanel.SIZE) {
            name = inputName.getText().trim(); // ลบช่องว่างที่ขึ้นต้นและท้าย
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Please input the folder name.");
            } else if (name.length() > 20) {
                JOptionPane.showMessageDialog(null, "The name entered is too long. Please try again.");
            } else if (!Pattern.matches(regex, name)) {
                JOptionPane.showMessageDialog(null, "Special characters are not allowed.");
            } else {
                // Create FolderLine and FolderPanel
                homepanel.createFolder(name);
                this.dispose();
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        inputName = new javax.swing.JTextField();
        confirmBT = new javax.swing.JButton();
        cancelBT = new javax.swing.JButton();
        countInput = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Folder");
        setResizable(false);

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setPreferredSize(new java.awt.Dimension(450, 100));

        label.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label.setText("Folder name :");

        inputName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inputName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputNameKeyTyped(evt);
            }
        });

        confirmBT.setBackground(new java.awt.Color(255, 49, 49));
        confirmBT.setForeground(new java.awt.Color(255, 255, 255));
        confirmBT.setText("Confirm");
        confirmBT.setFocusPainted(false);
        confirmBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBTActionPerformed(evt);
            }
        });

        cancelBT.setBackground(new java.awt.Color(204, 204, 204));
        cancelBT.setText("Cancel");
        cancelBT.setFocusPainted(false);
        cancelBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBTActionPerformed(evt);
            }
        });

        countInput.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        countInput.setText("0/20");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(confirmBT)
                        .addGap(30, 30, 30)
                        .addComponent(cancelBT))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(countInput, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6))
                        .addComponent(inputName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(countInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmBT)
                    .addComponent(cancelBT))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inputNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputNameKeyReleased
        int count = inputName.getText().length();
        countInput.setText(count + "/20");
    }//GEN-LAST:event_inputNameKeyReleased

    private void inputNameKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_inputNameKeyTyped
        int count = inputName.getText().length();
        if (inputName.getText().length() > 20) {
            String s = inputName.getText();
            s = s.substring(0, 20);
            inputName.setText(s);
        }

        if (count >= 20 && evt.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
            evt.consume(); // not allow typing over 20 characters
        }
    }// GEN-LAST:event_inputNameKeyTyped

    private void cancelBTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelBTActionPerformed
        this.dispose();
    }// GEN-LAST:event_cancelBTActionPerformed

    private void inputNameKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_inputNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (dao.isSheetNameExists(inputName.getText())) {
                JOptionPane.showMessageDialog(null, "Folder name already exists.");
            } else {
                confirmed();
            }
        }
    }// GEN-LAST:event_inputNameKeyPressed

    private void confirmBTActionPerformed(java.awt.event.ActionEvent evt) {
       if (dao.isSheetNameExists(inputName.getText())) {
                JOptionPane.showMessageDialog(null, "Folder name already exists.");
            } else {

                confirmed();
            }
    }// GEN-LAST:event_confirmBTActionPerformed

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
            java.util.logging.Logger.getLogger(addFolder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addFolder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addFolder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addFolder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                addFolder dialog = new addFolder(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel countInput;
    private javax.swing.JTextField inputName;
    private javax.swing.JLabel label;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}