package todolist;

import java.awt.*;
import javax.swing.*;
import todolist.ToDoList;

import DAO.TDLdao;
import java.util.ArrayList;

public class HomePanel extends javax.swing.JPanel {

    private TDLdao dao = new TDLdao();
    private ArrayList<FolderLine> FDLine;
    private ArrayList<FolderPanel> FDPanel;
    private ArrayList<NotiTask> notitasks = new ArrayList<>();
    public final int SIZE = 20;
    public static int warnDay = 1;
    static int currentFolder = 0;

    public HomePanel() {
        initComponents();

        // create fields
        FDLine = new ArrayList<FolderLine>(SIZE);
        FDPanel = new ArrayList<FolderPanel>(SIZE);

        // increase speed of scrolling
        JScrollBar FDscroll = folderScroll.getVerticalScrollBar();
        FDscroll.setUnitIncrement(16);

        JScrollBar NTscroll = notiScroll.getVerticalScrollBar();
        NTscroll.setUnitIncrement(16);

        // laod warn day
        int wday = dao.loadWarnDay();
        if (wday == 0) {
            setWarnDay(1);
        } else {

            setWarnDay(wday);
        }
    }

    public FolderPanel getFolder(int index) {
        return FDPanel.get(index);
    }

    public ArrayList<FolderPanel> getAllFolders() {
        return FDPanel;
    }

    public ArrayList<FolderLine> getAllFolderLine() {
        return FDLine;
    }

    public FolderLine getFolderLine(int index) {
        return FDLine.get(index);
    }

    public JLabel getFolderListLabel() {
        return countFolderLB;
    }

    public void createFolder(String name) {
        ++currentFolder;
        if (currentFolder == 1) {
            dao.createFileFirstTime(name);
        } else {
            dao.createSheet(name);
        }

        // Create folder and folderLine
        FolderPanel folder = new FolderPanel(this, name);
        FolderLine fdLine = new FolderLine(this, name, folder);

        // add to ArrayList
        FDLine.add(fdLine);
        FDPanel.add(folder);

        // Add to Panel
        folderArea.add(fdLine);
        UpdatePanel();
    }

    public FolderPanel createFolderWithFolderPanel(String name) {
        ++currentFolder;
        if (currentFolder == 1) {
            dao.createFileFirstTime(name);
        } else {
            dao.createSheet(name);
        }

        // Create folder and folderLine
        FolderPanel folder = new FolderPanel(this, name);
        FolderLine fdLine = new FolderLine(this, name, folder);

        // add to ArrayList
        FDLine.add(fdLine);
        FDPanel.add(folder);

        // Add to Panel
        folderArea.add(fdLine);
        UpdatePanel();
        return folder;
    }

    public void deleteFolder(FolderLine FdLine, FolderPanel folder) {
        --currentFolder;
        if (currentFolder == 0) {
            dao.deleteFile();
        }

        // delete object
        FDPanel.remove(folder);
        FDLine.remove(FdLine);

        // delete in Panel
        folderArea.remove(FdLine);
        UpdatePanel();
    }

    public void UpdatePanel() {
        getFolderListLabel().setText(currentFolder + " of 20 folders");
        folderArea.revalidate(); // update
        folderArea.repaint();
    }

    public JPanel getFolderArea() {
        return folderArea;
    }

    public JPanel getNotiArea() {
        return notiArea;
    }

    public void setWarnDay(int day) {
        this.warnDay = day;
    }

    public int getWarnDay() {
        return warnDay;
    }

    public void addtoNotiArea(NotiTask Ntask) {
        addIfNotExists(notitasks, Ntask);
        // Update
        notiArea.revalidate();
        notiArea.repaint();

    }

    public void addIfNotExists(ArrayList<NotiTask> list, NotiTask Ntask) {
        boolean exists = false;

        // ตรวจสอบว่ามี NotiTask ที่เหมือนกันหรือไม่
        for (NotiTask NT : list) {
            if (NT.equals(Ntask)) { // พบว่า obj ซ้ำ
                exists = true;
                break;
            }
        }

        // ถ้าไม่ซ้ำ เพิ่มลงใน list และ notiArea
        if (!exists) {
            list.add(Ntask);
            notiArea.add(Ntask);
        }

        // Update Panel
        notiArea.revalidate();
        notiArea.repaint();

    }

