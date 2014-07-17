import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;


public class FFAExcelFile {
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private InputStream file;
    private Iterator rows;
    private Iterator cells;
    private Chapter currentChapter;
    private Student student;

    public FFAExcelFile(File excelFile, Chapter currentChapter) throws IOException {
        this.file = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(this.file);
        this.sheet = workbook.getSheetAt(2);
        this.rows = sheet.rowIterator();
        this.currentChapter = currentChapter;
        this.student = new Student();
    }

    public boolean hasNextRow() {
        return this.rows.hasNext();
    }

    public void setRow() {
        this.row = (XSSFRow)this.rows.next();
    }

    public boolean isValidRow() {
        if(this.row.getRowNum() == 0 || this.row.getRowNum() == 1) {
            return false;
        } else if(this.row.getRowNum() > 41) {
            return false;
        } else {
            return true;
        }
    }

    public void setCellIterator() {
        this.cells = this.row.cellIterator();
    }

    public boolean hasNextCell() {
        return this.cells.hasNext();
    }

    public void setCell() {
        this.cell = (XSSFCell)cells.next();
    }

    public void setName() {
        String[] splitNames = cell.getStringCellValue().split(" ");
        if(splitNames.length == 3) {
            this.student.setFirstName(splitNames[0] + " " + splitNames[1]);
            this.student.setLastName(splitNames[2]);
        } else if(splitNames.length == 2) {
            this.student.setFirstName(splitNames[0]);
            this.student.setLastName(splitNames[1]);
        }
    }

    public void setGender() {
        this.student.setGender(cell.getStringCellValue());
    }

    public void setOffice() {
        for(String comm : Main.committees) {
            if(cell.getStringCellValue().equals(comm)) {
                this.student.setIsCommittee(true);
            }
        }
        this.student.setOffice(cell.getStringCellValue());
    }

    public void setSpecialInterest() {
        if(this.cell.getCellStyle().getFont().getBold()) {
            this.setSpecialInterestAM();
        } else if(cell.getCellStyle().getFont().getItalic()) {
            this.setSpecialInterestPM();
        }
    }

    private void setSpecialInterestAM() {
        if(this.cell.getStringCellValue().equals("Communication Skills")
                && this.cell.getCellStyle().getFont().getUnderline() == 1) {
            this.student.setSpecialInterestAM("Communication Skills B");
        } else if(this.cell.getStringCellValue().equals("Communication Skills")) {
            this.student.setSpecialInterestAM("Communication Skills A");
        } else {
            this.student.setSpecialInterestAM(this.cell.getStringCellValue());
        }
    }

    private void setSpecialInterestPM() {
        if(this.cell.getStringCellValue().equals("Communication Skills")
                && this.cell.getCellStyle().getFont().getUnderline() == 1) {
            this.student.setSpecialInterestPM("Communication Skills B");
        } else if(this.cell.getStringCellValue().equals("Communication Skills")) {
            this.student.setSpecialInterestPM("Communication Skills A");

        } else {
            this.student.setSpecialInterestPM(cell.getStringCellValue());
        }
    }

    public void setGroupNumber() {
        this.student.setGroupNum((int)(Math.random() * 8) + 1);
    }

    public void addStudent() {
        this.currentChapter.addStudent(this.student);
        this.student = new Student();
    }
    public int getCellColumn() {
        return this.cell.getColumnIndex();
    }

    public Chapter getCurrentChapter() {
        return this.currentChapter;
    }

    public void closeInputStream() {
        try {
            this.file.close();
        } catch(IOException e) {
            System.out.println("File could not be closed");
        }
    }
}
