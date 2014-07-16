import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

//Implementation of the Robot Class to put records from
//excel document into the FileMaker Pro software
//Created: 6/24/14
//Author: Sheldon Burks

public class FFARobot {
    private Chapter chapter;
    private Robot ffaRobot;

    public FFARobot(Chapter chapter) throws AWTException {
        this.chapter = chapter;
        this.ffaRobot = new Robot();
    }

    //This method starts the Robot process.
    //There is a cycle throughout the ArrayList<Student>
    //that will actually put every member in the computer
    public void start() throws Exception {
       for(int i = 0; i < this.chapter.getSize(); i++){
           if(this.chapter.getStudents().get(i).getFirstName() == null) {
               break;
            }
            this.newRecord();
            this.ffaRobot.delay(1000);
            this.setWeek(this.chapter.getWeek());
            this.setChapter();
            if(this.chapter.getStudents().get(i).getIsCommittee()) {
                this.setCommittee(this.chapter.getStudents().get(i).getOffice());
            }
            else {
                this.setOffice(this.chapter.getStudents().get(i).getOffice());
            }
            this.setFirstName(this.chapter.getStudents().get(i).getFirstName());
            this.setLastName(this.chapter.getStudents().get(i).getLastName());
            this.setGender(this.chapter.getStudents().get(i).getGender());
            this.setSpecialInterestClasses(this.chapter.getStudents().get(i).getSpecialInterestAM(),
                                           this.chapter.getStudents().get(i).getSpecialInterestPM());
            this.setGroupNum(Integer.toString(this.chapter.getStudents().get(i).getGroupNum()));
            this.ffaRobot.delay(1000);
       }
    }

