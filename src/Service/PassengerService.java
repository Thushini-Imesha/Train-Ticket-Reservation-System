package Service;

import model.Passanger;
import java.util.ArrayList;
import java.util.Scanner;

/*
this manages all passanger CRUD operation.
 */

public class PassengerService {
    //arraylist is used to temporially store passanger objects.
    private ArrayList<Passanger> passangers = new ArrayList<>();

    //return the passanager list
    //this allowa other services to accsses this
    public ArrayList<Passanger> getPassangers(){return passangers;}

    //this means the entire application uses ine scanner object.
    private Scanner scanner;

    // Constructor for PassengerService,Receives the Scanner created in Main.java.*/
    public PassengerService(Scanner scanner) {
        this.scanner = scanner;}

    //create,add a new passanger to the system.
    public void addPassenger() {
        System.out.println("\n======== ADD PASSENGER =========");

        System.out.print("Enter Passenger ID: ");
        int userid = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Passenger Name: ");
        String username = scanner.nextLine();

        System.out.print("Enter Passenger email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Passenger phone number: ");
        String tele = scanner.nextLine();

        System.out.print("Enter Passenger NIC: ");
        String nic = scanner.nextLine();

        //create new passanger object
        Passanger passanger = new Passanger(userid, username, email, tele, nic);

        //add the passanger object to the arraylist.
        passangers.add(passanger);
        System.out.println("Passenger added successfully.");
    }
    /*
    READ - display all passengers currently sored in the system.
     */
    public void viewPassangers() {
        System.out.println("\n========ALL PASSENGERS ======");

        //check whether the list is empty
        if (passangers.isEmpty()) {
            System.out.println("no passanger found");
            return;
        }
        //loop through all passenger objects
        for (Passanger passanger : passangers) {
            passanger.displayPassangerDetails();
            System.out.println("-----------------------");
        }
        }
        /*
        READ - Search for a passanger using the userid
         */
        public void searchPassanger(){
            System.out.println("\n======= SEARCH PASSENGER ========");
            System.out.println("Enter Passenger ID:");
            int userid = scanner.nextInt();

            //search for the arraylist
            for(Passanger passanger : passangers){
                if(passanger.getUserid() == userid){
                    passanger.displayPassangerDetails();
                    return;
                }
            }
            //if no customer was found
            System.out.println("Passenger not found");
        }
        /*
        UPDATE - PASSANGER INFORMATION UPDATE
         */
        public void updatePassanger(){
            System.out.println("\n======== UPDATE PASSENGER ======");
            System.out.println("Enter Passanger Id:");
            int userid = scanner.nextInt();
            scanner.nextLine();

            //Search for passanger
            for(Passanger passanger : passangers){
                if(passanger.getUserid() == userid){
                    System.out.println("Enter New name:");
                    String username = scanner.nextLine();

                    System.out.println("Enter new email:");
                    String email = scanner.nextLine();

                    System.out.println("Enter new Phone Nuumber:");
                    String tele = scanner.nextLine();

                    System.out.println("Enter New NIC:");
                    String nic = scanner.nextLine();

                    //updade the passanger details
                    passanger.setUsername(username);
                    passanger.setEmail(email);
                    passanger.setTele(tele);
                    passanger.setNic(nic);
                    System.out.println("Passanger updated successfully!");
                    return;
                }
            }
            System.out.println("Passanger not found");
        }

        /*
        DELETE - Delete a passanger using passanger id
         */
        public void deletePassanger(){
            System.out.println("\n======= DELETE PASSENGER===========");
            System.out.println("Enter Passanger ID:");
            int userid = scanner.nextInt();

            //Search through the passanger list.
            for(Passanger passanger : passangers){
                if(passanger.getUserid() == userid){
                    //remove passanger object
                    passangers.remove(passanger);
                    System.out.println("Passenger Deletes Successfully!");
                    return;
                }
            }
            System.out.println("Passenger not found.");
        }
    /*
     * =====================================================
     * ADD PASSENGER FROM GUI
     * =====================================================
     *
     * This method receives passenger details directly from the graphical user interface.
     */

    /*
     * =====================================================
     * ADD PASSENGER FROM GUI
     * =====================================================
     *
     * This method receives passenger information
     * directly from the graphical user interface.
     */
    public boolean addPassenger(
            int passengerId,
            String username,
            String email,
            String telephone,
            String nic) {

        /*
         * Check whether Passenger ID already exists.
         */
        for (Passanger passenger : passangers) {

            if (passenger.getUserid() == passengerId) {

                return false;
            }
        }

        /*
         * Create a new Passenger object.
         */
        Passanger newPassenger =
                new Passanger(
                        passengerId,
                        username,
                        email,
                        telephone,
                        nic
                );

        /*
         * Store passenger in the ArrayList.
         */
        passangers.add(newPassenger);

        return true;
    }
    /*
     * =====================================================
     * SEARCH PASSENGER BY ID
     * =====================================================
     *
     * This method searches for a passenger using
     * the passenger ID.
     *
     * If found:
     *     return the Passenger object.
     *
     * If not found:
     *     return null.
     */
    public Passanger findPassengerById(int passengerId) {

        for (Passanger passenger : passangers) {

            if (passenger.getUserid() == passengerId) {

                return passenger;
            }
        }

        return null;
    }
    /*
     * =====================================================
     * UPDATE PASSENGER
     * =====================================================
     *
     * This method updates passenger information
     * using the Passenger ID.
     *
     * Returns true if passenger is found and updated.
     * Returns false if passenger does not exist.
     */
    public boolean updatePassenger(
            int passengerId,
            String username,
            String email,
            String telephone,
            String nic) {

        /*
         * Search for the passenger.
         */
        Passanger passenger =
                findPassengerById(
                        passengerId
                );

        /*
         * Check whether passenger exists.
         */
        if (passenger == null) {

            return false;
        }

        /*
         * Update passenger information.
         */
        passenger.setUsername(username);
        passenger.setEmail(email);
        passenger.setTele(telephone);
        passenger.setNic(nic);

        return true;
    }
    /*
     * =====================================================
     * DELETE PASSENGER
     * =====================================================
     *
     * This method deletes a passenger using Passenger ID.
     *
     * Returns true if passenger is found and deleted.
     * Returns false if passenger does not exist.
     */
    public boolean deletePassenger(int passengerId) {

        /*
         * Search for passenger.
         */
        Passanger passenger =
                findPassengerById(
                        passengerId
                );

        /*
         * Check whether passenger exists.
         */
        if (passenger == null) {

            return false;
        }

        /*
         * Remove passenger from the ArrayList.
         */
        passangers.remove(passenger);

        return true;
    }
}


