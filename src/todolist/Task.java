package todolist;

import DAO.TDLdao;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.JOptionPane;

public class Task extends javax.swing.JPanel {

    private NotiTask NTask;
    private HomePanel home;
    private FolderPanel fdPanel;
    private String folderName;
    private Date date;
    private String dateFormat; // Date data
    private String details; // Detail data
    private boolean isChecked; // checkbox data
    private boolean isWarning = false;
    private boolean isLated;
    private Color green = new Color(168, 235, 140);
    private Color original = new Color(204, 204, 204);
    private Color red = new Color(246, 114, 114);
    private Color yellow = new Color(255, 145, 0);
    private TDLdao dao = new TDLdao();

    /**
     * Creates new form Task
     */
    public Task() {
        initComponents();

    }

    public Task(Date date, String details, FolderPanel fdPanel) {
        this();

        this.fdPanel = fdPanel;
        this.home = fdPanel.getHomepage();
        this.date = date;
        String d = fdPanel.formatDate(date);
        setNewData(d, details);
        setDataToBT();
        this.NTask = new NotiTask(this);
        TaskUpdate();

    }

    public Task(String isChecked, String date, String details, FolderPanel fdPanel) { // for load Obj
        this(fdPanel.changetoDateClass(date), details, fdPanel);
        this.isChecked = Boolean.parseBoolean(isChecked);
        checkBox.setSelected(this.isChecked);
        TaskUpdate();
        this.NTask = new NotiTask(this);
        doNotiTask(isWarning, this.isChecked);

    }

    public FolderPanel getFolder() {
        return this.fdPanel;
    }

    public NotiTask getNTask() {
        return NTask;
    }

    public void setDataToBT() {
        dateBT.setText(dateFormat);
        detailsBT.setText(details);
    }

    public void editDataToBT(String dateFormat, String details) {
        setNewData(dateFormat, details);
        dateBT.setText(dateFormat);
        detailsBT.setText(details);
    }

    public void setNewData(String date, String details) {
        this.dateFormat = date;
        this.details = details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }

    public void deleteTask() {
        dao.deleteData(this);
        // delete this Task
        fdPanel.delThisTask(this);

        // delete task on JScroll
        fdPanel.getTaskArea().remove(this);
        // Update
        fdPanel.UpdatePanel();
        
    }

    public void setFormatDate(String date) {
        dateFormat = date;
    }

    public String getFormatDate() {
        return dateFormat;
    }

    public void setDate(Date newDate) {
        this.date = newDate;
    }

    public String[] getAllData() {
        String[] data = { Boolean.toString(isChecked), dateFormat, details };
        return data;
    }

    public String getCheckState() {
        return Boolean.toString(isChecked);
    }

    public String getFolderName() {
        return fdPanel.getFolderName();
    }

    private Integer prepareDate() {

        String s = dateFormat.replace("/", "");

        String d = s.substring(0, 2);
        String m = s.substring(2, 4);
        String y = s.substring(4);
        if (d.length() < 1) {
            d = "0" + d;
        }
        return Integer.parseInt(y + m + d);
    }

    public void lateState() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate prepareDate = LocalDate.parse(Integer.toString(prepareDate()), formatter);
        LocalDate today = LocalDate.parse(Integer.toString(DateDisplay.TODAY), formatter);