    public void removefromNotiArea(NotiTask Ntask) {
        // ตรวจสอบว่ามี object ที่ต้องการหรือไม่
        if (notitasks.contains(Ntask)) {

            // removing NotiTask from Arraylist and NotiArea
            notitasks.remove(Ntask);
            notiArea.remove(Ntask);

            // Update Panel
            notiArea.revalidate();
            notiArea.repaint();

           
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        countFolderLB = new javax.swing.JLabel();
        dateDisplay1 = new todolist.DateDisplay();
        notiTitleLB = new javax.swing.JLabel();
        folderTitleLB = new javax.swing.JLabel();
        newFolder = new javax.swing.JButton();
        folderScroll = new javax.swing.JScrollPane();
        folderArea = new javax.swing.JPanel();
        setwarnBT = new javax.swing.JButton();
        notiScroll = new javax.swing.JScrollPane();
        notiArea = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 189, 89));
        setMaximumSize(new java.awt.Dimension(750, 800));
        setMinimumSize(new java.awt.Dimension(750, 800));
        setPreferredSize(new java.awt.Dimension(750, 800));

        countFolderLB.setBackground(new java.awt.Color(0, 74, 173));
        countFolderLB.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        countFolderLB.setForeground(new java.awt.Color(0, 74, 173));
        countFolderLB.setText(currentFolder + " of 20 Folders");

        notiTitleLB.setBackground(new java.awt.Color(255, 255, 255));
        notiTitleLB.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        notiTitleLB.setForeground(new java.awt.Color(255, 255, 255));
        notiTitleLB.setText("Notifications");

        folderTitleLB.setBackground(new java.awt.Color(255, 255, 255));
        folderTitleLB.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        folderTitleLB.setForeground(new java.awt.Color(255, 255, 255));
        folderTitleLB.setText("Folders");

        newFolder.setBackground(new java.awt.Color(255, 49, 49));
        newFolder.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        newFolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus.png"))); // NOI18N
        newFolder.setText("Make new folder");
        newFolder.setBorder(null);
        newFolder.setFocusPainted(false);
        newFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFolderActionPerformed(evt);
            }
        });

        folderScroll.setBorder(null);
        folderScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        folderArea.setBackground(new java.awt.Color(255, 255, 255));
        folderArea.setLayout(new java.awt.GridLayout(20, 0, 0, 5));
        folderScroll.setViewportView(folderArea);

        setwarnBT.setBackground(new java.awt.Color(255, 189, 89));
        setwarnBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settings.png"))); // NOI18N
        setwarnBT.setBorder(null);
        setwarnBT.setFocusPainted(false);
        setwarnBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setwarnBTActionPerformed(evt);
            }
        });

        notiScroll.setBorder(null);
        notiScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        notiScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        notiArea.setBackground(new java.awt.Color(255, 243, 193));
        notiArea.setLayout(new java.awt.GridLayout(999, 0, 0, 5));
        notiScroll.setViewportView(notiArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(notiScroll)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout
                                                .createSequentialGroup()
                                                .addComponent(notiTitleLB)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(setwarnBT, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(dateDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 221,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(folderTitleLB)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(newFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 168,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(countFolderLB)
                                                .addGap(6, 6, 6))
                                        .addComponent(folderScroll, javax.swing.GroupLayout.Alignment.LEADING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
                                .addGap(67, 67, 67)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(37, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(setwarnBT, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(notiTitleLB, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(dateDisplay1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(notiScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 264,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(folderTitleLB)
                                        .addComponent(countFolderLB)
                                        .addComponent(newFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addComponent(folderScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 260,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)));

        getAccessibleContext().setAccessibleName("panel");
    }// </editor-fold>//GEN-END:initComponents

    private void setwarnBTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_setwarnBTActionPerformed
        new setWarnDay(null, true, this).show();
    }// GEN-LAST:event_setwarnBTActionPerformed

    private void newFolderActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newFolderActionPerformed
        if (currentFolder == 20) {
            JOptionPane.showMessageDialog(null, "You have reached the limit for adding folder.",
                    "To Do List",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            new addFolder(null, true, this).show();
        }
    }// GEN-LAST:event_newFolderActionPerformed

    private void testBTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_testBTActionPerformed
        ToDoList.cardPanel.add(new FolderPanel(this, "TEST FOLDER"), "Folder");
        CardLayout card = (CardLayout) (ToDoList.cardPanel.getLayout());
        card.show(ToDoList.cardPanel, "Folder");
    }// GEN-LAST:event_testBTActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel countFolderLB;
    private todolist.DateDisplay dateDisplay1;
    private javax.swing.JPanel folderArea;
    private javax.swing.JScrollPane folderScroll;
    private javax.swing.JLabel folderTitleLB;
    private javax.swing.JButton newFolder;
    private javax.swing.JPanel notiArea;
    private javax.swing.JScrollPane notiScroll;
    private javax.swing.JLabel notiTitleLB;
    private javax.swing.JButton setwarnBT;
    // End of variables declaration//GEN-END:variables
}