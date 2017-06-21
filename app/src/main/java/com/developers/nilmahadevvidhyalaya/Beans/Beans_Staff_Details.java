package com.developers.nilmahadevvidhyalaya.Beans;

/**
 * Created by Developers on 10-05-2017.
 */

public class Beans_Staff_Details {
    String Name, Qualification, Image;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Beans_Staff_Details() {
        Name = null;
        Qualification = null;
        Image = null;
    }

    public Beans_Staff_Details(String name, String qualification, String image) {
        Name = name;
        Qualification = qualification;
        Image = image;
    }
}
