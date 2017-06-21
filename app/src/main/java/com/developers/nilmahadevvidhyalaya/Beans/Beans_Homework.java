package com.developers.nilmahadevvidhyalaya.Beans;

/**
 * Created by Developers on 11-05-2017.
 */

public class Beans_Homework {

    String date, St_Id, Div_Id, Lession, Subject_Name;

    public Beans_Homework() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSt_Id() {
        return St_Id;
    }

    public void setSt_Id(String st_Id) {
        St_Id = st_Id;
    }

    public String getDiv_Id() {
        return Div_Id;
    }

    public void setDiv_Id(String div_Id) {
        Div_Id = div_Id;
    }

    public String getLession() {
        return Lession;
    }

    public void setLession(String lession) {
        Lession = lession;
    }

    public String getSubject_Name() {
        return Subject_Name;
    }

    public void setSubject_Name(String subject_Name) {
        Subject_Name = subject_Name;
    }

    public Beans_Homework(String date, String st_Id, String div_Id, String lession, String subject_Name) {
        this.date = date;
        St_Id = st_Id;
        Div_Id = div_Id;
        Lession = lession;
        Subject_Name = subject_Name;
    }
}