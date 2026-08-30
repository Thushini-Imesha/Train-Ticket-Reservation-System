package model;

/*
 * Reservation represents a ticket booking made by a passenger. *
 * A Reservation connects: Passenger + Train + Seat + Travel Date
 * This class demonstrates encapsulation because all attributes are private.
 */

public class Reservation {
    private int reservationId;
    private Passanger passanger;
    private Train train;
    private int seatNumber;
    private String travelDate;
    private String status = "Active";

    //Constructor creates a new reservation.
    public Reservation(int reservationId, Passanger passenger,Train train,int seatNumber, String travelDate) {
        this.reservationId = reservationId;
        this.passanger = passenger;
        this.train = train;
        this.seatNumber = seatNumber;
        this.travelDate = travelDate;
        // New reservations are confirmed.
        this.status = "Confirmed";
    }
    // getters and setters
    public int getReservationId() {return reservationId;}
    public Passanger getPassenger() {return passanger;}
    public Train getTrain() {return train;}
    public int getSeatNumber() {return seatNumber;}
    public String getTravelDate() {return travelDate;}
    public String getStatus() {return status;}

     /* Cancel the reservation.*/
    public void cancelReservation() {status = "Cancelled";
        System.out.println("Resevation status changed to cancelled");
    }
    /*
     * =====================================================
     * DISPLAY RESERVATION DETAILS
     * =====================================================
     * This method displays all important information about a reservation.
     */
    public void displayReservationDetails() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("          RESERVATION DETAILS");
        System.out.println("==========================================");
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Passenger ID   : " + passanger.getUserid());
        System.out.println("Passenger Name : " + passanger.getUsername());
        System.out.println("Train ID       : " + train.getTrainId());
        System.out.println("Train Name     : " + train.getTrainName());
        System.out.println("Seat Number    : " + seatNumber);
        System.out.println("Travel Date    : " + travelDate);
        System.out.println("Status         : " + status);
        System.out.println("==========================================");
    }
    @Override
    public String toString() {

        return "Reservation "
                + getReservationId()
                + " - "
                + getPassenger();
    }
}