package Service;

import model.Seat;
import model.Train;
import java.util.ArrayList;
import java.util.Scanner;

public class SeatService {
    // Stores all Seat objects.
    private ArrayList<Seat> seats = new ArrayList<>();
    private Scanner scanner;

    //constructor
    public SeatService(Scanner scanner) {
        this.scanner = scanner;}

    //Add a new seat to a specific train.
    public void addSeat(ArrayList<Train> trains) {
        System.out.println("\n===== ADD SEAT =====");
        System.out.print("Enter Seat ID: ");
        int trainId = scanner.nextInt();
        Train selectedTrain = null;
        //search for the train
        for(Train train:trains){
            if(train.getTrainId() == trainId){
                selectedTrain = train;
                break;
            }
        }
        //chech whether train exixst
        if(selectedTrain == null){
            System.out.println("Train not found");
            return;
        }
        System.out.println("Enter Seat ID:");
        int seatId = scanner.nextInt();
        System.out.print("Enter Seat Number: ");
        int seatNumber = scanner.nextInt();

        //prevent duplication
        if(selectedTrain.findSeat(seatNumber) != null) {
            System.out.println("This seat already exists in this train.");
            return;
        }

        Seat seat = new Seat(seatId, seatNumber,selectedTrain);

        //add the seat to selected train
        selectedTrain.addSeat(seat);
        // Add the seat to the ArrayList.
        seats.add(seat);
        System.out.println("Seat added successfully!");
    }

    /* READ */
    public void viewSeats() {
        System.out.println("\n===== ALL SEATS =====");
        if (seats.isEmpty()) {
            System.out.println("No seats found.");
            return;
        }

        for (Seat seat : seats) {
            seat.displaySeatDetails();
            System.out.println("--------------------------");
        }
    }

    /* READ  */
    public void searchSeat() {
        System.out.println("\n===== SEARCH SEAT =====");
        System.out.print("Enter Seat ID: ");
        int seatId = scanner.nextInt();

        for (Seat seat : seats) {
            if (seat.getSeatId() == seatId) {
                seat.displaySeatDetails();
                return;
            }
        }
        System.out.println("Seat not found.");
    }

    /* UPDATE */
    public void updateSeat() {
        System.out.println("\n===== UPDATE SEAT =====");
        System.out.print("Enter Seat ID: ");
        int seatId = scanner.nextInt();

        for (Seat seat : seats) {
            if (seat.getSeatId() == seatId) {
                System.out.print("Enter New Seat Number: ");
                int newNumber = scanner.nextInt();
                seat.setSeatNumber(newNumber);
                System.out.println("Seat updated successfully!");
                return;
            }
        }
        System.out.println("Seat not found.");
    }

    /* DELETE */
    public void deleteSeat() {
        System.out.println("\n===== DELETE SEAT =====");
        System.out.print("Enter Seat ID: ");
        int seatId = scanner.nextInt();

        for (Seat seat : seats) {
            if (seat.getSeatId() == seatId) {
                seats.remove(seat);
                System.out.println("Seat deleted successfully!");
                return;
            }
        }
        System.out.println("Seat not found.");
    }

    /*RESERVE SEAT
     * This operation changes an available seat into a reserved seat.
     */
    public void reserveSeat() {
        System.out.println("\n===== RESERVE SEAT =====");
        System.out.print("Enter Seat ID: ");
        int seatId = scanner.nextInt();

        for (Seat seat : seats) {
            if (seat.getSeatId() == seatId) {

                //Check whether the seat is available.
                if (seat.isAvailable()) {
                    seat.reserveSeat();
                } else {
                    System.out.println("This seat is already reserved!");
                }
                return;
            }
        }
        System.out.println("Seat not found.");
    }

