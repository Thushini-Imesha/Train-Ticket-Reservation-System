package model;

/*
 * Admin represents an administrator of the
 * Train Ticket Reservation System.
 *
 * Admin inherits common properties from User.
 *
 * This demonstrates:
 * INHERITANCE
 * ENCAPSULATION
 */

public class Admin extends User {
    // Username used for administrator login.
    private String username;
    // Password used for administrator login.
    private String password;

    /* Constructor for creating an Admin object.
     * super() calls the constructor of the User class.
     */
    public Admin(int userid, String name, String username, String password) {
        // Call the parent User constructor.
        super(userid, username);
        this.username = username;
        this.password = password;
    }
    // Getter and setters.
    public String getUsername() {return username;}
    public String getPassword() {return password;}

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * Checks whether the entered username and password are correct.
     * Returns true if both are correct.
     * Returns false otherwise.
     */
    public boolean login(String enteredUsername, String enteredPassword) {return username.equals(enteredUsername) && password.equals(enteredPassword);}
}