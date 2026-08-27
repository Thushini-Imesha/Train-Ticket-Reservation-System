package model;
import java.util.ArrayList;
/*
    *Train reperesents a real world train.
    * the class uses private attributes and getters and setters demonstrating ENCAPSULATION.
    *
 */
public class Train {
    private ArrayList<Seat> seats = new ArrayList<>();
    private int trainId;
    private String trainName;
    private String trainType;
    private String departureStation;
    private String destinationStation;
    private String departureTime;
    private double ticketPrice;

    //Constructors
    public Train(int trainId,String trainName,String trainType,String departureStation,String destinationStation,String departureTime,double ticketPrice){
        this.trainId = trainId;
        this.trainName = trainName;
        this.trainType = trainType;
        this.departureStation = departureStation;
        this.destinationStation = destinationStation;
        this.departureTime = departureTime;
        this.ticketPrice = ticketPrice;
    }

    //Getters and setters
    public int getTrainId(){return trainId;}
    public String getTrainName(){return trainName;}
    public String getTrainType(){return trainType;}
    public String getDepartureStation(){return departureStation;}
    public String getDestinationStation(){return destinationStation;}
    public String getDepartureTime(){return departureTime;}
    public double getTicketPrice(){return ticketPrice;}

    public void setTrainId(int trainId){
        this.trainId = trainId;
    }
    public void setTrainName(String trainName){
        this.trainName = trainName;
    }
    public void setTrainType(String trainType){
        this.trainType = trainType;
    }
    public void setDepartureStation(String departureStation){
        this.departureStation = departureStation;
    }
    public void setDestinationStation(String destinationStation){
        this.destinationStation = destinationStation;
    }
    public void setDepartureTime(String departureTime){
        this.departureTime = departureTime;
    }
    public void setTicketPrice(double ticketPrice){
        this.ticketPrice = ticketPrice;
    }

    //display train Information.
    public void displayTrainDetails(){
        System.out.println("------Train Details--------");
        System.out.println("Train ID: " +trainId);
        System.out.println("Train Name: " +trainName);
        System.out.println("Train Type: " +trainType);
        System.out.println("Departure: " +departureStation);
        System.out.println("Destinantion: " +destinationStation);
        System.out.println("Dparture time: " +departureTime);
        System.out.println("Ticket price: Rs. " +ticketPrice);
    }
    //Add a seat to this train.
    public void addSeat(Seat seat) {seats.add(seat);}

    // * Return all seats belonging to this train.
    public ArrayList<Seat> getSeats() {return seats;}
    //Find a seat by seat number.
    public Seat findSeat(int seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber() == seatNumber) {
                return seat;
            }
        }
        return null;
    }
    /*
     * Display train name inside JComboBox.
     */
    @Override
    public String toString() {

        return trainName;
    }
}
