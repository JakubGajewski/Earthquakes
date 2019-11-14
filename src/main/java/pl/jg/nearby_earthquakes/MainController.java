package pl.jg.nearby_earthquakes;

import pl.jg.nearby_earthquakes.model.domain.Earthquake;
import pl.jg.nearby_earthquakes.model.service.EarthquakeService;
import pl.jg.nearby_earthquakes.model.utils.InputValidator;
import pl.jg.nearby_earthquakes.model.domain.Location;

import java.util.*;

//Main class to run application
public class MainController {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Nearby Earthquakes Application! This application helps you to find the 10 closests earthquakes near any location.");

        try {
            System.out.println("Please type the latitude of the desired location - like 50,0647 for Krakow");
            final double askedLatitude = sc.nextDouble();

            System.out.println("Please type the longitude of the desired location - like 19,9450 for Krakow");
            final double askedLongitude = sc.nextDouble();

            System.out.println("Data is downloading, please wait...");

            InputValidator.validateRange(askedLatitude, askedLongitude);

            final EarthquakeService earthquakeService = new EarthquakeService();
            Queue<Earthquake> earthquakes = earthquakeService.placeEarthquakeObjectsInQueue(new Location(askedLatitude, askedLongitude));
            Set<Location> locations = new HashSet<>();

            //NPE!
            try {
                for (int i = 0; i < 10; i++) {

                    Earthquake earthquake = earthquakes.poll();
                    if (locations.contains(earthquake.getLocation())) {
                        i--;
                        continue;
                    }
                    locations.add(earthquake.getLocation());
                    System.out.println(earthquake);
                }
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }

        } catch (InputMismatchException ime) {
            System.out.println("Wrong input. Good bye!");
        }
    }
}
