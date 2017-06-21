package com.developers.nilmahadevvidhyalaya.Beans;

/**
 * Created by Developers on 11-05-2017.
 */

public class Beans_Class_Rooms {
    String Name, Description, Images;

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public Beans_Class_Rooms(String name, String description, String images) {
        Name = name;
        Description = description;
        Images = images;
    }

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


    public Beans_Class_Rooms() {
        Name = null;
        Description = null;
        Images = null;
    }
}
