package service;

import model.Driver;
import java.util.List;

public class RideMatcher {
    public static Driver findNearestDriver(List<Driver> drivers, double lat, double lon) {
        Driver nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (Driver d : drivers) {
            if (d.isAvailable()) {
                double dist = GeoUtils.calculateDistance(lat, lon, d.getLat(), d.getLon());
                if (dist < minDistance) {
                    minDistance = dist;
                    nearest = d;
                }
            }
        }
        return nearest;
    }
}