package pl.jg.nearby_earthquakes.model.domain;

import java.util.Objects;

//Object represents a single earthquake, extracted from JSON, ready to be stored and compared
public class Earthquake implements Comparable<Earthquake> {

    private int calculatedDistance;
    private Location location;
    private String title;

    @Override
    public int compareTo(Earthquake o) {
        return this.calculatedDistance - o.calculatedDistance;
    }

    public Earthquake(Location location, String title) {
        this.location = location;
        this.title = title;
    }

    public void setCalculatedDistance(int calculatedDistance) {
        this.calculatedDistance = calculatedDistance;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Earthquake that = (Earthquake) o;
        return calculatedDistance == that.calculatedDistance &&
                Objects.equals(location, that.location) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calculatedDistance, location, title);
    }

    @Override
    public String toString() {
        return title + " || " + calculatedDistance;
    }
}
