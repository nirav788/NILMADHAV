package com.developers.nilmahadevvidhyalaya.Beans;

/**
 * Created by Developers on 10-05-2017.
 */

public class Beans_Image_Gallary {

    public String getYearID() {
        return YearID;
    }

    public void setYearID(String yearID) {
        YearID = yearID;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    String YearID, CID, Image;

    public Beans_Image_Gallary() {
        YearID = null;
        this.CID = null;
        Image = null;
    }

    public Beans_Image_Gallary(String yearID, String CID, String image) {
        YearID = yearID;
        this.CID = CID;
        Image = image;
    }


}
