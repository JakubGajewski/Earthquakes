package pl.jg.nearby_earthquakes.model.domain;

import java.util.Objects;

// Coordinates of any object in a map
public class Location {
    private double Latitude;
    private double Longitude;

    public Location(double latitude, double longitude) {
        Latitude = latitude;
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.Latitude, Latitude) == 0 &&
                Double.compare(location.Longitude, Longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Latitude, Longitude);
    }
}
