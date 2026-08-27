package model;
/*
    *Passanger inherit from user
    *
    * this demonstrate the INHERITANCE because Passangerextends the user class
    *
 */

public class Passanger extends User {
    //passanger specific attribute
    private String nic;

    //constructor
    public Passanger (int userid,String username,String email,String tele,String nic ){
        //calling the parent class constructor.
        super(userid,username,email,tele);
            this.nic = nic;
    }

    // getter for nic
    public String getNic(){return nic;}
    //setter for nic
    public void setNic(String nic){
        this.nic = nic;
    }
/*
    *Method overriding
    * this is and example for polymorphism
    * the user class has displayrole(),and passanger provides its own implementation
 */
    @Override
    public void displayRole(){
        System.out.println("Role: Passanger");
    }

    //display passanger details
    public void displayPassangerDetails(){
        System.out.println("------Pssanger Details-----");
        System.out.println("ID : " +getUserid());
        System.out.println("Name : " +getUsername());
        System.out.println("Email : " +getEmail());
        System.out.println("Phone Number: " +getTele());
        System.out.println("Nic : " +getNic());
    }
    /*
     * Display passenger name inside JComboBox.
     */
    @Override
    public String toString() {

        return getUsername();
    }
}
