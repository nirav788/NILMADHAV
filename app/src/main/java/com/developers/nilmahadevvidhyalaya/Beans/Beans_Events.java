package com.developers.nilmahadevvidhyalaya.Beans;

/**
 * Created by Developers on 11-05-2017.
 */

public class Beans_Events {

    String Column1,Event_Name;

    public String getColumn1() {
        return Column1;
    }

    public void setColumn1(String column1) {
        Column1 = column1;
    }

    public String getEvent_Name() {
        return Event_Name;
    }

    public void setEvent_Name(String event_Name) {
        Event_Name = event_Name;
    }

    public Beans_Events() {
        Column1 = null;
        Event_Name = null;
    }

    public Beans_Events(String column1, String event_Name) {
        Column1 = column1;
        Event_Name = event_Name;
    }
}
