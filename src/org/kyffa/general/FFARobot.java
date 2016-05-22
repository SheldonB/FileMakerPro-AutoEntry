package org.kyffa.general;

import org.kyffa.models.Chapter;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

/*
Implementation of the Robot Class to put records from
excel document into the FileMaker Pro software
Created: 6/24/14
Author: Sheldon Burks
*/

public class FFARobot {
    private Chapter chapter;
    private Robot ffaRobot;

    public FFARobot(Chapter chapter) {
        this.chapter = chapter;

        try {
            this.ffaRobot = new Robot();
        } catch(AWTException e) {
            System.out.println("Could Not Initialize Robot");
        }
    }

    public void delayOneSec() {
        this.ffaRobot.delay(1000);
    }
    /*
    This method clicks on the New Record Button in FileMaker Pro.
    */
    public void newRecord() {
        this.ffaRobot.mouseMove(571, 73);
        this.ffaRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        this.ffaRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    /*
    This method takes the week provided in the chapter
    object and uses the Java robot class to select the proper
    week within FileMaker Pro.
    */
    public void setWeek() {
        this.ffaRobot.mouseMove(175, 127);
        this.pressAndRelease();
        this.typeCharacter(Integer.toString(chapter.getWeek()));
    }

    public void setChapter() {
        this.ffaRobot.mouseMove(180, 148);
        this.pressAndRelease();
        this.typeString(this.chapter.getChapterName());
    }

    public void setOffice(String office) {
        if(office == null) {
            return;
        }
        this.ffaRobot.mouseMove(180, 169);
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

    public void setFirstName(String firstName) {
        this.ffaRobot.mouseMove(212, 228);
        this.pressAndRelease();
        this.typeString(firstName);

    }

    public void setLastName(String lastName) {
        this.ffaRobot.mouseMove(299, 229);
        this.pressAndRelease();
        this.typeString(lastName);
    }

    public void setGender(String gender) {
        this.ffaRobot.mouseMove(173, 251);
        this.pressAndRelease();
        this.pressAndRelease();
        if(gender == null) {
            return;
        } else if(gender.equalsIgnoreCase("M")) {
            gender = "Male";
        }
        else {
            gender = "Female";
        }
        this.typeString(gender);
    }

    public void setSpecialInterestClasses(String amClass, String pmClass) {
        if(amClass == null || pmClass == null) {
            return;
        }
        this.ffaRobot.mouseMove(172, 271);
        this.pressAndRelease();
        this.typeCharacter(this.determineClassLetter(amClass));
        this.ffaRobot.mouseMove(172, 292);
        this.pressAndRelease();
        this.typeCharacter(this.determineClassLetter(pmClass));
    }

    public void setGroupNum(String groupNum) {
        this.ffaRobot.mouseMove(180, 315);
        this.pressAndRelease();
        this.pressAndRelease();
        this.typeCharacter(groupNum);
    }

    /*
    Method sets the committee class. The method checks the String value of
    committee and compares it to the corresponding classes. There is an
    array of String in the class org.kyffa.general.Main that contains each committee.
    */
    public void setCommittee(String committee, int counter) {
        this.ffaRobot.mouseMove(190, 208);
        this.pressAndRelease();
        this.pressAndRelease();
        if (committee.equals(Main.committees[0]) && counter % 2 == 0) {
            this.typeString("Chapter Development A");
        } else if(committee.equals(Main.committees[0]) && counter % 2 == 1) {
            this.typeString("Chapter Development B");
        } else if(committee.equals(Main.committees[1]) && counter % 2 == 0) {
            this.typeString("Community Development A");
        } else if(committee.equals(Main.committees[1]) && counter % 2 == 1) {
            this.typeString("Community Development B");
        } else if(committee.equals(Main.committees[2]) && counter % 2 == 0) {
            this.typeString("Student Development A");
        } else if(committee.equals(Main.committees[2]) && counter % 2 == 1) {
            this.typeString("Student Development B");
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
            case "Communication Skills B":
                return "K";
            case "Livestock Judging":
                return "L";
            case "Archery (Beginner)":
                return "M";
            case "Archery (Advanced)":
                return "N";
            case "Water Safety":
                return "O";
            case "High ROPES (Sr Only)":
                return "P";
            case "Tractor Driving":
                return "Q";
            case "Ag Issues":
                return "R";
        }
        return "";
    }

    private void pressAndRelease() {
        this.ffaRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        this.ffaRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void typeString(String stringToPrint) {
        for(int i = 0; i < stringToPrint.length(); i++) {
            this.typeCharacter((Character.toString(stringToPrint.charAt(i))));
        }
    }

    private void typeCharacter(String currentChar) {
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
        int keyCode = 0;
        try {
            Field field = clazz.getField(variableName);
            keyCode = field.getInt(null);
        } catch(Exception e) {
            System.out.println("Failed Typing Character");
        }
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
