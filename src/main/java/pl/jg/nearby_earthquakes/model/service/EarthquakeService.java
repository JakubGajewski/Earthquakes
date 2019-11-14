package pl.jg.nearby_earthquakes.model.service;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.jg.nearby_earthquakes.model.domain.Location;
import pl.jg.nearby_earthquakes.model.domain.Earthquake;

import java.util.PriorityQueue;
import java.util.Queue;

//Service class with a method to create Earthquake objects from JSON and place them in oredered queue.
public class EarthquakeService {
    //nietestowalen!
    public Queue<Earthquake> placeEarthquakeObjectsInQueue (Location pointedLocation) {

        String websiteResponse = JSONHandler.getJson("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson");

        JSONObject jsonObject = new JSONObject(websiteResponse);
        Queue<Earthquake> earthquakes = new PriorityQueue();

        try {
            for (int i = 0; i < jsonObject.getJSONArray("features").length(); i++) {

                JSONObject jsonFeaturesObject = jsonObject.getJSONArray("features").getJSONObject(i);
                JSONArray jObjCoordinates = jsonFeaturesObject.getJSONObject("geometry").getJSONArray("coordinates");
                double latitude = Double.parseDouble(jObjCoordinates.get(1).toString());
                double longitude = Double.parseDouble(jObjCoordinates.get(0).toString());
                String title = jsonFeaturesObject.getJSONObject("properties").get("title").toString();

                Earthquake earthquake = new Earthquake(new Location(latitude, longitude), title);
                earthquake.setCalculatedDistance(DistanceCalculator.calculateDistance(pointedLocation, earthquake.getLocation()));
                earthquakes.add(earthquake);
            }

        } catch (JSONException je) {
            je.printStackTrace();
        }

        return earthquakes;
    }


}
