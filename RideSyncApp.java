import model.Driver;
import model.Rider;
import service.GeoUtils;
import service.RideMatcher;

import java.util.*;
import java.util.Scanner;

public class RideSyncApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver("Ravi", 12.9716, 77.5946));
        drivers.add(new Driver("Sneha", 12.2958, 76.6394));
        drivers.add(new Driver("Arun", 13.0827, 80.2707));

        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter pickup address: ");
            String pickupAddress = scanner.nextLine();
            double[] pickup = GeoUtils.getLatLon(pickupAddress);

            System.out.print("Enter drop address: ");
            String dropAddress = scanner.nextLine();
            double[] drop = GeoUtils.getLatLon(dropAddress);

            Rider rider = new Rider(name, pickup[0], pickup[1]);
            Driver matched = RideMatcher.findNearestDriver(drivers, rider.getLat(), rider.getLon());

            if (matched == null) {
                System.out.println("No drivers available.");
                return;
            }

            matched.setAvailable(false);
            double distance = GeoUtils.calculateDistance(pickup[0], pickup[1], drop[0], drop[1]);
            double fare = distance * 9.5;

            System.out.println("\nRide Details:");
            System.out.println("Driver: " + matched.getName());
            System.out.printf("Pickup Location: (%.5f, %.5f)\n", pickup[0], pickup[1]);
            System.out.printf("Drop Location: (%.5f, %.5f)\n", drop[0], drop[1]);
            System.out.printf("Estimated Distance: %.2f km\n", distance);
            System.out.printf("Estimated Fare: ₹%.2f\n", fare);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
