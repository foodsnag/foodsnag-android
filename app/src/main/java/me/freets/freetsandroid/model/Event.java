package me.freets.freetsandroid.model;

import java.util.GregorianCalendar;

/**
 * Created by skroh on 2/21/15.
 */
public class Event {
    public final String name;
    public final String location;
    public final String college;
    public final GregorianCalendar cal;

    public Event(String name, String location, String college, GregorianCalendar cal)
    {
        this.name = name;
        this.location = location;
        this.college = college;
        this.cal = cal;
    }
}
