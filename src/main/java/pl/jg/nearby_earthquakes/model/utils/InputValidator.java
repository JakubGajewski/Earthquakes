package pl.jg.nearby_earthquakes.model.utils;

import java.util.InputMismatchException;

//Validates if the doubles inserted by the user are correct latitude and longitude
public class InputValidator {
    public static void validateRange(double latitude, double longitude) {

        if (latitude > 180 || latitude < -180) {
            throw new InputMismatchException();
        }
        if (longitude > 180 || longitude < -180) {
            throw new InputMismatchException();
        }
    }
}
