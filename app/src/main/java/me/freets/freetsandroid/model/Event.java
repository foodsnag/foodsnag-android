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

    public Event(String name, String location, String college, GregorianCalendar cal) {
        this.name = name;
        this.location = location;
        this.college = college;
        this.cal = cal;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Event) {
            Event that = (Event) other;

            return (this.name.equals(that.name) &&
                    this.location.equals(that.location) &&
                    this.college.equals(that.college) &&
                    this.cal.equals(that.cal));
        } else {
            return false;
        }
    }
}
