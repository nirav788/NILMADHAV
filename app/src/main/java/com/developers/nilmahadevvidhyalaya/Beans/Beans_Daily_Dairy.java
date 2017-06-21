package com.developers.nilmahadevvidhyalaya.Beans;

/**
 * Created by Developers on 13-06-2017.
 */

public class Beans_Daily_Dairy {

    public Beans_Daily_Dairy() {
    }

    public Beans_Daily_Dairy(String dates, String division, String description, String name, String subject_Name, String standard) {

        Dates = dates;
        Division = division;
        Description = description;
        Name = name;
        Subject_Name = subject_Name;
        Standard = standard;
    }

    String Dates, Division, Description, Name, Subject_Name, Standard;

    public String getDates() {
        return Dates;
    }

    public void setDates(String dates) {
        Dates = dates;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSubject_Name() {
        return Subject_Name;
    }

    public void setSubject_Name(String subject_Name) {
        Subject_Name = subject_Name;
    }

    public String getStandard() {
        return Standard;
    }

    public void setStandard(String standard) {
        Standard = standard;
    }
}
