/**
 * Class models a student at camp. Each student has a name
 * a gender, an office, and two special interest classes
 *
 * Author: Sheldon Burks
 * Created: 6/22/14
 */
public class Student {
    private String firstName;
    private String lastName;
    private String gender;
    private String office;
    private String specialInterestAM;
    private String specialInterestPM;
    private int groupNum;
    private boolean isCommittee;

    public Student(String firstName, String lastName, String gender, String office,
                   String specialInterestAM, String specialInterestPM, int groupNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.office = office;
        this.specialInterestAM = specialInterestAM;
        this.specialInterestPM = specialInterestPM;
        this.groupNum = groupNum;
    }


    public Student() {
        this.firstName = null;
        this.lastName = null;
        this.gender = null;
        this.office = null;
        this.specialInterestAM = null;
        this.specialInterestPM = null;
        this.groupNum = 0;
        this.isCommittee = false;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getGender() {
        return gender;
    }

    public String getOffice() {
        return office;
    }

    public String getSpecialInterestAM() {
        return specialInterestAM;
    }

    public String getSpecialInterestPM() {
        return specialInterestPM;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public void setSpecialInterestAM(String specialInterestAM) {
        this.specialInterestAM = specialInterestAM;
    }

    public void setSpecialInterestPM(String specialInterestPM) {
        this.specialInterestPM = specialInterestPM;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public void setIsCommittee(boolean isCommittee) {
        this.isCommittee = isCommittee;
    }

    public boolean getIsCommittee() {
        return this.isCommittee;
    }
}
