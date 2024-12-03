package DAO;

import java.io.*;

import java.util.*;

import javax.swing.JOptionPane;

import todolist.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TDLdao {
    private HomePanel home;
    private static String FILE_PATH = "src/data/data.xlsx";
    private static String FILE_WARNDAY_PATH = "src/data/warnday.txt";

    public TDLdao() {

    }

    public TDLdao(HomePanel home) {
        this.home = home;
    }

    public void createFileFirstTime(String foldername) {
        // Create a new workbook (for .xlsx format)
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.getSheet(foldername);

        sheet = workbook.createSheet(foldername);
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("State");
        headerRow.createCell(1).setCellValue("Date");
        headerRow.createCell(2).setCellValue("Details");

        // Save the empty workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
            workbook.write(fileOut);
            System.out.println("[ToDoList] Created data file!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the workbook
            try {
                workbook.close();
            } catch (IOException e) {
                // e.printStackTrace();
            }
        }
    }

    public void createSheet(String folderName) {
        Workbook workbook = null;
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) { // Not found file = create new file
                createFileFirstTime(folderName);
            } else {
                // open file
                try (FileInputStream fis = new FileInputStream(file)) {
                    workbook = new XSSFWorkbook(fis);

                    Sheet sheet = workbook.getSheet(folderName);
                    if (sheet == null) {
                        sheet = workbook.createSheet(folderName); // สร้างชีตใหม่ถ้ายังไม่มี

                        // สร้างแถวหัวข้อ
                        Row headerRow = sheet.createRow(0);
                        headerRow.createCell(0).setCellValue("State");
                        headerRow.createCell(1).setCellValue("Date");
                        headerRow.createCell(2).setCellValue("Details");

                    }

                    // write file
                    try (FileOutputStream fileOut = new FileOutputStream(file)) {
                        workbook.write(fileOut);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void checkBoxUpdate(String checkState, Task t) {
        Workbook workbook = null;
        File file = new File(FILE_PATH);
        String folderName = t.getFolderName();

        try {
            if (!file.exists()) {
                System.out.println("File not found: " + FILE_PATH);
                return;
            }

            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheet(folderName);
                String[] data = t.getAllData();

                if (sheet == null) {
                    System.out.println("Sheet not found: " + folderName);
                    return;
                }

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {

                        String dateCell = row.getCell(1).getStringCellValue();
                        String detailsCell = row.getCell(2).getStringCellValue();

                        if (dateCell.equals(data[1]) && (detailsCell.equals(data[2]))) {
                            Cell checkboxCell = row.getCell(0);
                            if (checkboxCell != null) {
                                checkboxCell.setCellValue(checkState);
                                break;
                            }
                        }

                    }
                }

                try (FileOutputStream fos = new FileOutputStream(file)) {
                    workbook.write(fos);
                    // System.out.println("Checkbox state updated successfully.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void editSheet(String oldName, String newName) {
        Workbook workbook = null;

        // Get excel file
        File file = new File(FILE_PATH);

        try {
            if (!file.exists()) { // Not found file = create new file
                createFileFirstTime(newName);
            } else {

                // open file
                try (FileInputStream fis = new FileInputStream(file)) {
                    workbook = new XSSFWorkbook(fis);
                    int totalSheets = workbook.getNumberOfSheets();

                    for (int i = 0; i < totalSheets; i++) {
                        Sheet sheet = workbook.getSheetAt(i);
                        if (sheet.getSheetName().equals(oldName)) {
                            workbook.setSheetName(i, newName);
                            break;
                        }

                    }
                }

                // write file
                try (FileOutputStream fileOut = new FileOutputStream(file)) {
                    workbook.write(fileOut);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null)
                    workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void editData(String folderName, String oldDetails, String newDate, String newDetails,
            String newCheckbox) {
        Workbook workbook = null;
        File file = new File(FILE_PATH);

        try {
            if (!file.exists()) {
                System.out.println("File not found: " + FILE_PATH);
                return;
            }

            // Open the workbook
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheet(folderName);

                if (sheet == null) {
                    System.out.println("Sheet not found: " + folderName);
                    return;
                }

                // Loop through rows to find the task
                boolean taskFound = false;
                for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from 1 to skip header
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        String taskDetails = row.getCell(2).getStringCellValue();
                        if (taskDetails.equals(oldDetails)) {
                            // Edit the task's data
                            row.getCell(0).setCellValue(newCheckbox);
                            row.getCell(1).setCellValue(newDate);
                            row.getCell(2).setCellValue(newDetails);
                            taskFound = true;
                            break;
                        }
                    }
                }

                if (!taskFound) {
                    System.out.println("Task not found: " + oldDetails);
                }

            }

            // Save the workbook back to the file
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
                // System.out.println("Task updated successfully.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteData(Task T) {
        String folderName = T.getFolderName();
        File file = new File(FILE_PATH);
        String[] data = T.getAllData();
        // check
        try {
            if (!file.exists()) {
                System.out.println("File not found: " + FILE_PATH);
                return;
            }
            // open
            try (FileInputStream fis = new FileInputStream(file);
                    Workbook workbook = new XSSFWorkbook(fis)) {
                // sheet
                Sheet sheet = workbook.getSheet(folderName);
                if (sheet == null) {
                    System.out.println("Sheet not found: " + folderName);
                    return;
                }
                for (int i = sheet.getLastRowNum(); i > 0; i--) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        String cell1 = row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "";
                        String cell2 = row.getCell(1) != null ? row.getCell(1).getStringCellValue() : "";
                        String cell3 = row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "";

                        if (cell1.equals(data[0]) &&
                                cell2.equals(data[1]) &&
                                cell3.equals(data[2])) {
                            sheet.removeRow(row);
                            if (i < sheet.getLastRowNum()) {
                                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                            }
                        }
                    }
                }
                try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                    workbook.write(fos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteSheet(String sheetName) {
        Workbook workbook = null;
        File file = new File(FILE_PATH);
        try {
            if (file.exists()) { // check file exist
                // open file
                try (FileInputStream fis = new FileInputStream(file)) {
                    workbook = new XSSFWorkbook(fis);

                    // Check workbook
                    int sheetIndex = workbook.getSheetIndex(sheetName);
                    if (sheetIndex != -1) {
                        workbook.removeSheetAt(sheetIndex); // ลบชีต

                        // Write in EXCEL file
                        try (FileOutputStream fileOut = new FileOutputStream(file)) {
                            workbook.write(fileOut);
                        }
                        System.out.println("[ToDoList] Folder \"" + sheetName + "\" has been deleted.");
                    } else {
                        System.out.println("[ToDoList] Folder \"" + sheetName + "\" not found.");
                    }
                }
            } else {
                System.out.println("File not found: " + FILE_PATH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteFile() {
        File file = new File(FILE_PATH);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("[ToDoList] : Data file has been deleted!");
            } else {
                System.out.println("[ToDoList] : Failed to delete data.xlsx");
            }
        } else {
            System.out.println("[ToDoList] : File does not exist.");
        }

    }

    public void saveData(Task t) {
        String folderName = t.getFolderName();
        String details = "";
        String date = "";
        String checkbox = "";

        // Prepare data
        String[] data = t.getAllData();
        for (int i = 0; i < data.length; i++) {
            switch (i) {
                case 0: // date value
                    checkbox = data[0];
                    break;
                case 1: // details value
                    date = data[1];
                    break;
                case 2: // checkbox value
                    details = data[2];
                    break;
                default:
                    break;
            }
        }

        Workbook workbook = null;
        File file = new File(FILE_PATH);

        try {
            if (!file.exists()) {

            } else {
                try (FileInputStream fis = new FileInputStream(file)) {
                    workbook = new XSSFWorkbook(fis);
                    Sheet sheet = workbook.getSheet(folderName);

                    // update data
                    int lastRowNum = sheet.getLastRowNum();
                    Row newRow = sheet.createRow(lastRowNum + 1);
                    newRow.createCell(0).setCellValue(checkbox);
                    newRow.createCell(1).setCellValue(date);
                    newRow.createCell(2).setCellValue(details);
                }

                try (FileOutputStream fos = new FileOutputStream(file)) {
                    workbook.write(fos);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null)
                    workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadData() {
        List<String> data = new ArrayList<>(3);

        int headerRow = 0;
        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
                Workbook workbook = new XSSFWorkbook(fis)) {

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);

                System.out.println(String.format("[ToDoList] Loaded folder (%s).", sheet.getSheetName()));
                FolderPanel folderPanel = home.createFolderWithFolderPanel(sheet.getSheetName()); // createFolder
                for (int rowIndex = headerRow + 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row != null) {
                        Object[] rowData = new Object[sheet.getRow(0).getPhysicalNumberOfCells()];
                        int count = 0;
                        for (int colIndex = 0; colIndex < row.getPhysicalNumberOfCells(); colIndex++) {
                            Cell cell = row.getCell(colIndex);
                            if (cell != null) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        rowData[count] = cell.getStringCellValue();
                                        data.add(cell.getStringCellValue()); // out

                                        // create task;
                                        count++;
                                        if (data.size() == 3) {
                                            String isChecked = data.get(0);
                                            String date = data.get(1);
                                            String details = data.get(2);
                                            folderPanel.createTask(isChecked, date, details);
                                            data.removeAll(data);

                                        }
                                        break;
                                    default:
                                        System.out.print("N/A\n");
                                        count = 0;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File data.xlsx not found.");
        }
    }

    public void saveWarnDay(String day) {
        File f = new File(FILE_WARNDAY_PATH);
        if (f.exists()) {
            try {
                f.delete();
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter writer = new FileWriter(f, false)) { // false = overwrite
            writer.write(day);
            System.out.println("[ToDoList] Set warning in " + day + " day(s).");
            hideFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int loadWarnDay() {
        int warnDay = 1; // default value

        if (!Files.exists(Paths.get(FILE_WARNDAY_PATH))) {
            // System.out.println("Warning day file not found. Returning default value: " +
            // warnDay);
            return warnDay;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_WARNDAY_PATH))) {
            String line;
            if ((line = reader.readLine()) != null) {
                warnDay = Integer.parseInt(line.trim());
            }
            System.out.println("[ToDoList] Loaded warning day.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // System.out.println("Error: Invalid number format in the warning day file.");
        }

        return warnDay;
    }

    private void hideFile() {
        try {
            java.nio.file.Files.setAttribute(Paths.get(FILE_WARNDAY_PATH), "dos:hidden", true); // set to hidden file
            // System.out.println("File hidden successfully: " + FILE_WARNDAY_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSheetNameExists(String sheetName) {
        Workbook workbook;
        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH))) {
            workbook = new XSSFWorkbook(fis);

            // วนลูปตรวจสอบว่าชื่อ sheet ที่ต้องการมีอยู่หรือไม่
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                if (workbook.getSheetAt(i).getSheetName().equals(sheetName)) {
                    workbook.close();
                    return true; // พบชื่อ sheet
                }
            }

        } catch (IOException e) {
            // e.printStackTrace();
        }
        return false; // ไม่พบชื่อ sheet
    }
}
