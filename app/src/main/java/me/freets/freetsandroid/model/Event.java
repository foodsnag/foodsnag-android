package me.freets.freetsandroid.model;

import java.util.GregorianCalendar;
import java.util.HashMap;

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

    public Event(JsonEvent je) {
        this.name = je.name;
        this.location = je.place;
        this.college = ""; // TODO: Convert from location_id
        GregorianCalendar cal = new GregorianCalendar();
//        cal.setTime(je.time);
        this.cal = cal;
        this.ic = EventIcon.getIcon(je.serving);
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

        FRUIT(R.drawable.ic_food4, "Fruit"),
        LEMONADE(R.drawable.ic_food13, "Lemonade"),
        BREAKFAST(R.drawable.ic_food59, "Breakfast"),
        MEAT(R.drawable.ic_food62, "Meat"),
        SAUSAGE(R.drawable.ic_food71, "Sausage"),
        HOT_DOG(R.drawable.ic_food118, "Hot dog"),
        CANDY(R.drawable.ic_food83, "Candy"),
        ICE_CREAM(R.drawable.ic_food89, "Ice cream"),
        BEVERAGES(R.drawable.ic_food92, "Beverages"),
        SOUP(R.drawable.ic_food101, "Soup"),
        ALCOHOL(R.drawable.ic_food110, "Alcohol"),
        BURGERS(R.drawable.ic_food115, "Burgers"),
        PIZZA(R.drawable.ic_food117, "Pizza"),
        CHICKEN(R.drawable.ic_food126, "Chicken"),
        FISH(R.drawable.ic_food127, "Fish"),
        CAKE(R.drawable.ic_food134, "Cake"),
        BBQ(R.drawable.ic_food156, "BBQ"),
        FORMAL_DINNER(R.drawable.ic_food164, "Formal dinner"),
        OTHER(R.drawable.ic_food174, "Other"),
        SMOOTHIE(R.drawable.ic_food181, "Smoothie"),
        COFFEE(R.drawable.ic_food194, "Coffee"),
        TEA(R.drawable.ic_food195, "Tea");

        private static HashMap<String, EventIcon> map;

        public final int icid;
        public final String name;

        EventIcon(int icid, String name) {
            this.icid = icid; this.name = name;
        }

        public static EventIcon getIcon(String name) {
            if(map == null) {
                map = new HashMap<String, EventIcon>();
                for(EventIcon ei: EventIcon.values()) {
                    map.put(ei.name, ei);
                }
            }
            if(map.containsKey(name)) {
                return map.get(name);
            } else {
                return OTHER;
            }
        }
    }
}