    /*
    This method clicks on the New Record Button in FileMaker Pro.
    */
    public void newRecord() {
        this.ffaRobot.mouseMove(570, 101);
        this.ffaRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        this.ffaRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    /*
    This method takes the week provided in the chapter
    object and uses the Java robot class to select the proper
    week within FileMaker Pro.
    */
    public void setWeek(int week) throws Exception {
        this.ffaRobot.mouseMove(176, 153);
        this.pressAndRelease();
        this.typeCharacter(Integer.toString(week));
    }

    public void setChapter() throws Exception {
        this.ffaRobot.mouseMove(308, 176);
        this.pressAndRelease();
        this.typeString(this.chapter.getChapterName());
    }

    public void setOffice(String office) throws Exception {
        this.ffaRobot.mouseMove(293, 196);
        this.pressAndRelease();
        this.pressAndRelease();
        if(office.equals("Vice-Pres")) {
            office = "Vice President";
        }
        else if(office.equals("Aux Officer")) {
            office = "Auxillary";
        }
        this.typeString(office);
    }

    public void setFirstName(String firstName) throws Exception {
        this.ffaRobot.mouseMove(191, 258);
        this.pressAndRelease();
        this.typeString(firstName);

    }

    public void setLastName(String lastName) throws Exception {
        this.ffaRobot.mouseMove(320, 258);
        this.pressAndRelease();
        this.typeString(lastName);
    }

    public void setGender(String gender)throws Exception {
        this.ffaRobot.mouseMove(175, 278);
        this.pressAndRelease();
        this.pressAndRelease();
        if(gender.equalsIgnoreCase("M")) {
            gender = "Male";
        }
        else {
            gender = "Female";
        }
        this.typeString(gender);
    }

    public void setSpecialInterestClasses(String amClass, String pmClass) throws Exception {
        this.ffaRobot.mouseMove(173, 300);
        this.pressAndRelease();
        this.typeCharacter(this.determineClassLetter(amClass));
        this.ffaRobot.mouseMove(172, 321);
        this.pressAndRelease();
        this.typeCharacter(this.determineClassLetter(pmClass));
    }

    public void setGroupNum(String groupNum) throws Exception {
        this.ffaRobot.mouseMove(173, 342);
        this.pressAndRelease();
        this.pressAndRelease();
        this.typeCharacter(groupNum);
    }

    /*
    Method sets the committee class. The method checks the String value of
    committee and compares it to the corresponding classes. There is an
    array of String in the class Main that contains each committee.

    Committee Class One:
        - Earnings, Savings, and Investments
        - Scholarship
    Committee Class Two:
        - Public Relations
        - Investments
        - Scholarship
    Committee Class Three:
        - SAE
        - Leadership
    Committee Class Four:
        - Coop
        - Community Service
        - Recreation
    */
    public void setCommittee(String committee) throws Exception {
        this.ffaRobot.mouseMove(306, 238);
        this.pressAndRelease();
        this.pressAndRelease();
        if(committee.equals(Main.committees[5]) || committee.equals(Main.committees[6])) {
            this.typeString("Committeemen Class 1");
        }
        else if(committee.equals(Main.committees[2]) || committee.equals(Main.committees[3]) || committee.equals(Main.committees[4])) {
            this.typeString("Committeemen Class 2");
        }
        else if(committee.equals(Main.committees[0]) || committee.equals(Main.committees[1])) {
            this.typeString("Committeemen Class 3");
        }
        else if(committee.equals(Main.committees[7]) || committee.equals(Main.committees[8]) || committee.equals(Main.committees[9])) {
            this.typeString("Committeemen Class 4");
        }
    }

    /*
    Each Special interest class has a corresponding letter to
    it that must be entered into FileMaker Pro. The following method
    returns the letter that must go with each. This could possibly be
    implemented with proper Hash Mapping.
    */
    private String determineClassLetter(String stuClass) {
        switch(stuClass) {
            case "Ag Careers":
                return "A";
            case "Conservation":
                return "B";
            case "Alt. Energy/Fuels":
                return "C";
            case "Going to College":
                return "D";
            case "Parliamentary Pro.":
                return "E";
            case "Ag Advocacy/PR":
                return "F";
            case "SAE":
                return "G";
            case "The Great Outdoors":
                return "H";
            case "Auctioneering":
                return "I";
            case "Communication Skills A":
                return "J";
            case "Reasons":
                return "K";
            case "Archery (Beginner)":
                return "L";
            case "Archery (Advanced)":
                return "M";
            case "Canoeing":
                return "N";
            case "Fishing":
                return "O";
            case "Tractor Driving":
                return "P";
            case "Communication Skills B":
                return "Q";
        }
        return "";
    }

    private void pressAndRelease() {
        this.ffaRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        this.ffaRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void typeString(String stringToPrint) throws Exception {
        for(int i = 0; i < stringToPrint.length(); i++) {
            this.typeCharacter((Character.toString(stringToPrint.charAt(i))));
        }
    }

    private void typeCharacter(String currentChar) throws Exception {
        String variableName;
        if(Character.isUpperCase(currentChar.charAt(0))) {
            this.ffaRobot.keyPress(KeyEvent.VK_SHIFT);
        }

        switch (currentChar) {
            case " ":
                variableName = "VK_SPACE";
                break;
            case ".":
                variableName = "VK_PERIOD";
                break;
            case "-":
                variableName = "VK_MINUS";
                break;
            case "'":
                variableName = "VK_ASTERISK";
                break;
            default:
                variableName = "VK_" + currentChar.toUpperCase();
                break;
        }

        Class clazz = KeyEvent.class;
        Field field = clazz.getField(variableName);
        int keyCode = field.getInt(null);
        this.ffaRobot.keyPress(keyCode);
        this.ffaRobot.keyRelease(keyCode);
        this.ffaRobot.keyRelease(KeyEvent.VK_SHIFT);
    }

    //Uses a simple Point object to retrieve
    // the X and Y coordinates of FileMaker Pro
    public void getXYCord() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        System.out.println("X: " + p.getX());
        System.out.println("Y: " + p.getY());
    }
}
