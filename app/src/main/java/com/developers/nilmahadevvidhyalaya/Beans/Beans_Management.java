package com.developers.nilmahadevvidhyalaya.Beans;

/**
 * Created by Developers on 10-05-2017.
 */

public class Beans_Management {

    String Name, Description, Image;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Beans_Management() {
        Name = null;
        Description = null;
        Image = null;

    }

    public Beans_Management(String name, String description, String image) {
        Name = name;
        Description = description;
        Image = image;
    }
}
