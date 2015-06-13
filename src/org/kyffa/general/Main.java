package org.kyffa.general;

import org.kyffa.gui.FFAMainPanel;
import org.kyffa.models.Chapter;
import org.kyffa.models.FFAExcelFile;
import org.kyffa.models.Student;

import javax.swing.*;
import java.io.File;
import java.io.IOException;


/*
The following is an implementation of a bot, to ease the putting of
data in to the program FileMaker Pro.
C:\Users\sheldon.burke@education.ky.gov\Dropbox\Project\FFA_Filemaker_Bot
Author: Sheldon Burks
Started: 6/22/13
*/
public class Main {
    private static JFrame frame;

    public static final String[] committees = {
                                                "Chapter Dev.",
                                                "Community Dev.",
                                                "Student Dev."
                                              };

    public static final String[] specialInterestClasses = {
                                                            "Ag Careers",
                                                            "Conservation",
                                                            "Alt. Energy/Fuels",
                                                            "Going to College",
                                                            "Parliamentary Pro.",
                                                            "Ag Advocacy/PR",
                                                            "SAE",
                                                            "The Great Outdoors",
                                                            "Auctioneering",
                                                            "Communication Skills A",
                                                            "Communication Skills B",
                                                            "Reasons",
                                                            "Archery (Beginner)",
                                                            "Archery (Advanced)",
                                                            "Canoeing",
                                                            "Fishing",
                                                            "Tractor Driving"
                                                          };

    public static void main(String[] args) throws Exception {

        frame = new JFrame("KY FFA LTC FileMaker Pro Bot");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FFAMainPanel());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        //FFARobot ffaRobot = new FFARobot(new Chapter("test", 1));
        //while(true) {
        //    ffaRobot.getXYCord();
        //}
        //test cases
        //run(new Chapter("Ballard Memorial H.S.", 5), new File("C:\\Users\\sheldon.burke@education.ky.gov\\Documents\\Registration Copy\\Week 5\\Ballard Memorial.xlsx"));
    }

    /*
    Current refactor of the run loop. Adding error handling and better OOP support
    */
    public static boolean run(Chapter chapter, File excelDoc) {

        FFAExcelFile readFile;
        try {
            readFile = new FFAExcelFile(excelDoc, chapter);
        } catch (IOException e) {
            return false;
        }

        while(readFile.hasNextRow()) {
            readFile.setRow();
            if(!readFile.isValidRow()) {
                continue;
            }
            readFile.setCellIterator();
            while(readFile.hasNextCell()) {
                readFile.setCell();
                switch (readFile.getCellColumn()) {
                    case 1:
                        readFile.setName();
                        break;
                    case 2:
                        readFile.setGender();
                        break;
                    case 3:
                        readFile.setOffice();
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        readFile.setSpecialInterest();
                        break;
                }
            }
            readFile.setGroupNumber();
            readFile.addStudent();
        }
        //chapter.printChapter();
        readFile.addAdvisors();
        readFile.addMaleChaperones();
        readFile.addFemaleChaperones();
        readFile.addMaleChildren();
        readFile.addFemaleChildren();

        startRobot(chapter);
        readFile.closeInputStream();


        return true;
    }

    private static void startRobot(Chapter currentChapter) {
        FFARobot ffaRobot = new FFARobot(currentChapter);
        int aOrBCounter = 0;
        for(Student student : currentChapter.getStudents()) {
            if(student.getFirstName() == null) {
                continue;
            }

            ffaRobot.newRecord();
            ffaRobot.delayOneSec();
            ffaRobot.setWeek();
            ffaRobot.setChapter();
            if(student.getIsCommittee()) {
                ffaRobot.setCommittee(student.getOffice(), aOrBCounter);
                aOrBCounter++;
            } else {
                ffaRobot.setOffice(student.getOffice());
            }
            ffaRobot.setFirstName(student.getFirstName());
            ffaRobot.setLastName(student.getLastName());
            if(!student.getOffice().equals("Advisor") || !student.getOffice().equals("Chaperone")
                    || !student.getOffice().equals("Child")) {
                ffaRobot.setGender(student.getGender());
                ffaRobot.setSpecialInterestClasses(student.getSpecialInterestAM(), student.getSpecialInterestPM());
            }
            ffaRobot.setGroupNum(Integer.toString(student.getGroupNum()));
            ffaRobot.delayOneSec();
        }
        frameVisible(true);
    }

    public static void frameVisible(boolean visible) {
        frame.setVisible(visible);
    }
}