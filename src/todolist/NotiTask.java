/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package todolist;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author hp
 */
public class NotiTask extends javax.swing.JPanel {
    private Task task;
    /**
     * Creates new form notiTask
     */
    public NotiTask() {
        initComponents();
    }
    
    public NotiTask(Task t) {
        this();
        this.task = t;
        setLabel();
    }
    
    public void setLabel(){
        String date = task.getFormatDate();
        String details = task.getDetails();
        String name = task.getFolderName();
        
        detailsLB.setText(details);
        dateLB.setText(date);
        linkBT.setText(name);
       
    }
    
    public JPanel getThisNotiTask(){
        return this;
    }
    
    public Task getTask(){
        return this.task;
    }
    
    public void setLinkButtonName(String name){
        linkBT.setText(name);
    }
    
    public void setDateLB(String date){
        dateLB.setText(date);
    }
    
    public void setDetailsLB(String details){
        detailsLB.setText(details);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        detailsLB = new javax.swing.JLabel();
        dateLB = new javax.swing.JLabel();
        linkBT = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 145, 0));
        setMaximumSize(new java.awt.Dimension(600, 50));
        setMinimumSize(new java.awt.Dimension(600, 50));

        detailsLB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        detailsLB.setText("Details");

        dateLB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dateLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateLB.setText("Date");

        linkBT.setBackground(new java.awt.Color(255, 145, 0));
        linkBT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        linkBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/link.png"))); // NOI18N
        linkBT.setBorderPainted(false);
        linkBT.setFocusPainted(false);
        linkBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(dateLB, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(detailsLB, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(linkBT, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linkBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(detailsLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void linkBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkBTActionPerformed
        FolderPanel folder = task.getFolder();
        String cardname = folder.getFolderName();
        ToDoList.cardPanel.add(folder, cardname);
        CardLayout card = (CardLayout) (ToDoList.cardPanel.getLayout());
        card.show(ToDoList.cardPanel, cardname);
    }//GEN-LAST:event_linkBTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateLB;
    private javax.swing.JLabel detailsLB;
    private javax.swing.JButton linkBT;
    // End of variables declaration//GEN-END:variables
}