        // Changing red and green color
        if (today.isAfter(prepareDate)) { // Late
            changeColor(red);
            isLated = true;
        } else { // Not Late
            changeColor(original);
            isLated = false;
        }

    }

    public void changeColor(Color color) {
        dateBT.setBackground(color);
        checkBox.setBackground(color);
        detailsBT.setBackground(color);
        setBackground(color);
    }

    public void TaskUpdate() {

        if (checkBox.isSelected()) { // checked
            isChecked = true;
            changeColor(green);
            dao.checkBoxUpdate(Boolean.toString(isChecked), this);

        } else { // unchecked
            isChecked = false;
            lateState();
            checkWarning();

            dao.checkBoxUpdate(Boolean.toString(isChecked), this);

        }

    }

    public void checkWarning() {

        long result = calDay(Integer.toString(prepareDate()), Integer.toString(DateDisplay.TODAY));

        if (!isLated && !isChecked) { // uncheck
            if (result == home.getWarnDay() ) { // Warn follow by Warned Day
                changeColor(yellow);
                isWarning = true;
            } else if (result == 0 ) { // today
                changeColor(yellow);
                isWarning = true;
            } else if (result - home.getWarnDay() < 1) { // closing Warned day
                changeColor(yellow);
                isWarning = true;
            } else { // lated
                isWarning = false;
            }
        } else { // lated
            isWarning = false;
        }
    }

    public long calDay(String date1, String date2) {
        //  Date formatted
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // แปลง String เป็น LocalDate
        LocalDate d1 = LocalDate.parse(date1, formatter);
        LocalDate d2 = LocalDate.parse(date2, formatter);

        // date difference
        long diff = ChronoUnit.DAYS.between(d2, d1);

        return diff;
    }

    public boolean getIsWarning() {
        return this.isWarning;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public void doNotiTask(boolean warning, boolean checked) {
        if (warning && !checked) {
            NotiTask temp = new NotiTask(this);
            this.NTask = temp;
            home.addtoNotiArea(temp);
        } else if (warning && checked) {
            home.removefromNotiArea(NTask);
        }
    }

    public void UpdateNotiPanel() {
        NTask.setLinkButtonName(fdPanel.getFolderName());
        NTask.setDetailsLB(details);
        NTask.setDateLB(dateFormat);
        TaskUpdate();
        doNotiTask(isWarning, this.isChecked);
        home.getNotiArea().revalidate();
        home.getNotiArea().repaint();
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

        checkBox = new javax.swing.JCheckBox();
        dateBT = new javax.swing.JButton();
        detailsBT = new javax.swing.JButton();
        delTaskBT = new javax.swing.JButton();
        editTaskBT = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(600, 50));
        setMinimumSize(new java.awt.Dimension(600, 50));

        checkBox.setBackground(new java.awt.Color(204, 204, 204));
        checkBox.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        checkBox.setBorder(null);
        checkBox.setFocusPainted(false);
        checkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkBox.setMargin(new java.awt.Insets(5, 5, 5, 5));
        checkBox.setPreferredSize(new java.awt.Dimension(100, 100));
        checkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        dateBT.setBackground(new java.awt.Color(204, 204, 204));
        dateBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dateBT.setText("Date");
        dateBT.setBorder(null);
        dateBT.setBorderPainted(false);
        dateBT.setFocusPainted(false);
        dateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateBTActionPerformed(evt);
            }
        });

        detailsBT.setBackground(new java.awt.Color(204, 204, 204));
        detailsBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        detailsBT.setText("Details");
        detailsBT.setAlignmentX(0.5F);
        detailsBT.setBorder(null);
        detailsBT.setBorderPainted(false);
        detailsBT.setFocusPainted(false);
        detailsBT.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        detailsBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsBTActionPerformed(evt);
            }
        });

        delTaskBT.setBackground(new java.awt.Color(255, 49, 49));
        delTaskBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/trash-bin.png"))); // NOI18N
        delTaskBT.setBorder(null);
        delTaskBT.setBorderPainted(false);
        delTaskBT.setFocusPainted(false);
        delTaskBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delTaskBTActionPerformed(evt);
            }
        });

        editTaskBT.setBackground(new java.awt.Color(126, 217, 87));
        editTaskBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit-text.png"))); // NOI18N
        editTaskBT.setBorder(null);
        editTaskBT.setBorderPainted(false);
        editTaskBT.setFocusPainted(false);
        editTaskBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTaskBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(checkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateBT, javax.swing.GroupLayout.PREFERRED_SIZE, 126,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(detailsBT, javax.swing.GroupLayout.PREFERRED_SIZE, 241,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(editTaskBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(delTaskBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(33, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(checkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(detailsBT, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delTaskBT, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(editTaskBT, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(dateBT, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    private void detailsBTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_detailsBTActionPerformed
        new editTask(null, true, fdPanel, this, date).show();
    }// GEN-LAST:event_detailsBTActionPerformed

    private void checkBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_checkBoxActionPerformed
        UpdateNotiPanel();
    }// GEN-LAST:event_checkBoxActionPerformed

    private void delTaskBTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_delTaskBTActionPerformed

        int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete this task?", "To Do List ",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            home.removefromNotiArea(NTask);
            deleteTask();
        }

    }// GEN-LAST:event_delTaskBTActionPerformed

    private void dateBTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_dateBTActionPerformed
        new editTask(null, true, fdPanel, this, date).show();
    }// GEN-LAST:event_dateBTActionPerformed

    private void editTaskBTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_editTaskBTActionPerformed
        new editTask(null, true, fdPanel, this, date).show();
    }// GEN-LAST:event_editTaskBTActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBox;
    private javax.swing.JButton dateBT;
    private javax.swing.JButton delTaskBT;
    private javax.swing.JButton detailsBT;
    private javax.swing.JButton editTaskBT;
    // End of variables declaration//GEN-END:variables
}
