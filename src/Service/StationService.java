package Service;

import model.Passanger;
import model.Station;
import java.util.ArrayList;
import java.util.Scanner;

// manages all station operations
public class StationService {
    //arraylist is used to temporially store station objects.
    private ArrayList<Station> stations = new ArrayList<>();
    private Scanner scanner;

    //constructor
    public StationService(Scanner scanner) {
        this.scanner = scanner;}

    /* CREATE */
    public void addStation() {
        System.out.println("\n===== ADD STATION =====");
        System.out.print("Enter Station ID: ");
        int stationId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Station Name: ");
        String stationName = scanner.nextLine();

        // Create a Station object.
        Station station = new Station(stationId, stationName);

        // Add the station to the ArrayList.
        stations.add(station);
        System.out.println("Station added successfully!");
    }
    /* READ */
    public void viewStations() {
        System.out.println("\n===== ALL STATIONS =====");

        // Check whether the list is empty.
        if (stations.isEmpty()) {
            System.out.println("No stations found.");
            return;
        }
        // Display every station.
        for (Station station : stations) {
            station.dispalyStationDetails();
            System.out.println("--------------------------");
        }
    }
    /* READ Search foe station using ststion id*/
    public void searchStation() {
        System.out.println("\n===== SEARCH STATION =====");
        System.out.print("Enter Station ID: ");
        int stationId = scanner.nextInt();

        // Search through all stations.
        for (Station station : stations) {
            if (station.getStationId() == stationId) {
                station.dispalyStationDetails();
                return;
            }
        }
        System.out.println("Station not found.");
    }
    /* UPDATE */
    public void updateStation() {
        System.out.println("\n===== UPDATE STATION =====");
        System.out.print("Enter Station ID: ");
        int stationId = scanner.nextInt();
        scanner.nextLine();

        // Search for the station.
        for (Station station : stations) {
            if (station.getStationId() == stationId) {
                System.out.print("Enter New Station Name: ");
                String stationName = scanner.nextLine();

                //update
                station.setStationName(stationName);
                System.out.println("Station updated successfully!");
                return;
            }
        }
        System.out.println("Station not found.");
    }
    /* DELETE */
    public void deleteStation() {
        System.out.println("\n===== DELETE STATION =====");
        System.out.print("Enter Station ID: ");
        int stationId = scanner.nextInt();

        // Search through the station list.
        for (Station station : stations) {
            if (station.getStationId() == stationId) {
                // Remove the station.
                stations.remove(station);
                System.out.println("Station deleted successfully!");
                return;
            }
        }
        System.out.println("Station not found.");
    }
    /*
     * =====================================================
     * ADD STATION FROM GUI
     * =====================================================
     *
     * This method adds a station using values
     * received from the GUI.
     *
     * Returns true if the station was added.
     * Returns false if Station ID already exists.
     */
    public boolean addStation(
            int stationId,
            String stationName) {

        /*
         * Check whether Station ID already exists.
         */
        for (Station station : stations) {

            if (station.getStationId() == stationId) {

                return false;
            }
        }

        /*
         * Create new Station object.
         */
        Station newStation =
                new Station(
                        stationId,
                        stationName
                );

        /*
         * Add station to ArrayList.
         */
        stations.add(newStation);

        return true;
    }
    /*
     * =====================================================
     * GET ALL STATIONS
     * =====================================================
     *
     * Returns the list of stations.
     * This is used by the GUI to display stations.
     */
    public ArrayList<Station> getStations() {

        return stations;
    }
    /*
     * =====================================================
     * SEARCH STATION BY ID
     * =====================================================
     *
     * This method searches for a station using Station ID.
     *
     * Returns the Station object if found.
     * Returns null if station does not exist.
     */
    public Station findStationById(int stationId) {

        for (Station station : stations) {

            if (station.getStationId() == stationId) {

                return station;
            }
        }

        return null;
    }
    /*
     * =====================================================
     * UPDATE STATION FROM GUI
     * =====================================================
     *
     * This method updates a station using Station ID.
     *
     * Returns true if station is found and updated.
     * Returns false if station does not exist.
     */
    public boolean updateStation(
            int stationId,
            String stationName) {

        /*
         * Search for the station.
         */
        Station station =
                findStationById(
                        stationId
                );

        /*
         * Check whether station exists.
         */
        if (station == null) {

            return false;
        }

        /*
         * Update the station name.
         */
        station.setStationName(
                stationName
        );

        return true;
    }
    /*
     * =====================================================
     * DELETE STATION FROM GUI
     * =====================================================
     *
     * This method deletes a station using Station ID.
     *
     * Returns true if station is found and deleted.
     * Returns false if station does not exist.
     */
    public boolean deleteStation(int stationId) {

        /*
         * Search for the station.
         */
        Station station =
                findStationById(
                        stationId
                );

        /*
         * Check whether station exists.
         */
        if (station == null) {

            return false;
        }

        /*
         * Remove station from ArrayList.
         */
        stations.remove(station);

        return true;
    }
}
