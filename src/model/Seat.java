package model;

/*
 * Seat represents a seat inside a particular train
 * A seat belongs to a Train.
 * This demonstrates ASSOCIATION between Train and Seat.
 */

public class Seat {
    private int seatId;
    private int seatNumber;
    private boolean available;
    // Train to which this seat belongs.
    private Train train;

    // Constructor for creating a Seat.
    public Seat(int seatId, int seatNumber, Train train) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.train = train;
        // New seats are available.
        this.available = true;
    }

    //Getter for seat ID.
    public int getSeatId() {return seatId;}
    public int getSeatNumber() {return seatNumber;}
    public Train getTrain() {return train;}

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    //Check whether the seat is available
    public boolean isAvailable() {
        return available;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    //Reserve this seat
    public void reserveSeat() {
        if (available) {
            available = false;
            System.out.println("Seat " + seatNumber + " has been reserved.");
        }
        else{
            System.out.println("Seat " + seatNumber + " is already reserved.");
        }
    }

    //Release this seat
    public void releaseSeat() {
        if (!available) {
            available = true;
            System.out.println("Seat " + seatNumber + " is now available.");
        }
        else {
            System.out.println("Seat " + seatNumber + " is already available.");
        }
    }

    // Display seat details.
    public void displaySeatDetails() {
        System.out.println("Seat ID     : " + seatId);
        System.out.println("Seat Number : " + seatNumber);
        if (train != null) {
            System.out.println("Train       : " + train.getTrainName());
        }
        System.out.println("Status      : " + (available ? "Available" : "Reserved"));
        System.out.println("----------------------------");
    }
    /*
     * Display seat number inside JComboBox.
     */
    @Override
    public String toString() {

        return "Seat " + seatNumber;
    }
}