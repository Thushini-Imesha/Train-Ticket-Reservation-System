package Service;

import model.Route;
import model.Station;
import java.util.ArrayList;
import java.util.Scanner;

public class RouteService {
    // ArrayList stores Route objects.
    private ArrayList<Route> routes = new ArrayList<>();
    private Scanner scanner;

    //constructor
    public RouteService(Scanner scanner) {
        this.scanner = scanner;}


    /* CREAT */
    public void addRoute() {
        System.out.println("\n===== ADD ROUTE =====");
        System.out.print("Enter Route ID: ");
        int routeId = scanner.nextInt();
        scanner.nextLine();

        // Get departure station information
        System.out.print("Enter Departure Station ID: ");
        int departureId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Departure Station Name: ");
        String departureName = scanner.nextLine();
        //Create departure Station object.
        Station departureStation = new Station(departureId, departureName);

         //Get destination station information.
        System.out.print("Enter Destination Station ID: ");
        int destinationId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Destination Station Name: ");
        String destinationName = scanner.nextLine();

        //Create destination Station object.
        Station destinationStation = new Station(destinationId, destinationName);

        //Get distance
        System.out.print("Enter Distance (km): ");
        double distance = scanner.nextDouble();

        /* Create Route object.*/
        Route route = new Route(routeId, departureStation,destinationStation, distance);

        /* Add Route object to ArrayList.*/
        routes.add(route);
        System.out.println("Route added successfully!");
    }

    /* READ */
    public void viewRoutes() {
        System.out.println("\n===== ALL ROUTES =====");
        if (routes.isEmpty()) {
            System.out.println("No routes found.");
            return;
        }
        //Loop through all Route objects.
        for (Route route : routes) {
            route.displayRouteDetails();
            System.out.println("--------------------------");
        }
    }

    /* READ - Search route using Route ID.*/
    public void searchRoute() {
        System.out.println("\n===== SEARCH ROUTE =====");
        System.out.print("Enter Route ID: ");
        int routeId = scanner.nextInt();

        /* SEARCH */
        for (Route route : routes) {
            if (route.getRouteId() == routeId) {
                route.displayRouteDetails();
                return;
            }
        }
        System.out.println("Route not found.");
    }

    /* UPDATE */
    public void updateRoute() {
        System.out.println("\n===== UPDATE ROUTE =====");
        System.out.print("Enter Route ID: ");
        int routeId = scanner.nextInt();
        scanner.nextLine();

        for (Route route : routes) {
            if (route.getRouteId() == routeId) {
                System.out.print("Enter New Distance (km): ");
                double distance = scanner.nextDouble();
                route.setDistance(distance);
                System.out.println("Route updated successfully!");
                return;
            }
        }
        System.out.println("Route not found.");
    }

    /* DELETE */
    public void deleteRoute() {
        System.out.println("\n===== DELETE ROUTE =====");
        System.out.print("Enter Route ID: ");
        int routeId = scanner.nextInt();

        for (Route route : routes) {
            if (route.getRouteId() == routeId) {
                routes.remove(route);
                System.out.println("Route deleted successfully!");
                return;
            }
        }
        System.out.println("Route not found.");
    }
    /*
     * =====================================================
     * ADD ROUTE FROM GUI
     * =====================================================
     */
    public boolean addRoute(
            int routeId,
            Station departureStation,
            Station destinationStation,
            double distance) {

        /*
         * Check whether Route ID already exists.
         */
        for (Route route : routes) {

            if (route.getRouteId() == routeId) {

                return false;
            }
        }


        /*
         * Prevent same station being used
         * as both departure and destination.
         */
        if (departureStation == destinationStation) {

            return false;
        }


        /*
         * Create Route object.
         */
        Route newRoute =
                new Route(
                        routeId,
                        departureStation,
                        destinationStation,
                        distance
                );


        /*
         * Store route.
         */
        routes.add(newRoute);

        return true;
    }
    /*
     * =====================================================
     * GET ALL ROUTES
     * =====================================================
     *
     * Returns all route objects.
     * Used by the GUI to display routes.
     */
    public ArrayList<Route> getRoutes() {

        return routes;
    }
    /*
     * =====================================================
     * SEARCH ROUTE BY ID
     * =====================================================
     *
     * Returns the Route object if found.
     * Returns null if no route exists with that ID.
     */
    public Route findRouteById(int routeId) {

        for (Route route : routes) {

            if (route.getRouteId() == routeId) {
                return route;
            }
        }

        return null;
    }
    /*
     * =====================================================
     * UPDATE ROUTE FROM GUI
     * =====================================================
     *
     * Updates an existing route using Route ID.
     *
     * Returns true if the route was found and updated.
     * Returns false if the route does not exist.
     */
    public boolean updateRoute(
            int routeId,
            Station departureStation,
            Station destinationStation,
            double distance) {

        /*
         * Find the route.
         */
        Route route =
                findRouteById(
                        routeId
                );

        /*
         * Check whether route exists.
         */
        if (route == null) {

            return false;
        }

        /*
         * Do not allow the same station
         * as departure and destination.
         */
        if (departureStation == destinationStation) {

            return false;
        }

        /*
         * Update route information.
         */
        route.setDepartureStation(
                departureStation
        );

        route.setDestinationStation(
                destinationStation
        );

        route.setDistance(
                distance
        );

        return true;
    }
    /*
     * =====================================================
     * DELETE ROUTE FROM GUI
     * =====================================================
     *
     * Deletes a route using Route ID.
     *
     * Returns true if the route was found and deleted.
     * Returns false if the route does not exist.
     */
    public boolean deleteRoute(int routeId) {

        /*
         * Find the route.
         */
        Route route =
                findRouteById(
                        routeId
                );

        /*
         * Check whether route exists.
         */
        if (route == null) {

            return false;
        }

        /*
         * Remove the route from ArrayList.
         */
        routes.remove(route);

        return true;
    }
}
