package org.kyffa.models;

import java.util.ArrayList;

/**
 * This class simply models a chapter.
 * Each chapter has student and a name.
 *
 * Author: Sheldon Burks
 * Created: 6/22/14
 */

public class Chapter {
    private String chapterName;
    private int week;
    private ArrayList<Student> students;

    public Chapter(String chapterName, int week) {
        this.chapterName = chapterName;
        this.week = week;
        this.students = new ArrayList<>();
    }

    public String getChapterName() {
        return this.chapterName;
    }

    public int getWeek() {
        return this.week;
    }

    public void addStudent(Student newStudent) {
        this.students.add(newStudent);
    }

    public void printChapter() {
        for(Student s : this.students) {
            if(s.getFirstName() == null) {
                continue;
            }
            System.out.println(s.getFirstName() + " " + s.getLastName() + " " + s.getGender() + " "
                    + s.getOffice() + " " + s.getSpecialInterestAM() + " " + s.getSpecialInterestPM() + " " + s.getGroupNum());
        }
    }

    public int getSize() {
        return this.students.size();
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

}
