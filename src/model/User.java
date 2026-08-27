package model;
/*
    *User is the parent class.
    *Passanger and admin will inherit common user information from this class.
    *
    *This demonstrates:
    * 1.Encapsulation
    * 2.Inheritence

 */
public class User {
    //Private attributes demonstrates encapsulation.
    private int userid;
    private String username;
    private String email;
    private String tele;

    //Constructor used to create a user object
    public User(int userid,String username,String email,String tele) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.tele = tele;
    }

    public User(int userid, String username) {
    }

    //Getters and setter for userid.
    public int getUserid() {return userid;}
    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public String getTele() {return tele;}

    public void setUserid(int userid){
            this.userid = userid;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setTele(String tele){
        this.tele = tele;
    }

    //this method can be overidden by child classes
    public void displayRole() {
        System.out.println("User");
    }

}
