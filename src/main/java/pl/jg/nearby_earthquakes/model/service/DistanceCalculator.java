package pl.jg.nearby_earthquakes.model.service;

import pl.jg.nearby_earthquakes.model.domain.Location;

//Service to calculate distance between two points on the Earth surface, if we know their latitude and longitude,
//with the use of the Haversine formula
public class DistanceCalculator {

    public static final int EARTH_RADIUS = 6371;

    public static int calculateDistance(Location pointALocation, Location pointBLocation) {

        double pointALatitude = pointALocation.getLatitude();
        double pointALongitude = pointALocation.getLongitude();

        double pointBLatitude = pointBLocation.getLatitude();
        double pointBLongitude = pointBLocation.getLongitude();

        double deltaLatitude = Math.toRadians(pointBLatitude - pointALatitude);
        double deltaLongitude = Math.toRadians(pointBLongitude - pointALongitude);

        pointALatitude = Math.toRadians(pointALatitude);
        pointBLatitude   = Math.toRadians(pointBLatitude);

        double valC = haversine(deltaLatitude) + Math.cos(pointALatitude) * Math.cos(pointBLatitude) * haversine(deltaLongitude);
        double valD = 2 * Math.atan2(Math.sqrt(valC), Math.sqrt(1 - valC));

        double distance = EARTH_RADIUS * valD;

        return (int)Math.round(distance);
    }

    public static double haversine (double value) {
        return Math.pow(Math.sin(value/2), 2);
    }
}
