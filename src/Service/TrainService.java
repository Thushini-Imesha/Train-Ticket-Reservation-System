package Service;

import model.Train;
import java.util.ArrayList;
import java.util.Scanner;

/*
    manages all train CRDU Operations
 */

public class TrainService {
    //Array list stores all train objects
    private ArrayList<Train> trains = new ArrayList<>();
    public ArrayList<Train> getTrains(){return trains;}
    private Scanner scanner;

    //constructor
    public TrainService(Scanner scanner){
        this.scanner = scanner;
    }

    //CREATE
    public void addTrain(){
        System.out.println("\n===== ADD TRAIN =====");

        System.out.println("Enter Train ID:");
        int trainId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Train Name:");
        String trainName = scanner.nextLine();

        System.out.println("Enater Train Type:");
        String trainType = scanner.nextLine();

        System.out.println("Enter Departure Station:");
        String departureStation = scanner.nextLine();

        System.out.println("Enater Destination Station:");
        String destinationStation = scanner.nextLine();

        System.out.println("Enter Departure time:");
        String departureTime = scanner.nextLine();

        System.out.println("Enter Ticket Price:");
        double ticketPrice = scanner.nextDouble();

        //create train object
        Train train = new Train(trainId,trainName,trainType,departureStation,destinationStation,departureTime,ticketPrice);

        //add the trin object tp arraylist.
        trains.add(train);
        System.out.println("Train Added Successfully");
    }

    /* READ - display all trains*/
    public void viewTrains(){
        System.out.println("\n===== ALL TRAINS =====");

        //check whether there are any trains.
        if(trains.isEmpty()){
            System.out.println("NO TRAINS FOUND");
            return;
        }
        //loop through all the trains
        for (Train train : trains){
            train.displayTrainDetails();
            System.out.println("----------------------------");
        }
    }

    /*READ- Search for a train using train Id. */
    public void searchTrain(){
        System.out.println("\n============ SEARCH TRAIN ==========");
        System.out.println("Enter Train ID:");
        int trainId = scanner.nextInt();

        //search through the arraylist
        for (Train train : trains){
            if(train.getTrainId() == trainId){
                train.displayTrainDetails();
                return;
            }
        }

        //display this message if the train does not exis
        System.out.println("Train not found.");
    }

    /*  UPDATE */
    public void updateTrain(){
        System.out.println("\n===== UPDATE TRAIN =====");
        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        scanner.nextLine();

        //search for the train
        for(Train train : trains) {
            if (train.getTrainId() == trainId) {
                System.out.println("Enter Train New Name:");
                String trainName = scanner.nextLine();

                System.out.println("Enater Train New Type:");
                String trainType = scanner.nextLine();

                System.out.println("Enter New Departure Station:");
                String departureStation = scanner.nextLine();

                System.out.println("Enater New Destination Station:");
                String destinationStation = scanner.nextLine();

                System.out.println("Enter New Departure time:");
                String departureTime = scanner.nextLine();

                System.out.println("Enter New Ticket Price:");
                double ticketPrice = scanner.nextDouble();

                //update exixsting one
                train.setTrainName(trainName);
                train.setTrainType(trainType);
                train.setDepartureStation(departureStation);
                train.setDestinationStation(destinationStation);
                train.setDepartureTime(departureTime);
                train.setTicketPrice(ticketPrice);
                System.out.println("Train Updated Successfully.");
                return;
            }
        }
        System.out.println("Train not found");
    }
    /* DELETE */
    public void deleteTrain(){
        System.out.println("\n===== DELETE TRAIN =====");
        System.out.println("Enter Train ID:");
        int trainId = scanner.nextInt();

        //search throght the arraylsit
        for (Train train : trains){
            if(train.getTrainId() == trainId){
                //remove the train object
                trains.remove(train);
                System.out.println("Train Deleted Successfully!!");
                return;
            }
        }
        System.out.println("Train Not found.");
    }
    /*
     * =====================================================
     * ADD TRAIN FROM GUI
     * =====================================================
     */
    public boolean addTrain(
            int trainId,
            String trainName,
            String trainType,
            String departureStation,
            String destinationStation,
            String departureTime,
            double ticketPrice) {

        /*
         * Check whether Train ID already exists.
         */
        for (Train train : trains) {

            if (train.getTrainId() == trainId) {

                return false;
            }
        }

        /*
         * Create Train object.
         */
        Train newTrain =
                new Train(
                        trainId,
                        trainName,
                        trainType,
                        departureStation,
                        destinationStation,
                        departureTime,
                        ticketPrice
                );

        /*
         * Store train.
         */
        trains.add(newTrain);

        return true;
    }
    /*
     * =====================================================
     * SEARCH TRAIN BY ID
     * =====================================================
     *
     * This method searches for a train using
     * the Train ID.
     *
     * If found:
     *     return the Train object.
     *
     * If not found:
     *     return null.
     */
    public Train findTrainById(int trainId) {

        for (Train train : trains) {

            if (train.getTrainId() == trainId) {

                return train;
            }
        }

        return null;
    }
    /*
     * =====================================================
     * UPDATE TRAIN
     * =====================================================
     *
     * This method updates train information
     * using the Train ID.
     *
     * Returns true if train is found and updated.
     * Returns false if train does not exist.
     */
    public boolean updateTrain(
            int trainId,
            String trainName,
            String trainType,
            String departureStation,
            String destinationStation,
            String departureTime,
            double ticketPrice) {

        /*
         * Search for train.
         */
        Train train =
                findTrainById(
                        trainId
                );

        /*
         * Check whether train exists.
         */
        if (train == null) {

            return false;
        }

        /*
         * Update train information.
         */
        train.setTrainName(trainName);
        train.setTrainType(trainType);
        train.setDepartureStation(departureStation);
        train.setDestinationStation(destinationStation);
        train.setDepartureTime(departureTime);
        train.setTicketPrice(ticketPrice);

        return true;
    }
    /*
     * =====================================================
     * DELETE TRAIN
     * =====================================================
     *
     * This method deletes a train using Train ID.
     *
     * Returns true if train is found and deleted.
     * Returns false if train does not exist.
     */
    public boolean deleteTrain(int trainId) {

        /*
         * Search for the train.
         */
        Train train =
                findTrainById(
                        trainId
                );

        /*
         * Check whether train exists.
         */
        if (train == null) {

            return false;
        }

        /*
         * Remove train from ArrayList.
         */
        trains.remove(train);

        return true;
    }
}
