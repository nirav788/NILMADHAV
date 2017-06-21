package com.developers.nilmahadevvidhyalaya.Beans;

/**
 * Created by Developers on 20-05-2017.
 */

public class Beans_Video_Gallery {

    public Beans_Video_Gallery(String yearID, String CID, String video, String video_Name) {
        YearID = yearID;
        this.CID = CID;
        Video = video;
        Video_Name = video_Name;
    }

    public Beans_Video_Gallery() {
    }

    String YearID, CID, Video, Video_Name;

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

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }

    public String getVideo_Name() {
        return Video_Name;
    }

    public void setVideo_Name(String video_Name) {
        Video_Name = video_Name;
    }
}
