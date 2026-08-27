package Service;

import model.Passanger;
import model.Reservation;
import model.Train;
import model.Seat;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/*
 * ReservationService manages train ticket reservations.
 *
 * Main operations:
 * - Create Reservation
 * - View Reservations
 * - Search Reservation
 * - Cancel Reservation
 */

public class ReservationService {
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private Scanner scanner ;

    //constructor
    public ReservationService(Scanner scanner) {
        this.scanner = scanner;}

    /*
     * =====================================================
     * CREATE RESERVATION
     * =====================================================
     * This method connects a Passenger with a Trainand creates a Reservation object.
     */
    public void makeReservation(ArrayList<Passanger> passengers, ArrayList<Train> trains,SeatService seatService) {
        System.out.println("\n===== MAKE RESERVATION =====");
        // Check whether passengers exist.
        if (passengers.isEmpty()) {
            System.out.println("No passengers available.");
            return;
        }
        // Check whether trains exist.
        if (trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }

        /* Enter reservation ID.  */
        System.out.print("Enter Reservation ID: ");
        int reservationId = scanner.nextInt();
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                System.out.println("Reservation ID already exists.");
                return;
            }
        }
        /*
         * =================================================
         * SELECT PASSENGER
         * =================================================
         */
        System.out.print("Enter Passenger ID: ");
        int passengerId = scanner.nextInt();
        Passanger selectedPassenger = null;

        for (Passanger passenger : passengers) {
            if (passenger.getUserid() == passengerId) {
                selectedPassenger = passenger;
                break;
            }
        }

        /* Check whether passenger exists.*/
        if (selectedPassenger == null) {
            System.out.println("Passenger not found.");
            return;
        }

        /*
         * =================================================
         * SELECT TRAIN
         * =================================================
         */
        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        Train selectedTrain = null;

        for (Train train : trains) {
            if (train.getTrainId() == trainId) {
                selectedTrain = train;
                break;
            }
        }

        /* Check whether train exists. */
        if (selectedTrain == null) {
            System.out.println("Train not found.");
            return;
        }

        /*
         * =================================================
         * SELECT SEAT
         * =================================================
         */
        System.out.print("Enter Seat Number: ");
        int seatNumber = scanner.nextInt();
        scanner.nextLine();

        /*
         * Find the seat INSIDE the selected train.
         * Example:
         * Train 101 -> Seat 1
         * Train 102 -> Seat 1
         * These are different seats.
         */
        Seat selectedSeat = seatService.findSeatByTrainAndNumber(selectedTrain,seatNumber);
        //check whether the seat exists
        if(selectedSeat == null){
            System.out.println("Seat does not exit");
            return;
        }
        /*
         * =================================================
         * TRAVEL DATE
         * =================================================
         */
        System.out.print("Enter Travel Date (YYYY-MM-DD): ");
        String travelDate = scanner.nextLine();
        /*
         * Validate the entered date.
         * LocalDate checks whether the date is a real calendar date.
         * Example:
         * 2026-08-30  -> Valid
         * 2026-02-30  -> Invalid
         */
        try {
            LocalDate date = LocalDate.parse(travelDate);
            System.out.println("Travel date accepted: " + date);
        }
        catch (DateTimeParseException e) {
            System.out.println("Invalid date.");
            System.out.println("Please use YYYY-MM-DD format.");
            return;
        }
        /*
         * =================================================
         * CHECK DOUBLE BOOKING
         * =================================================
         *
         * A seat cannot be booked twice for the same train and the same travel date.
         */
        if (isSeatAlreadyBooked(selectedTrain, seatNumber, travelDate)) {
            System.out.println("Sorry! This seat is already booked " + "for this travel date.");
            return;
        }

        /*
         * =================================================
         * CREATE RESERVATION
         * =================================================
         */
        Reservation reservation = new Reservation(reservationId, selectedPassenger, selectedTrain, seatNumber, travelDate);
        //this change the seat status from available => reserved
        selectedSeat.reserveSeat();
        //Store the reservation.
        reservations.add(reservation);
        System.out.println("\nReservation created successfully!");

        //Display the generated ticket.
        reservation.displayReservationDetails();
    }
    /*
     * =====================================================
     * VIEW RESERVATIONS
     * =====================================================
     */
    public void viewReservations() {
        System.out.println("\n===== ALL RESERVATIONS =====");
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        /* Display each reservation. */
        for (Reservation reservation : reservations) {
            reservation.displayReservationDetails();
        }
    }

    /*
     * =====================================================
     * SEARCH RESERVATION
     * =====================================================
     */
    public void searchReservation() {
        System.out.println("\n===== SEARCH RESERVATION =====");
        System.out.print("Enter Reservation ID: ");
        int reservationId = scanner.nextInt();

        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                System.out.println("Reservation ID already exists!");
                return;
            }
        }
        System.out.println("Reservation not found.");
    }
     /*
     * =====================================================
     * CANCEL RESERVATION
     * =====================================================
     * When a reservation is cancelled:
     * Reservation -> Cancelled
     * Seat        -> Available
      */
    public void cancelReservation(SeatService seatService) {
        System.out.println("\n===== CANCEL RESERVATION =====");
        System.out.print("Enter Reservation ID: ");
        int id = scanner.nextInt();

        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == id){
                // Get the train associated with this reservation.
                reservation.cancelReservation();
                System.out.println("Reservation cancelled successfully!");
                return;
            }
        }
        //Reservation was not found.
        System.out.println("Reservation not found.");
    }
    /*
     * =====================================================
     * CHECK WHETHER A SEAT IS ALREADY BOOKED
     * =====================================================
     * This method checks:
     * 1. Same Train
     * 2. Same Seat Number
     * 3. Same Travel Date
     * If all three are the same, the seat is already booked.
     */
    private boolean isSeatAlreadyBooked(
            Train train,
            int seatNumber,
            String travelDate) {

        for (Reservation reservation :
                reservations) {

            if (reservation
                    .getStatus()
                    .equals("ACTIVE")) {

                if (reservation
                        .getTrain()
                        .getTrainId()
                        == train.getTrainId()) {

                    if (reservation
                            .getSeatNumber()
                            == seatNumber) {

                        if (reservation
                                .getTravelDate()
                                .equals(travelDate)) {

                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
    /*
     * =====================================================
     * DISPLAY AVAILABLE SEATS
     * =====================================================
     * This method displays the seats of a selected train for a particular travel date.
     * A seat is BOOKED when:
     * Train + Seat Number + Travel Date
     * already exists in the reservations list.
     */
    public void displayAvailableSeats(ArrayList<Train> trains, SeatService seatService) {
        System.out.println("\n===== AVAILABLE SEATS =====");

        //Check whether trains exist.
        if (trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }
        //Ask the user for the Train ID.
        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        scanner.nextLine();

        //Find the selected train.
        Train selectedTrain = null;
        for (Train train : trains) {
            if (train.getTrainId() == trainId) {
                selectedTrain = train;
                break;
            }
        }

        //Check whether the train exists.
        if (selectedTrain == null) {
            System.out.println("Train not found.");
            return;
        }
        //Ask for the travel date.
        System.out.print("Enter Travel Date (YYYY-MM-DD): ");
        String travelDate = scanner.nextLine();

        //Display the selected train and date.
        System.out.println("\nTrain ID: " + trainId);
        System.out.println("Travel Date: " + travelDate);
        System.out.println("\n----- SEAT STATUS -----");

        //Go through every seat belonging to the selected train
        for (Seat seat : selectedTrain.getSeats()) {
            boolean booked = isSeatAlreadyBooked(selectedTrain, seat.getSeatNumber(), travelDate);
            if (booked) {
                System.out.println("Seat " + seat.getSeatNumber() + " - BOOKED");
            } else {
                System.out.println("Seat " + seat.getSeatNumber() + " - AVAILABLE");
            }
        }
    }
        /*
         * =====================================================
         * RESERVATION SUMMARY
         * =====================================================
         *
         * This method displays:
         *
         * 1. Total number of reservations
         * 2. Number of active reservations
         * 3. Number of cancelled reservations
         */

    public void displayReservationSummary() {
            System.out.println();
            System.out.println("==========================================");
            System.out.println("          RESERVATION SUMMARY");
            System.out.println("==========================================");
            // Store the number of active reservations.
            int activeCount = 0;

             // Store the number of cancelled reservations.
            int cancelledCount = 0;
            for (Reservation reservation : reservations) {
                if (reservation.getStatus().equals("ACTIVE")) {
                    activeCount++;
                }
                else if (
                        reservation.getStatus().equals("CANCELLED")) {
                    cancelledCount++;
                }
            }
            int totalCount =
                    activeCount + cancelledCount;
            //Display the summary.
            System.out.println("Total Reservations    : " + totalCount);
            System.out.println("Active Reservations   : " + activeCount);
            System.out.println("Cancelled Reservations: " + cancelledCount);
            System.out.println("==========================================");
        }
    /*
     * =====================================================
     * GET ALL RESERVATIONS
     * =====================================================
     */
    public ArrayList<Reservation> getReservations() {

        return reservations;
    }


    /*
     * =====================================================
     * FIND RESERVATION BY ID
     * =====================================================
     */
    public Reservation findReservationById(
            int reservationId) {

        for (Reservation reservation :
                reservations) {

            if (reservation.getReservationId()
                    == reservationId) {

                return reservation;
            }
        }

        return null;
    }


    /*
     * =====================================================
     * MAKE RESERVATION FROM GUI
     * =====================================================
     */
    public boolean makeReservation(
            int reservationId,
            Passanger passenger,
            Train train,
            Seat seat,
            String travelDate) {

        /*
         * Check null values.
         */
        if (passenger == null
                || train == null
                || seat == null) {

            return false;
        }


        /*
         * Reservation ID must be unique.
         */
        if (findReservationById(
                reservationId) != null) {

            return false;
        }


        /*
         * Check whether this exact seat is already
         * booked for this exact train and date.
         */
        if (isSeatAlreadyBooked(
                train,
                seat.getSeatNumber(),
                travelDate)) {

            return false;
        }


        /*
         * Create Reservation object.
         *
         * IMPORTANT:
         * If your Reservation constructor is slightly
         * different, this may be the only line we need
         * to adjust.
         */
        Reservation reservation =
                new Reservation(
                        reservationId,
                        passenger,
                        train,
                        seat.getSeatNumber(),
                        travelDate
                );


        /*
         * Add reservation.
         */
        reservations.add(
                reservation
        );


        /*
         * DO NOT use:
         *
         * seat.reserveSeat();
         *
         * here because our booking is date-specific.
         */

        return true;
    }


    /*
     * =====================================================
     * GET AVAILABLE SEATS FOR A DATE
     * =====================================================
     */
    public ArrayList<Seat> getAvailableSeats(
            Train train,
            String travelDate) {

        ArrayList<Seat> availableSeats =
                new ArrayList<>();


        if (train == null) {

            return availableSeats;
        }


        /*
         * Check every seat belonging to the train.
         */
        for (Seat seat :
                train.getSeats()) {

            boolean booked =
                    isSeatAlreadyBooked(
                            train,
                            seat.getSeatNumber(),
                            travelDate
                    );


            if (!booked) {

                availableSeats.add(
                        seat
                );
            }
        }


        return availableSeats;
    }
}