    /*
     * RELEASE SEAT
     * This operation makes a reserved seat available again.
     */
    public void releaseSeat() {
        System.out.println("\n===== RELEASE SEAT =====");
        System.out.print("Enter Seat ID: ");
        int seatId = scanner.nextInt();

        for (Seat seat : seats) {

            if (seat.getSeatId() == seatId) {
                seat.releaseSeat();
                return;
            }
        }
        System.out.println("Seat not found.");
    }
    /*
     * Find a seat using the seat number.
     * This method is used by ReservationService when a passenger wants to book a seat.
     */
    public Seat findSeatByTrainAndNumber(int seatNumber){
        // Search through all seats.
        for (Seat seat : seats) {
            if (seat.getSeatNumber() == seatNumber) {
                return seat;
            }
        }
        // Return null if the seat does not exist.
        return null;
    }
    /*
     * =====================================================
     * FIND SEAT BY TRAIN AND SEAT NUMBER
     * =====================================================
     *
     * This method searches for a particular seat inside a particular train.
     * Example:
     * Train 101 -> Seat 1
     * Train 102 -> Seat 1
     * These are two different seats.
     * Therefore, we must check both:
     *
     * 1. Train
     * 2. Seat Number
     */
    public Seat findSeatByTrainAndNumber(Train train, int seatNumber) {
        if (train == null) {
            return null;
        }
         // Search through the seats belongin to this particular train.
        for (Seat seat : train.getSeats()) {
            if (seat.getSeatNumber() == seatNumber) {
                return seat;
            }
        }
        return null;
    }
    /*
     * =====================================================
     * ADD SEAT FROM GUI
     * =====================================================
     *
     * Adds a new seat to the selected train.
     *
     * Returns true if the seat was added.
     * Returns false if the Seat ID or Seat Number
     * already exists for that train.
     */
    public boolean addSeat(
            int seatId,
            int seatNumber,
            Train train) {

        /*
         * Check whether a train is selected.
         */
        if (train == null) {
            return false;
        }


        /*
         * Check for duplicate Seat ID
         * or duplicate Seat Number.
         */
        for (Seat seat : train.getSeats()) {

            if (seat.getSeatId() == seatId) {
                return false;
            }

            if (seat.getSeatNumber() == seatNumber) {
                return false;
            }
        }


        /*
         * Create new Seat object.
         */
        Seat newSeat =
                new Seat(
                        seatId,
                        seatNumber,
                        train
                );


        /*
         * Add seat to the selected train.
         */
        train.addSeat(
                newSeat
        );

        return true;
    }
    /*
     * =====================================================
     * GET ALL SEATS
     * =====================================================
     *
     * Returns all seats from all trains.
     * Used by the GUI.
     */
    public ArrayList<Seat> getAllSeats(
            ArrayList<Train> trains) {

        ArrayList<Seat> allSeats =
                new ArrayList<>();


        /*
         * Go through every train.
         */
        for (Train train : trains) {

            /*
             * Get seats belonging to that train.
             */
            for (Seat seat : train.getSeats()) {

                allSeats.add(
                        seat
                );
            }
        }

        return allSeats;
    }
    /*
     * =====================================================
     * FIND SEAT BY ID
     * =====================================================
     *
     * Searches all trains and returns the Seat object
     * that matches the entered Seat ID.
     *
     * Returns null if no seat is found.
     */
    public Seat findSeatById(
            int seatId,
            ArrayList<Train> trains) {

        /*
         * Go through every train.
         */
        for (Train train : trains) {

            /*
             * Go through every seat in the train.
             */
            for (Seat seat : train.getSeats()) {

                if (seat.getSeatId() == seatId) {

                    return seat;
                }
            }
        }

        return null;
    }
    /*
     * =====================================================
     * UPDATE SEAT FROM GUI
     * =====================================================
     *
     * Updates Seat Number and Train using Seat ID.
     *
     * Returns true if the seat was found and updated.
     * Returns false if the seat does not exist.
     */
    public boolean updateSeat(
            int seatId,
            int newSeatNumber,
            Train newTrain,
            ArrayList<Train> trains) {

        /*
         * Find the seat.
         */
        Seat seat =
                findSeatById(
                        seatId,
                        trains
                );

        /*
         * Check whether seat exists.
         */
        if (seat == null) {
            return false;
        }

        /*
         * Check whether train is selected.
         */
        if (newTrain == null) {
            return false;
        }

        /*
         * Check for duplicate Seat Number
         * inside the selected train.
         */
        for (Seat existingSeat : newTrain.getSeats()) {

            if (existingSeat.getSeatNumber() == newSeatNumber
                    && existingSeat.getSeatId() != seatId) {

                return false;
            }
        }

        /*
         * If the train is changed,
         * remove the seat from the old train
         * and add it to the new train.
         */
        if (seat.getTrain() != newTrain) {

            seat.getTrain()
                    .getSeats()
                    .remove(seat);

            newTrain.addSeat(
                    seat
            );

            seat.setTrain(
                    newTrain
            );
        }

        /*
         * Update Seat Number.
         */
        seat.setSeatNumber(
                newSeatNumber
        );

        return true;
    }
    /*
     * =====================================================
     * DELETE SEAT FROM GUI
     * =====================================================
     *
     * Deletes a seat using Seat ID.
     *
     * Returns true if the seat was found and deleted.
     * Returns false if the seat does not exist.
     */
    public boolean deleteSeat(
            int seatId,
            ArrayList<Train> trains) {

        /*
         * Find the seat.
         */
        Seat seat =
                findSeatById(
                        seatId,
                        trains
                );

        /*
         * Check whether seat exists.
         */
        if (seat == null) {
            return false;
        }

        /*
         * Remove the seat from the train
         * that currently contains it.
         */
        seat.getTrain()
                .getSeats()
                .remove(seat);

        return true;
    }
}