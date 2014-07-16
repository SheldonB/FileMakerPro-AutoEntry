import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;


/*
The following is an implementation of a bot, to ease the putting of
data in to the program FileMaker Pro.
C:\Users\sheldon.burke@education.ky.gov\Dropbox\Project\FFA_Filemaker_Bot
Author: Sheldon Burks
Started: 6/22/13
*/
public class Main {

    public static final String[] committees = {
                                                "Leadership Committee",
                                                "SAE Committee",
                                                "Public Relations Committee",
                                                "Alumni Relations Committee",
                                                "Conduct/Meetings Committee",
                                                "Earnings/Savings Committee",
                                                "Scholarship Committee",
                                                "Cooperation Committee",
                                                "Comm. Service Committee",
                                                "Recreation Committee"
                                              };

    public static void main(String[] args) throws Exception {
        //JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.getContentPane().add(new FFAMainPanel());
        //frame.pack();
        //frame.setVisible(true);

        Chapter currentChapter = new Chapter("Trimble Co.", 5);
        readExcelFile(currentChapter);
        FFARobot newRobot = new FFARobot(currentChapter);
        newRobot.start();
        //newRobot.getXYCord();
        //currentChapter.printChapter();
    }

    /*
    Method reads an Excel file into the database. Iterates through the excel spreadsheet
    and stores each in memory. The FFARobot class then takes and puts each one through
    keystrokes into the computer.

    This could be made a lot cleaner and modular, will work on that when I have the chance too.

    A feature needs to be added so that the first two lines of sheet 3 are not read and it does not read the entire file,
    only a certain part of it. Currently the entire sheet is being read.
    */
    private static void readExcelFile(Chapter currentChapter) throws Exception {
        InputStream file = new FileInputStream(
                new File("C:\\Users\\sheldon.burke@education.ky.gov\\Documents\\Registration Copy\\Week 5\\Trimble Co..xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(2);
        XSSFRow row;
        XSSFCell cell;

        Iterator rows = sheet.rowIterator();
        while(rows.hasNext()) {
            row = (XSSFRow)rows.next();
            if(row.getRowNum() == 0 || row.getRowNum() == 1) {
                continue;
            }
            else if(row.getRowNum() > 41) {
                break;
            }
            Iterator cells = row.cellIterator();
            Student tempStudent = new Student();

            while(cells.hasNext()) {
                cell = (XSSFCell) cells.next();
                if(cell.getColumnIndex() == 1) {
                    String[] splitNames = cell.getStringCellValue().split(" ");
                    if(splitNames.length == 3) {
                        tempStudent.setFirstName(splitNames[0] + " " + splitNames[1]);
                        tempStudent.setLastName(splitNames[2]);

                    }
                     else if(splitNames.length == 2) {
                        tempStudent.setFirstName(splitNames[0]);
                        tempStudent.setLastName(splitNames[1]);

                    }
                }
                else if(cell.getColumnIndex() == 2) {
                   tempStudent.setGender(cell.getStringCellValue());
                }
                else if(cell.getColumnIndex() == 3) {
                    for(String comm : committees) {
                        if(cell.getStringCellValue().equals(comm)) {
                            tempStudent.setIsCommittee(true);
                        }
                    }
                    tempStudent.setOffice(cell.getStringCellValue());
                }
                else if(cell.getColumnIndex() == 4 || cell.getColumnIndex() == 5
                        || cell.getColumnIndex() == 6 || cell.getColumnIndex() == 7) {
                    if(cell.getCellStyle().getFont().getBold()) {
                        if(cell.getStringCellValue().equals("Communication Skills")
                           && cell.getCellStyle().getFont().getUnderline() == 1) {
                            tempStudent.setSpecialInterestAM("Communication Skills B");
                        } else if(cell.getStringCellValue().equals("Communication Skills")) {
                             tempStudent.setSpecialInterestAM("Communication Skills A");
                        } else {
                           tempStudent.setSpecialInterestAM(cell.getStringCellValue());
                        }
                    } else if(cell.getCellStyle().getFont().getItalic()) {
                        if(cell.getStringCellValue().equals("Communication Skills")
                                && cell.getCellStyle().getFont().getUnderline() == 1) {
                           tempStudent.setSpecialInterestPM("Communication Skills B");

                        } else if(cell.getStringCellValue().equals("Communication Skills")) {
                            tempStudent.setSpecialInterestPM("Communication Skills A");

                        } else {
                            tempStudent.setSpecialInterestPM(cell.getStringCellValue());
                        }
                    }
                }
            }
            tempStudent.setGroupNum((int)(Math.random() * 8) + 1);
            currentChapter.addStudent(tempStudent);
        }

        /*
        sheet = workbook.getSheetAt(0);
        for(int i = 8; i < 12; i++) {
            CellReference cellReference = new CellReference("D" + i);
            row = sheet.getRow(cellReference.getRow());
            cell = row.getCell(cellReference.getCol());
            //System.out.println(cell.getStringCellValue());
            if(cell.getStringCellValue().equals("")) {
                continue;
            }
            else {
                currentChapter.addStudent(new Student());
            }
        }
        */

        file.close();
    }
}