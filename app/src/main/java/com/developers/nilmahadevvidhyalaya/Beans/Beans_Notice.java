package com.developers.nilmahadevvidhyalaya.Beans;

/**
 * Created by Developers on 10-05-2017.
 */

public class Beans_Notice {

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

    public Beans_Notice() {
        Name = null;
        Description = null;
        Image = null;

    }

    public Beans_Notice(String name, String description, String image) {
        Name = name;
        Description = description;
        Image = image;
    }
}
