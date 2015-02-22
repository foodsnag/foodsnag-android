package me.freets.freetsandroid.model;

/**
 * Created by skroh on 2/22/15.
 */
public class Location {
    public final int id;
    public final String name;
    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Location(JsonLocation jl) {
        this.id = jl.id.intValue();
        this.name = jl.name;
    }

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Location) {
            Location that = (Location) other;
            return (this.id == that.id && this.name.equals(that.name));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return id;
    }
}
