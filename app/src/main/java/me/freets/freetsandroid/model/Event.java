package me.freets.freetsandroid.model;

import java.util.GregorianCalendar;

import me.freets.freetsandroid.R;

/**
 * Created by skroh on 2/21/15.
 */
public class Event {
    public final String name;
    public final String location;
    public final String college;
    public final GregorianCalendar cal;
    public final EventIcon ic;

    public Event(String name, String location, String college, GregorianCalendar cal, EventIcon ic) {
        this.name = name;
        this.location = location;
        this.college = college;
        this.cal = cal;
        this.ic = ic;
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

    public enum EventIcon {

        FRUIT(R.drawable.ic_food4),
        LEMONADE(R.drawable.ic_food13),
        BREAKFAST(R.drawable.ic_food59),
        MEAT(R.drawable.ic_food62),
        SAUSAGE(R.drawable.ic_food71),
        HOT_DOG(R.drawable.ic_food118),
        CANDY(R.drawable.ic_food83),
        ICE_CREAM(R.drawable.ic_food89),
        BEVERAGES(R.drawable.ic_food92),
        SOUP(R.drawable.ic_food101),
        ALCOHOL(R.drawable.ic_food110),
        BURGERS(R.drawable.ic_food115),
        PIZZA(R.drawable.ic_food117),
        CHICKEN(R.drawable.ic_food126),
        FISH(R.drawable.ic_food127),
        CAKE(R.drawable.ic_food134),
        BBQ(R.drawable.ic_food156),
        FORMAL_DINNER(R.drawable.ic_food164),
        OTHER(R.drawable.ic_food174),
        SMOOTHIE(R.drawable.ic_food181),
        COFFEE(R.drawable.ic_food194),
        TEA(R.drawable.ic_food195);

        public final int icid;
        EventIcon(int icid) {
            this.icid = icid;
        }
    }
}
