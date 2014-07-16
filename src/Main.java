import java.awt.*;
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

        boolean success = run(new Chapter("Ballard Memorial", 5));
        if(success) {
            System.out.println("Successful Run");
        } else {
            System.out.println("Run did not finish");
        }
    }

    /*
    Current refactor of the run loop. Adding error handling and better OOP support
    */
    private static boolean run(Chapter chapter) {
        FFAExcelFile readFile;
        try {
            readFile = new FFAExcelFile(new File("C:\\Users\\sheldon.burke@education.ky.gov\\Documents\\Registration Copy\\Week 5\\Ballard Memorial.xlsx"), chapter);
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
        try {
            startRobot(chapter);
        } catch (AWTException e) {
            return false;
        }
        readFile.closeInputStream();
        return true;
    }

    private static void startRobot(Chapter currentChapter) throws AWTException {
        FFARobot ffaRobot = new FFARobot(currentChapter);
        try {
            ffaRobot.start();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}